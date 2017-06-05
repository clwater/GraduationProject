package com.clwater.zhiji.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clwater.zhiji.R;
import com.clwater.zhiji.database.BeanNote;
import com.clwater.zhiji.ui.activity.NoteActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;




import android.util.Log;

import java.util.ArrayList;

/**
 * Created by yszsyf on 17/3/17.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NormalTextViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context context;
    List<BeanNote> list = new ArrayList<BeanNote>();

    public NoteAdapter(Context context , List<BeanNote> _list) {
        this.list = _list;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.adapter_note, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        holder.textview_adapterNote_text.setText(list.get(position).getText().toString());
//        holder.textview_adapterNote_text.setText("11");
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class NormalTextViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_adapterNote_text)
        TextView textview_adapterNote_text;

        NormalTextViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("gzb", "onClick--> position = " + getPosition());

                    Intent intent = new Intent(context , NoteActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("class", list.get(getPosition()));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}