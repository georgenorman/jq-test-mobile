/*
 *   Copyright 2006-2012 George Norman
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

import org.apache.commons.lang3.StringUtils;

/**
 * REPLACE THIS WITH: http://code.google.com/p/owasp-java-html-sanitizer/
 * <p>
 * Strip out specific html tags from an RSS description (used as a tooltip). Also, truncate
 * the result to the requested length.
 *
 * @author George Norman
 */
public class RssSanitizer {

  /** max size of description */
  public static final int MAX_TOOL_TIP_SIZE = 500;

  /** remove troublesome characters from given description */
  public static String sanitizeDescription(String description, final int maxSize) {
    // return immediately if description is empty
    if (StringUtils.isEmpty(description)) {
      return "";
    }

    // remove html tags from given description
    description = RssSanitizer.removeHtmlTags(description);

    // abbreviate
    return StringUtils.abbreviate(description, maxSize);
  }

  /** remove html tags from given string */
  protected static String removeHtmlTags(final String string) {
    return RssSanitizer.doRemoveFrom(string, "<", ">");
  }

  /** remove substring beginning and ending with given tags. */
  protected static String doRemoveFrom(String string, final String beginTag, final String endTag) {
    while (true) {
      String tag = StringUtils.substringBetween(string, beginTag, endTag);
      if (StringUtils.isEmpty(tag)) {
        break;
      }
      string = StringUtils.remove(string, beginTag + tag + endTag);
    }

    return string;
  }
}
