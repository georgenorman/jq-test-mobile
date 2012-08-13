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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.thruzero.applications.faces.demo.utils.DscUtils;
import com.thruzero.applications.faces.demo.utils.RssSanitizer;
import com.thruzero.common.core.infonode.InfoNodeElement;
import com.thruzero.common.core.locator.ServiceLocator;
import com.thruzero.common.core.support.EntityPath;
import com.thruzero.common.core.utils.DateTimeFormatUtilsExt;
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
@javax.faces.bean.ManagedBean(name="feedsBean")
@javax.faces.bean.RequestScoped
public class FeedsBean extends AbstractDemoPageBean {
  private static final long serialVersionUID = 1L;

  private static final Class<InfoNodeService> SERVICE_CLASS = InfoNodeService.class;

  private List<SyndFeedWrapper> feeds;

  // ------------------------------------------------------
  // NewsEntry
  // ------------------------------------------------------

  /** An instance represents a single news item from an RSS feed. */
  public static class NewsEntry {
    private String title;
    private String link;
    private String description;

    public NewsEntry(String title, String link, String description, boolean stripTags) {
      this.title = title;
      this.link = link;
      this.description = stripTags ? RssSanitizer.sanitizeDescription(description, 256) : description;
    }

    public String getTitle() {
      return title;
    }

    public String getLink() {
      return link;
    }

    public String getDescription() {
      return description;
    }
  }

  // ------------------------------------------------------
  // SyndFeedWrapper
  // ------------------------------------------------------

  /** An instance represents the title, status and news items for a single RSS feed. */
  public static class SyndFeedWrapper {
    private String errorStatus;
    private String title;
    private String titleLink;
    private String publishedDate;
    private List<NewsEntry> entries = new ArrayList<NewsEntry>();

    public SyndFeedWrapper(String errorStatus, InfoNodeElement spec, Date publishedDate, List<SyndEntry> sourceEntries) {
      this.errorStatus = errorStatus;

      // if no error, then gather info
      if (StringUtils.isEmpty(errorStatus)) {
        this.title = spec.getAttributeValue("title");
        this.titleLink = spec.getAttributeValue("titleLink");
        this.publishedDate = publishedDate == null ? "?" : DateTimeFormatUtilsExt.formatMmDdYyyy(publishedDate);

        int maxEntries = spec.getAttributeTransformer("size").getIntValue(5);
        boolean stripTags = spec.getAttributeTransformer("stripTags").getBooleanValue();
        if (maxEntries > 0) {
          for (int i = 0; i < sourceEntries.size() && i < maxEntries; i++) {
            SyndEntry syndEntry = sourceEntries.get(i);
            String description = syndEntry.getDescription() == null ? "" : syndEntry.getDescription().getValue();

            entries.add(new NewsEntry(syndEntry.getTitle(), syndEntry.getLink(), description, stripTags));
          }
        }
      }
    }

    public String getStatus() {
      return errorStatus;
    }

    public String getTitle() {
      return title;
    }

    public String getTitleLink() {
      return titleLink;
    }

    public String getPublishedDate() {
      return publishedDate;
    }

    public List<NewsEntry> getEntries() {
      return entries;
    }
  }

  // ============================================================================
  // FeedsBean
  // ============================================================================

  @PostConstruct
  public void initBean() {
    InfoNodeService service = ServiceLocator.locate(SERVICE_CLASS);

    initInfoDialog(service, new UrlBean("/apps/demo/feeds.jsf", true)); // TODO(george):p1  back URL should come from jsf page
  }

  public List<SyndFeedWrapper> getFeeds() {
    ensureFeeds();

    return feeds;
  }

  private void ensureFeeds() {
    if (feeds == null) {
      InfoNodeService infoNodeService = ServiceLocator.locate(SERVICE_CLASS);
      EntityPath nodePath = new EntityPath("/jcat/devRes/", "feeds.xml");
      InfoNodeElement feedNodes = infoNodeService.getInfoNode(nodePath);

      // print some diagnostic info if DscInfoNodeService is used
      DscUtils.assertValidInfoNode(feedNodes, "feeds", infoNodeService, nodePath);

      feedNodes.enableRootNode();

      // now read the RSS feeds
      URL feedSource;

      feeds = new ArrayList<SyndFeedWrapper>();
      for (InfoNodeElement node : (List<InfoNodeElement>)feedNodes.getChildren()) {
        String feedUrl = node.getText();

        try {
          feedSource = new URL(feedUrl);
          SyndFeedInput input = new SyndFeedInput();
          SyndFeed feed = input.build(new XmlReader(feedSource));

          feeds.add(new SyndFeedWrapper("", node, feed.getPublishedDate(), (List<SyndEntry>)feed.getEntries()));
        } catch (MalformedURLException e) {
          feeds.add(new SyndFeedWrapper("RSS Feed URL is malformed: " + feedUrl, null, null, null));
        } catch (Exception e) {
          feeds.add(new SyndFeedWrapper("RSS Feed could not be read: " + e.getClass().getSimpleName(), null, null, null));
        }
      }
    }
  }
}
