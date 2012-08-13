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
package com.thruzero.applications.faces.demo.beans.dialog;

import com.thruzero.common.jsf.support.beans.BrandBean;
import com.thruzero.common.jsf.support.beans.dialog.AbstractAboutApplicationBean;

/**
 * Demo about box.
 *
 * @author George Norman
 */
@javax.faces.bean.ManagedBean(name = "demoAboutApplicationBean")
@javax.faces.bean.ApplicationScoped
public class DemoAboutApplicationBean extends AbstractAboutApplicationBean {
  private static final long serialVersionUID = 1L;

  @javax.faces.bean.ManagedProperty(value="#{brandBean}")
  private BrandBean brandBean;

  @Override
  public String getAppVersion() {
    return brandBean.getAppVersion();
  }

  // IoC Functions ///////////////////////////////////////////////////////////////////

  public void setBrandBean(BrandBean brandBean) {
    this.brandBean = brandBean;
  }

}
