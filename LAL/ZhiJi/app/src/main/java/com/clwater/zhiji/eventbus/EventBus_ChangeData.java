package com.clwater.zhiji.eventbus;

/**
 *  .
 */

public class EventBus_ChangeData {
    public String year , mouth ,day;

    public EventBus_ChangeData(String year , String mouth , String day){
        this.year = year;
        this.mouth = mouth;
        this.day = day;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMouth() {
        return mouth;
    }

    public void setMouth(String mouth) {
        this.mouth = mouth;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
