package com.clwater.zhiji.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.clwater.zhiji.R;
import com.clwater.zhiji.database.BaseControl;
import com.clwater.zhiji.database.BeanCalendar;
import com.clwater.zhiji.eventbus.e_back;
import com.clwater.zhiji.eventbus.e_front;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class CalendarListActivity extends AppCompatActivity {


    @BindView(R.id.tv_month_day) TextView tv_month_day;
    @BindView(R.id.tv_year) TextView tv_year;
    @BindView(R.id.tv_lunar) TextView tv_lunar;
    @BindView(R.id.listview_calendarlist_list) ListView listview_calendarlist_list;


    String _year , _month , _day , _lunar;
    List<BeanCalendar> _list = new ArrayList<BeanCalendar>();
    List<BeanCalendar> _listshow = new ArrayList<BeanCalendar>();
    String[] strs;
    Activity acticity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_calendarlist);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        acticity = this;
        init();
        getDate();
        showDate();

    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new e_back());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void backrun(e_back e){
        try {
            Thread.sleep(1000);
            EventBus.getDefault().post(new e_front());
            Thread.sleep(5000);
            EventBus.getDefault().post(new e_front());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void frontrun(e_front e){
        getDate();
        init();
        showDate();
    }




    private void getDate() {
        LiteOrm liteOrm = new BaseControl().Initialize(this);
        QueryBuilder qb = new QueryBuilder(BeanCalendar.class);
        _list = liteOrm.query(qb);


        Log.d("LAL" , "sadasd" + _year + _month + _day);





        int index = 0;

        for (int i = 0 ; i < _list.size() ; i ++){
            BeanCalendar b = _list.get(i);
//            Log.d("LAL" , "sadaasdsaddssd" + _year + _month + _day);
//            Log.d("LAL" , "sadaasdsaddssd" + b.getYear() + b.getMouth() + b.getDay());

            if ( b.getYear().equals(_year) && b.getMouth().equals(_month) && b.getDay().equals(_day)){
                index++;
            }
        }

        strs = new String[index];

        index = 0 ;
        for (int i = 0 ; i < _list.size() ; i ++){
            BeanCalendar b = _list.get(i);
            if ( b.getYear().equals(_year) && b.getMouth().equals(_month) && b.getDay().equals(_day)){
                _listshow.add(b);
                strs[index] = b.getText();
                index ++;
            }
        }


    }

    public void showDate(){
        listview_calendarlist_list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,strs));






        listview_calendarlist_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent( acticity, CalendarAddActivity.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("class" , _listshow.get(position));
                intent.putExtras(bundle);



                LiteOrm liteOrm = new BaseControl().Initialize(CalendarListActivity.this);
                liteOrm.delete(_listshow.get(position));

                startActivity(intent);

            }
        });
    }

    private void init() {
        getIntentDate();
        initTitle();
    }

    private void getIntentDate() {
        Intent intent = getIntent();

        _year = String.format("%04d", Integer.valueOf(intent.getStringExtra("year")));
        _month = String.format("%02d", Integer.valueOf(intent.getStringExtra("month")));
        _day = String.format("%02d", Integer.valueOf(intent.getStringExtra("day")));

        _lunar = intent.getStringExtra("lunar");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initTitle() {
        tv_month_day.setText(_month + "月" + _day + "日");
        tv_year.setText(_year);
        tv_lunar.setText(_lunar);
    }


}
