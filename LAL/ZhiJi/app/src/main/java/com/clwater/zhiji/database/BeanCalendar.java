package com.clwater.zhiji.database;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Created by yszsyf on 17/3/18.
 */

@Table("Calendar")
public class BeanCalendar implements Serializable {
    public BeanCalendar(String text, String year , String mouth , String day) {
        this.text = text;
        this.year = year;
        this.mouth = mouth;
        this.day = day;
    }

    public BeanCalendar(){

    }

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    @Column("year")
    private String year;

    @Column("mouth")
    private String mouth;

    @Column("day")
    private String day;


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
