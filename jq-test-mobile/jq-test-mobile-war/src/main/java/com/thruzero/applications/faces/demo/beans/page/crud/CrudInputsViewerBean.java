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
package com.thruzero.applications.faces.demo.beans.page.crud;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

import com.thruzero.applications.faces.demo.beans.page.CrudListBean.BackButtonCallback;
import com.thruzero.applications.faces.demo.beans.page.CrudListBean.InputsRow;
import com.thruzero.applications.faces.demo.beans.page.CrudListBean.InputsStateBean;
import com.thruzero.common.core.locator.ServiceLocator;
import com.thruzero.common.jsf.support.beans.UrlBean;
import com.thruzero.common.jsf.support.beans.page.AbstractDemoPageBean;
import com.thruzero.common.jsf.utils.FacesUtils;
import com.thruzero.domain.demo.model.InputsModel;
import com.thruzero.domain.demo.service.InputsService;

/**
 * Page bean for the CRUD Inputs viewer.
 *
 * @author George Norman
 */
@javax.faces.bean.ManagedBean(name="crudInputsViewerBean")
@javax.faces.bean.RequestScoped
public class CrudInputsViewerBean extends AbstractDemoPageBean {
  private static final long serialVersionUID = 1L;

  private static final Class<InputsService> SERVICE_CLASS = InputsService.class;

  /** Request parameter key used to identify the record to read */
  private static final String ID_REQUEST_PARAMETER_KEY = "id";

  private InputsModel inputsModel;

  /** index of the inputs in the InputsStateBean to display (defined by request parameter specified by ID_REQUEST_PARAMETER_KEY). */
  private String index;

  private BackButtonCallback backButtonCallback;

  private SaveOrUpdateActionHandler saveOrUpdateActionHandler = new SaveOrUpdateActionHandler();

  private DeleteActionHandler deleteActionHandler = new DeleteActionHandler();

  @javax.faces.bean.ManagedProperty(value="#{inputsStateBean}")
  private InputsStateBean inputsStateBean;

  // ------------------------------------------------------
  // AbstractActionHandler
  // ------------------------------------------------------

  /** Abstract base class for the CRUD actions (defines the doHandleAction abstract method). */
  public abstract class AbstractActionHandler {
    public String handleAction() {
      // update the model's ID (primary key)
      refreshModelId();

      // do the action
      String result = doHandleAction(ServiceLocator.locate(SERVICE_CLASS));

      // reset the state bean (so the list will update)
      inputsStateBean.resetAll();

      return result;
    }

    protected String getSuccessOutcome() {
      return new UrlBean(doGetSuccessOutcome(), true).getUrl();
    }

    protected void refreshModelId() {
      // update the model's ID (primary key)
      if (StringUtils.isNotEmpty(index)) {
        int intIndex = Integer.parseInt(index);
        InputsRow inputsRow = inputsStateBean.getInputRows().get(intIndex);
        getInputsModel().setId(inputsRow.getPrimaryKey());
      }
    }

    protected abstract String doHandleAction(InputsService inputsService);
  }

  // ------------------------------------------------------
  // SaveOrUpdateActionHandler
  // ------------------------------------------------------

  /**
   * Saves or updates a given InputsModel to a persistent store. The store is defined by InputsDAO,
   * which can be configured for the HTTP session (HttpSessionInputsDAO) or a database via JPA.
   */
  public class SaveOrUpdateActionHandler extends AbstractActionHandler {
    @Override
    protected String doHandleAction(InputsService inputsService) {
      // for DEMO, if maximum record count is reached, remove all saved inputs and start over
      Collection<? extends InputsModel> inputs = inputsService.getInputs();

      if (inputs.size() > 10) {
        for (InputsModel inputsModel : inputs) {
          inputsService.deleteInputsModel(inputsModel);
        }
      }

      // now save inputs
      inputsService.saveInputs(inputsModel);

      // TODO-p1(george) Success outcome; should also add a fail outcome (e.g., required word missing).
      return getSuccessOutcome();
    }
  }

  // ------------------------------------------------------
  // DeleteActionHandler
  // ------------------------------------------------------

  /** Deletes an InputsModel from the store. */
  public class DeleteActionHandler extends AbstractActionHandler {
    @Override
    protected String doHandleAction(InputsService inputsService) {
      inputsService.deleteInputsModel(inputsModel);

      return getSuccessOutcome();
    }
  }

  // ============================================================================
  // InputsViewerBean
  // ============================================================================

  @PostConstruct
  public void initBean() {
    backButtonCallback = new BackButtonCallback(inputsStateBean);

    InputsService inputsService = ServiceLocator.locate(SERVICE_CLASS);

    initInfoDialog(inputsService, new UrlBean(FacesUtils.getRequest().getServletPath(), true));
  }

  public BackButtonCallback getBackButtonCallback() {
    return backButtonCallback;
  }

  public InputsModel getInputsModel() {
    if (inputsModel == null) {
      index = FacesUtils.getRequest().getParameter(ID_REQUEST_PARAMETER_KEY);

      if (StringUtils.isEmpty(index)) {
        inputsModel = new InputsModel();
      } else {
        InputsService inputsService = ServiceLocator.locate(SERVICE_CLASS);
        int intIndex = Integer.parseInt(index);
        InputsRow inputsRow = inputsStateBean.getInputRows().get(intIndex);
        inputsModel = inputsService.getByKey(inputsRow.getPrimaryKey());
      }
    }

    return inputsModel;
  }

  public String getIndex() {
    return index;
  }

  public void setIndex(String index) {
    this.index = index;
  }

  protected String doGetSuccessOutcome() {
    return "/apps/demo/crudList.jsf";
  }

  // Action functions/handlers ////////////////////////////////////////////////////

  public String saveAction() {
    return saveOrUpdateActionHandler.handleAction();
  }

  public String updateAction() {
    return saveOrUpdateActionHandler.handleAction();
  }

  public String deleteAction() {
    return deleteActionHandler.handleAction();
  }

  // IoC functions /////////////////////////////////////////////////////////////////

  public InputsStateBean getInputsStateBean() {
    return inputsStateBean;
  }

  public void setInputsStateBean(InputsStateBean inputsStateBean) {
    this.inputsStateBean = inputsStateBean;
  }
}
