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

import com.thruzero.domain.store.AbstractPersistent;

/**
 * Represents the data collected by the inputs page (which tests the various input types).
 *
 * @author George Norman
 */
public class InputsModel extends AbstractPersistent {
  private static final long serialVersionUID = 1L;

  private String title;
  private String slider1;
  private String choice1;

  private String checkbox1a;
  private String checkbox2a;
  private String checkbox3a;
  private String checkbox4a;

  private String number;
  private String numberPattern;
  private String telephone;
  private String time;
  private String date;
  private String month;
  private String datetime;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSlider1() {
    return slider1;
  }

  public void setSlider1(String slider1) {
    this.slider1 = slider1;
  }

  public String getChoice1() {
    return choice1;
  }

  public void setChoice1(String choice1) {
    this.choice1 = choice1;
  }

  public String getCheckbox1a() {
    return checkbox1a;
  }

  public void setCheckbox1a(String checkbox1a) {
    this.checkbox1a = checkbox1a;
  }

  public String getCheckbox2a() {
    return checkbox2a;
  }

  public void setCheckbox2a(String checkbox2a) {
    this.checkbox2a = checkbox2a;
  }

  public String getCheckbox3a() {
    return checkbox3a;
  }

  public void setCheckbox3a(String checkbox3a) {
    this.checkbox3a = checkbox3a;
  }

  public String getCheckbox4a() {
    return checkbox4a;
  }

  public void setCheckbox4a(String checkbox4a) {
    this.checkbox4a = checkbox4a;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getNumberPattern() {
    return numberPattern;
  }

  public void setNumberPattern(String numberPattern) {
    this.numberPattern = numberPattern;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public String getDatetime() {
    return datetime;
  }

  public void setDatetime(String datetime) {
    this.datetime = datetime;
  }

  public String getData() {
    return this.toString();
  }

  @Override
  public String toString() {
    return "<span style=\"font-weight:bold; font-size:larger;\">TextHelper</span> [\n  title=" + title + ",\n  slider1=" + slider1 + ",\n  choice1=" + choice1 + ",\n  checkbox1a=" + checkbox1a + ",\n  checkbox2a=" + checkbox2a + ",\n  checkbox3a=" + checkbox3a
        + ",\n  checkbox4a=" + checkbox4a + ",\n  number=" + number + ",\n  numberPattern=" + numberPattern + "\n  telephone=" + telephone + ",\n  time=" + time + ",\n  date=" + date + ",\n  month=" + month
        + ",\n  datetime=" + datetime + "\n]";
  }

}
