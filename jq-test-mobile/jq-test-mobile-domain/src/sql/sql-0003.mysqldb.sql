
SET sql_safe_updates=0;


-- INFO_NODES
INSERT INTO testy_schema.TEXT_ENVELOPE VALUES (1000, '/jcat/devRes/', 'movies.xml',
'<?xml version="1.0" encoding="utf-8"?>
<movies>
  <movie id="1" title="Everglades" image="/images/apps/demo/movies/scenic/CocoaIsles.png" disabled="false">
      At the place where we put our boat in the lake, the water was too shallow to use our motor.
      So we paddled our boat out from shore, moving in the direction of the flamingoes.
      However, as we expected, the flamingoes saw us.  Unlike their dwarfed duck companions,
      which made one mad loud scramble to get into the air, the flamingoes walked slowly along
      the lake bottom, the water reaching only the midjoint on their long, stilt legs making the big
      birds  appear as a duet of slow skaters moving in rhythm  to some secret waltz.
      They accelerated as they moved along and when the needed speed was attained, they gracefully
      lifted themselves from the water and soon were flying, legs stretched out behind.
      ${break}${break}
    - (c) Edward W. Norman - Autobiography
  </movie>
  <movie id="2" title="Flight Training" image="/images/apps/demo/movies/scenic/GoldenGate.png" disabled="false">
    We were always warned never to spin the prop in starting our engines when there was no one in the cockpit at the controls.
    One afternoon when I went to fly the plane that had been assigned to me I learned that one of the mechanics had cranked up my plane with no one in the cockpit.
    The plane leaped the chocks and would have gone off down the taxi strip on its own; but he grabbed the end of the wing.
    The plane began going around in circles, with the mechanic hanging on.  Round and around it went until my plane ended up with
    its nose jammed into the wall of the hanger.  I was assigned another plane.
      ${break}${break}
    - (c) Edward W. Norman - Autobiography
  </movie>
  <movie id="3" title="Clouds Race Across the Sky" image="/images/apps/demo/movies/scenic/Yosemite.png" disabled="false">
    We traveled through heavy fog for a while.  But then we found ourselves in the sun, flying over a region of evergreen trees.
    We landed at Goose Bay, Labrador. After our evening meal we resumed our journey.
    We flew over a cloud mass, broken here and there permitting a view of the sea below.
    It was near midnight when I saw the sun, a ball of fire sinking slowly into the sea.
    When the sun had disappeared, the clouds in the west glowed with reflecting fire.  Twilight remained.
      ${break}${break}
    - (c) Edward W. Norman - Autobiography
  </movie>
</movies>
');

INSERT INTO testy_schema.TEXT_ENVELOPE VALUES (1001, '/', 'dashboard.xml',
'<?xml version="1.0" encoding="utf-8"?>
<dashboard id="dashboard">
  <column id="1">
    <panel id="panelTests" title="Panel Tests" type="simple">
      <link href="/apps/demo/simplePanel.jsf">Simple Panel</link>
      <link href="/apps/demo/multiplePanels.jsf">Multiple Panels</link>
      <link href="/apps/demo/dscPanels.jsf">DSC Panels</link>
    </panel>

    <panel id="componentTests" title="Miscellaneous Tests" type="simple">
      <link href="/apps/demo/crudList.jsf" refresh="true">CRUD</link>
      <link href="/apps/demo/ajax.jsf" refresh="true">Ajax</link>
    </panel>

    <panel id="componentTests" title="Component Tests" type="simple">
      <link href="/apps/demo/navigationButtons.jsf">Navigation Buttons</link>
      <link href="/apps/demo/commandButtons.jsf">Command Buttons</link>
      <link href="/apps/demo/feeds.jsf">RSS Feeds</link>
      <link href="/apps/demo/charts.jsf">Charts</link>
      <link href="/apps/demo/maps.jsf">Maps</link>
    </panel>
  </column>

  <column id="2">
    <panel id="imageGallery" title="Image Galleries" type="simple">
      <link href="/apps/demo/galleria.jsf">Galleria</link>
    </panel>

    <panel id="jpaTests" title="Tools" type="simple">
      <link href="/apps/demo/guestBook.jsf" refresh="true">Guest Book</link>
      <link href="/apps/demo/escapeTool.jsf" refresh="true">Escape Tool</link>
    </panel>

    <panel id="authTests" title="Auth Tests" type="simple">
      <link href="/auth/login.jsf" refresh="true">Login</link>
      <link href="/apps/demo/secure/secure1.jsf">Secure Page Test #1</link>
      <link href="/apps/demo/secure/secure2.jsf">Secure Page Test #2</link>
    </panel>
  </column>

  <column id="3">
    <panel id="links" title="Links" type="simple">
      <text style="font-style:italic;">Sites</text>
      <link class="l2" href="http://www.alistapart.com/">A List Apart</link>
      <link class="l2" href="http://slashdot.org/">Slashdot</link>
      <link class="l2" href="http://www.reddit.com/">reddit</link>

      <text style="font-style:italic; margin-top:12px;">Podcasts</text>
      <link class="l2" href="http://thisdeveloperslife.com/">This Developer''s Life</link>
      <link class="l2" href="http://5by5.tv/bigwebshow">The Big Web Show</link>
      <link class="l2" href="http://www.startupsfortherestofus.com/">Startups For the Rest of Us</link>
    </panel>
  </column>

  <column id="3">
    <panel id="pageTests" title="Page Tests" type="simple">
      <link href="/apps/demo/multiPageEx1.jsf">Multi-page Example-1</link>
      <!--link href="/apps/demo/embeddedPage.jsf">Embedded</link-->
      <!--link href="/apps/demo/nestedPageEx1.jsf">Nested Page</link-->
    </panel>
  </column>

</dashboard>
');


INSERT INTO testy_schema.TEXT_ENVELOPE VALUES (1002, '/jcat/devRes/', 'basic-panels.xml',
'<?xml version="1.0" encoding="utf-8"?>
<panels>
  <!-- Basic Panels -->
  <dynamicPanel>
    <title>We Try Our Hand at Making Maple Syrup</title>
    <footer>This panel is broken on resize</footer>
    <contents>
      On the hillside behind our house was a small building.
      I don''t know what its original purpose was.
      One Spring when I was a freshman in high school a group of us got permission to use the building for making maple syrup.
      We moved an old  sterilizer from the dairy into the building.
      We tapped Maple trees located on the farm by boring a hole in each tree,  inserting  a special
      spigot into each hole, and attaching  a  clean  can so that it would hang just below the spigot.
      From the tree came a thin slow drip of sap dropping into each can.
      Each morning before school, a couple of us would collect the Maple sap.
      We dumped this into the sterilizer.
      ${break}${break}
      A steady fire was maintained under the liquid until it was cooked down into syrup.
      It was a long drawn-out process because we kept adding fresh sap each morning.
      Another task was to saw and split wood to burn under the sterilizer.
      We had access to some oak and birch trees which we chopped down.
      Then we used a "buck" saw to cut them up into manageable lengths to haul them  to the syrup house.
      It was a rather  primitive  method  of  syrup  making;  but it worked.
      We made many gallons of good maple syrup over a period of two or three years.
      ${break}${break}
      One day when I got home from school  I learned that our syrup house had burned to the ground.
      We decided that the bottom had burned out of the old sterilizer and the fire had dropped to the
      floor and soon spread to the entire building.  That ended our syrup business.
      ${break}${break}
      - (c) Edward W. Norman - Autobiography
    </contents>
  </dynamicPanel>
</panels>
');


INSERT INTO testy_schema.TEXT_ENVELOPE VALUES (1003, '/jcat/devRes/', 'charts.xml',
'<?xml version="1.0" encoding="utf-8"?>
<properties>
    <!-- Charts -->
    <chart id="1">
      <title>Linear Chart</title>
      <collection>
        <series label="Series 1" markerStyle="">2,1, 3, 6, 8</series>
        <series label="Series 2" markerStyle="diamond">6, 3, 2, 7, 9</series>
      </collection>
    </chart>

    <chart id="2">
      <title>Pie Chart</title>
      <map>
        <entry key="Brand 1">540</entry>
        <entry key="Brand 2">325</entry>
        <entry key="Brand 3">702</entry>
        <entry key="Brand 4">421</entry>
      </map>
    </chart>

    <chart id="3">
      <title>Sample Bubble Chart</title>
      <bubbles>
        <bubble x="70" y="183" radius="55">Acura</bubble>
        <bubble x="45" y="92" radius="36">Alfa Romeo</bubble>
        <bubble x="24" y="104" radius="40">AM General</bubble>
        <bubble x="50" y="123" radius="60">Bugatti</bubble>
        <bubble x="15" y="89" radius="25">BMW</bubble>
        <bubble x="40" y="180" radius="80">Audi</bubble>
        <bubble x="70" y="70" radius="48">Aston Martin</bubble>
      </bubbles>
    </chart>

    <chart id="4">
      <title>Sample Meter Gauge</title>
      <series>20, 50, 120, 220</series>
    </chart>
</properties>
');


INSERT INTO testy_schema.TEXT_ENVELOPE VALUES (1004, '/jcat/devRes/', 'feeds.xml',
'<?xml version="1.0" encoding="utf-8"?>
<feeds>
  <rss id="1" title="Yahoo News: Internet" titleLink="http://www.yahoo.com/" size="5">http://rss.news.yahoo.com/rss/internet</rss>
  <rss id="2" title="Slashdot" titleLink="http://slashdot.org/" size="5">http://rss.slashdot.org/Slashdot/slashdot/to</rss>
  <rss id="3" title="Android Tablet News" titleLink="http://www.androidtablets.net/" size="5">http://feeds.feedburner.com/androidtablets</rss>
  <rss id="4" title="TheServerSide.com: News" titleLink="http://www.theserverside.com/" size="5" stripTags="true">http://feeds.pheedo.com/techtarget/tsscom/home</rss>
</feeds>
');


INSERT INTO testy_schema.TEXT_ENVELOPE VALUES (1005, '/', 'galleries.xml',
'<?xml version="1.0" encoding="utf-8"?>
<galleries>
  <gallery id="1">
    <series label="gallery 1">IMG_0575.JPG, IMG_0579.JPG, IMG_0594.JPG, IMG_0614.JPG, IMG_0629.JPG,
      IMG_0675.JPG, IMG_0677.JPG, IMG_0681.JPG, IMG_0689.JPG</series>
  </gallery>
</galleries>
');


INSERT INTO testy_schema.TEXT_ENVELOPE VALUES (1006, '/', 'maps.xml',
'<?xml version="1.0" encoding="utf-8"?>
<maps>
  <coordinate id="1" latitude="28.284309" longitude="-80.606775" zoom="19">Cocoa Beach</coordinate>
  <coordinate id="2" latitude="37.78659" longitude="-122.38915" zoom="15">San Francisco</coordinate>
  <coordinate id="3" latitude="37.62" longitude="-122.38" zoom="15">SFO</coordinate>
  <coordinate id="3" latitude="41.866365" longitude="-87.60674" zoom="15">Chicago</coordinate>
</maps>
');


-- Sample Guest Book Messages
INSERT INTO testy_schema.GUEST_BOOK_MESSAGE VALUES (1000, 'Good morning all', 'Ed Hutton', 'Monterey Bay Aquarium', 'Really enjoyed the shark tank.', STR_TO_DATE('07/12/2011', '%c/%d/%Y'));
INSERT INTO testy_schema.GUEST_BOOK_MESSAGE VALUES (1001, 'Greetings from St. Louis', 'Nigel', 'Missouri', 'Thanks for keeping the spirit of the A''s alive!', STR_TO_DATE('01/16/2012', '%c/%d/%Y'));
INSERT INTO testy_schema.GUEST_BOOK_MESSAGE VALUES (1002, 'EARN $1000 AN HOUR ON THE INTERNET!!!!', 'Sir Spamalot', 'ANCHORAGE', 'YOUR CHANCE FOR FINANCIAL FREEDOM VERY EASILY DO NOT PASS THIS BY. ONLY $500, etc.', STR_TO_DATE('01/16/2012', '%c/%d/%Y'));

-- Sample Registered Users (passwords are d1lb3rt, d0gb3rt, w@lly and s@lly).
INSERT INTO testy_schema.USER_DETAILS VALUES (1000, 'Dilbert', '', 'Adams', 'dilbert@thruzero.com', '', '', '(555) 555-5555', null, null);
INSERT INTO testy_schema.REGISTERED_USER VALUES (1000, 'dilbert', 'fb5a4dff834b510cfc40c898794d2246', '', 0, 0, null, 'A', 1000);

INSERT INTO testy_schema.USER_DETAILS VALUES (1001, 'Dogbert', '', 'Adams', 'dogbert@thruzero.com', '', '', '(555) 555-5555', null, null);
INSERT INTO testy_schema.REGISTERED_USER VALUES (1001, 'dogbert', 'c153aa8262a280cc6c6fea2672fd2c36', '', 0, 0, null, 'A', 1001);

INSERT INTO testy_schema.USER_DETAILS VALUES (1002, 'Wally', '', 'Adams', 'wally@thruzero.com', '', '', '(555) 555-5555', null, null);
INSERT INTO testy_schema.REGISTERED_USER VALUES (1002, 'wally', 'cbbafd058b5bab01164e40b95a352435', '', 0, 0, null, 'A', 1002);

INSERT INTO testy_schema.USER_DETAILS VALUES (1003, 'Sally', '', 'Adams', 'sally@thruzero.com', '', '', '(555) 555-5555', null, null);
INSERT INTO testy_schema.REGISTERED_USER VALUES (1003, 'sally', '33d51e033c793b1e1a4bbfa3161da7af', '', 0, 0, null, 'A', 1003);

-- Permissions
INSERT INTO testy_schema.PERMISSION VALUES (101, 'demoSecure2', 'view,edit,create', 'User can view, edit and create items on the "/apps/demo/secure/secure2.jsf" page.');
INSERT INTO testy_schema.PERMISSION VALUES (102, 'demoSecure2', 'view', 'User can only view items on the "/apps/demo/secure/secure2.jsf" page.');

-- Grants
INSERT INTO testy_schema.REGISTERED_USER_PERMISSION_ASSOC VALUES (1001, 101); -- dogbert gets 'view,edit,create'
INSERT INTO testy_schema.REGISTERED_USER_PERMISSION_ASSOC VALUES (1002, 102); -- wally gets 'view'
INSERT INTO testy_schema.REGISTERED_USER_PERMISSION_ASSOC VALUES (1003, 102); -- sally gets 'view'

commit;
