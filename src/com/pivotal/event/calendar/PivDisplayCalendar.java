package com.pivotal.event.calendar;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author gaurav
 */
public class PivDisplayCalendar extends Form {
    Cursor cur;
    Database myDataBase;
    PivEventCalendar EventObject = new PivEventCalendar();
    PivCalendarModel ModelObject = new PivCalendarModel();
    PivCalendarDatabase DatabaseObject = new PivCalendarDatabase();
    int length2 =1;
    int length = 31;
    int[] monthStart = {0,3,3,6,1,4,6,2,5,0,3,5};
     private ComboBox year;
     private static final String[] DAYS = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
     private static final String[] LABELS = {"Su", "M", "Tu", "W", "Th", "F", "Sa"};
     private static final String[] MONTHS = {"January","February","March","April","May","June","July","August","September","October","November","December"};
     private static final int[] YEARS = {2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027};
     public int i = 1, j=0;
     public Date value = new Date();
     public Object metaData;
     Button dayButton= new Button();
     private ArrayList<Button> allButtons = new ArrayList<Button>();
     Object[][] DateObject;
     int columns;

   
    public PivDisplayCalendar(){
        super(new BoxLayout(BoxLayout.Y_AXIS));      
            
            Log.p("Calendar invoked");
            Container calendarTitle = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
            Container title = new Container(new GridLayout(1,7));
            Container days = new Container(new GridLayout(6, 7));
            Container calendarTitleCopy = new Container(new GridLayout(1, 1));
            calendarTitleCopy.setUIID("CalendarTitleCopy");
            this.addComponent(calendarTitleCopy);
            this.addComponent(title);
            this.addComponent(days);
            
            
            Button prevMonth = new Button("<");
            Button nextMonth = new Button(">");
            Label month = new Label(MONTHS[0]);
            Label yearLabel = new Label( "2017");
            calendarTitle.add(BorderLayout.WEST, prevMonth);
            Container TitleLabel = FlowLayout.encloseIn(month,yearLabel);
            calendarTitle.add(BorderLayout.CENTER, TitleLabel);
            calendarTitle.add(BorderLayout.EAST, nextMonth);
            calendarTitleCopy.add(calendarTitle);
            
            
            
           if(UIManager.getInstance().isThemeConstant("calTitleDayStyleBool", false)) {
                title.setUIID("CalendarTitleArea");
                days.setUIID("CalendarDayArea");
            }
            for (int iter = 0; iter < DAYS.length; iter++) {
                title.addComponent(createDayTitle(iter));
            }
            for (int iter = 0; iter < length; iter++) {
                if(iter<monthStart[j]){
                    dayButton=new Button("");
                }else{
                        dayButton = new Button(""+ length2);
                        length2++;
                }
              /* Log.p("Getting data from Model class");
               Log.p(""+DatabaseObject.ModelObject.getEventData()); */
                    dayButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Log.p("Action event triggered");
                            Picker datePicker = new Picker();
                           
                            try{
                                Display.getInstance().showNativePicker(Display.PICKER_TYPE_DATE,PivDisplayCalendar.this, value, metaData);
                                Storage.getInstance().writeObject("Date", value);
                                Log.p(value.toString());
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            //Button b1 = (Button)(evt.getActualComponent());
                            //Log.p( b1.getText() );
                        }
                    });
               
               allButtons.add(dayButton);
                days.addComponent(dayButton);
                if (iter <= 7) {
                    dayButton.setNextFocusUp(year);
                }
            }    
            
            nextMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int length3 = 1;
                month.setText(MONTHS[i]);
                yearLabel.setText(""+YEARS[j]);
                if((i<MONTHS.length-1)){
                    i++;
                    int k = 0;
                   
                    if(k<monthStart.length-1){
                        
                        k++;
                        
                        for (int iter = 0; iter < length; iter++) {
                if(iter<monthStart[k]){
                    Log.p("After block invoked");
                    if(contains(days)){
                        days.removeAll();
                    dayButton=new Button("");
                    days.revalidate();
                    }
                }else{
                        dayButton = new Button(""+ length3);
                        length3++;
                        days.revalidate();
                }
              /* Log.p("Getting data from Model class");
               Log.p(""+DatabaseObject.ModelObject.getEventData()); */
                    dayButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Log.p("Action event triggered");
                            Picker datePicker = new Picker();
                           
                            try{
                                Display.getInstance().showNativePicker(Display.PICKER_TYPE_DATE,PivDisplayCalendar.this, value, metaData);
                                Storage.getInstance().writeObject("Date", value);
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            //Button b1 = (Button)(evt.getActualComponent());
                            //Log.p( b1.getText() );
                        }
                    });
               
               allButtons.add(dayButton);
                days.addComponent(dayButton);
                if (iter <= 7) {
                    dayButton.setNextFocusUp(year);
                }
            }  
                      
                    }
                   
                   
                }
                else{
                    i=0;
                    j++;
                }
            }
        });
   
            prevMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            month.setText(MONTHS[i]);
            yearLabel.setText(""+YEARS[j]);
            if((i<MONTHS.length+1)){
                i--;
            }
            else{
                i=0;
                j--;
            }
        }
   });
            
    }
       
     protected Label createDayTitle(int day) {
        String value = getUIManager().localize("Calendar" + DAYS[day], LABELS[day]);
        Label dayh = new Label(value, "Label");
        dayh.setEndsWith3Points(false);
        dayh.setTickerEnabled(false);
        return dayh;
    }    
     
    
}
