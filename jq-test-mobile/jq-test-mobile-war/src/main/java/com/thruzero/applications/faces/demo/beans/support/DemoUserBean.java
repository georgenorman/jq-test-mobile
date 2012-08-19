/*
 *   Copyright 2009-2012 George Norman
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
package com.thruzero.applications.faces.demo.beans.support;

import javax.faces.event.ActionEvent;

import org.apache.shiro.subject.Subject;

import com.thruzero.auth.beans.UserBean;
import com.thruzero.auth.service.UserService;
import com.thruzero.common.core.locator.ServiceLocator;

/**
 * Session-scoped managed bean representing the current user.
 *
 * @author George Norman
 */
@javax.faces.bean.ManagedBean(name="demoUserBean")
@javax.faces.bean.SessionScoped
public class DemoUserBean extends UserBean {
  private static final long serialVersionUID = 1L;

  public void logoutListener(ActionEvent e) {
    Subject currentSubject = ServiceLocator.locate(UserService.class).getCurrentSubject();

    currentSubject.logout();
  }
}