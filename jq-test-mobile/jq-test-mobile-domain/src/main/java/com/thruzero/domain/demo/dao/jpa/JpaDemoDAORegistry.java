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
package com.thruzero.domain.demo.dao.jpa;

import com.thruzero.common.core.bookmarks.LocatorRegistryBookmark;
import com.thruzero.common.core.locator.RegistryLocatorStrategy.LocatorLogHelper;
import com.thruzero.domain.dao.DAO;
import com.thruzero.domain.dao.impl.DAORegistry;
import com.thruzero.domain.demo.dao.GuestBookMessageDAO;
import com.thruzero.domain.demo.dao.InputsDAO;
import com.thruzero.domain.demo.dao.dsc.DscInputsDAO;
import com.thruzero.domain.jpa.dao.JpaTextEnvelopeDAO;
import com.thruzero.domain.locator.DAOLocator;

/**
 * Convenience class for registering all of the Demo JPA DAO-related interfaces provided by this package. Clients may use
 * this class, or alternatively, use the config file or even register the interfaces manually via the {@link
 * com.thruzero.domain.locator.DAOLocator#getRegistry() DAOLocator.getRegistry()} method.
 *
 * @author George Norman
 */
@LocatorRegistryBookmark(comment = "JpaDemoDAORegistry")
public final class JpaDemoDAORegistry extends DAORegistry {
  private static final JpaDemoDAORegistry instance = new JpaDemoDAORegistry();

  /** Use getInstance() to get an instance of this class. */
  private JpaDemoDAORegistry() {
  }

  public static JpaDemoDAORegistry getInstance() {
    return instance;
  }

  public void registerAllInterfaces() {
    // this is only called once
    new LocatorLogHelper(JpaDemoDAORegistry.class).logBeginRegisterInterfaces(DAO.class.getName(), JpaDemoDAORegistry.class);

    DAOLocator.getRegistry().registerInterface(GuestBookMessageDAO.class, JpaGuestBookMessageDAO.class);

    DAOLocator.getRegistry().registerInterface(InputsDAO.class, DscInputsDAO.class); // DAO for InputsModel
    DAOLocator.getRegistry().registerInterface(JpaTextEnvelopeDAO.class, JpaTextEnvelopeDAO.class); // DAO for DscInputsDAO
  }

}
