package com.clwater.zhiji.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clwater.zhiji.R;

import butterknife.ButterKnife;

/**
 * Created by yszsyf on 17/2/26.
 */

public class ScheduleFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.bind(this , view);

        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }



}