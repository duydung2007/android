package com.example.bai20_datetime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JobInWeek {
    private String title;
    private String description;
    Date dateFinish;
    Date hourFinish;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDateFinish() {
        return dateFinish;
    }
    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }
    public Date getHourFinish() {
        return hourFinish;
    }
    public void setHourFinish(Date hourFinish) {
        this.hourFinish = hourFinish;
    }
    public JobInWeek(String title, String description, Date dateFinish,
            Date hourFinish) {
        super();
        this.title = title;
        this.description = description;
        this.dateFinish = dateFinish;
        this.hourFinish = hourFinish;
    }
    public JobInWeek() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public String getDateFormat(Date d)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(d);
    }
    
    public String getHourFormat(Date d)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return sdf.format(d);
    }
    
    @Override
    public String toString() {
    return this.title + " - " + getDateFormat(this.dateFinish) + " - " + getHourFormat(this.hourFinish);
    }
}
