package com.clwater.zhiji.database;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Created by yszsyf on 17/3/18.
 */

@Table("Diary")
public class BeanDiary implements Serializable {
    public BeanDiary(String title , String text, String changeDate) {
        this.title = title;
        this.text = text;
        this.changeDate = changeDate;

    }

    public BeanDiary(){}

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    @Column("title")
    private String title;

    @Column("changeDate")
    private String changeDate;

    @Column("text")
    private String text;


    public int getId() {
        return id;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
