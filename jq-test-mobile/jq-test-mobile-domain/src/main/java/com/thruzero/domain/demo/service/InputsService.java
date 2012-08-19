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

import java.io.Serializable;
import java.util.Collection;

import com.thruzero.common.core.service.Service;
import com.thruzero.common.core.support.SimpleInfo;
import com.thruzero.common.core.support.SimpleInfoProvider;
import com.thruzero.domain.demo.dao.InputsDAO;
import com.thruzero.domain.demo.model.InputsModel;
import com.thruzero.domain.locator.DAOLocator;

/**
 * Manages CRUD operations for the inputs form test.
 *
 * @author George Norman
 * @see com.thruzero.domain.demo.model.InputsModel
 */
public class InputsService implements Service, SimpleInfoProvider {
  private final InputsDAO inputsDAO = DAOLocator.locate(InputsDAO.class);

  //TODO-p1(george) need to add criteria/filter (in case there are hundreds of items). Also, support pagination.
  public Collection<? extends InputsModel> getInputs() {
    Collection<? extends InputsModel> result = inputsDAO.getAll();

    return result;
  }

  public InputsModel getByKey(Serializable primaryKey) {
    return inputsDAO.getByKey(primaryKey);
  }

  public void saveInputs(final InputsModel inputs) {
    inputsDAO.saveOrUpdate(inputs);
  }

  public void deleteInputsModel(final InputsModel inputs) {
    inputsDAO.delete(inputs);
  }

  /** Return a simple description of the service configuration (e.g., "GenericInfoNodeService configured using JpaTextEnvelopeDAO"). */
  @Override
  public SimpleInfo getSimpleInfo() {
    return SimpleInfo.createSimpleInfo(this, inputsDAO);
  }

}
