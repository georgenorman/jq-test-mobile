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
package com.thruzero.domain.demo.service;

import java.util.Collection;

import com.thruzero.common.core.service.Service;
import com.thruzero.common.core.support.SimpleInfo;
import com.thruzero.common.core.support.SimpleInfoProvider;
import com.thruzero.domain.demo.dao.GuestBookMessageDAO;
import com.thruzero.domain.demo.model.GuestBookMessage;
import com.thruzero.domain.locator.DAOLocator;

/**
 * Manages CRUD operations for the guest book.
 *
 * @author George Norman
 * @see com.thruzero.domain.demo.model.GuestBookMessage
 */
public final class GuestBookService implements Service, SimpleInfoProvider {
  private final GuestBookMessageDAO guestBookMessageDAO = DAOLocator.locate(GuestBookMessageDAO.class);

  /**
   * Use {@link com.thruzero.common.core.locator.ServiceLocator ServiceLocator} to access a particular Service.
   */
  private GuestBookService() {
  }

  public Collection<? extends GuestBookMessage> getGuestBookMessages() {
    Collection<? extends GuestBookMessage> result = guestBookMessageDAO.getAll();

    return result;
  }

  public void saveGuestBookMessage(final GuestBookMessage message) {
    guestBookMessageDAO.saveOrUpdate(message);
  }

  public void deleteGuestBookMessage(final GuestBookMessage message) {
    guestBookMessageDAO.delete(message);
  }

  /** Return a simple description of the service configuration (e.g., "GenericInfoNodeService configured using JpaTextEnvelopeDAO"). */
  @Override
  public SimpleInfo getSimpleInfo() {
    return SimpleInfo.createSimpleInfo(this, guestBookMessageDAO);
  }

}
