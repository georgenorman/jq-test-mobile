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

package com.thruzero.applications.faces.demo.provider;

import org.apache.commons.lang3.StringUtils;

import com.thruzero.auth.model.impl.BasicUserDataStoreInfo;
import com.thruzero.common.core.locator.Initializable;
import com.thruzero.common.core.locator.InitializationException;
import com.thruzero.common.core.locator.InitializationStrategy;
import com.thruzero.common.core.locator.LocatorUtils;
import com.thruzero.common.core.map.StringMap;
import com.thruzero.domain.model.DataStoreInfo;
import com.thruzero.domain.provider.DataStoreInfoProvider;

/**
 *
 * @author George Norman
 */
public class DemoDataStoreInfoProvider implements DataStoreInfoProvider, Initializable {
  private String defaultUserName;

  @Override
  public DataStoreInfo getDataStoreInfo() {
    // for the demo, force everyone to use the same data-store context
    return new BasicUserDataStoreInfo("<db-info><context>" + defaultUserName + "</context></db-info>");
  }

  @Override
  public DataStoreInfo getDataStoreInfo(String userName) {
    // for the demo, force everyone to use the same data-store context
    return getDataStoreInfo();
  }

  @Override
  public void init(InitializationStrategy initStrategy) {
    StringMap initParams = LocatorUtils.getInheritedParameters(initStrategy, this.getClass(), DataStoreInfoProvider.class);

    defaultUserName = initParams.getValueTransformer(DEFAULT_USER_NAME_KEY).getStringValue();

    if (StringUtils.isEmpty(defaultUserName)) {
      throw InitializationException.createMissingKeyInitializationException(DataStoreInfoProvider.class.getName(), DEFAULT_USER_NAME_KEY, initStrategy);
    }
  }

  @Override
  public void reset() {
  }

}
