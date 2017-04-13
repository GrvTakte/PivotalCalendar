
package com.pivotal.event.calendar;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Calendar;
import com.codename1.ui.Display;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 *
 * @author gaurav
 */
public class PivCalendarDatabase {
    
    Cursor cur;
    Database myDataBase;
    
    public PivCalendarDatabase(){
        
     /*   String path = Display.getInstance().getDatabasePath("Events.db");
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
        */
    }
    
    public void InsertEvent(String eventDate, String eventName, String eventDescription){
        try{
       String[] DatabaseArgument = new String[]{eventDate,eventName,eventDescription};
        myDataBase.execute("INSERT INTO CalendarData('Date','EventName','EventDescription') VALUES(?,?,?)",  DatabaseArgument );
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    public void DisplayEvent(){
        
    }
    
    
    
}
