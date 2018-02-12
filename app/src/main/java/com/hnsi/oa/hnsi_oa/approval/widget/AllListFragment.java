package com.hnsi.oa.hnsi_oa.approval.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.beans.PersonEntity;
import com.hnsi.oa.hnsi_oa.database.ConstactsInfoTableHelper;
import com.hnsi.oa.hnsi_oa.widgets.MyContactsAdapter;
import com.hnsi.oa.hnsi_oa.widgets.MyNewsItemDecoration;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/2/11.
 */

public class AllListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    private int mDepartmentId;

    private ConstactsInfoTableHelper mHelper;

    public AllListFragment(int departmentId) {
        mDepartmentId= departmentId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_alllist, container, false);

        mRecyclerView= (RecyclerView) view.findViewById(R.id.fragment_alllist_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mProgressBar= (ProgressBar) view.findViewById(R.id.fragment_alllist_progress);

        Log.e("departmentId", ""+mDepartmentId);
        mHelper= new ConstactsInfoTableHelper(getActivity());
        ArrayList<PersonEntity> contactList= mHelper.queryAllOrgidContacts(mDepartmentId);

        //如果读取到0条数据，请求网络数据
        if (contactList== null || contactList.size()== 0) {
            Toast.makeText(getContext(), "本地无通讯录数据！", Toast.LENGTH_SHORT).show();
            getActivity().finish();
            return null;
        }

        mRecyclerView.setAdapter(new MyContactsAdapter(getActivity(), contactList));
        mRecyclerView.addItemDecoration(new MyNewsItemDecoration());
        mProgressBar.setVisibility(View.GONE);

        return view;
    }
}
