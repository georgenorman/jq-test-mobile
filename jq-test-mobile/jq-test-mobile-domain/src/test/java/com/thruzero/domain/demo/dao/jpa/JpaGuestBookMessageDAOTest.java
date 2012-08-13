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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.Date;

import org.junit.Test;

import com.thruzero.common.core.utils.DateTimeFormatUtilsExt;
import com.thruzero.domain.demo.dao.GuestBookMessageDAO;
import com.thruzero.domain.demo.model.GuestBookMessage;
import com.thruzero.domain.locator.DAOLocator;
import com.thruzero.test.support.demo.AbstractPfJpaTestCase;

/**
 * Unit test for JpaGuestBookMessageDAO.
 *
 * @author George Norman
 */
public class JpaGuestBookMessageDAOTest extends AbstractPfJpaTestCase {
  public static String TEST_MESSAGE = "This is a test message";

  public GuestBookMessage createGuestBookMessage() {
    GuestBookMessage result = new GuestBookMessage();

    result.setGuestName("Test One");
    result.setLocation("Tallahassee Florida");
    result.setMessage("This is a test message.");
    result.setTitle("Howdy from Tallahassee!");
    result.setPostDate(new Date());

    return result;
  }

  @Test
  public void testLocatePreferenceDAO() {
    GuestBookMessageDAO dao = DAOLocator.locate(GuestBookMessageDAO.class);

    assertTrue(dao != null);
    assertTrue(dao.getClass().equals(JpaGuestBookMessageDAO.class));
  }

  @Test
  public void testGetNonExistantGuestBookMessage() {
    GuestBookMessageDAO dao = DAOLocator.locate(GuestBookMessageDAO.class);

    for (int i = 1; i > 0; i++) {
      beginTransaction(); // simulate the Transaction per Request pattern - Begin request cycle
      switch (i) {
        case 1: {
          GuestBookMessage message = dao.getByKey(new Long(101));
          assertNull(message);
        }
          break;
        default:
          i = -1;
      }
      commitTransaction(); // simulate the Transaction per Request pattern - End request cycle
    }
  }

  @Test
  public void testCreateNewPreference() {
    GuestBookMessageDAO dao = DAOLocator.locate(GuestBookMessageDAO.class);
    Serializable persistedMessageId = -1;

    for (int i = 1; i > 0; i++) {
      beginTransaction(); // simulate the Transaction per Request pattern - Begin request cycle
      switch (i) {
        case 1: {
          GuestBookMessage newMessage = createGuestBookMessage();
          dao.save(newMessage);
          persistedMessageId = newMessage.getId();
        }
          break;
        case 2: {
          GuestBookMessage persistedMessage = dao.getByKey(persistedMessageId);
          assertEquals(createGuestBookMessage().getGuestName(), persistedMessage.getGuestName());
          assertEquals(createGuestBookMessage().getLocation(), persistedMessage.getLocation());
          assertEquals(createGuestBookMessage().getMessage(), persistedMessage.getMessage());
          assertEquals(createGuestBookMessage().getTitle(), persistedMessage.getTitle());
          assertEquals(DateTimeFormatUtilsExt.truncateTime(createGuestBookMessage().getPostDate()), DateTimeFormatUtilsExt.truncateTime(persistedMessage.getPostDate()));
        }
          break;
        default:
          i = -1;
      }
      commitTransaction(); // simulate the Transaction per Request pattern - End request cycle
    }
  }

  @Test
  public void testDeletePersistedPreference() {
    GuestBookMessageDAO dao = DAOLocator.locate(GuestBookMessageDAO.class);
    GuestBookMessage persistedMessage = null;
    Serializable persistedMessageId = -1;

    for (int i = 1; i > 0; i++) {
      beginTransaction(); // simulate the Transaction per Request pattern - Begin request cycle
      switch (i) {
        case 1: {
          GuestBookMessage newMessage = createGuestBookMessage();
          dao.save(newMessage);
          persistedMessageId = newMessage.getId();
        }
          break;
        case 2: {
          persistedMessage = dao.getByKey(persistedMessageId);
          assertNotNull(persistedMessage);
          dao.delete(persistedMessage);
        }
          break;
        case 3: {
          persistedMessage = dao.getByKey(persistedMessageId);
          assertNull(persistedMessage);
        }
          break;
        default:
          i = -1;
      }
      commitTransaction(); // simulate the Transaction per Request pattern - End request cycle
    }
  }

  @Test
  public void testUpdatePersistedPreference() {
    GuestBookMessageDAO dao = DAOLocator.locate(GuestBookMessageDAO.class);
    GuestBookMessage persistedMessage = null;
    Serializable persistedMessageId = -1;

    for (int i = 1; i > 0; i++) {
      beginTransaction(); // simulate the Transaction per Request pattern - Begin request cycle
      switch (i) {
        case 1: {
          GuestBookMessage newMessage = createGuestBookMessage();
          dao.save(newMessage);
          persistedMessageId = newMessage.getId();
        }
          break;
        case 2: {
          persistedMessage = dao.getByKey(persistedMessageId);
          assertNotNull(persistedMessage);
        }
          break;
        case 3: {
          persistedMessage = dao.getByKey(persistedMessageId);
          persistedMessage.setMessage(TEST_MESSAGE);
          dao.update(persistedMessage);
        }
          break;
        case 4: {
          persistedMessage = dao.getByKey(persistedMessageId);
          assertNotNull(persistedMessage);
          assertEquals(TEST_MESSAGE, persistedMessage.getMessage());
        }
          break;
        default:
          i = -1;
      }
      commitTransaction(); // simulate the Transaction per Request pattern - End request cycle
    }
  }

  @Test
  public void testSaveOrUpdatePersistedPreference() {
    GuestBookMessageDAO dao = DAOLocator.locate(GuestBookMessageDAO.class);
    GuestBookMessage persistedMessage = null;
    Serializable persistedMessageId = -1;

    for (int i = 1; i > 0; i++) {
      beginTransaction(); // simulate the Transaction per Request pattern - Begin request cycle
      switch (i) {
        case 1: {
          GuestBookMessage newMessage = createGuestBookMessage();
          dao.saveOrUpdate(newMessage);
          persistedMessageId = newMessage.getId();
        }
          break;
        case 2: {
          persistedMessage = dao.getByKey(persistedMessageId);
          assertNotNull(persistedMessage);
        }
          break;
        case 3: {
          persistedMessage = dao.getByKey(persistedMessageId);
          persistedMessage.setMessage(TEST_MESSAGE);
          dao.saveOrUpdate(persistedMessage);
        }
          break;
        case 4: {
          persistedMessage = dao.getByKey(persistedMessageId);
          assertNotNull(persistedMessage);
          assertEquals(TEST_MESSAGE, persistedMessage.getMessage());
        }
          break;
        default:
          i = -1;
      }
      commitTransaction(); // simulate the Transaction per Request pattern - End request cycle
    }
  }

  protected void beginTransaction() {
    getTransactionService().beginTransaction(); // simulate the Transaction per Request pattern - Begin request cycle
  }

  protected void commitTransaction() {
    getTransactionService().commitTransaction(); // simulate the Transaction per Request pattern - End request cycle
  }

}
