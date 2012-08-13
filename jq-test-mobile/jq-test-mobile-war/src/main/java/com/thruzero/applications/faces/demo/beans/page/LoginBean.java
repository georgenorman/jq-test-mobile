/*
 *   Copyright 2012 George Norman
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.thruzero.applications.faces.demo.beans.page;

import javax.annotation.PostConstruct;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.thruzero.auth.exception.MessageIdAuthenticationException;
import com.thruzero.auth.service.UserService;
import com.thruzero.auth.utils.AuthenticationUtils;
import com.thruzero.common.core.locator.ProviderLocator;
import com.thruzero.common.core.locator.ServiceLocator;
import com.thruzero.common.core.provider.ResourceProvider;
import com.thruzero.common.jsf.support.beans.UrlBean;
import com.thruzero.common.jsf.support.beans.page.AbstractDemoPageBean;
import com.thruzero.common.jsf.utils.MessageUtils;

/**
 * Page bean for the auth/login.xhtml page. Demonstrates the ability for a user to log in and out of
 * the application (via Shiro).
 *
 * @author George Norman
 */
@javax.faces.bean.ManagedBean(name = "loginBean")
@javax.faces.bean.ViewScoped
public class LoginBean extends AbstractDemoPageBean {
  private static final long serialVersionUID = 1L;

  private static final Class<UserService> SERVICE_CLASS = UserService.class;

  private static UserService userService;

  private String loginId;
  private String password;

  /** The nonce used to form a one-time password. This field is protected from client tampering. */
  private String nonce;

  /** starts out as nonce and then is combined with the user password to become a one-time password, used to login. */
  private String oneTimePw;

  public LoginBean() {
    updateNonce();
  }

  @PostConstruct
  public void initBean() {
    userService = ServiceLocator.locate(SERVICE_CLASS);

    initInfoDialog(userService, new UrlBean("/apps/demo/login.jsf", true));
  }

  public String getLoginId() {
    return loginId;
  }

  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * starts out as nonce and then is combined with the user password to become a one-time password, used to login.
   */
  public String getOneTimePw() {
    return oneTimePw;
  }

  public void setOneTimePw(String oneTimePw) {
    this.oneTimePw = oneTimePw;
  }

  public void reset() {
    loginId = null;
    password = null;
    nonce = null;
    oneTimePw = null;
  }

  public void loginListener(ActionEvent event) throws AbortProcessingException {
    Subject currentSubject = userService.getCurrentSubject();

    if (!currentSubject.isAuthenticated()) {
      UsernamePasswordToken token = new UsernamePasswordToken(loginId, oneTimePw, nonce);

      try {
        // assert that password has been set to "***" (prevent introduction of bugs that might expose user password in production)
        if (StringUtils.isNotEmpty(password) && !StringUtils.containsOnly(password, "*")) {
          // this will be handled below
          throw new Exception("ERROR: Regression - password has not been set to '****'.");
        }

        // attempt to log in
        currentSubject.login(token);
        if (currentSubject.isAuthenticated()) {
          MessageUtils.addInfo("Success!");
          reset();
        } else {
          // this will be handled below
          throw new Exception("ERROR: User not authorized to access this resource.");
        }
      } catch (Exception e) {
        if (e instanceof MessageIdAuthenticationException) {
          ResourceProvider resourceProvider = ProviderLocator.locate(ResourceProvider.class);
          MessageUtils.addError(resourceProvider.getResource(e.getMessage()));
        } else {
          MessageUtils.addError(e);
        }
        updateNonce();
        password = "";
        throw new AbortProcessingException("FAILED to log in.");
      }
      token.clear(); // clear encrypted password
    }
  }

  private void updateNonce() {
    nonce = AuthenticationUtils.createNonce();
    oneTimePw = nonce; // start with salt and combine with password at login
  }

}
