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
package com.thruzero.applications.faces.support.beans;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

import com.thruzero.applications.faces.demo.beans.support.DemoLocaleSwitcherBean;
import com.thruzero.applications.faces.demo.beans.support.DemoStateBean;
import com.thruzero.applications.faces.demo.beans.support.ThemeStateBean;
import com.thruzero.applications.faces.support.model.SimpleNotificationModel;
import com.thruzero.common.core.config.Config;
import com.thruzero.common.core.locator.ConfigLocator;
import com.thruzero.common.core.map.StringMap;
import com.thruzero.common.core.utils.StringUtilsExt;

/**
 * Page bean for the Settings page.
 *
 * @author George Norman
 */
@javax.faces.bean.ManagedBean(name="settingsBean")
@javax.faces.bean.RequestScoped
public class SettingsBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private String themeName;

  private SimpleNotificationModel simpleNotificationModel;

  @javax.faces.bean.ManagedProperty(value="#{demoStateBean}")
  private DemoStateBean demoStateBean;

  @javax.faces.bean.ManagedProperty(value="#{themeStateBean}")
  private ThemeStateBean themeStateBean;

  @javax.faces.bean.ManagedProperty(value="#{demoLocaleSwitcherBean}")
  private DemoLocaleSwitcherBean demoLocaleSwitcherBean;

  @PostConstruct
  public void init() {
    themeName = demoStateBean.getThemeName();
    demoLocaleSwitcherBean.setLocaleLanguage(demoStateBean.getLocale().getLanguage());
  }

  public String getThemeName() {
    return themeName;
  }

  public void setThemeName(String themeName) {
    this.themeName = themeName;
  }

  public SelectItem[] getThemeSelectItems() {
    Config config = ConfigLocator.locate();
    Map<String,String> themes = config.getSection(ThemeStateBean.class.getName());
    SelectItem[] result = new SelectItem[themes.size()];
    int i = 0;

    for (Entry<String, String> entry : themes.entrySet()) {
      StringMap themeMap = StringUtilsExt.tokensToMap(entry.getValue(), "|");
      result[i++] = new SelectItem(entry.getKey(), themeMap.get("themeTitle"));
    }

    return result;
  }

  public String saveAction() {
    demoStateBean.setThemeName(themeName);
    themeStateBean.loadTheme(themeName);

    demoStateBean.setLocale(demoLocaleSwitcherBean.getLocale());

    return "/apps/demo/dashboard.jsf?faces-redirect=true";
  }

  public SimpleNotificationModel getNotificationModel() {
    return simpleNotificationModel;
  }

  // IoC functions ///////////////////////////////////////////////////////////////

  public DemoStateBean getDemoStateBean() {
    return demoStateBean;
  }

  public void setDemoStateBean(DemoStateBean demoStateBean) {
    this.demoStateBean = demoStateBean;
  }

  public ThemeStateBean getThemeStateBean() {
    return themeStateBean;
  }

  public void setThemeStateBean(ThemeStateBean themeStateBean) {
    this.themeStateBean = themeStateBean;
  }

  public DemoLocaleSwitcherBean getDemoLocaleSwitcherBean() {
    return demoLocaleSwitcherBean;
  }

  public void setDemoLocaleSwitcherBean(DemoLocaleSwitcherBean demoLocaleSwitcherBean) {
    this.demoLocaleSwitcherBean = demoLocaleSwitcherBean;
  }

}
