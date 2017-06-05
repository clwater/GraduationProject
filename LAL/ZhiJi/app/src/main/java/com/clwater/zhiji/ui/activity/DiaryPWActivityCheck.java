package com.clwater.zhiji.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.clwater.zhiji.R;
import com.clwater.zhiji.eventbus.EventBus_pw;
import com.clwater.zhiji.other.PswInputView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;



public class DiaryPWActivityCheck extends AppCompatActivity {
    @BindView(R.id.psw_input) PswInputView psw_input;
    @BindView(R.id.textview_diarypw_text) TextView textview_diarypw_text;


    String _tempPw;

    boolean pd = true;
    String pw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarypw);

        ButterKnife.bind(this);

        checkOldPw();

        psw_input.setInputCallBack(new PswInputView.InputCallBack() {
            @Override
            public void onInputFinish(String result) {

               if (result.equals(pw)){
                   pd = false;
                   DiaryPWActivityCheck.this.finish();
               }else {
                    EventBus.getDefault().post(new EventBus_pw(""));
                   DiaryPWActivityCheck.this.finish();

               }
            }
        });
    }


    private void checkOldPw() {
        SharedPreferences sharedPreferences = getSharedPreferences("LAL", Context.MODE_PRIVATE);
         pw = sharedPreferences.getString("pw", "");

        Log.d("LAL" , "pw" + pw );
    }

    @Override
    protected void onDestroy() {
        if (pd) {
            EventBus.getDefault().post(new EventBus_pw(""));
        }
        super.onDestroy();
    }
}
