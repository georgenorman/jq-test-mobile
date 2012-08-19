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

import com.thruzero.domain.demo.dao.GuestBookMessageDAO;
import com.thruzero.domain.demo.model.GuestBookMessage;
import com.thruzero.domain.jpa.dao.JpaGenericDAO;

/**
 * This is a concrete implementation of the GuestBookMessageDAO interface using JPA - requires 'testy_schema'.'guest_book_message' table.
 * <p>
 * The GuestBookService will use any registered implementation for the GuestBookMessageDAO interface.
 * Below is an example of how to register the JpaGuestBookMessageDAO implementation declaratively:
 *
 * <pre>
 *    &lt;entry key="com.thruzero.domain.demo.dao.GuestBookMessageDAO" value="com.thruzero.domain.demo.dao.jpa.JpaGuestBookMessageDAO" /&gt;
 * </pre>
 *
 * @author George Norman
 * @see com.thruzero.domain.demo.model.GuestBookMessage
 * @see com.thruzero.domain.demo.service.GuestBookService
 */
public class JpaGuestBookMessageDAO extends JpaGenericDAO<GuestBookMessage> implements GuestBookMessageDAO {

  protected JpaGuestBookMessageDAO() {
    super(GuestBookMessage.class);
  }

}

