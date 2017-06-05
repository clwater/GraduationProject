package com.clwater.zhiji.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.clwater.zhiji.R;
import com.clwater.zhiji.other.PswInputView;

import butterknife.BindView;
import butterknife.ButterKnife;



public class DiaryPWActivity2 extends AppCompatActivity {
    @BindView(R.id.psw_input) PswInputView psw_input;
    @BindView(R.id.textview_diarypw_text) TextView textview_diarypw_text;


    String _tempPw;
    String _temp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarypw);

        ButterKnife.bind(this);

        gettemppw();

        textview_diarypw_text.setText("请再次输入密码");


        psw_input.setInputCallBack(new PswInputView.InputCallBack() {
            @Override
            public void onInputFinish(String result) {
//                Toast.makeText(DiaryPWActivity2.this,result,Toast.LENGTH_SHORT).show();
                _tempPw = result;
                if (_tempPw.equals(_temp)){
                    savePW(_tempPw);
                    Toast.makeText(DiaryPWActivity2.this,"密码设置成功",Toast.LENGTH_SHORT).show();
                    DiaryPWActivity2.this.finish();
                }else {
                    Toast.makeText(DiaryPWActivity2.this,"密码设置失败",Toast.LENGTH_SHORT).show();
                    DiaryPWActivity2.this.finish();
                }
            }
        });
    }

    private void savePW(String tempPw) {
        SharedPreferences sharedPreferences = getSharedPreferences("LAL", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("pw", tempPw);
        editor.commit();//提交修改
    }

    private void gettemppw() {
        _temp =  getIntent().getStringExtra("pw");
    }

}
