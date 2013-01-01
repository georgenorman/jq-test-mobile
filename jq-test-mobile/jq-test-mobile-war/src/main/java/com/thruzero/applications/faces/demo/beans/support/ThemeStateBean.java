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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.thruzero.common.core.config.Config;
import com.thruzero.common.core.locator.ConfigLocator;
import com.thruzero.common.core.map.StringMap;
import com.thruzero.common.core.utils.StringUtilsExt;


/**
 * Session state bean that enables the user to changes the theme at runtime as well as provide a few additional theme elements
 * (e.g., supports separate page header and list header swatches via listViewDividerTheme and pageHeaderDataTheme).
 *
 * @author George Norman
 */
@ManagedBean(name="themeStateBean")
@SessionScoped
public class ThemeStateBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String DEFAULT_THEME = "theme2";

  StringMap model;

  public ThemeStateBean() {
    loadTheme(DEFAULT_THEME);
  }

  public void loadTheme(String name) {
    Config config = ConfigLocator.locate();

    String themeSpec = config.getValue(getClass().getName(), name);
    model = StringUtilsExt.tokensToMap(themeSpec, "|");
  }

  public StringMap getModel() {
    return model;
  }

}
