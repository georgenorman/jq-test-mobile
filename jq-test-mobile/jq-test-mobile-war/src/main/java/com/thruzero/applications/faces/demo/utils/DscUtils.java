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
package com.thruzero.applications.faces.demo.utils;

import com.thruzero.common.core.infonode.InfoNodeElement;
import com.thruzero.common.core.support.EntityPath;
import com.thruzero.domain.dsc.service.DscInfoNodeService;
import com.thruzero.domain.service.InfoNodeService;
import com.thruzero.domain.store.BaseStorePath;

/**
 * Utilities used by DSC-related pages.
 *
 * @author George Norman
 */
public class DscUtils {

  /** This is a utility class - Allow for class extensions; disallow client instantiation */
  protected DscUtils() {
  }

  public static void assertValidInfoNode(InfoNodeElement infoNode, String nodeName, InfoNodeService infoNodeService, EntityPath nodePath) {
    if (infoNode == null && infoNodeService instanceof DscInfoNodeService) {
      BaseStorePath baseStorePath = ((DscInfoNodeService)infoNodeService).getBaseStorePath();
      String storePath = (baseStorePath == null) ? "" : baseStorePath.toString();
      throw new RuntimeException("ERROR: Could not load the '" + nodeName + "' InfoNode (path is '" + storePath + nodePath + "').");
    }
  }
}
