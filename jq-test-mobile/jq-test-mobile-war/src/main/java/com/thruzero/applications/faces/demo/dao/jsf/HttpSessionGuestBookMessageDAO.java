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
package com.thruzero.applications.faces.demo.dao.jsf;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.thruzero.common.core.utils.DateTimeUtilsExt;
import com.thruzero.domain.demo.dao.GuestBookMessageDAO;
import com.thruzero.domain.demo.model.GuestBookMessage;
import com.thruzero.domain.jsf.dao.HttpSessionDAO;

/**
 * Saves data created by the guest book test page, in the session.
 *
 * @author George Norman
 */
public final class HttpSessionGuestBookMessageDAO extends HttpSessionDAO<GuestBookMessage> implements GuestBookMessageDAO {

  // ------------------------------------------------------
  // HttpMemoryGuestBookStore
  // ------------------------------------------------------

  public static class HttpMemoryGuestBookStore extends HttpMemoryStore<GuestBookMessage> {

    public HttpMemoryGuestBookStore() {
      super(GuestBookMessage.class);
    }

    @Override
    protected Map<Serializable, GuestBookMessage> createNewEntityMap() {
      Map<Serializable, GuestBookMessage> result = super.createNewEntityMap();

      GuestBookMessage msg;

      // prime with same data as used for database
      msg = new GuestBookMessage();
      msg.setId("1000");
      msg.setTitle("Good morning all");
      msg.setGuestName("Ed Hutton");
      msg.setLocation("Monterey Bay Aquarium");
      msg.setMessage("Really enjoyed the shark tank.");
      msg.setPostDate(DateTimeUtilsExt.stringToDate("07/12/2011", new Date()));
      result.put(msg.getId(), msg);

      msg = new GuestBookMessage();
      msg.setId("1001");
      msg.setTitle("Greetings from St. Louis");
      msg.setGuestName("Nigel");
      msg.setLocation("Missouri");
      msg.setMessage("Thanks for keeping the spirit of the A''s alive!");
      msg.setPostDate(DateTimeUtilsExt.stringToDate("01/16/2012", new Date()));
      result.put(msg.getId(), msg);

      msg = new GuestBookMessage();
      msg.setId("1002");
      msg.setTitle("EARN $1000 AN HOUR ON THE INTERNET!!!!");
      msg.setGuestName("Sir Spamalot");
      msg.setLocation("ANCHORAGE");
      msg.setMessage("YOUR CHANCE FOR FINANCIAL FREEDOM VERY EASILY DO NOT PASS THIS BY. ONLY $500, etc.");
      msg.setPostDate(DateTimeUtilsExt.stringToDate("01/16/2012", new Date()));
      result.put(msg.getId(), msg);

      return result;
    }

  }

  // ============================================================================
  // HttpSessionGuestBookMessageDAO
  // ============================================================================

  /**
   * Use {@link com.thruzero.domain.locator.DAOLocator DAOLocator} to access a particular DAO.
   */
  private HttpSessionGuestBookMessageDAO() {
    super(new HttpMemoryGuestBookStore());
  }

}
