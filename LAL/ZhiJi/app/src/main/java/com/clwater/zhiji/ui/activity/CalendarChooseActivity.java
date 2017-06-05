package com.clwater.zhiji.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clwater.zhiji.eventbus.EventBus_ChangeData;
import com.clwater.zhiji.R;
import com.haibin.calendarview.CalendarView;
import com.haibin.calendarview.OnDateChangeListener;
import com.haibin.calendarview.OnDateSelectedListener;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by yszsyf on 17/2/26.
 */

public class CalendarChooseActivity extends AppCompatActivity {


    TextView mTextMonthDay;

    TextView mTextYear;

    TextView mTextLunar;

    TextView mTextCurrentDay;

    CalendarView mCalendarView;

    RelativeLayout mRelativeTool;
    private int mYear;


    private boolean pd = false;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendarchoose);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        pd = false;

//        ButterKnife.bind(this );
//        EventBus.getDefault().register(this);




        mTextMonthDay = (TextView) findViewById(R.id.tv_month_day);
        mTextYear = (TextView) findViewById(R.id.tv_year);
        mTextLunar = (TextView) findViewById(R.id.tv_lunar);
        mRelativeTool = (RelativeLayout) findViewById(R.id.rl_tool);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mTextCurrentDay = (TextView) findViewById(R.id.tv_current_day);
        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.showSelectLayout(mYear);
                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });
        findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });

        mCalendarView.showSelectLayout(2017);

        mCalendarView.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, String lunar, String scheme) {

            }
        });
        mCalendarView.setOnDateChangeListener(new OnDateChangeListener() {
            @Override
            public void onDateChange(int year, int month, int day, String lunar, String scheme) {
                mTextLunar.setVisibility(View.VISIBLE);
                mTextYear.setVisibility(View.VISIBLE);
                mTextMonthDay.setText(month + "月" + day + "日");
                mTextYear.setText(String.valueOf(year));
                mTextLunar.setText(lunar);
                mYear = year;


                Log.d("LAL" , "' " + pd + "    " + String.valueOf(year)+  String.valueOf(month)+ String.valueOf(day));

                if (pd) {
                    returnChooseDate(String.valueOf(year), String.valueOf(month), String.valueOf(day));
                }
                pd = true;

            }

            @Override
            public void onYearChange(int year) {
                mTextMonthDay.setText(String.valueOf(year));

            }
        });
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));


    }



    public void returnChooseDate(String year, String month, String day) {
        EventBus_ChangeData e = new EventBus_ChangeData(year , month , day );
        EventBus.getDefault().post(e);
        CalendarChooseActivity.this.finish();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
}

