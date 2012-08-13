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
package com.thruzero.applications.faces.demo.beans.support;

import java.io.Serializable;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.thruzero.common.jsf.utils.FacesUtils;

/**
 * User preferences in Session.
 *
 * @author George Norman
 */
@javax.faces.bean.ManagedBean(name="demoStateBean")
@javax.faces.bean.SessionScoped
public class DemoStateBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private String themeName = ThemeStateBean.DEFAULT_THEME;
  private Locale locale = Locale.US;

  public String getThemeName() {
    return themeName;
  }

  public void setThemeName(String themeName) {
    this.themeName = themeName;
  }

  public Locale getLocale() {
    return locale;
  }

  public void setLocale(Locale locale) {
    this.locale = locale;
  }

  /** TODO-p1(george) HACK for iPhone Home Screen save. */
  public String getSlideDataTransition() {
    String ua = FacesUtils.getRequestHeader("User-Agent");

    if ((StringUtils.containsIgnoreCase(ua, "iPhone") || StringUtils.containsIgnoreCase(ua, "iPad")) && !StringUtils.containsIgnoreCase(ua, "Safari")) {
      return "slidefade";
    } else {
      return "slide";
    }
  }

  /** TODO-p1(george) HACK for iPhone Home Screen save. */
  public String getFlipDataTransition() {
    String ua = FacesUtils.getRequestHeader("User-Agent");

    if ((StringUtils.containsIgnoreCase(ua, "iPhone") || StringUtils.containsIgnoreCase(ua, "iPad")) && !StringUtils.containsIgnoreCase(ua, "Safari")) {
      return "fade";
    } else {
      return "flip";
    }
  }

}
