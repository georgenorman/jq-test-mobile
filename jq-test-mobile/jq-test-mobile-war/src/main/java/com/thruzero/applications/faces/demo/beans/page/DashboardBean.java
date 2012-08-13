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
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jdom.Attribute;
import org.jdom.JDOMException;

import com.thruzero.applications.faces.demo.utils.DscUtils;
import com.thruzero.common.core.infonode.InfoNodeElement;
import com.thruzero.common.core.locator.ServiceLocator;
import com.thruzero.common.core.support.EntityPath;
import com.thruzero.common.jsf.utils.FacesUtils;
import com.thruzero.domain.dsc.service.DscInfoNodeService;

/**
 * Page bean for the dashboard.xhtml page (main page).
 *
 * @author George Norman
 */
@javax.faces.bean.ManagedBean(name="dashboardBean")
@javax.faces.bean.RequestScoped
public class DashboardBean implements Serializable  {
  private static final long serialVersionUID = 1L;

  private List<InfoNodeElement> dashboardPanels;

  public static class DashboardPanel {

  }

  public List<InfoNodeElement> getDashboard() {
    if (dashboardPanels == null) {
      DscInfoNodeService dscInfoNodeService = ServiceLocator.locate(DscInfoNodeService.class); // locate Data Store version
      EntityPath nodePath = new EntityPath("dashboard.xml");
      InfoNodeElement dashboard = dscInfoNodeService.getInfoNode(nodePath);

      // print some diagnostic info if DscInfoNodeService is used
      DscUtils.assertValidInfoNode(dashboard, "dashboard", dscInfoNodeService, nodePath);

      try {
        dashboard.enableRootNode();
        @SuppressWarnings("unchecked")
        List<InfoNodeElement> allPanels = (List<InfoNodeElement>)dashboard.findAll("//panel");
        dashboardPanels = allPanels;

        // make adjustments for the mobile implementation
        @SuppressWarnings("unchecked")
        List<InfoNodeElement> allLinks = (List<InfoNodeElement>)dashboard.findAll("//link");
        for (InfoNodeElement link : allLinks) {
          // add context to links, if needed
          Attribute href = link.getAttribute("href");
          if (StringUtils.startsWith(href.getValue(), "/")) {
            href.setValue("/"+FacesUtils.getServletContextName()+href.getValue());
          }
        }
      } catch (JDOMException e) {
        e.printStackTrace();
      }
    }

    return dashboardPanels;
  }

}
