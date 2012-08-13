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

import com.thruzero.common.core.bookmarks.LocatorRegistryBookmark;
import com.thruzero.common.core.locator.RegistryLocatorStrategy.LocatorLogHelper;
import com.thruzero.common.core.locator.ServiceLocator;
import com.thruzero.common.core.service.Service;
import com.thruzero.common.core.service.impl.ServiceRegistry;

/**
 * Convenience class for registering all of the demo Service-related interfaces provided by this package. Clients may use
 * this class, or alternatively, use the config file or even register the interfaces manually via the {@link
 * com.thruzero.common.core.locator.ServiceLocator#getRegistry() ServiceLocator.getRegistry()} method.
 *
 * @author George Norman
 */
@LocatorRegistryBookmark(comment = "DemoServiceRegistry")
public class DemoServiceRegistry extends ServiceRegistry {
  private static final DemoServiceRegistry instance = new DemoServiceRegistry();

  /** Allow for class extensions; disallow client instantiation */
  protected DemoServiceRegistry() {
  }

  public static DemoServiceRegistry getInstance() {
    return instance;
  }

  public void registerAllInterfaces() {
    // this is only called once
    new LocatorLogHelper(DemoServiceRegistry.class).logBeginRegisterInterfaces(Service.class.getName(), DemoServiceRegistry.class);

    ServiceLocator.getRegistry().registerInterface(GuestBookService.class, GuestBookService.class);
    ServiceLocator.getRegistry().registerInterface(InputsService.class, InputsService.class);
  }

}
