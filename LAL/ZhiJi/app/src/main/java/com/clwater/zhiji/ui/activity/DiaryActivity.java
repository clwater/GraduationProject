package com.clwater.zhiji.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.clwater.zhiji.R;
import com.clwater.zhiji.database.BaseControl;
import com.clwater.zhiji.database.BeanDiary;
import com.clwater.zhiji.utils.TimeUtil;
import com.litesuits.orm.LiteOrm;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ren.qinc.edit.PerformEdit;



public class DiaryActivity extends AppCompatActivity {
    @BindView(R.id.editText_editdiary_text) MaterialEditText editText_editdiary_text;
    @BindView(R.id.editText_editdiary_title) MaterialEditText editText_editdiary_title;
    @BindView(R.id.view_editdiary_spaceview) View view_editdiary_spaceview;


    PerformEdit mPerformEdit;

    private BeanDiary beanDiary = new BeanDiary();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);



        getDate();
        init();

    }

    private void getDate() {
        Intent intent = this.getIntent();
        beanDiary = (BeanDiary) intent.getSerializableExtra("class");

        LiteOrm liteOrm = new BaseControl().Initialize(this);
        liteOrm.delete(beanDiary);

    }

    private void init() {
        mPerformEdit = new PerformEdit(editText_editdiary_text);
        mPerformEdit.setDefaultText(beanDiary.getText());
        init_editText_edit_text();
        editText_editdiary_title.setText(beanDiary.getTitle());
    }



    private void init_editText_edit_text() {

        editText_editdiary_text.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                RelativeLayout.LayoutParams relaParams = (RelativeLayout.LayoutParams)  view_editdiary_spaceview.getLayoutParams();
                if (hasFocus) {
                    relaParams.height = 0;
                } else {
                    if(editText_editdiary_text.getHeight() < 1000) {
                        relaParams.height = 500;
                    }
                }
                view_editdiary_spaceview.setLayoutParams(relaParams);

            }
        });
    }

    @OnClick(R.id.view_editdiary_spaceview)
    public void view_edit_spaceview_onclick(){
        editText_editdiary_text.setFocusable(true);
        editText_editdiary_text.setFocusableInTouchMode(true);
        editText_editdiary_text.requestFocus();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuitem_edit_undo:
                mPerformEdit.undo();
                return true;
            case R.id.menuitem_edit_redo:
                mPerformEdit.redo();
                return true;
            case R.id.menuitem_edit_save:
                savePage();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void savePage() {
        Log.d("LAL" , "savePage()");
        LiteOrm liteOrm = new BaseControl().Initialize(this);
        BeanDiary beandiary = new BeanDiary(editText_editdiary_title.getText().toString(), editText_editdiary_text.getText().toString() ,  TimeUtil.getTime());
        liteOrm.save(beandiary);

        this.finish();

    }

    @Override
    protected void onDestroy() {
        savePage();
        super.onDestroy();
    }
}
