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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringEscapeUtils;

import com.thruzero.common.jsf.support.beans.UrlBean;
import com.thruzero.common.jsf.support.beans.page.AbstractDemoPageBean;
import com.thruzero.common.jsf.utils.FlashUtils;

/**
 * Page bean for the escapeTool.xhtml page. This is a simple test that reads user input, processes it and then displays the
 * result on a single page.
 *
 * @author George Norman
 */
@ManagedBean(name = "escapeToolBean")
@RequestScoped
public class EscapeToolBean extends AbstractDemoPageBean {
  private static final long serialVersionUID = 1L;


  private static final SelectItem[] ESCAPE_TYPE_SELECT_ITEMS = new SelectItem[]{
    new SelectItem("xml","XML"),
    new SelectItem("html4", "HTML4"),
    new SelectItem("java", "Java")
  };

  private String escapeType;

  private String inputText;
  private String escapedText;

  @PostConstruct
  public void initBean() {
    initInfoDialog(new UrlBean("/apps/demo/escapeTool.jsf", true)); // TODO-p1(george) Back URL should come from jsf page

    escapedText = (String)FlashUtils.removeFlashAttribute();
  }

  public String getEscapeType() {
    return escapeType;
  }

  public void setEscapeType(String escapeType) {
    this.escapeType = escapeType;
  }

  public SelectItem[] getEscapeTypeSelectItems() {
    return ESCAPE_TYPE_SELECT_ITEMS;
  }

  public String getInputText() {
    return inputText;
  }

  public void setInputText(String inputText) {
    this.inputText = inputText;
  }

  public String getEscapedText() {
    return escapedText;
  }

  public void setEscapedText(String escapedText) {
    this.escapedText = escapedText;
  }

  public String escapeTextAction() {
    if ("xml".equals(escapeType)) {
      escapedText = StringEscapeUtils.escapeXml(inputText);
    } else if ("html4".equals(escapeType)) {
      escapedText = StringEscapeUtils.escapeHtml4(inputText);
    } else if ("java".equals(escapeType)) {
      escapedText = StringEscapeUtils.escapeJava(inputText);
    } else {
      throw new RuntimeException("Unknown Escape selection");
    }

    String flashHackKey = FlashUtils.saveFlashAttribute(escapedText);

    return "/apps/demo/escapeTool.jsf?faces-redirect=true&fhk=" + flashHackKey;
  }

  public void resetFormListener(ActionEvent event) {
    inputText = null;
    escapedText = null;
  }

}
