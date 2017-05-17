package com.clwater.zhiji.ui.activity;

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
import com.rengwuxian.materialedittext.MaterialEditText;

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

    PerformEdit mPerformEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        ButterKnife.bind(this);

        init();
    }

    private void init() {
        mPerformEdit = new PerformEdit(editText_edit_text);
        mPerformEdit.setDefaultText("");


        init_editText_edit_text();

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
