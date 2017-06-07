package com.clwater.zhiji.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clwater.zhiji.R;
import com.clwater.zhiji.database.BaseControl;
import com.clwater.zhiji.database.BeanCalendar;
import com.clwater.zhiji.ui.activity.CalendarListActivity;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.haibin.calendarview.OnDateChangeListener;
import com.haibin.calendarview.OnDateSelectedListener;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yszsyf on 17/2/26.
 */

public class CalendarFragment extends Fragment {


    TextView mTextMonthDay;

    TextView mTextYear;

    TextView mTextLunar;

    TextView mTextCurrentDay;

    CalendarView mCalendarView;

    RelativeLayout mRelativeTool;
    private int mYear;

    List<BeanCalendar> _list = new ArrayList<BeanCalendar>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this , view);


        getDate();


        mTextMonthDay = (TextView) view.findViewById(R.id.tv_month_day);
        mTextYear = (TextView) view.findViewById(R.id.tv_year);
        mTextLunar = (TextView) view.findViewById(R.id.tv_lunar);
        mRelativeTool = (RelativeLayout) view.findViewById(R.id.rl_tool);
        mCalendarView = (CalendarView) view.findViewById(R.id.calendarView);
        mTextCurrentDay = (TextView) view.findViewById(R.id.tv_current_day);
        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.showSelectLayout(mYear);
                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });
        view.findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });

//        mCalendarView.showSelectLayout(2017);

        mCalendarView.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, String lunar, String scheme) {
                ShowCalendarDate(String.valueOf(year) , String.valueOf(month) ,String.valueOf(day) , String.valueOf(lunar));

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

                Log.d("LAL" , "" + year + month + day);


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

        List<Calendar> schemes = new ArrayList<>();

        for (int i = 0 ; i < _list.size() ; i++){
            BeanCalendar b = _list.get(i);


            Calendar calendar = new Calendar();
            calendar.setYear(Integer.valueOf(b.getYear()));
            calendar.setMonth(Integer.valueOf(b.getMouth()));
            calendar.setDay(Integer.valueOf(b.getDay()));
            schemes.add(calendar);
        }



        mCalendarView.setSchemeDate(schemes);

        return view;
    }

    private void ShowCalendarDate(String year, String month, String day , String lunar) {
        Intent intent = new Intent(getActivity() , CalendarListActivity.class);
        intent.putExtra("year" , year);
        intent.putExtra("month" , month);
        intent.putExtra("day" , day);
        intent.putExtra("lunar" , lunar);
        startActivity(intent);
    }

    private void getDate() {
        LiteOrm liteOrm = new BaseControl().Initialize(getActivity());
        QueryBuilder qb = new QueryBuilder(BeanCalendar.class);
//        QueryBuilder qb = new QueryBuilder(BeanCalendar.class)
//                .appendOrderDescBy("id");
        _list = liteOrm.query(qb);
    }


}
