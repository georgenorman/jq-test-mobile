/*
  ~ Copyright (c) 2012 George Norman.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  ~
 */

function encryptPassword(whichForm) {
  var plainTextPassword = document.getElementById(whichForm+":password");
  var oneTimePw = document.getElementById(whichForm+":oneTimePw");
  var passwordLen = plainTextPassword.value.length;
  oneTimePw.value = hex_md5(oneTimePw.value + hex_md5(plainTextPassword.value));
  plainTextPassword.value = "";

  // don't transmit the unencrypted password
  for (var i=0; i<passwordLen; i++) {
    plainTextPassword.value += "*";
  }

  return true;
}

function getQueryParam( name, defaultVal ) {
  name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");

  var regex = new RegExp( "[\\?&]"+name+"=([^&#]*)" );
  var result = regex.exec( window.location.href );

  if( result == null ) {
    return defaultVal;
  } else {
    return result[1];
  }
}

// TODO-p1(george) This is a hack to get the page loading indicator to show on POST events.
function showPageLoadingIndicator() {
  $.mobile.showPageLoadingMsg();
}
