package com.clwater.zhiji.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.clwater.zhiji.R;
import com.clwater.zhiji.ui.fragment.CalendarFragment;
import com.clwater.zhiji.ui.fragment.DiaryFragment;
import com.clwater.zhiji.ui.fragment.NoteFragment;
import com.clwater.zhiji.ui.fragment.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    CalendarFragment _calendarFragment;     //日历
    SearchFragment _searchFragment;         //搜索
    NoteFragment _noteFragment;             //笔记
    DiaryFragment _diaryFragemnt;           //今日

    private int bottemIndex = 0;

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
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher, "日历"))
                .addItem(new BottomNavigationItem(R.drawable.ic_reloj_max, "搜索"))
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
                            transaction.replace(R.id.framelayout_content, _calendarFragment);
                            break;
                        case 3:
                            transaction.replace(R.id.framelayout_content, _searchFragment);
                            break;
                    }

                    transaction.commit();

                }

            }
            @Override
            public void onTabUnselected(int position) {
                Log.d("gzb" , "onTabUnselected:  " + position );
            }
            @Override
            public void onTabReselected(int position) {
                Log.d("gzb" , "onTabReselected:  " + position );
            }
        });
    }

    private void initBottomNavigationBar() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        _calendarFragment = new CalendarFragment();
        _searchFragment = new SearchFragment();
        _noteFragment = new NoteFragment();
        _diaryFragemnt = new DiaryFragment();

        transaction.replace(R.id.framelayout_content, _diaryFragemnt);
        transaction.commit();
    }
}