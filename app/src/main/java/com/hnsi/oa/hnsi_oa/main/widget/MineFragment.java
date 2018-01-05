package com.hnsi.oa.hnsi_oa.main.widget;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.login.widget.LoginActivity;
import com.hnsi.oa.hnsi_oa.widgets.LazyLoadFragment;

/**
 * Created by Zheng on 2017/10/24.
 */

public class MineFragment extends LazyLoadFragment implements View.OnClickListener {
    private View mView;

    private RelativeLayout mChangePasswordBtn;

    private RelativeLayout mLogoutBtn;

    private boolean isLoadedOnce;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView= inflater.inflate(R.layout.fragment_mine,container,false);

        findViews(mView);

        lazyLoad();

        return mView;
    }

    private void findViews(View mView) {
        mChangePasswordBtn= (RelativeLayout) mView.findViewById(R.id.mine_rly_change_password);
        mChangePasswordBtn.setOnClickListener(this);

        mLogoutBtn= (RelativeLayout) mView.findViewById(R.id.mine_rly_logout);
        mLogoutBtn.setOnClickListener(this);
    }

    @Override
    public void lazyLoad() {

        if (!isVisible || isLoadedOnce)
            return;

//        mView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mView.setBackgroundColor(Color.BLUE);
//                isLoadedOnce= true;
//            }
//        },1000);
    }

    @Override
    public void onClick(View v) {
        int vId= v.getId();
        switch (vId){
            case R.id.mine_rly_change_password:
                startActivity(new Intent(getContext(),ChangePasswordActivity.class));
                break;
            case R.id.mine_rly_logout:
                startActivity(new Intent(getContext(),LoginActivity.class));
                getActivity().finish();
                break;
        }
    }
}
