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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.jdom.JDOMException;

import com.thruzero.applications.faces.demo.utils.DscUtils;
import com.thruzero.common.core.infonode.InfoNodeElement;
import com.thruzero.common.core.locator.ServiceLocator;
import com.thruzero.common.core.support.EntityPath;
import com.thruzero.common.core.utils.MapUtilsExt;
import com.thruzero.common.jsf.support.beans.UrlBean;
import com.thruzero.common.jsf.support.beans.page.AbstractDemoPageBean;
import com.thruzero.domain.service.InfoNodeService;

/**
 * Page bean for the galleria.xhtml page - NOT Implemented in the UI.
 *
 * @author George Norman
 */
@ManagedBean(name="imageGalleryBean")
@RequestScoped
public class ImageGalleryBean extends AbstractDemoPageBean {
  private static final long serialVersionUID = 1L;

  private static final Class<InfoNodeService> SERVICE_CLASS = InfoNodeService.class;

  private GalleriaHelper galleriaHelper = new GalleriaHelper();

  // ----------------------------------------------------
  // GalleriaHelper
  // ----------------------------------------------------

  public static class GalleriaHelper {
    private List<String> images = new ArrayList<String>();

    public GalleriaHelper() {
      InfoNodeService dscInfoNodeService = ServiceLocator.locate(SERVICE_CLASS); // locate Data Store version
      EntityPath nodePath = new EntityPath("galleries.xml");
      InfoNodeElement rootNode = dscInfoNodeService.getInfoNode(nodePath);

      DscUtils.assertValidInfoNode(rootNode, "galleries", dscInfoNodeService, nodePath);

      try {
        rootNode.enableRootNode();
        InfoNodeElement gallery1 = (InfoNodeElement)rootNode.find("/galleries/gallery[@id='1']");

        images = Arrays.asList(MapUtilsExt.getValueAsStringSeries(gallery1.getChildText("series")));
      } catch (JDOMException e) {
        throw new RuntimeException("Exception while attempting to load gallery.", e);
      }
    }

    public List<String> getImages() {
      return images;
    }
  }

  // =====================================================================
  // ImagesBean
  // =====================================================================

  @PostConstruct
  public void initBean() {
    initInfoDialog(new UrlBean("/apps/demo/galleria.jsf", true));
  }

  public GalleriaHelper getGalleriaHelper() {
    return galleriaHelper;
  }

}
