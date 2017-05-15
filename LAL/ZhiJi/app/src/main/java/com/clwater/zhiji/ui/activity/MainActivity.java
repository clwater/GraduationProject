package com.clwater.zhiji.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.clwater.zhiji.R;
import com.clwater.zhiji.ui.fragment.CalendarFragment;
import com.clwater.zhiji.ui.fragment.DiaryFragment;
import com.clwater.zhiji.ui.fragment.NoteFragment;
import com.clwater.zhiji.ui.fragment.RemindFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    CalendarFragment _calendarFragment;     //日历
    RemindFragment _remindFragment;         //提醒
    NoteFragment _noteFragment;             //笔记
    DiaryFragment _diaryFragemnt;           //日记

    private int bottemIndex = 0;

    @BindView(R.id.image_mian_edit) ImageView image_mian_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();

    }

    private void init() {

        initBottomNavigationBar();
        initFragmentManager();

    }

    private void initFragmentManager() {
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher, "今日"))
                .addItem(new BottomNavigationItem(R.drawable.ic_reloj_max, "笔记"))
                .addItem(new BottomNavigationItem(R.drawable.ic_reloj_max, "搜索"))
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher, "日历"))
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {

                if (position != bottemIndex){
                    bottemIndex = position;
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    switch (position){
                        case 0:
                            transaction.replace(R.id.framelayout_content, _diaryFragemnt);
                            break;
                        case 1:
                            transaction.replace(R.id.framelayout_content, _noteFragment);
                            break;
                        case 2:
                            transaction.replace(R.id.framelayout_content, _remindFragment);
                            break;
                        case 3:
                            transaction.replace(R.id.framelayout_content, _calendarFragment);
                            break;
                    }

                    transaction.commit();

                }

            }
            @Override
            public void onTabUnselected(int position) {
                Log.d("lal" , "onTabUnselected:  " + position );
            }
            @Override
            public void onTabReselected(int position) {
                Log.d("lal" , "onTabReselected:  " + position );
            }
        });
    }

    private void initBottomNavigationBar() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        _calendarFragment = new CalendarFragment();
        _remindFragment = new RemindFragment();
        _noteFragment = new NoteFragment();
        _diaryFragemnt = new DiaryFragment();

        transaction.replace(R.id.framelayout_content, _diaryFragemnt);
        transaction.commit();
    }

    @OnClick(R.id.image_mian_edit)
    public void image_mian_edit_onclick(){
        Log.d("lal" , "image_mian_edit is click");
    }
}
