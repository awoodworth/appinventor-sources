// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2014 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.runtime;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Handler;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.Dates;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.YailList;

import android.app.DatePickerDialog;
import android.view.View;

/**
 * Calendar
 */
@DesignerComponent(version = 1,
    category = ComponentCategory.USERINTERFACE,
    description = "<p>A calendar, hopefully</p>")
@SimpleObject
public class CalendarControl extends AndroidViewComponent {

  private DatePickerDialog date;
  //month is the property that AI devs see, and it's always javaMonth + 1; month is 0-based in Java
  private int year, month, javaMonth, day;
  private Calendar instant;
  private String [] localizedMonths = new DateFormatSymbols().getMonths();
  private Form form;
  private final android.widget.Button view;
  private int backgroundColor;
  private int backgroundColorPressed;
  private boolean customDate;
  private Handler androidUIHandler;


  /**
   * Creates a new DatePicker component.
   * @param container the container in which the component will be placed in.
   */
  public CalendarControl(ComponentContainer container) {
    super(container);
    view = new android.widget.Button(container.$context());
    form = container.$form();

    FontSize(20);
    BackgroundColor(Component.COLOR_WHITE);
    Width(LENGTH_FILL_PARENT);
    final Calendar c = Calendar.getInstance();
    year = c.get(Calendar.YEAR);
    javaMonth = c.get(Calendar.MONTH);
    month = javaMonth + 1;
    day = c.get(Calendar.DAY_OF_MONTH);
    instant = Dates.DateInstant(year, month, day);
    TextViewUtil.setFontTypeface(view, Component.TYPEFACE_DEFAULT, false, true);
//    androidUIHandler = new Handler();
  }

  /**
   * Returns the year that is displayed using the CalendarControl.
   * @return the year in numeric format
   */
  @SimpleProperty(description = "the year that is displayed using the CalendarControl",
      category = PropertyCategory.APPEARANCE)
  public int Year() {
    return year;
  }

  /**
   * Returns the number of the month that is displayed using the CalendarControl.
   * @return the month in numeric format
   */
  @SimpleProperty(description = "the number of the month that is displayed using the " +
      "CalendarControl. Note that months start in 1 = January, 12 = December.",
      category = PropertyCategory.APPEARANCE)
  public int Month() {
    return month;
  }

  /**
   * Returns the name of the month that is displayed using the CalendarControl.
   * @return the month in textual format.
   */
  @SimpleProperty(description = "Returns the name of the month that is displayed using the " +
      "CalendarContol, in textual format.",
      category = PropertyCategory.APPEARANCE)
  public String MonthInText() {
    return localizedMonths[javaMonth];
  }

  /**
   * Returns the day of the month that was last picked using the CalendarControl.
   * @return the day in numeric format
   */
  @SimpleProperty(description = "the day of the month that was last picked using the CalendarControl.",
    category = PropertyCategory.APPEARANCE)
  public int Day() {
    return day;
  }

  /**
   * Returns instant of the date that was last picked using the CalendarControl.
   * @return instant of the date
   */
  @SimpleProperty(description = "the instant of the date that was last picked using the CalendarControl.",
    category = PropertyCategory.APPEARANCE)
  public Calendar Instant() {
    return instant;
  }

  @SimpleFunction(description = "Allows the user to set the date to be displayed when the date picker opens.\n" +
    "Valid values for the month field are 1-12 and 1-31 for the day field.\n")
  public void SetDateToDisplay(int year, int month, int day) {
    int jMonth = month - 1;
    try {
      GregorianCalendar cal = new GregorianCalendar(year, jMonth, day);
      cal.setLenient(false);
      cal.getTime();
    } catch (java.lang.IllegalArgumentException e) {
      form.dispatchErrorOccurredEvent(this, "SetDateToDisplay", ErrorMessages.ERROR_ILLEGAL_DATE);
    }
    date.updateDate(year, jMonth, day);
    instant = Dates.DateInstant(year, month, day);
    customDate = true;
  }

  @SimpleFunction(description = "Allows the user to set the date from the instant to be displayed when the date picker opens.")
  public void SetDateToDisplayFromInstant(Calendar instant) {
    int year = Dates.Year(instant);
    int month = Dates.Month(instant);
    int day = Dates.Day(instant);
    date.updateDate(year, month, day);
    instant = Dates.DateInstant(year, month, day);
    customDate = true;
  }

  @SimpleFunction(description = "Allows the user to set an array of dates that have events on the calendar. Date format: YYYY-MM-DD")
  public void SetDaysWithEvents(YailList events) {
    //TODO: check that dates are valid and set to array that's checked in the view to bold dates.
  }
  
  
  //TODO:
  //day click
  //month name click
  
  public void onClick(View view) {
//    form.dispatchErrorOccurredEvent(this, this.toString(), 1);
    click();
  }
  
  public void click() {
    Click();
  }
  
  @SimpleEvent(description = "User tapped and released the button.")
  public void Click() {
    EventDispatcher.dispatchEvent(this, "Click");
  }
  
  private void previousMonth() {
    month -= 1;
    int jMonth = month - 1;
    date.updateDate(year, jMonth, day);
    instant = Dates.DateInstant(year, month, day);
    // update view?
  }
  
  private void nextMonth() {
    month += 1;
    int jMonth = month - 1;
    date.updateDate(year, jMonth, day);
    instant = Dates.DateInstant(year, month, day);
    // update view?
  }
  

  
//  @SimpleFunction(description="Launches the DatePicker popup.")
//  public void LaunchPicker() {
//    click();
//  }
//
//  /**
//   * Overriding method from superclass to show the date picker dialog when the button is clicked
//   */
//  @Override
//  public void click() {
//    if (!customDate) {
//      Calendar c = Calendar.getInstance();
//      int year = c.get(Calendar.YEAR);
//      int jMonth = c.get(Calendar.MONTH);
//      int day = c.get(Calendar.DAY_OF_MONTH);
//      date.updateDate(year, jMonth, day);
//      instant = Dates.DateInstant(year, jMonth+1, day);
//    } else {
//      customDate = false;
//    }
//    date.show();
//  }
//
//  /**
//   * Listener for the Dialog. It will update the fields after selection.
//   */
//  private DatePickerDialog.OnDateSetListener datePickerListener =
//      new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(android.widget.DatePicker datePicker, int selectedYear,
//                              int selectedMonth, int selectedDay) {
//          if (datePicker.isShown()) {
//            year = selectedYear;
//            javaMonth = selectedMonth;
//            month = javaMonth + 1;
//            day = selectedDay;
//            date.updateDate(year, javaMonth, day);
//            instant = Dates.DateInstant(year, month, day);
//            // We post an event to the Android handler to do the App Inventor
//            // event dispatch. This way it gets called outside of the context
//            // of the datepicker's event. This permits the App Inventor dispatch
//            // handler to re-launch this date picker
//            androidUIHandler.post(new Runnable() {
//                public void run() {
//                  AfterDateSet();
//                }
//              });
//          }
//        }
//      };
//
//  /**
//   * Runs when the user sets the date in the Dialog.
//   */
//  @SimpleEvent(description = "Event that runs after the user chooses a Date in the dialog")
//  public void AfterDateSet() {
//    EventDispatcher.dispatchEvent(this, "AfterDateSet");
//  }
  
  @Override
  public View getView() {
    return view;
  }
  
  /**
   * Specifies the label's text's font size, measured in sp(scale-independent pixels).
   *
   * @param size  font size in sp (scale-independent pixels)
   */
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_FLOAT,
      defaultValue = Component.FONT_DEFAULT_SIZE + "")
  @SimpleProperty
  public void FontSize(float size) {
    TextViewUtil.setFontSize(view, size);
  }
  
  /**
   * Specifies the buttons' background color as an alpha-red-green-blue
   * integer.
   *
   * @param argb  background RGB color with alpha
   */
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR,
      defaultValue = Component.DEFAULT_VALUE_COLOR_WHITE)
  @SimpleProperty
  public void BackgroundColor(int argb) {
    backgroundColor = argb;
    if (argb != Component.COLOR_DEFAULT) {
      TextViewUtil.setBackgroundColor(view, argb);
    } else {
      TextViewUtil.setBackgroundColor(view, Component.COLOR_WHITE);
    }
  }
  
  
  /**
   * Specifies the button background color when it is pressed as an alpha-red-green-blue
   * integer.
   *
   * @param argb  background RGB color with alpha
   */
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR,
      defaultValue = Component.DEFAULT_VALUE_COLOR_LTGRAY)
  @SimpleProperty
  public void BackgroundColorPressed(int argb) {
    backgroundColor = argb;
    if (argb != Component.COLOR_DEFAULT) {
      //TextViewUtil.setBackgroundColor(view, argb);
      backgroundColorPressed = argb;
    } else {
      //TextViewUtil.setBackgroundColor(view, Component.COLOR_LTGRAY);
      backgroundColorPressed = Component.COLOR_LTGRAY;
    }
  }
  
}
