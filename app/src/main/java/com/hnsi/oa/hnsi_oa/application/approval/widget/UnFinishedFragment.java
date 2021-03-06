package com.hnsi.oa.hnsi_oa.application.approval.widget;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hnsi.oa.hnsi_oa.application.beans.FlowClassifyEntity;
import library.widgets.RecyclerFragment.BaseRecyclerPresenter;
import library.widgets.RecyclerFragment.BaseRecyclerFragment;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/1/5.
 */

public class UnFinishedFragment extends BaseRecyclerFragment {

    public UnFinishedFragment(BaseQuickAdapter adapter, RecyclerView.ItemDecoration itemDecoration) {
        super(true, adapter, itemDecoration);
    }

    public void setFlowNumList(ArrayList<FlowClassifyEntity> list){
        ((ApprovalActivity)getActivity()).initListView(list);
    }

    public void setNewPresenter(BaseRecyclerPresenter presenter){
        mPresenter= presenter;
    }
}
