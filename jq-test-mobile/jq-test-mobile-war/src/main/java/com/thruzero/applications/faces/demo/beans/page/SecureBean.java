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

import java.io.Serializable;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.thruzero.auth.model.User;
import com.thruzero.auth.model.UserPermission;

/**
 * Page bean for the secure1.xhtml and secure2.xhtml pages - secure1.xhtml requires only a valid login, whereas secure2.xhtml
 * requires login plus authorization to access certain features.
 *
 * @author George Norman
 */
@javax.faces.bean.ManagedBean(name="secureBean")
@javax.faces.bean.RequestScoped
public class SecureBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private EditorHelper editorHelper = new EditorHelper();

  // ----------------------------------------------
  // EditorHelper
  // ----------------------------------------------

  public static class EditorHelper {
    private String text1;

    public String getText1() {
      return text1;
    }

    public void setText1(String text1) {
      this.text1 = text1;
    }

    public boolean isEditable() {
      Subject currentUser = SecurityUtils.getSubject();

      return currentUser.isPermittedAll("demoSecure2:edit");
    }
  }

  // ===========================================================================
  // SecureBean
  // ===========================================================================

  public String getSecurityActions() {
    Subject subject = SecurityUtils.getSubject();
    User currentUser = (User)subject.getPrincipal();
    Set<UserPermission> permissions = currentUser.getPermissions();

    return permissions.iterator().next().getActions();
  }

  public EditorHelper getEditorHelper() {
    return editorHelper;
  }

}
