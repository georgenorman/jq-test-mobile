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
package com.thruzero.domain.demo.dao.dsc;

import com.thruzero.domain.demo.dao.InputsDAO;
import com.thruzero.domain.demo.model.InputsModel;
import com.thruzero.domain.dsc.dao.AbstractDataStoreDAO;
import com.thruzero.domain.dsc.dao.GenericDscDAO;

/**
 * An implementation of InputsDAO that uses a DataStoreContainer (DSC) as storage. Can be configured to use FileDataStoreContainer,
 * com.thruzero.domain.dsc.ws.WsDataStoreContainer or com.thruzero.domain.jpa.dao.JpaDataStoreContainer.
 * <p>
 * This DAO requires initialization (see {@link AbstractDataStoreDAO.DataStoreDAOInitParamKeys} for
 * details). Following is an example initialization using the config file using the FileDataStoreContainer:
 *
 * <xmp>
 *   <!-- Define the generic file-system DAO settings -->
 *   <section name="com.thruzero.domain.dsc.dao.DscInputsModelDAO">
 *     <entry key="com.thruzero.domain.dsc.store.DataStoreContainerFactory" value="[com.thruzero.domain.dsc.dao.AbstractDataStoreDAO]{com.thruzero.domain.dsc.store.DataStoreContainerFactory}" />
 *   </section>
 *
 *   <!-- Define application-specific file-system DAO settings -->
 *   <section name="com.thruzero.domain.dsc.dao.AbstractDataStoreDAO">
 *     <!-- The type of DataStoreContainerFactory to use -->
 *     <entry key="com.thruzero.domain.dsc.store.DataStoreContainerFactory" value="com.thruzero.domain.dsc.fs.FileDataStoreContainerFactory" />
 *   </section>
 * </xmp>
 *
 * Since this DAO derives from GenericDscDAO, it uses {@code XStreamDomainObjectTransformer} to flatten and resurrect instances of InputsModel to
 * the particular data store configured above.
 *
 *
 * @author George Norman
 */
public class DscInputsDAO extends GenericDscDAO<InputsModel>  implements InputsDAO {

  public DscInputsDAO() {
    super(InputsModel.class);
  }

}
