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
package com.thruzero.domain.demo.model;

import java.util.Date;

import com.thruzero.domain.store.AbstractPersistent;
import com.thruzero.domain.store.Persistent;

/**
 * Represents a persistent message created by a visitor to the guest book page.
 *
 * @author George Norman
 */
public class GuestBookMessage extends AbstractPersistent {
  private static final long serialVersionUID = 1L;

  private String title;
  private String guestName;
  private String location;
  private String message;
  private Date postDate;

  @Override
  public void copyFrom(final Persistent source) {
    super.copyFrom(source);

    GuestBookMessage guestBookMessageSource = (GuestBookMessage)source;
    title = guestBookMessageSource.getTitle();
    guestName = guestBookMessageSource.getGuestName();
    location = guestBookMessageSource.getLocation();
    message = guestBookMessageSource.getMessage();
    postDate = guestBookMessageSource.getPostDate();
  }

  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  public String getGuestName() {
    return guestName;
  }
  public void setGuestName(String guestName) {
    this.guestName = guestName;
  }

  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }

  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }

  public Date getPostDate() {
    return postDate;
  }
  public void setPostDate(Date postDate) {
    this.postDate = postDate;
  }

}
