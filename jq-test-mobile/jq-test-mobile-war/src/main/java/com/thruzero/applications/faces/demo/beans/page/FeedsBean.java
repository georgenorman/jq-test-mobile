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
package com.thruzero.applications.faces.demo.beans.page;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.thruzero.common.core.locator.ServiceLocator;
import com.thruzero.common.jsf.support.beans.UrlBean;
import com.thruzero.common.jsf.support.beans.page.AbstractDemoPageBean;
import com.thruzero.domain.service.InfoNodeService;

/**
 * Page bean for the feeds.xhtml page. Uses ROME to read a few RSS feeds (specified by the InfoNodeService).
 *
 * @author George Norman
 * @see: http://www.jarvana.com/jarvana/view/rome/rome/0.9/rome-0.9-javadoc.jar!/com/sun/syndication/feed/synd/SyndEntryImpl.html
 * @see https://rometools.jira.com/wiki/display/ROME/Home
 */
@ManagedBean(name="feedsBean")
@RequestScoped
public class FeedsBean extends AbstractDemoPageBean {
  private static final long serialVersionUID = 1L;

  @PostConstruct
  public void initBean() {
    InfoNodeService service = ServiceLocator.locate(InfoNodeService.class);

    initInfoDialog(service, new UrlBean("/apps/demo/feeds.jsf", true)); // TODO-p1(george) Back URL should come from jsf page
  }
}
