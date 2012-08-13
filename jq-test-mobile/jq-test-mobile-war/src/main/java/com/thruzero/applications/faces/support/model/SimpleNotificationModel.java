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
package com.thruzero.applications.faces.support.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * A simple notification model usable by any notification panel, to display a simple message.
 *
 * @author George Norman
 */
public class SimpleNotificationModel implements Serializable {
  private static final long serialVersionUID = 1L;

  private boolean rendered;
  private String message;

  public SimpleNotificationModel() {
    this(null);
  }

  public SimpleNotificationModel(String message) {
    this(StringUtils.isNotEmpty(message), message);
  }

  public SimpleNotificationModel(boolean rendered, String message) {
    this.rendered = rendered;
    this.message = message;
  }

  public boolean getRendered() {
    return rendered;
  }

  public String getMessage() {
    return message;
  }
}
