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

import com.thruzero.domain.demo.dao.InputsDAO;
import com.thruzero.domain.demo.model.InputsModel;
import com.thruzero.domain.jpa.dao.JpaGenericDAO;

/**
 * A DAO for {@code InputsModel}, which uses JPA for storage - requires a 'testy_schema'.'inputs_model' table created in the database as well as JPA mapping file.
 *
 * @author George Norman
 */
public class JpaInputsDAO extends JpaGenericDAO<InputsModel> implements InputsDAO {

  protected JpaInputsDAO() {
    super(InputsModel.class);
  }

}
