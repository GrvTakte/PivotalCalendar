package com.pivotal.event.calendar;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author gaurav
 */

public class PivCalendarModel {
    
    private int id;
    private Date eventDate;
    private String eventName;
    private String eventDescription;
    private String month;
    private String days;
    private ArrayList<String[]> eventData = new ArrayList<>();
    
    public PivCalendarModel(){
        
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int Id){
        this.id = Id;
    }
    
    public Date getEventDate(){
        return eventDate;
    }
    
    public void setEventDate(Date EventDate){
        this.eventDate = EventDate;
    }
    
    public String getEventName(){
        return eventName;
    }
    
    public void setEventName(String EventName){
        this.eventName = EventName;
    }
    
    public String getEventDescription(){
        return eventDescription;
    }
    
    public void setEventDescription(String EventDescription){
        this.eventDescription = EventDescription;
    }
    
    public String getMonth(){
        return month;
    }
    
    public void setMonth(String Month){
        this.month = Month;
    }
    
    public String getDays(){
        return days;
    }
    
    public void setDays(String Days){
        this.days = Days;
    }
    
    public ArrayList getEventData(){
        return eventData;
    }
    
    public void setEventData(ArrayList<String[]> EventData){
        this.eventData = EventData;
    }
}
