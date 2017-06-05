package com.clwater.zhiji.ui.activity;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;

import com.clwater.zhiji.eventbus.EventBus_ChangeData;
import com.clwater.zhiji.R;
import com.clwater.zhiji.database.BaseControl;
import com.clwater.zhiji.database.BeanCalendar;
import com.clwater.zhiji.database.BeanDiary;
import com.clwater.zhiji.database.BeanNote;
import com.clwater.zhiji.utils.TimeUtil;
import com.litesuits.orm.LiteOrm;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ren.qinc.edit.PerformEdit;

/**
 * Created by yszsyf on 2017/5/15.
 */

public class EditActivity extends AppCompatActivity {
    @BindView(R.id.editText_edit_text) MaterialEditText editText_edit_text;
    @BindView(R.id.editText_edit_title) MaterialEditText editText_edit_title;
    @BindView(R.id.view_edit_spaceview) View view_edit_spaceview;
    @BindView(R.id.textView_edit_data) TextView textView_edit_data;

    @BindView(R.id.textview_edit_chooseNote) TextView textview_edit_chooseNote;
    @BindView(R.id.textview_edit_chooseDiary) TextView textview_edit_chooseDiary;
    @BindView(R.id.textview_edit_chooseRC) TextView textview_edit_chooseRC;
    @BindView(R.id.textview_edit_chooseRL) TextView textview_edit_chooseRL;

    PerformEdit mPerformEdit;

    private String _year , _month , _day;

    private int _index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        init();

    }

    private void init() {
        mPerformEdit = new PerformEdit(editText_edit_text);
        mPerformEdit.setDefaultText("");
        init_editText_edit_text();
        initChooseItem(0);


    }


    private void hideView() {
        editText_edit_title.setVisibility(View.GONE);
        textView_edit_data.setVisibility(View.GONE);
    }

    @OnClick(R.id.textview_edit_chooseNote)
    public void textview_edit_chooseNote_onclick(){
        _index = 0;
        initChooseItem(_index);
        hideView();


    }



    @OnClick(R.id.textview_edit_chooseDiary)
    public void textview_edit_chooseDiary_onclick(){
        _index = 1;
        initChooseItem(_index);
        hideView();
        editText_edit_title.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.textview_edit_chooseRC)
    public void textview_edit_chooseRC_onclick(){
        _index = 2;
        initChooseItem(_index);
        hideView();
    }

    @OnClick(R.id.textview_edit_chooseRL)
    public void textview_edit_chooseRL_onclick(){
        _index = 3;
        initChooseItem(_index);
        hideView();
        textView_edit_data.setVisibility(View.VISIBLE);
        List<String> alldate = TimeUtil.getALLDate();
        _year = alldate.get(0);
        _month = alldate.get(1);
        _day = alldate.get(2);
        textView_edit_data.setText(TimeUtil.getDay());
    }

    @OnClick(R.id.textView_edit_data)
    public void textView_edit_data_onClick(){
        startActivity(new Intent(this , CalendarChooseActivity.class));
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeData(EventBus_ChangeData e){
        String da = e.getYear() + "年" + e.getMouth()+ "月" + e.getDay()+"日";
        _year = e.getYear();
        _month = e.getMouth();
        _day = e.getDay();
        textView_edit_data.setText(da);
    }

    private void initChooseItem(int index) {
        textview_edit_chooseNote.setTextColor(Color.parseColor("#000000"));
        textview_edit_chooseNote.setBackgroundResource(R.drawable.item_white);
        textview_edit_chooseDiary.setTextColor(Color.parseColor("#000000"));
        textview_edit_chooseDiary.setBackgroundResource(R.drawable.item_white);
        textview_edit_chooseRC.setTextColor(Color.parseColor("#000000"));
        textview_edit_chooseRC.setBackgroundResource(R.drawable.item_white);
        textview_edit_chooseRL.setTextColor(Color.parseColor("#000000"));
        textview_edit_chooseRL.setBackgroundResource(R.drawable.item_white);

        switch (index){
            case 0:
                textview_edit_chooseNote.setTextColor(Color.parseColor("#ffffff"));
                textview_edit_chooseNote.setBackgroundResource(R.drawable.item_black);
                break;
            case 1:
                textview_edit_chooseDiary.setTextColor(Color.parseColor("#ffffff"));
                textview_edit_chooseDiary.setBackgroundResource(R.drawable.item_black);
                break;
            case 2:
                textview_edit_chooseRC.setTextColor(Color.parseColor("#ffffff"));
                textview_edit_chooseRC.setBackgroundResource(R.drawable.item_black);
                break;
            case 3:
                textview_edit_chooseRL.setTextColor(Color.parseColor("#ffffff"));
                textview_edit_chooseRL.setBackgroundResource(R.drawable.item_black);
        }

    }

    private void init_editText_edit_text() {

        editText_edit_text.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                RelativeLayout.LayoutParams relaParams = (RelativeLayout.LayoutParams)  view_edit_spaceview.getLayoutParams();
                if (hasFocus) {
                    relaParams.height = 0;
                } else {
                    if(editText_edit_text.getHeight() < 1000) {
                        relaParams.height = 500;
                    }
                }
                view_edit_spaceview.setLayoutParams(relaParams);

            }
        });
    }

    @OnClick(R.id.view_edit_spaceview)
    public void view_edit_spaceview_onclick(){
        editText_edit_text.setFocusable(true);
        editText_edit_text.setFocusableInTouchMode(true);
        editText_edit_text.requestFocus();
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
        switch (_index){
            case 0:
                BeanNote  beanNote = new BeanNote(editText_edit_text.getText().toString() , TimeUtil.getTime());
//                Log.d("LAL", "savePage: TimeUtil.getTime" + TimeUtil.getTime());
                liteOrm.save(beanNote);
                break;
            case 1:
                BeanDiary beanDiary = new BeanDiary(editText_edit_title.getText().toString() , editText_edit_text.getText().toString() , TimeUtil.getTime());
//                Log.d("LAL", "savePage: TimeUtil.getTime" + TimeUtil.getTime());
                liteOrm.save(beanDiary);
                break;

            case 3:
                Log.d("LAL" , _year + _month + _day);
                BeanCalendar beanCalender = new BeanCalendar(editText_edit_text.getText().toString() , _year ,_month, _day);
                liteOrm.save(beanCalender);
        }

        this.finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
