package com.clwater.zhiji.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.clwater.zhiji.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ren.qinc.edit.PerformEdit;

/**
 * Created by yszsyf on 2017/5/15.
 */

public class EditActivity extends AppCompatActivity {
    @BindView(R.id.editText_edit_text)
    MaterialEditText editText_edit_text;

    @BindView(R.id.id1) Button button1;
    @BindView(R.id.id2) Button button2;
    @BindView(R.id.id3) Button button3;

    PerformEdit mPerformEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ButterKnife.bind(this);

        init();
    }

    private void init() {
        mPerformEdit = new PerformEdit(editText_edit_text);
        mPerformEdit.setDefaultText("这是初始值,不做撤销记录");
        

    }

    @OnClick(R.id.id1)
    public void b1(){
        mPerformEdit.undo();
    }

    @OnClick(R.id.id2)
    public void b2(){
        mPerformEdit.redo();
    }

    @OnClick(R.id.id3)
    public void b3(){
        mPerformEdit.clearHistory();
    }

}
