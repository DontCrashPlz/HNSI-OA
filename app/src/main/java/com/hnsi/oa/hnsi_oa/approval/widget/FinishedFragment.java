package com.hnsi.oa.hnsi_oa.approval.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.widgets.LazyLoadFragment;

/**
 * Created by Zheng on 2018/1/5.
 */

public class FinishedFragment extends LazyLoadFragment {

    private TextView mTestTv;

    private boolean isPrepared;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView= inflater.inflate(R.layout.fragment_test, container, false);

        mTestTv= (TextView) mView.findViewById(R.id.testTv);

        isPrepared= true;

        lazyLoad();
        return mView;
    }

    @Override
    public void lazyLoad() {
        if (!isVisible || !isPrepared){
            return;
        }

        if (this.getActivity()== null){
            Log.e("null","1");
        }
        ((ApprovalActivity)getActivity()).initListView(new String[]{"110", "120", "122", "119"});
    }

    public void setTextViewText(String str){
        mTestTv.setText(str);
    }

}
