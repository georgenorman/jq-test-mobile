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

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.thruzero.applications.faces.demo.beans.page.CommandButtonsBean.InputCommandsTestHelper.FormTestModel;
import com.thruzero.applications.faces.support.model.SimpleNotificationModel;
import com.thruzero.common.core.locator.ProviderLocator;
import com.thruzero.common.core.locator.ServiceLocator;
import com.thruzero.common.core.provider.ResourceProvider;
import com.thruzero.common.jsf.support.AbstractActionCallback;
import com.thruzero.common.jsf.support.beans.UrlBean;
import com.thruzero.common.jsf.support.beans.dialog.AbstractDialogBean.AbstractDialogModel;
import com.thruzero.common.jsf.support.beans.dialog.AbstractFlashDialogBean.DialogFlashPayload;
import com.thruzero.common.jsf.support.beans.dialog.ConfirmationDialogBean.ConfirmationDialogModel;
import com.thruzero.common.jsf.support.beans.dialog.MessageDialogBean.MessageDialogModel;
import com.thruzero.common.jsf.support.beans.page.AbstractDemoPageBean;
import com.thruzero.common.jsf.utils.FlashUtils;
import com.thruzero.domain.dsc.service.DscInfoNodeService;

/**
 * Page bean for the commandButtons.xhtml page - demonstrates JSF commandButton actions:
 * <ul>
 *  <li>Command-1: Calls an action which sets up a confirmation dialog to execute an ok or cancel action.</li>
 *  <li>Command-2: Calls an action which displays a simple message dialog.</li>
 *  <li>Command-10: In a separate form validates a text input field and sets up a confirmation dialog.</li>
 * </ul>
 *
 * @author George Norman
 */
@ManagedBean(name="commandButtonsBean")
@RequestScoped
public class CommandButtonsBean extends AbstractDemoPageBean {
  private static final long serialVersionUID = 1L;

  private static final Class<DscInfoNodeService> SERVICE_CLASS = DscInfoNodeService.class;

  private static String LANDING_PAGE = "/apps/demo/commandButtons.jsf";

  private SimpleNotificationModel simpleNotificationModel;

  private SimpleCommandsTestHelper simpleCommandsTestHelper = new SimpleCommandsTestHelper();
  private InputCommandsTestHelper inputCommandsTestHelper = new InputCommandsTestHelper();

  // ----------------------------------------------------
  // CommandFlashPayload
  // ----------------------------------------------------

  public static class CommandFlashPayload implements DialogFlashPayload {
    private FormTestModel formTestModel;
    private SimpleNotificationModel simpleNotificationModel;
    private AbstractDialogModel dialogModel;

    public FormTestModel getFormTestModel() {
      return formTestModel;
    }

    public void setFormTestModel(FormTestModel formTestModel) {
      this.formTestModel = formTestModel;
    }

    public SimpleNotificationModel getSimpleNotificationModel() {
      return simpleNotificationModel;
    }

    public void setSimpleNotificationModel(SimpleNotificationModel simpleNotificationModel) {
      this.simpleNotificationModel = simpleNotificationModel;
    }

    @Override
    public AbstractDialogModel getDialogModel() {
      return dialogModel;
    }

    public void setDialogModel(AbstractDialogModel dialogModel) {
      this.dialogModel = dialogModel;
    }

    public static CommandFlashPayload ensureFlashPayload() {
      CommandFlashPayload result = (CommandFlashPayload)FlashUtils.removeFlashAttribute();

      if (result == null) {
        result = new CommandFlashPayload();
      }

      return result;
    }
  }

  // ----------------------------------------------------
  // CommandOkActionCallback
  // ----------------------------------------------------

  public class CommandOkActionCallback extends AbstractActionCallback {

    public CommandOkActionCallback(UrlBean actionOutcome) {
      super(actionOutcome);
    }

    @Override
    public UrlBean handleAction() {
      UrlBean result = getActionOutcome();

      CommandFlashPayload flashPayload = CommandFlashPayload.ensureFlashPayload();
      flashPayload.setSimpleNotificationModel(new SimpleNotificationModel("You clicked the OK button."));

      String flashHackKey = FlashUtils.saveFlashAttribute(flashPayload);

      result.appendQueryParam(FlashUtils.FLASH_HACK_REQUEST_PARAMETER_KEY, flashHackKey);

      return result;
    }
  }

  // ----------------------------------------------------
  // CommandCancelActionCallback
  // ----------------------------------------------------

  public class CommandCancelActionCallback extends AbstractActionCallback {

    public CommandCancelActionCallback(UrlBean actionOutcome) {
      super(actionOutcome);
    }

    @Override
    public UrlBean handleAction() {
      UrlBean result = getActionOutcome();

      CommandFlashPayload flashPayload = CommandFlashPayload.ensureFlashPayload();
      flashPayload.setSimpleNotificationModel(new SimpleNotificationModel("You clicked the Cancel button."));

      String flashHackKey = FlashUtils.saveFlashAttribute(flashPayload);

      result.appendQueryParam(FlashUtils.FLASH_HACK_REQUEST_PARAMETER_KEY, flashHackKey);

      return result;
    }
  }

  // ----------------------------------------------------
  // SimpleCommandsTestHelper
  // ----------------------------------------------------

  public class SimpleCommandsTestHelper {

    public String command1Action() {
      ConfirmationDialogModel confirmationDialogModel = new ConfirmationDialogModel();

      String title = ProviderLocator.locate(ResourceProvider.class).getResource("dialog.confirmation.title");
      confirmationDialogModel.setTitle(title);

      String header = ProviderLocator.locate(ResourceProvider.class).getResource("dialog.simple.test.command1.header");
      confirmationDialogModel.setHeader(header);
      String message = ProviderLocator.locate(ResourceProvider.class).getResource("dialog.simple.test.command1.message");
      confirmationDialogModel.setMessage(message);
      confirmationDialogModel.setOkActionCallback(new CommandOkActionCallback(new UrlBean(LANDING_PAGE, true)));
      confirmationDialogModel.setCancelActionCallback(new CommandCancelActionCallback(new UrlBean(LANDING_PAGE, true)));

      CommandFlashPayload flashPayload = CommandFlashPayload.ensureFlashPayload();
      flashPayload.setDialogModel(confirmationDialogModel);

      String flashHackKey = FlashUtils.saveFlashAttribute(flashPayload);

      return "/common/dialogs/confirmationDialog.jsf?faces-redirect=true&fhk=" + flashHackKey;
    }

    public String command2Action() {
      MessageDialogModel messageDialogModel = new MessageDialogModel();

      String title = ProviderLocator.locate(ResourceProvider.class).getResource("dialog.message.title");
      messageDialogModel.setTitle(title);

      String header = ProviderLocator.locate(ResourceProvider.class).getResource("dialog.simple.test.command2.header");
      messageDialogModel.setHeader(header);
      String message = ProviderLocator.locate(ResourceProvider.class).getResource("dialog.simple.test.command2.message");
      messageDialogModel.setMessage(message);
      messageDialogModel.setDoneOutcome(new UrlBean(LANDING_PAGE, true));

      getMessageDialogBean().setDialogModel(messageDialogModel);

      return "/common/dialogs/infoDialog.jsf?faces-redirect=true";
    }
  }

  // ----------------------------------------------------
  // InputCommandsTestHelper
  // ----------------------------------------------------

  public class InputCommandsTestHelper {
    private FormTestModel formTestModel;

    // - - - - - - - - - - - - - - - - - -
    // FormTestModel
    // - - - - - - - - - - - - - - - - - -

    /**
     * Model for the form tests. Can live in any scope (e.g., flash).
     */
    public class FormTestModel implements Serializable {
      private static final long serialVersionUID = 1L;

      private String title;

      public String getTitle() {
        return title;
      }

      public void setTitle(String title) {
        this.title = title;
      }
    }

    public class CancelActionCallback extends AbstractActionCallback {

      public CancelActionCallback(UrlBean actionOutcome) {
        super(actionOutcome);
      }

      @Override
      public UrlBean handleAction() {
        UrlBean result = getActionOutcome();

        CommandFlashPayload flashPayload = CommandFlashPayload.ensureFlashPayload();
        flashPayload.setFormTestModel(formTestModel);

        String flashHackKey = FlashUtils.saveFlashAttribute(flashPayload);

        result.appendQueryParam(FlashUtils.FLASH_HACK_REQUEST_PARAMETER_KEY, flashHackKey);

        return result;
      }
    }

    // = = = = = = = = = = = = = = = = = = = = = = = =
    // InputCommandsTestHelper
    // = = = = = = = = = = = = = = = = = = = = = = = =

    public FormTestModel getFormTestModel() {
      if (formTestModel == null) {
        formTestModel = new FormTestModel();
      }

      return formTestModel;
    }

    public void setFormTestModel(FormTestModel formTestModel) {
      this.formTestModel = formTestModel;
    }

    public String command10Action() {
      ConfirmationDialogModel confirmationDialogModel = new ConfirmationDialogModel();

      String title = ProviderLocator.locate(ResourceProvider.class).getResource("dialog.confirmation.title");
      confirmationDialogModel.setTitle(title);
      String header = ProviderLocator.locate(ResourceProvider.class).getResource("dialog.form.test.command10.header");
      confirmationDialogModel.setHeader(header);
      String message = ProviderLocator.locate(ResourceProvider.class).getResource("dialog.form.test.command10.message");
      confirmationDialogModel.setMessage(message);
      confirmationDialogModel.setOkActionCallback(new CommandOkActionCallback(new UrlBean(LANDING_PAGE, true)));
      confirmationDialogModel.setCancelActionCallback(new CancelActionCallback(new UrlBean(LANDING_PAGE, true)));

      CommandFlashPayload flashPayload = CommandFlashPayload.ensureFlashPayload();
      flashPayload.setDialogModel(confirmationDialogModel);
      flashPayload.setFormTestModel(formTestModel);

      String flashHackKey = FlashUtils.saveFlashAttribute(flashPayload);

      return "/common/dialogs/confirmationDialog.jsf?faces-redirect=true&fhk=" + flashHackKey;
    }
  }

  // =====================================================================
  // CommandButtonsBean
  // =====================================================================

  @PostConstruct
  public void initBean() {
    DscInfoNodeService dscInfoNodeService = ServiceLocator.locate(SERVICE_CLASS); // locate Data Store version

    initInfoDialog(dscInfoNodeService, new UrlBean(LANDING_PAGE, true)); // TODO-p1(george) The back URL should come from jsf page

    CommandFlashPayload flashPayload = CommandFlashPayload.ensureFlashPayload();
    simpleNotificationModel = (flashPayload.getSimpleNotificationModel() == null) ? new SimpleNotificationModel() : flashPayload.getSimpleNotificationModel();
    inputCommandsTestHelper.setFormTestModel(flashPayload.getFormTestModel());
  }

  public SimpleNotificationModel getNotificationModel() {
    return simpleNotificationModel;
  }

  public SimpleCommandsTestHelper getSimpleCommandsTestHelper() {
    return simpleCommandsTestHelper;
  }

  public InputCommandsTestHelper getInputCommandsTestHelper() {
    return inputCommandsTestHelper;
  }
}
