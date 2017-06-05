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
import com.clwater.zhiji.database.BeanDiary;
import com.clwater.zhiji.database.BeanNote;
import com.clwater.zhiji.ui.adapter.DiaryAdapter;
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

public class DiaryFragment extends Fragment {

    @BindView(R.id.recyclerView_diary_list) RecyclerView recyclerView_diary_list;

    private List<BeanDiary> _list = new ArrayList<BeanDiary>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_diary, container, false);
        ButterKnife.bind(this , view);


        getDate();

        init();

        return view;
    }

    private void init() {
        initList();
    }

    private void initList() {
        recyclerView_diary_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        DiaryAdapter noteAdapter = new DiaryAdapter(getActivity() , _list);
        recyclerView_diary_list.setAdapter(noteAdapter);
        recyclerView_diary_list.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    private void getDate() {
        LiteOrm liteOrm = new BaseControl().Initialize(getActivity());
        QueryBuilder qb = new QueryBuilder(BeanDiary.class).appendOrderDescBy("changeDate");
        _list = liteOrm.query(qb);

//        for (int i = 0 ; i < _list.size() ; i++){
//            BeanDiary b = (BeanDiary) _list.get(i);
//            Log.d("LAL" , "b.getText()" + b.getText() + "  b.gettime " + b.getChangeDate());
//        }

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }



}