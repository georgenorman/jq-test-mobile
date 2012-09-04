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

import com.thruzero.domain.demo.dao.InputsDAO;
import com.thruzero.domain.demo.model.InputsModel;
import com.thruzero.domain.jsf.dao.HttpSessionDAO;

/**
 * Saves data created by the inputs test page, in the session.
 *
 * @author George Norman
 */
public final class HttpSessionInputsDAO extends HttpSessionDAO<InputsModel> implements InputsDAO {

  // ------------------------------------------------------
  // HttpMemoryGuestBookStore
  // ------------------------------------------------------

  public static class HttpMemoryInputsStore extends HttpMemoryStore<InputsModel> {

    public HttpMemoryInputsStore() {
      super(InputsModel.class);
    }
  }

  // ============================================================================
  // HttpSessionGuestBookMessageDAO
  // ============================================================================

  /**
   * Use {@link com.thruzero.domain.locator.DAOLocator DAOLocator} to access a particular DAO.
   */
  private HttpSessionInputsDAO() {
    super(new HttpMemoryInputsStore());
  }
}
