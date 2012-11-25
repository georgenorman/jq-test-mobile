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
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang3.StringUtils;

import com.thruzero.common.core.locator.ServiceLocator;
import com.thruzero.common.jsf.support.ActionListenerCallback;
import com.thruzero.common.jsf.support.beans.page.AbstractDemoPageBean;
import com.thruzero.domain.demo.model.InputsModel;
import com.thruzero.domain.demo.service.InputsService;

/**
 * Page bean for the crudList.xhtml page. Displays and filters (via JQuery) a list of persisted data rows.
 *
 * @author George Norman
 */
@javax.faces.bean.ManagedBean(name="crudListBean")
@javax.faces.bean.RequestScoped
public class CrudListBean extends AbstractDemoPageBean {
  private static final long serialVersionUID = 1L;

  protected static final Class<InputsService> SERVICE_CLASS = InputsService.class;

  private BackButtonCallback backButtonCallback;

  @javax.faces.bean.ManagedProperty(value="#{inputsStateBean}")
  private InputsStateBean inputsStateBean;

  // ------------------------------------------------------
  // InputsStateBean
  // ------------------------------------------------------

  @javax.faces.bean.ManagedBean(name="inputsStateBean")
  @javax.faces.bean.SessionScoped
  public static class InputsStateBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<InputsRow> inputRows = new ArrayList<InputsRow>();
    private String backUrl;
    private boolean readOnly;

    public boolean isInitialized() {
      return StringUtils.isNotEmpty(backUrl);
    }

    public void init(List<InputsModel> inputsModels, String backUrl, boolean readOnly) {
      this.backUrl = backUrl;
      this.readOnly = readOnly;
      this.inputRows.clear();

      if (inputsModels != null) {
        int index = 0;
        for (InputsModel inputsModel : inputsModels) {
          inputRows.add(new InputsRow(index++, inputsModel));
        }
      }
    }

    public void resetAll() {
      inputRows.clear();
      backUrl = null;
    }

    public List<InputsRow> getInputRows() {
      return inputRows;
    }

    public String getBackUrl() {
      return backUrl;
    }

    public boolean isReadOnly() {
      return readOnly;
    }
  }

  // ------------------------------------------------------
  // InputsRow
  // ------------------------------------------------------

  public static class InputsRow implements Serializable {
    private static final long serialVersionUID = 1L;

    private int index;
    private Serializable primaryKey;
    private String title;

    public InputsRow(int index, InputsModel inputs) {
      this.index = index;
      this.primaryKey = inputs.getId();
      this.title = inputs.getTitle();
    }

    public int getIndex() {
      return index;
    }

    public Serializable getPrimaryKey() {
      return primaryKey;
    }

    public String getTitle() {
      return title;
    }
  }

  // ------------------------------------------------------
  // BackButtonCallback
  // ------------------------------------------------------

  /** Resets all of the input values. */
  public static class BackButtonCallback implements ActionListenerCallback {
    private InputsStateBean inputsStateBean;

    public BackButtonCallback(InputsStateBean inputsStateBean) {
      this.inputsStateBean = inputsStateBean;
    }

    @Override
    public void handleAction(AjaxBehaviorEvent event) {
      inputsStateBean.resetAll();
    }
  }

  // ============================================================================
  // AbstractCrudListBean
  // ============================================================================

  protected boolean isReadOnly() {
    return false;
  }

  /**
   * Return the URL to the page represented by this bean (e.g., crudList.jsf). This is used for the action handlers of editors
   * called by this page (e.g., the update editor must return to the crudList.jsf page).
   */
  protected String getCrudListUrl() {
    return "/apps/demo/crudList.jsf";
  }

  @PostConstruct
  public void initBean() {
    if (!getInputsStateBean().isInitialized()) {
      InputsService inputsService = ServiceLocator.locate(SERVICE_CLASS);

      // TODO-p1(george) Need to add criteria/filter (in case there are hundreds of items). Also, support pagination.
      List<InputsModel> inputModels = new ArrayList<InputsModel>(inputsService.getInputs());

      getInputsStateBean().init(inputModels, getCrudListUrl(), isReadOnly());
    }

    backButtonCallback = new BackButtonCallback(getInputsStateBean());
  }

  public BackButtonCallback getBackButtonCallback() {
    return backButtonCallback;
  }

  // IoC functions /////////////////////////////////////////////////////////////////

  public InputsStateBean getInputsStateBean() {
    return inputsStateBean;
  }

  public void setInputsStateBean(InputsStateBean inputsStateBean) {
    this.inputsStateBean = inputsStateBean;
  }
}
