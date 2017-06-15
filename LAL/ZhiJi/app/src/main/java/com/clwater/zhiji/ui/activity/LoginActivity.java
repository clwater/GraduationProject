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

    @BindView(R.id.button_login_login) com.gc.materialdesign.views.ButtonRectangle button_login_login;
    @BindView(R.id.button_login_re) com.gc.materialdesign.views.ButtonRectangle button_login_re;
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

        if (editText_login_name.getText().toString().isEmpty() || editText_login_pw.getText().toString().isEmpty()){
            Toast.makeText(LoginActivity.this , "请填写用户名和密码" , Toast.LENGTH_SHORT).show();
        }else {
            if (editText_login_pw.getText().toString().indexOf("7") != -1) {
                Toast.makeText(LoginActivity.this, "用户名或密码密码错误请重试", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }
    }

    @OnClick(R.id.button_login_re)
    public void button_login_re_onclick(){
        if (editText_login_name.getText().toString().isEmpty() || editText_login_pw.getText().toString().isEmpty()){
            Toast.makeText(LoginActivity.this , "请填写用户名和密码" , Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(LoginActivity.this , "注册成功" , Toast.LENGTH_SHORT).show();
        this.finish();

    }
}
