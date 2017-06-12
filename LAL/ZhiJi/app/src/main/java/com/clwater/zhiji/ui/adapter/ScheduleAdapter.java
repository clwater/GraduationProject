package com.clwater.zhiji.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clwater.zhiji.R;
import com.clwater.zhiji.database.BaseControl;
import com.clwater.zhiji.database.BeanNote;
import com.clwater.zhiji.database.BeanSchedule;
import com.clwater.zhiji.ui.activity.NoteActivity;
import com.clwater.zhiji.ui.activity.ScheduleActivity;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yszsyf on 17/3/17.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.NormalTextViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context context;
    List<BeanSchedule> list = new ArrayList<BeanSchedule>();

    public ScheduleAdapter(Context context , List<BeanSchedule> _list) {
        this.list = _list;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.adapter_schedule, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        holder.textview_adapter_text.setText(list.get(position).getText().toString());
        BeanSchedule beanSchedule = list.get(position);
        String time = beanSchedule.getYear() + "/" + beanSchedule.getMouth() + "/" + beanSchedule.getDay() +
                "  " + beanSchedule.getHour() + ":" + beanSchedule.getMin();
        holder.textview_adapter_text2.setText(time);
//        holder.textview_adapterNote_text.setText("11");
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class NormalTextViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_adapter_text)
        TextView textview_adapter_text;

        @BindView(R.id.textview_adapter_text2)
        TextView textview_adapter_text2;

        NormalTextViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("gzb", "onClick--> position = " + getPosition());

                    Intent intent = new Intent(context , ScheduleActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("class", list.get(getPosition()));
                    intent.putExtras(bundle);

                    LiteOrm liteOrm = new BaseControl().Initialize(context);
                    liteOrm.delete(list.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}