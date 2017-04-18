
package com.pivotal.event.calendar;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.ui.Command;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author gaurav
 */
public class PivCalendarDatabase {
    
    PivCalendarModel ModelObject = new PivCalendarModel();
    
    Cursor cur;
    Database myDataBase;
    ArrayList<String[]> data,data1,datafur;
    int columns;
    Date[] EventDateArray;
    Object[][] arr;
    
    
    
    
    public PivCalendarDatabase(){
      
    }
    
    public void insertEvent(String eventDate, String eventName, String eventDescription){
        try{
       String[] DatabaseArgument = new String[]{eventDate,eventName,eventDescription};
        myDataBase.execute("INSERT INTO CalendarData('Date','EventName','EventDescription') VALUES(?,?,?)",  DatabaseArgument );
        Log.p("Table successfully created");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void createDataBase(){
          
      String path = Display.getInstance().getDatabasePath("Events.db");
        FileSystemStorage fs = FileSystemStorage.getInstance();
          if(!fs.exists(path)) {
                    try (InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/Events.db");
                            OutputStream os = fs.openOutputStream(path)) 
                            {  
                            Util.copy(is, os);
                    } 
                            catch(IOException err) {
                                    Log.e(err);
                            }
          } 
                    try{
                                myDataBase = Display.getInstance().openOrCreate("Events.db");
                                myDataBase.execute("CREATE TABLE IF NOT EXISTS CalendarData (Date date NOT NULL,EventName varchar(255) NOT NULL, EventDescription varchar(255) NOT NULL)");
                    }
                            catch(IOException e){
                                        e.printStackTrace();
                            }
                    Log.p("Database successfully created");
                    ModelObject.setEventName("Hello from calendar");
    }
    
    public void displayEvent(){
        try{
            Form DataEvent = new Form("Event Explorer", new BorderLayout());
            Toolbar Tbar = new Toolbar();
            Command back1 = new Command("Back"){
         public void actionPerformed(ActionEvent ev){
             PivEventCalendar EventObject = new PivEventCalendar();
         EventObject.start();
         }
         };
            DataEvent.setToolBar(Tbar);
            Tbar.addCommandToLeftBar("", FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK,UIManager.getInstance().getComponentStyle("Title")), back1);
          cur = myDataBase.executeQuery("SELECT * FROM CalendarData");
          int columns = cur.getColumnCount();
          if(columns > 0) {
          boolean next = cur.next();
          if(next) {
          ArrayList<String[]> dataArray = new ArrayList<>();
          String[] columnNames = new String[columns];
          for(int iter = 0 ; iter < columns ; iter++) {
          columnNames[iter] = cur.getColumnName(iter);
          }
          while(next) {
          Row currentRow = cur.getRow();
          String[] currentRowArray = new String[columns];
          for(int iter = 0 ; iter < columns ; iter++) {
          currentRowArray[iter] = currentRow.getString(iter);
          }
          dataArray.add(currentRowArray);
          next = cur.next();
          }
          Object[][] arr = new Object[dataArray.size()][];
          dataArray.toArray(arr);
          DataEvent.add(BorderLayout.CENTER, new Table(new DefaultTableModel(columnNames, arr)));
          DataEvent.show();
          } else {
          DataEvent.add(BorderLayout.CENTER, "Query returned no results");
          }
          } else {
          DataEvent.add(BorderLayout.CENTER, "Query returned no results");
          }
          }catch(IOException e){
          e.printStackTrace();
          }
    }
    
    public void checkEvent(){
        try{
            cur = myDataBase.executeQuery("SELECT Date, EventName, EventDescription FROM CalendarData");
            columns = cur.getColumnCount();
            if(columns > 0){
                boolean next = cur.next();
                if(next){
                    data1 = new ArrayList<String[]>();
                    String[] columnNames = new String[columns];
                    for(int iter = 0; iter<columns ; iter++ ){
                        columnNames[iter] = cur.getColumnName(iter);
                     }
       while(next) {
                Row currentRow = cur.getRow();
                String[] currentRowArray = new String[columns];
                for(int iter = 0 ; iter < columns ; iter++) {
                             currentRowArray[iter] = currentRow.getString(iter);
                        }
                data1.add(currentRowArray);
                next = cur.next();
          }
                arr = new Object[data1.size()][];
                data1.toArray(arr);
          }
      }
}               catch(IOException e){
                            e.printStackTrace();
              }
                for(int i = 0 ; i< data1.size(); i++){           
                    for(int j=0;j<columns;j++){
                        Log.p(data1.get(i)[j]);
                    }
                    Log.p("*****************");
          }
                PivEventCalendar EventObject = new PivEventCalendar();
          EventObject.checkEvent(data1,columns);
          Log.p("CheckEvent() invoke");
          
  }
    
   
}
