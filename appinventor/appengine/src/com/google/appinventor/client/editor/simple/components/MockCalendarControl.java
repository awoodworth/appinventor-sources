// Author: Adam Woodworth (adamwoodworth94@siu.edu)

package com.google.appinventor.client.editor.simple.components;

import java.util.Date;

import com.google.appinventor.client.editor.simple.SimpleEditor;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;


/**
 * Mock Calendar component.
 *
 */
public final class MockCalendarControl extends MockVisibleComponent {

  public static final String TYPE = "CalendarControl";
  
  
//  private FlowPanel calendarWidget;
//  private final FlexTable calendarWidget;
  private Button leftArrowButton, monthNameButton, rightArrowButton;
  private Button[] weekdayButtons = new Button[7];
  private Button[] buttons = new Button[42];
  private Button button1;
//  private ArrayList<InlineLabel> dividers = new ArrayList<InlineLabel>();
//  private ArrayList<Label> horizontalDividers = new ArrayList<Label>();
  private String [] weekdays = {"S", "M", "T", "W", "T", "F", "S"};
  
  private Date today = new Date();
  private DateTimeFormat monthYear = DateTimeFormat.getFormat("MMMM yyyy");

  /**
   * Creates a new MockCalendarControl component.
   *
   * @param editor editor of source file the component belongs to
   */
  public MockCalendarControl(SimpleEditor editor) {
    super(editor, TYPE, images.calendar());

//    FlowPanel calendarWidget = new FlowPanel();
//    calendarWidget = new FlexTable();
//    calendarWidget.setSize(ComponentConstants.LISTVIEW_PREFERRED_WIDTH + "px", "100%");
//    calendarWidget.setStylePrimaryName("ode-SimpleMockComponent");
//    calendarWidget.setBorderWidth(0);
//    calendarWidget.setCellPadding(0);
//    calendarWidget.setCellSpacing(0);
    
    button1 = new Button();
    button1.setStyleName("ode-SimpleMockButton");
    DeckPanel calendarWidget1 = new DeckPanel();
    calendarWidget1.setStylePrimaryName("ode-SimpleMockComponent");
    calendarWidget1.add(button1);
    calendarWidget1.showWidget(0);
    
    Button button2 = new Button();
    button2.setStyleName("ode-SimpleMockButton");
    DeckPanel calendarWidget2 = new DeckPanel();
    calendarWidget2.setStylePrimaryName("ode-SimpleMockComponent");
    calendarWidget2.add(button2);
    calendarWidget2.showWidget(0);

    // setup header buttons
    headerSetup();
    
    // setup weekdays
    weekdaySetup();
    
    // add day buttons to panel
    Date date = (Date) today.clone();
    date.setDate(1);
    int dayOfWeek = date.getDay();
    int nextMonth = date.getMonth() + 1;
    date.setMonth(nextMonth);
    date.setDate(-1);
    int lastDay = date.getDate();
    
    
    
    
    
    for(int i = 0; i <= 41; i++) {
      Button button = new Button();
      button.addStyleName("ode-SimpleMockButton");
      if((i + 1 - dayOfWeek) > 0 && (i + 1 - dayOfWeek) <= lastDay + 1){
        button.setText(Integer.toString(i+1 - dayOfWeek));
      }
//      button.setWidth("13.2%");
//      button.setHeight("12%");
      button.setWidth("100%");
      button.setHeight("100%");
      
      buttons[i] = button;
//      calendarWidget.add(button);
//      int row = 0;
//      if (i < 7) {
//        row = 2;
//      } else if (i < 14) {
//        row = 3;
//      } else if (i < 21) {
//        row = 4;
//      } else if (i < 28) {
//        row = 5;
//      } else if (i < 35) {
//        row = 6;
//      } else {
//        row = 7;
//      }
//      calendarWidget.setWidget(row, i % 7, button);
      button.setVisible(true);
//      calendarWidget.add(button);
      if(i == 0 || (i + 1) % 7 != 0){
//        makeDivider();
      } else {
        /*Label label = new Label();
        label.setWidth("0px");
        calendarWidget.add(label);*/
//        makeHorizontalDivider();
      }
    }
    
    
    
    
    setFontItalicProperty("true");
    
//    leftArrowButton.getElement().getStyle().setProperty("padding", "0px");
//    monthNameButton.getElement().getStyle().setProperty("padding", "0px");
//    rightArrowButton.getElement().getStyle().setProperty("padding", "0px");
//    for(Button button : weekdayButtons) {
//      button.getElement().getStyle().setProperty("padding", "0px");
//    }
//    for(Button button : buttons) {
//      button.getElement().getStyle().setProperty("padding", "0px");
//    }

    initComponent(calendarWidget1);
//    initComponent(calendarWidget2);
  }


  private void headerSetup(){
    leftArrowButton = new Button();
    leftArrowButton.setText("<");
//    leftArrowButton.setWidth("15%");
//    leftArrowButton.setHeight("14%");
    leftArrowButton.setWidth("100%");
    leftArrowButton.setHeight("100%");
    
    monthNameButton = new Button();
    monthNameButton.setText(monthYear.format(today));
//    monthNameButton.setWidth("70%");
//    monthNameButton.setHeight("14%");
    monthNameButton.setWidth("100%");
    monthNameButton.setHeight("100%");

    rightArrowButton = new Button();
    rightArrowButton.setText(">");
//    rightArrowButton.setWidth("15%");
//    rightArrowButton.setHeight("14%");
    rightArrowButton.setWidth("100%");
    rightArrowButton.setHeight("100%");

//    calendarWidget.add(leftArrowButton);
//    calendarWidget.add(monthNameButton);
//    calendarWidget.add(rightArrowButton);
//    calendarWidget.setWidget(0, 0, leftArrowButton);
//    calendarWidget.setWidget(0, 1, monthNameButton);
//    calendarWidget.setWidget(0, 2, rightArrowButton);
//    
//    calendarWidget.getFlexCellFormatter().setColSpan(0, 1, 5);
//    calendarWidget.getFlexCellFormatter().setHeight(0, 0, "12%");
//    calendarWidget.getFlexCellFormatter().setHeight(0, 1, "12%");
//    calendarWidget.getFlexCellFormatter().setHeight(0, 2, "12%");
  }

  private void weekdaySetup(){
    for(int i = 0; i < 7; i++){
      Button day = new Button();
      day.addStyleName("ode-SimpleMockButton");
      day.setText(weekdays[i]);
//      day.setWidth("13.2%");
//      day.setHeight("8%");
      day.setWidth("100%");
      day.setHeight("100%");
//      day.setEnabled(false);
//      calendarWidget.add(day);
      weekdayButtons[i] = day;
//      calendarWidget.setWidget(1, i, day);
//      calendarWidget.getFlexCellFormatter().setHeight(1, i, "9%");
//      calendarWidget.getFlexCellFormatter().setWidth(1, i, 100.0/7+"%");
  
      if(i < 6){
//    	makeDivider();
      } else {
//	      Label label = new Label();
//	      label.setWidth("0px");
//	      calendarWidget.add(label);
//        makeHorizontalDivider();
      }
    }
  }

//  private void makeHorizontalDivider(){
//    Label divider = new Label();
//    divider.setWidth("100%");
//    divider.setHeight("1px");
//    divider.setStylePrimaryName("ode-SimpleMockComponent");
//    horizontalDividers.add(divider);
//    calendarWidget.add(divider);
//  }

//  private void makeDivider(){
//    InlineLabel divider = new InlineLabel();
//    divider.setStylePrimaryName("ode-SimpleMockComponent");
//    divider.setWidth("0.0005%");
//    divider.setHeight("100%");
//    dividers.add(divider);
//    calendarWidget.add(divider);
//  }
  
  
  
  
  
  
  /*
   * Sets the labels' BackgroundColor property to a new value.
   */
  private void setBackgroundColorProperty(String text) {
    if (MockComponentsUtil.isDefaultColor(text)) {
      text = "&HFFFFFFFF";  // white
    }
    String textBlack = "&HFF000000"; // black
//    for(InlineLabel label : dividers){
//      MockComponentsUtil.setWidgetBackgroundColor(label, textBlack);
//    }
//    for(Label label : horizontalDividers){
//    	MockComponentsUtil.setWidgetBackgroundColor(label, textBlack);
//    }
    for(Button button : buttons){
      MockComponentsUtil.setWidgetBackgroundColor(button, text);
    }
  }
  
//  /*
//   * Sets the labels' TextColor property to a new value.
//   */
//  private void setTextColorProperty(String text) {
//    if (MockComponentsUtil.isDefaultColor(text)) {
//      text = "&HFFFFFFFF";  // white
//    }
//    for(Button button : buttons){
//      MockComponentsUtil.setWidgetTextColor(button, text);
//    }
//  }


  /*
   * Sets the button's Enabled property to a new value.
   */
  private void setEnabledProperty(String text) {
    MockComponentsUtil.setEnabled(this, text);
  }
  
  /*
   * Sets the label's FontSize property to a new value.
   */
  private void setFontSizeProperty(String text) {
    MockComponentsUtil.setWidgetFontSize(monthNameButton, text);
  }
  
  /*
   * Sets the button's FontItalic property to a new value.
   */
  private void setFontItalicProperty(String text) {
    Date tempDate = (Date) today.clone();
	tempDate.setDate(1);
    int todayButtonIndex = tempDate.getDay() + today.getDate() - 1;
    for(int i = 0; i < 41; i++){
      if(i == todayButtonIndex){
        MockComponentsUtil.setWidgetFontItalic(buttons[i], text);
      }
    }
  }

  @Override
  public int getPreferredWidth() {
    return ComponentConstants.LISTVIEW_PREFERRED_WIDTH;
  }

  @Override
  public int getPreferredHeight() {
    return ComponentConstants.LISTVIEW_PREFERRED_HEIGHT;
  }


  // override the width and height hints, so that automatic will in fact be fill-parent
  @Override
  int getWidthHint() {
    int widthHint = super.getWidthHint();
    if (widthHint == LENGTH_PREFERRED) {
      widthHint = LENGTH_FILL_PARENT;
    }
    return widthHint;
  }

  @Override 
  int getHeightHint() {
    int heightHint = super.getHeightHint();
    if (heightHint == LENGTH_PREFERRED) {
      heightHint = LENGTH_FILL_PARENT;
    }
    return heightHint;
  }
  
  
  //PropertyChangeListener implementation
  @Override
  public void onPropertyChange(String propertyName, String newValue) {
    super.onPropertyChange(propertyName, newValue);

    // Apply changed properties to the mock component
    if (propertyName.equals(PROPERTY_NAME_TEXTALIGNMENT)) {
//     setTextAlignmentProperty(newValue);
    } else if (propertyName.equals(PROPERTY_NAME_BACKGROUNDCOLOR)) {
      setBackgroundColorProperty(newValue);
      refreshForm();
    } else if (propertyName.equals(PROPERTY_NAME_ENABLED)) {
      setEnabledProperty(newValue);
    } else if (propertyName.equals(PROPERTY_NAME_FONTBOLD)) {
//      setFontBoldProperty(newValue);
      refreshForm();
    } else if (propertyName.equals(PROPERTY_NAME_FONTITALIC)) {
      setFontItalicProperty(newValue);
      refreshForm();
    } else if (propertyName.equals(PROPERTY_NAME_FONTSIZE)) {
      setFontSizeProperty(newValue);
      refreshForm();
    } else if (propertyName.equals(PROPERTY_NAME_FONTTYPEFACE)) {
//     setFontTypefaceProperty(newValue);
      refreshForm();
    } else if (propertyName.equals(PROPERTY_NAME_TEXT)) {
//     setTextProperty(newValue);
      refreshForm();
    } else if (propertyName.equals(PROPERTY_NAME_TEXTCOLOR)) {
//      setTextColorProperty(newValue);
    }
  }
}
