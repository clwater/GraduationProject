package com.clwater.zhiji.database;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Created by yszsyf on 17/3/18.
 */

@Table("Note")
public class BeanNote implements Serializable {
    public BeanNote(String text, String changeDate) {
        this.text = text;
        this.changeDate = changeDate;

    }

    public BeanNote(){

    }

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

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


}
