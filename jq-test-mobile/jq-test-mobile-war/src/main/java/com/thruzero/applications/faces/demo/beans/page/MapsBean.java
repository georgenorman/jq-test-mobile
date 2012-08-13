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

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.thruzero.applications.faces.demo.utils.DscUtils;
import com.thruzero.common.core.infonode.InfoNodeElement;
import com.thruzero.common.core.locator.ServiceLocator;
import com.thruzero.common.core.support.EntityPath;
import com.thruzero.common.jsf.support.beans.UrlBean;
import com.thruzero.common.jsf.support.beans.page.AbstractDemoPageBean;
import com.thruzero.domain.service.InfoNodeService;

/**
 * Page bean for the maps.xhtml page - NOT Implemented in the UI.
 *
 * @author George Norman
 */
@javax.faces.bean.ManagedBean(name="mapsBean")
@javax.faces.bean.RequestScoped
public class MapsBean extends AbstractDemoPageBean {
  private static final long serialVersionUID = 1L;

  private static final Class<InfoNodeService> SERVICE_CLASS = InfoNodeService.class;

  private String selectedCoordinateName;
  private Map<String,InfoNodeElement> coordinates;

  @PostConstruct
  public void initBean() {
    InfoNodeService infoNodeService = ServiceLocator.locate(SERVICE_CLASS);

    initInfoDialog(infoNodeService, new UrlBean("/apps/demo/maps.jsf", true)); // TODO(george):p1  back URL should come from jsf page
  }

  public String getSelectedCoordinateName() {
    return selectedCoordinateName;
  }

  public void setSelectedCoordinateName(String selectedCoordinateName) {
    this.selectedCoordinateName = selectedCoordinateName;
  }

  public String getSelectedCoordinate() {
    if (selectedCoordinateName == null) {
      selectedCoordinateName = getCoordinates().iterator().next().getText();
    }

    InfoNodeElement selectedCooridiante = coordinates.get(selectedCoordinateName);
    return selectedCooridiante.getAttributeValue("latitude") + ", " + selectedCooridiante.getAttributeValue("longitude");
  }

  public String getZoom() {
    if (selectedCoordinateName == null) {
      selectedCoordinateName = getCoordinates().iterator().next().getText();
    }

    InfoNodeElement selectedCooridiante = coordinates.get(selectedCoordinateName);
    return selectedCooridiante.getAttributeValue("zoom");
  }

  public Collection<InfoNodeElement> getCoordinates() {
    if (coordinates == null) {
      InfoNodeService dscInfoNodeService = ServiceLocator.locate(SERVICE_CLASS); // locate Data Store version
      EntityPath nodePath = new EntityPath("maps.xml");
      InfoNodeElement rootNode = dscInfoNodeService.getInfoNode(nodePath);

      DscUtils.assertValidInfoNode(rootNode, "maps", dscInfoNodeService, nodePath);

      coordinates = new LinkedHashMap<String,InfoNodeElement>();

      for (InfoNodeElement member : (List<InfoNodeElement>)rootNode.getChildren()) {
        coordinates.put(member.getText(), member);
      }
    }

    return coordinates.values();
  }
}
