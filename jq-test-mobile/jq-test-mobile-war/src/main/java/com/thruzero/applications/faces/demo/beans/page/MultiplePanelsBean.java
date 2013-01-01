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

import com.thruzero.common.core.infonode.InfoNodeElement;
import com.thruzero.common.core.locator.ServiceLocator;
import com.thruzero.common.core.support.EntityPath;
import com.thruzero.common.jsf.support.beans.UrlBean;
import com.thruzero.common.jsf.support.beans.page.AbstractDemoPageBean;
import com.thruzero.domain.service.InfoNodeService;

/**
 * Page bean for the multiplePanels.xhtml page - simply displays a list of panels read from the InfoNodeService.
 *
 * @author George Norman
 */
@ManagedBean(name="multiplePanelsBean")
@RequestScoped
public class MultiplePanelsBean extends AbstractDemoPageBean {
  private static final long serialVersionUID = 1L;

  private static final Class<InfoNodeService> SERVICE_CLASS = InfoNodeService.class;

  private InfoNodeElement notes;

  @PostConstruct
  public void initBean() {
    InfoNodeService service = ServiceLocator.locate(SERVICE_CLASS);

    initInfoDialog(service, new UrlBean("/apps/demo/multiplePanels.jsf", true));
  }

  public InfoNodeElement getNotes() {
    ensureNotes();

    return notes;
  }

  private void ensureNotes() {
    if (notes == null) {
      // For the demo app, this is configured for Hibernate or JPA.
      InfoNodeService infoNodeService = ServiceLocator.locate(SERVICE_CLASS); // locate version specified in config file (should be using Hibernate or JPA)
      EntityPath nodePath = new EntityPath("/jq-test-mobile/devRes/", "notes.xml");

      notes = infoNodeService.getInfoNode(nodePath);
    }
  }

}
