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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clwater.zhiji.R;
import com.clwater.zhiji.database.BaseControl;
import com.clwater.zhiji.database.BeanNote;
import com.clwater.zhiji.database.BeanSchedule;
import com.clwater.zhiji.utils.TimeUtil;
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.timepicker.TimePickerBuilder;
import com.codetroopers.betterpickers.timepicker.TimePickerDialogFragment;
import com.litesuits.orm.LiteOrm;
import com.loonggg.lib.alarmmanager.clock.AlarmManagerUtil;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ScheduleActivity extends AppCompatActivity implements TimePickerDialogFragment.TimePickerDialogHandler ,CalendarDatePickerDialogFragment.OnDateSetListener{
    @BindView(R.id.editText_schedule_datatext) MaterialEditText editText_schedule_datatext;
    @BindView(R.id.view_schedule_spaceview) View view_schedule_spaceview;
    @BindView(R.id.textView_schedle_data) TextView textView_schedle_data;
    @BindView(R.id.textView_schedle_time) TextView textView_schedle_time;
    @BindView(R.id.radioGroup) RadioGroup radioGroup;


    private BeanSchedule beanSchedule = new BeanSchedule();

    private String _year , _month , _day , _hour , _min;

    private boolean save = true;

    private int _index = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);



        getDate();
        init();

    }

    private void getDate() {
        Intent intent = this.getIntent();
        beanSchedule = (BeanSchedule) intent.getSerializableExtra("class");

    }

    private void init() {
        init_editText_edit_text();
        editText_schedule_datatext.setText(beanSchedule.getText());

        _year = beanSchedule.getYear();
        _month = beanSchedule.getMouth();
        _day = beanSchedule.getDay();
        _hour = beanSchedule.getHour();
        _min = beanSchedule.getMin();
        _index = Integer.valueOf(beanSchedule.getTixing() );

        textView_schedle_data.setText(_year +"年"+ _month + "月"+ _day+ "日");
        textView_schedle_time.setText(_hour + ":" + _min);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton rd = (RadioButton) radioGroup.getChildAt(i);
                    if (rd.isChecked()) {
                        _index = i;
                        break;
                    }
                }
            }
        });

        Log.d("LAL" , "" + _index);

        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton rd = (RadioButton) radioGroup.getChildAt(i);
            if (_index == i){
                rd.setChecked(true);
            }else {
                rd.setChecked(false);

            }
        }
    }



    private void init_editText_edit_text() {

        editText_schedule_datatext.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                RelativeLayout.LayoutParams relaParams = (RelativeLayout.LayoutParams)  view_schedule_spaceview.getLayoutParams();
                if (hasFocus) {
                    relaParams.height = 0;
                } else {
                    if(editText_schedule_datatext.getHeight() < 1000) {
                        relaParams.height = 500;
                    }
                }
                view_schedule_spaceview.setLayoutParams(relaParams);

            }
        });
    }

    @OnClick(R.id.view_schedule_spaceview)
    public void view_edit_spaceview_onclick(){
        editText_schedule_datatext.setFocusable(true);
        editText_schedule_datatext.setFocusableInTouchMode(true);
        editText_schedule_datatext.requestFocus();
    }



    @OnClick(R.id.textView_schedle_data)
    public void textView_edit_data_onClick(){
//        startActivity(new Intent(this , CalendarChooseActivity.class));
        CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(this);
        cdp.show(getSupportFragmentManager(), "选择日期");
    }

    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
//        mResultTextView.setText(getString(R.string.calendar_date_picker_result_values, year, monthOfYear, dayOfMonth));
        _year = String.format("%04d", Integer.valueOf(year));
        _month = String.format("%02d", Integer.valueOf(monthOfYear));
        _day = String.format("%02d", Integer.valueOf(dayOfMonth));
        textView_schedle_data.setText(_year +"年"+ _month + "月"+ _day+ "日");
    }

    public void onDialogTimeSet(int reference, int hourOfDay, int minute) {
        Log.d("LAL" , "time:" + hourOfDay + minute );
        _hour = String.format("%02d", hourOfDay);
        _min = String.format("%02d", minute);
        textView_schedle_time.setText(_hour + ":" + _min);
    }


    @OnClick(R.id.textView_schedle_time)
    public void textView_edit_time_onclick(){
        TimePickerBuilder tpb = new TimePickerBuilder()
                .setFragmentManager(getSupportFragmentManager())
                .setStyleResId(R.style.BetterPickersDialogFragment);
        tpb.show();
    }





    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuitem_edit_delete:
                save = false;
            case R.id.menuitem_edit_save:
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void savePage() {
        Log.d("LAL" , "savePage()");
        LiteOrm liteOrm = new BaseControl().Initialize(this);
        BeanSchedule beanSchedule = new BeanSchedule(editText_schedule_datatext.getText().toString() , _year , _month , _day , _hour , _min ,String.valueOf( _index) );
        liteOrm.save(beanSchedule);


        int mintime = 0, hourtime = 0;


        if (_index  != 0){

            if (_index == 1) {
                mintime = Integer.valueOf(_min) - 5;
            }else if (_index == 2) {
                mintime = Integer.valueOf(_min) - 30;
            }

            hourtime = Integer.valueOf(_hour);
            if (mintime < 0){
                mintime = mintime + 60;
                hourtime = hourtime - 1;
            }

            AlarmManagerUtil.setAlarm(this , 0 , hourtime, mintime , Integer.valueOf(_hour) + Integer.valueOf(_min) , 0 , beanSchedule.getText() , 2);


        }


    }

    @Override
    protected void onDestroy() {
        if (save) {
            savePage();
        }
        super.onDestroy();
    }

}
