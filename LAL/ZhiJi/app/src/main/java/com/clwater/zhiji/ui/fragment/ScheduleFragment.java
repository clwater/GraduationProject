package com.clwater.zhiji.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clwater.zhiji.R;
import com.clwater.zhiji.database.BaseControl;
import com.clwater.zhiji.database.BeanDiary;
import com.clwater.zhiji.database.BeanSchedule;
import com.clwater.zhiji.eventbus.e_back;
import com.clwater.zhiji.eventbus.e_front;
import com.clwater.zhiji.ui.adapter.DiaryAdapter;
import com.clwater.zhiji.ui.adapter.DividerItemDecoration;
import com.clwater.zhiji.ui.adapter.ScheduleAdapter;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.loonggg.lib.alarmmanager.clock.AlarmManagerUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yszsyf on 17/2/26.
 */

public class ScheduleFragment extends Fragment {

    @BindView(R.id.recyclerView_schedule_list) RecyclerView recyclerView_schedule_list;

    private List<BeanSchedule> _list = new ArrayList<BeanSchedule>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.bind(this , view);
        EventBus.getDefault().register(this);

        getDate();
        init();

//        AlarmManagerUtil.setAlarm(this , 0 , 23 , 00 , 1001 , 0 , "text" , 2);


        return view;
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
    }

    private void init() {
        initList();
    }

    private void initList() {
        recyclerView_schedule_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        ScheduleAdapter noteAdapter = new ScheduleAdapter(getActivity() , _list);
        recyclerView_schedule_list.setAdapter(noteAdapter);
        recyclerView_schedule_list.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void getDate() {
        LiteOrm liteOrm = new BaseControl().Initialize(getActivity());
        QueryBuilder qb = new QueryBuilder(BeanSchedule.class).appendOrderDescBy("year").appendOrderDescBy("mouth")
                .appendOrderDescBy("day").appendOrderDescBy("hour")
                .appendOrderDescBy("min");
        _list = liteOrm.query(qb);

    }



}