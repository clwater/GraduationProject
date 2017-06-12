package com.clwater.zhiji.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.clwater.zhiji.R;
import com.clwater.zhiji.database.BaseControl;
import com.clwater.zhiji.database.BeanCalendar;
import com.clwater.zhiji.database.BeanDiary;
import com.clwater.zhiji.database.BeanNote;
import com.clwater.zhiji.utils.TimeUtil;
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.timepicker.TimePickerDialogFragment;
import com.litesuits.orm.LiteOrm;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarAddActivity  extends AppCompatActivity implements CalendarDatePickerDialogFragment.OnDateSetListener{

    @BindView(R.id.editText_addCalendar_text) MaterialEditText editText_addCalendar_text;
    @BindView(R.id.textview_addC_time) TextView textview_addC_time;

    private BeanCalendar beanc;
    private String _year , _month , _day;

    private boolean save = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendaradd);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        getBaseIntent();
        init();
    }

    private void init() {
        editText_addCalendar_text.setText(beanc.getText());

        _year = String.format("%04d", Integer.valueOf(beanc.getYear()));
        _month = String.format("%02d", Integer.valueOf(beanc.getMouth()));
        _day = String.format("%02d", Integer.valueOf(beanc.getDay()));

        textview_addC_time.setText(_year +"年"+ _month +"月"+ _day  +"日");
    }

    private void getBaseIntent() {

        Intent intent = this.getIntent();
        beanc = (BeanCalendar) intent.getSerializableExtra("class");
    }


    @OnClick(R.id.textview_addC_time)
    public void textview_addC_time(){
        CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(this);
        cdp.show(getSupportFragmentManager(), "选择日期");
    }



    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
//        mResultTextView.setText(getString(R.string.calendar_date_picker_result_values, year, monthOfYear, dayOfMonth));
        _year = String.format("%04d", Integer.valueOf(year));
        _month = String.format("%02d", Integer.valueOf(monthOfYear));
        _day = String.format("%02d", Integer.valueOf(dayOfMonth));
        textview_addC_time.setText(_year +"年"+ _month + "月"+ _day+ "日");
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


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void savePage() {
        Log.d("LAL" , "savePage()" + _year + _month + _day);
        LiteOrm liteOrm = new BaseControl().Initialize(this);
        BeanCalendar beanCalendar = new BeanCalendar(editText_addCalendar_text.getText().toString() , _year , _month , _day);
        liteOrm.save(beanCalendar);


    }

    @Override
    protected void onDestroy() {
        if (save) {
            savePage();
        }
        super.onDestroy();
    }
}
