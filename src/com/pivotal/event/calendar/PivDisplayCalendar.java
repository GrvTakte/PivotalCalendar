/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotal.event.calendar;

import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import java.util.ArrayList;

/**
 *
 * @author gaurav
 */
public class PivDisplayCalendar extends Container {
    
    
    int length =37;
     private ComboBox year;
     private static final String[] DAYS = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
     private static final String[] LABELS = {"Su", "M", "Tu", "W", "Th", "F", "Sa"};
     
     private ArrayList<Button> allButtons = new ArrayList<Button>();
     //Button newButton = new Button("");
     
    public PivDisplayCalendar(){
        
        super(new BoxLayout(BoxLayout.Y_AXIS));
            Container calendarTitle = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
            Container title = new Container(new GridLayout(1,7));
            Container days = new Container(new GridLayout(6, 7));
            Container calendarTitleCopy = new Container(new GridLayout(1, 1));
                                this.addComponent(calendarTitleCopy);
                                this.addComponent(title);
                                this.addComponent(days);
    
                               
            Button prevMonth = new Button("<");
            Button nextMonth = new Button(">");
            SpanLabel monthYear = new SpanLabel("Month"+"Year");
            calendarTitle.add(BorderLayout.WEST, prevMonth);
            calendarTitle.add(BorderLayout.CENTER, monthYear);
            calendarTitle.add(BorderLayout.EAST, nextMonth);
            calendarTitleCopy.add(calendarTitle);
            Button dayButton= new Button();
            
            
            
            
           if(UIManager.getInstance().isThemeConstant("calTitleDayStyleBool", false)) {
                title.setUIID("CalendarTitleArea");
                days.setUIID("CalendarDayArea");
            }
            for (int iter = 0; iter < DAYS.length; iter++) {
                title.addComponent(createDayTitle(iter));
            }
            for (int iter = 0; iter < length; iter++) {
               dayButton = new Button(""+iter);
               
                    dayButton.addActionListener(new ActionListener() {
                        
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Log.p("Action event triggered");
                            
                            Button b1 = (Button)(evt.getActualComponent());
                            Log.p( b1.getText() );
                        }
                    });
               
               allButtons.add(dayButton);
                
                /*
                Picker datePicker = new Picker();
                datePicker.setText(""+iter);
                */
                //datePicker.setType(Display.PICKER_TYPE_DATE);
               // buttons[iter] = new Button(""+iter);
                days.addComponent(dayButton);
                if (iter <= 7) {
                    dayButton.setNextFocusUp(year);
                }
                
            }   
            
           
                
    }
   
    
     protected Label createDayTitle(int day) {
        String value = getUIManager().localize("Calendar." + DAYS[day], LABELS[day]);
        Label dayh = new Label(value, "CalendarTitle");
        dayh.setEndsWith3Points(false);
        dayh.setTickerEnabled(false);
        return dayh;
    }
}
