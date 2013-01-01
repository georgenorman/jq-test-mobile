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
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import com.thruzero.common.core.locator.ServiceLocator;
import com.thruzero.common.jsf.support.beans.page.AbstractDemoPageBean;
import com.thruzero.domain.demo.model.GuestBookMessage;
import com.thruzero.domain.demo.service.GuestBookService;

/**
 * Page bean for the guestBook.xhtml page - a semi-demonstration of CRUD (missing Update and Delete).
 * A user can view the guest book entries as well as create new ones, but can't edit or delete.
 *
 * @author George Norman
 */
@ManagedBean(name="guestBookBean")
@RequestScoped
public class GuestBookBean extends AbstractDemoPageBean {
  private static final long serialVersionUID = 1L;

  private static final Class<GuestBookService> SERVICE_CLASS = GuestBookService.class;

  private static GuestBookService guestBookService;

  private GuestBookMessage newMessage;

  /** Initialize the info dialog for this demo page bean. */
  @PostConstruct
  public void initBean() {
    guestBookService = ServiceLocator.locate(SERVICE_CLASS);

    initInfoDialog(guestBookService);
  }

  public Collection<? extends GuestBookMessage> getMessages() {
    Collection<? extends GuestBookMessage> messages = guestBookService.getGuestBookMessages();

    return messages;
  }

  public GuestBookMessage getNewMessage() {
    if (newMessage == null) {
      newMessage = new GuestBookMessage();
    }

    return newMessage;
  }

  public String postNewMessage() {
    newMessage.setPostDate(new Date());
    guestBookService.saveGuestBookMessage(newMessage);
    newMessage = null;

    return "/apps/demo/guestBook.jsf?faces-redirect=true";
  }

  public void resetNewMessageListener(ActionEvent event) {
    newMessage = null;
  }

}
