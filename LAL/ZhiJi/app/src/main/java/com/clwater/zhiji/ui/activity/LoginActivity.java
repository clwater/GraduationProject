package com.clwater.zhiji.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.clwater.zhiji.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yszsyf on 2017/6/5.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.button_login_login) Button button_login_login;
    @BindView(R.id.button_login_re) Button button_login_re;
    @BindView(R.id.editText_login_name) MaterialEditText editText_login_name;
    @BindView(R.id.editText_login_pw) MaterialEditText editText_login_pw;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_login_login)
    public void button_login_login_onclick(){
        Toast.makeText(LoginActivity.this , "服务器请求错误，请稍后重试" , Toast.LENGTH_SHORT).show();
        Log.d("LAL" , "button_login_login_onclick");
    }

    @OnClick(R.id.button_login_re)
    public void button_login_re_onclick(){
        Toast.makeText(LoginActivity.this , "服务器请求错误，请稍后重试" , Toast.LENGTH_SHORT).show();
    }
}
