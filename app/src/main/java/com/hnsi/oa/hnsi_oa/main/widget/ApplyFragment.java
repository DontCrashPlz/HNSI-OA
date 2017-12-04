package com.hnsi.oa.hnsi_oa.main.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.approval.widget.ApprovalActivity;
import com.hnsi.oa.hnsi_oa.news.widget.NewsActivity;
import com.hnsi.oa.hnsi_oa.news.widget.NoticeActivity;
import com.hnsi.oa.hnsi_oa.widgets.LazyLoadFragment;

/**
 * Created by Zheng on 2017/10/24.
 */

public class ApplyFragment extends LazyLoadFragment implements View.OnClickListener {

    private RelativeLayout mNewsBtn;
    private RelativeLayout mNoticeBtn;
    private RelativeLayout mMessageBtn;
    private RelativeLayout mApprovalBtn;

    private boolean isLoadedOnce;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView= inflater.inflate(R.layout.fragment_apply,container,false);

        findViews(mView);

        lazyLoad();

        return mView;
    }

    private void findViews(View mView) {
        mNewsBtn= (RelativeLayout) mView.findViewById(R.id.apply_btn_new);
        mNewsBtn.setOnClickListener(this);
        mNoticeBtn= (RelativeLayout) mView.findViewById(R.id.apply_btn_notice);
        mNoticeBtn.setOnClickListener(this);
        mMessageBtn= (RelativeLayout) mView.findViewById(R.id.apply_btn_msg);
        mMessageBtn.setOnClickListener(this);
        mApprovalBtn= (RelativeLayout) mView.findViewById(R.id.apply_btn_approval);
        mApprovalBtn.setOnClickListener(this);
    }

    @Override
    public void lazyLoad() {

        if (!isVisible || isLoadedOnce)
            return;
    }

    @Override
    public void onClick(View v) {
        int vId= v.getId();
        switch (vId){
            case R.id.apply_btn_new:
                startActivity(new Intent(getContext(), NewsActivity.class));
                break;
            case R.id.apply_btn_notice:
                startActivity(new Intent(getContext(), NoticeActivity.class));
                break;
            case R.id.apply_btn_msg:

                break;
            case R.id.apply_btn_approval:
                startActivity(new Intent(getContext(), ApprovalActivity.class));
                break;
            default:
                throw new RuntimeException("ApplyFragment appear a Click Exception!");
        }
    }
}