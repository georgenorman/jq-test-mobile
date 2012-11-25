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
import com.thruzero.domain.dsc.service.DscInfoNodeService;

/**
 * Page bean for the dscPanels.xhtml page - Uses the DscInfoNodeService to read and display data from the file system (or a web service).
 *
 * @author George Norman
 */
@javax.faces.bean.ManagedBean(name="dscPanelsBean")
@javax.faces.bean.RequestScoped
public class DscPanelsBean extends AbstractDemoPageBean {
  private static final long serialVersionUID = 1L;

  private InfoNodeElement movies;

  @PostConstruct
  public void initBean() {
    DscInfoNodeService dscInfoNodeService = ServiceLocator.locate(DscInfoNodeService.class); // locate Data Store version

    initInfoDialog(dscInfoNodeService, new UrlBean("/apps/demo/dscPanels.jsf", true)); // TODO-p1(george) Back URL should come from jsf page
  }

  public InfoNodeElement getMovies() {
    ensureMovies();

    return movies;
  }

  private void ensureMovies() {
    if (movies == null) {
      DscInfoNodeService dscInfoNodeService = ServiceLocator.locate(DscInfoNodeService.class); // locate Data Store version
      EntityPath nodePath = new EntityPath("/jcat/devRes/", "movies.xml");
      movies = dscInfoNodeService.getInfoNode(nodePath);

      DscUtils.assertValidInfoNode(movies, "movies", dscInfoNodeService, nodePath);
    }
  }
}
