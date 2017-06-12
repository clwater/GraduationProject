package com.clwater.zhiji.database;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Created by yszsyf on 17/3/18.
 */

@Table("Schedule")
public class BeanSchedule implements Serializable {
    public String getTixing() {
        return tixing;
    }

    public void setTixing(String tixing) {
        this.tixing = tixing;
    }

    public BeanSchedule(String text, String year , String mouth , String day , String hour , String min , String tixing) {
        this.text = text;
        this.year = year;
        this.mouth = mouth;
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.tixing = tixing;

    }

    public BeanSchedule(){

    }

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    @Column("year")
    private String year;

    @Column("mouth")
    private String mouth;

    @Column("day")
    private String day;

    @Column("hour")
    private String hour;

    @Column("min")
    private String min;

    @Column("tixing")
    private String tixing;

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    @Column("text")
    private String text;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
