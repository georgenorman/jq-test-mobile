package com.thruzero.applications.faces.demo.beans.page.buttons;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang3.StringUtils;

import com.thruzero.applications.faces.demo.utils.DscUtils;
import com.thruzero.common.core.infonode.InfoNodeElement;
import com.thruzero.common.core.infonode.builder.TokenStreamInfoNodeBuilder;
import com.thruzero.common.core.locator.ServiceLocator;
import com.thruzero.common.core.support.EntityPath;
import com.thruzero.common.jsf.support.beans.page.AbstractDemoPageBean;
import com.thruzero.common.jsf.utils.FacesUtils;
import com.thruzero.domain.dsc.service.DscInfoNodeService;

/**
 * Page bean for navigationButtons.xhtml. Provides the named data segment, specified by the "ds" request parameter, to
 * the navigationButtons.xhtml page (content section).
 *
 * @author George Norman
 */
@ManagedBean(name="navigationTestBean")
@RequestScoped
public class NavigationTestBean extends AbstractDemoPageBean {
  private static final long serialVersionUID = 1L;

  private static final Class<DscInfoNodeService> SERVICE_CLASS = DscInfoNodeService.class;

  /** Request parameter key used to identify the data segment to read */
  private static final String DATA_SEGMENT_REQUEST_PARAMETER_KEY = "ds";

  private InfoNodeElement dataSegment;

  public String getHeaderText() {
    String result = "Test-";
    String ds = FacesUtils.getRequest().getParameter(DATA_SEGMENT_REQUEST_PARAMETER_KEY);

    if (StringUtils.isEmpty(ds)) {
      result += "?";
    } else {
      result += ds;
    }

    return result;
  }

  public InfoNodeElement getDataSegment() {
    ensureDataSegment();

    return dataSegment;
  }

  private void ensureDataSegment() {
    if (dataSegment == null) {
      DscInfoNodeService dscInfoNodeService = ServiceLocator.locate(SERVICE_CLASS); // locate Data Store version
      EntityPath nodePath = new EntityPath("/jq-test-mobile/devRes/", "notes.xml");
      InfoNodeElement notes = dscInfoNodeService.getInfoNode(nodePath);

      DscUtils.assertValidInfoNode(notes, "notes", dscInfoNodeService, nodePath);

      String ds = FacesUtils.getRequest().getParameter(DATA_SEGMENT_REQUEST_PARAMETER_KEY);

      if (StringUtils.isNotEmpty(ds)) {
        try {
          notes.enableRootNode();
          dataSegment = (InfoNodeElement)notes.find("//note[@id='"+ds+"']");
        } catch (Exception e) {
          // ignore
        }
      }

      if (dataSegment == null) {
        TokenStreamInfoNodeBuilder builder = TokenStreamInfoNodeBuilder.WITH_ROOT_NODE;
        dataSegment = builder.buildInfoNode("note[@id='err', @title='ERROR', @image='/images/common/icons/error.png']=invalid ds ID.");
      }
    }
  }

}
