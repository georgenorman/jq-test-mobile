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

import com.thruzero.applications.faces.demo.utils.DscUtils;
import com.thruzero.common.core.infonode.InfoNodeElement;
import com.thruzero.common.core.locator.ServiceLocator;
import com.thruzero.common.core.support.EntityPath;
import com.thruzero.common.jsf.support.beans.UrlBean;
import com.thruzero.common.jsf.support.beans.page.AbstractDemoPageBean;
import com.thruzero.domain.service.InfoNodeService;

/**
 * Page bean for the simplePanel.xhtml page.
 *
 * @author George Norman
 */
@javax.faces.bean.ManagedBean(name="simplePanelBean")
@javax.faces.bean.RequestScoped
public class SimplePanelBean extends AbstractDemoPageBean {
  private static final long serialVersionUID = 1L;

  private static final Class<InfoNodeService> SERVICE_CLASS = InfoNodeService.class;

  private DynamicPanelHelper dynamicPanelHelper = new DynamicPanelHelper();

  // ----------------------------------------------------
  // DynamicPanelHelper
  // ----------------------------------------------------

  public static class DynamicPanelHelper {
    private InfoNodeElement panelNode;

    public InfoNodeElement getPanelNode() {
      if ( panelNode == null ) {
        InfoNodeService infoNodeService = ServiceLocator.locate(SERVICE_CLASS);
        EntityPath nodePath = new EntityPath("/jcat/devRes/", "basic-panels.xml");
        InfoNodeElement rootNode = infoNodeService.getInfoNode(nodePath);

        // print some diagnostic info if DscInfoNodeService is used
        DscUtils.assertValidInfoNode(rootNode, "simple-panel", infoNodeService, nodePath);

        panelNode = (InfoNodeElement)rootNode.getChild("dynamicPanel");
      }

      return panelNode;
    }
  }

  // ----------------------------------------------------
  // NotificationPanelHelper
  // ----------------------------------------------------

  public static class NotificationPanelHelper {
    private String message;

    public NotificationPanelHelper(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }
  }

  // =====================================================================
  // BasicPanelsBean
  // =====================================================================

  @PostConstruct
  public void initBean() {
    InfoNodeService infoNodeService = ServiceLocator.locate(SERVICE_CLASS);

    initInfoDialog(infoNodeService, new UrlBean("/apps/demo/simplePanel.jsf", true)); // TODO-p1(george) Back URL should come from jsf page
  }

  public DynamicPanelHelper getDynamicPanelHelper() {
    return dynamicPanelHelper;
  }
}
