package com.clwater.zhiji.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import com.clwater.zhiji.R;
import com.clwater.zhiji.database.BeanCalendar;
import com.clwater.zhiji.database.BeanNote;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarAddActivity  extends AppCompatActivity {

    @BindView(R.id.editText_addCalendar_text) MaterialEditText editText_addCalendar_text;
    @BindView(R.id.textview_addC_time) TextView textview_addC_time;

    private BeanCalendar beanc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendaradd);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        getBaseIntent();
        init();
    }

    private void init() {
        editText_addCalendar_text.setText(beanc.getText());
        textview_addC_time.setText(beanc.getYear() +"年"+ beanc.getMouth()+"月"+ beanc.getDay()+"日");
    }

    private void getBaseIntent() {

        Intent intent = this.getIntent();
        beanc = (BeanCalendar) intent.getSerializableExtra("class");
    }


}
