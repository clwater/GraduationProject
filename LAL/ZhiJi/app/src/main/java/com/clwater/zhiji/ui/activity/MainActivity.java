package com.clwater.zhiji.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.clwater.zhiji.R;
import com.clwater.zhiji.eventbus.EventBus_pw;
import com.clwater.zhiji.ui.fragment.CalendarFragment;
import com.clwater.zhiji.ui.fragment.DiaryFragment;
import com.clwater.zhiji.ui.fragment.NoteFragment;
import com.clwater.zhiji.ui.fragment.PwerrorFragment;
import com.clwater.zhiji.ui.fragment.ScheduleFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    CalendarFragment _calendarFragment;     //日历
    ScheduleFragment _scheduleFragment;      //提醒
    NoteFragment _noteFragment;             //笔记
    DiaryFragment _diaryFragemnt;           //日记
    PwerrorFragment _pwerror;

    private int bottemIndex = 0;

    @BindView(R.id.image_mian_edit) ImageView image_mian_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        init();

    }

    private void init() {

        initBottomNavigationBar();
        initFragmentManager();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void setError(EventBus_pw e){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        PwerrorFragment _pwerror = new PwerrorFragment();
        transaction.replace(R.id.framelayout_content, _pwerror);
        transaction.commit();
    }

    private void initFragmentManager() {
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher, "笔记"))
                .addItem(new BottomNavigationItem(R.drawable.ic_reloj_max, "日记"))
                .addItem(new BottomNavigationItem(R.drawable.ic_reloj_max, "日程"))
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
                            transaction.replace(R.id.framelayout_content, _noteFragment);
                            break;
                        case 1:
                            if (checkPw()){
                                transaction.replace(R.id.framelayout_content, _pwerror);
                            }else {
                                transaction.replace(R.id.framelayout_content, _diaryFragemnt);
                            }

                            startActivity(new Intent(MainActivity.this , DiaryPWActivityCheck.class));

                            break;
                        case 2:
                            transaction.replace(R.id.framelayout_content, _scheduleFragment);
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

    private boolean checkPw() {
        SharedPreferences sharedPreferences = getSharedPreferences("LAL", Context.MODE_PRIVATE);
        String pw = sharedPreferences.getString("pw", "");
        if (pw.isEmpty()){
            return true;
        }else {
            return false;
        }
    }


    private void initBottomNavigationBar() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        _calendarFragment = new CalendarFragment();
        _scheduleFragment = new ScheduleFragment();
        _noteFragment = new NoteFragment();
        _diaryFragemnt = new DiaryFragment();
        _pwerror = new PwerrorFragment();

        transaction.replace(R.id.framelayout_content, _noteFragment);
        transaction.commit();
    }

    @OnClick(R.id.image_mian_edit)
    public void image_mian_edit_onclick(){
        Log.d("lal" , "image_mian_edit is click");
        startActivity(new Intent(this , EditActivity.class));
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuitem_main_login:
                login();
                return true;
            case R.id.menuitem_main_pw:
                changepw();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void changepw() {
        startActivity(new Intent(this , DiaryPWActivity.class));
    }

    private void login() {
        startActivity(new Intent(this , LoginActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
