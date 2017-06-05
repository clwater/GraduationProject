package com.clwater.zhiji.ui.activity;

import android.content.Context;
import android.content.Intent;
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



public class DiaryPWActivity extends AppCompatActivity {
    @BindView(R.id.psw_input) PswInputView psw_input;
    @BindView(R.id.textview_diarypw_text) TextView textview_diarypw_text;


    String _tempPw;


    String pw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarypw);

        ButterKnife.bind(this);


        final boolean newpw = checkOldPw();


        psw_input.setInputCallBack(new PswInputView.InputCallBack() {
            @Override
            public void onInputFinish(String result) {
//                Toast.makeText(DiaryPWActivity.this,result,Toast.LENGTH_SHORT).show();
                _tempPw = result;

                if (newpw) {

                    Intent i = new Intent(DiaryPWActivity.this, DiaryPWActivity2.class);
                    i.putExtra("pw", _tempPw);
                    startActivity(i);
                    DiaryPWActivity.this.finish();
                }else {
                    SharedPreferences userInfo = getSharedPreferences("LAL", MODE_PRIVATE);
                    SharedPreferences.Editor editor = userInfo.edit();//获取Editor
                    editor.clear();
                    editor.commit();
                    Toast.makeText(DiaryPWActivity.this,"密码已删除",Toast.LENGTH_SHORT).show();
                    DiaryPWActivity.this.finish();
                }
            }
        });
    }


    private boolean checkOldPw() {
        SharedPreferences sharedPreferences = getSharedPreferences("LAL", Context.MODE_PRIVATE);
         pw = sharedPreferences.getString("pw", "");

        if (pw.isEmpty()){
            textview_diarypw_text.setText("请设置密码");
            return true;
        }{
            textview_diarypw_text.setText("输入就密码以删除密码");
            return false;
        }
    }
}
