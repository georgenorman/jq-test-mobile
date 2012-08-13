package com.thruzero.applications.faces.demo.beans.page;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang3.StringUtils;
import org.jdom.JDOMException;

import com.thruzero.common.core.infonode.InfoNodeElement;
import com.thruzero.common.core.locator.ServiceLocator;
import com.thruzero.common.core.support.EntityPath;
import com.thruzero.common.jsf.components.html5.TzInputTextarea;
import com.thruzero.common.jsf.support.beans.UrlBean;
import com.thruzero.common.jsf.support.beans.page.AbstractDemoPageBean;
import com.thruzero.domain.service.InfoNodeService;

/**
 * Page bean for the ajax.xhtml page. Tests partial-page rendering using the JSF &lt;f:ajax&gt; tag. Below are examples:
 * <ul>
 *  <li>&lt;f:ajax&gt; tag is used to display the remaining character count for a &lt;tzh:inputTextarea&gt;. This is not a good implementation of
 *      a remaining character counter, but it's a reasonable test of partial page rendering involving a call-back to the server.</li>
 * </ul>
 *
 * @author George Norman
 */
@javax.faces.bean.ManagedBean(name="ajaxBean")
@javax.faces.bean.RequestScoped
public class AjaxBean extends AbstractDemoPageBean {
  private static final long serialVersionUID = 1L;

  private static final Class<InfoNodeService> SERVICE_CLASS = InfoNodeService.class;

  private FormTestModel formTestModel = new FormTestModel();

  private TzInputTextarea detailsInputTextarea;

  @javax.faces.bean.ManagedProperty(value="#{ajaxStateBean}")
  private AjaxStateBean ajaxStateBean;

  // ----------------------------------------------------
  // FormTestModel
  // ----------------------------------------------------

  /**
   * Model for the form tests. Can live in any scope (e.g., flash).
   */
  public class FormTestModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String details;

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getDetails() {
      return details;
    }

    public void setDetails(String details) {
      this.details = details;
    }
  }

  // ----------------------------------------------------
  // AjaxStateBean
  // ----------------------------------------------------

  @javax.faces.bean.ManagedBean(name="ajaxStateBean")
  @javax.faces.bean.SessionScoped
  public static class AjaxStateBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int sequentialTextIndex;
    private int selectedMovieId = 1;

    public int getNextIndex() {
      if (sequentialTextIndex++ >= 3) {
        sequentialTextIndex = 0;
      }

      return sequentialTextIndex;
    }

    public int getSelectedMovieId() {
      return selectedMovieId;
    }

    public void setSelectedMovieId(int selectedMovieId) {
      this.selectedMovieId = selectedMovieId;
    }
  }

  // =====================================================================
  // AjaxBean
  // =====================================================================

  @PostConstruct
  public void initBean() {
    InfoNodeService service = ServiceLocator.locate(SERVICE_CLASS);

    initInfoDialog(service, new UrlBean("/apps/demo/ajax.jsf", true));
  }

  public FormTestModel getFormTestModel() {
    return formTestModel;
  }

  public int getNumDetailCharsAvailable() {
    int maxLength = detailsInputTextarea.getMaxLength();

    return maxLength - StringUtils.length((String)detailsInputTextarea.getValue());
  }

  public String getSequentialText() {
    String result;

    int index = ajaxStateBean.getNextIndex();
    switch(index) {
      case 0:
        result = "Test 0";
        break;
      case 1:
        result = "Test 1";
        break;
      case 2:
        result = "Test 2";
        break;
      default:
        result = "Undefined for index: " + index;
    }

    return result;
  }

  public String getSelectedMovie() throws JDOMException {
    String result;
    int id = ajaxStateBean.getSelectedMovieId();

    InfoNodeService infoNodeService = ServiceLocator.locate(SERVICE_CLASS); // locate version specified in config file (should be using Hibernate or JPA)
    EntityPath nodePath = new EntityPath("/jcat/devRes/", "movies.xml");
    InfoNodeElement movies = infoNodeService.getInfoNode(nodePath);
    movies.enableRootNode();
    InfoNodeElement child = (InfoNodeElement)movies.find("/movies/movie[@id='" + id + "']");

    if (child == null) {
      result = "Movie with ID '" + id + "' was not found.";
    } else {
      result = child.getValueTransformer().getAsRichText();
    }

    return result;
  }

  public void showSelectedMovieListener(AjaxBehaviorEvent event) {
    String id = event.getComponent().getId();
    int selectedTextIndex = Integer.parseInt(StringUtils.substring(id, 2));
    ajaxStateBean.setSelectedMovieId(selectedTextIndex);
  }

  // IoC functions ///////////////////////////////////////////////////////

  public TzInputTextarea getDetailsInputTextarea() {
    return detailsInputTextarea;
  }

  public void setDetailsInputTextarea(TzInputTextarea detailsInputTextarea) {
    this.detailsInputTextarea = detailsInputTextarea;
  }

  public AjaxStateBean getAjaxStateBean() {
    return ajaxStateBean;
  }

  public void setAjaxStateBean(AjaxStateBean ajaxStateBean) {
    this.ajaxStateBean = ajaxStateBean;
  }

}
