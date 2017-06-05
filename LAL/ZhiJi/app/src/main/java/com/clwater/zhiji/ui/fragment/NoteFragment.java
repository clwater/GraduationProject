package com.clwater.zhiji.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clwater.zhiji.R;
import com.clwater.zhiji.database.BaseControl;
import com.clwater.zhiji.database.BeanNote;
import com.clwater.zhiji.ui.adapter.DividerItemDecoration;
import com.clwater.zhiji.ui.adapter.NoteAdapter;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yszsyf on 17/2/26.
 */

public class NoteFragment extends Fragment {
    @BindView(R.id.recyclerView_note_list) RecyclerView recyclerView_note_list;
    private List<BeanNote> _list = new ArrayList<BeanNote>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        ButterKnife.bind(this , view);

        getDate();

        init();


        return view;
    }

    private void init() {
        initList();
    }

    private void initList() {
        recyclerView_note_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        NoteAdapter noteAdapter = new NoteAdapter(getActivity() , _list);
        recyclerView_note_list.setAdapter(noteAdapter);
        recyclerView_note_list.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    private void getDate() {
        LiteOrm liteOrm = new BaseControl().Initialize(getActivity());
        QueryBuilder qb = new QueryBuilder(BeanNote.class).appendOrderDescBy("changeDate");
        _list = liteOrm.query(qb);

//        for (int i = 0 ; i < _list.size() ; i++){
//            BeanNote b = (BeanNote) _list.get(i);
//            Log.d("LAL" , "b.getText()" + b.getText() + "  b.gettime " + b.getChangeDate());
//        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



}