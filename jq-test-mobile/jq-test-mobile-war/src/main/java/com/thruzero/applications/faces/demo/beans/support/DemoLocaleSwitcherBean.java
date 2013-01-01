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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 * Simple test bean used to switch between two static locales (en_us and es) and is a VERY rough
 * start on i18n support.
 *
 * @author George Norman
 */
@ManagedBean(name="demoLocaleSwitcherBean")
@RequestScoped
public class DemoLocaleSwitcherBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Map<String, Locale> LOCALE_MAP = new HashMap<String, Locale>();
  static {
    LOCALE_MAP.put("en_US", Locale.US);
    LOCALE_MAP.put("es", new Locale("es"));
  };

  private static final SelectItem[] LOCALE_SELECT_ITEMS = new SelectItem[] {
    new SelectItem("en_US", Locale.US.getDisplayName()),
    new SelectItem("es", "M\u00E9xico - Espa\u00F1ol"),
  };

  private String localeLanguage;

  public SelectItem[] getLocaleSelectItems() {
    return LOCALE_SELECT_ITEMS;
  }

  public Locale getLocale() {
    return LOCALE_MAP.get(localeLanguage);
  }

  public String getLocaleLanguage() {
    return localeLanguage;
  }

  public void setLocaleLanguage(String language) {
    this.localeLanguage = language;
  }

}
