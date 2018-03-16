package com.hnsi.oa.hnsi_oa.application.main.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.application.app.MyApplication;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnLoginListener;
import com.hnsi.oa.hnsi_oa.application.login.widget.LoginActivity;
import library.utils.SharedPrefUtils;
import library.widgets.CircleImageView;
import library.widgets.LazyLoadFragment;

/**
 * Created by Zheng on 2017/10/24.
 */

public class MineFragment extends LazyLoadFragment implements View.OnClickListener {

    private CircleImageView mCircleImageView;
    private TextView mUserNameTv;

    private RelativeLayout mChangePasswordBtn;

    private RelativeLayout mLogoutBtn;

    private boolean isLoadedOnce;
    private boolean isPrepared;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView= inflater.inflate(R.layout.fragment_mine,container,false);

        findViews(mView);

        lazyLoad();

        return mView;
    }

    private void findViews(View mView) {
        mCircleImageView= (CircleImageView) mView.findViewById(R.id.mine_civ_usericon);
        mCircleImageView.setOnClickListener(this);

        mUserNameTv= (TextView) mView.findViewById(R.id.mine_tv_username);

        mChangePasswordBtn= (RelativeLayout) mView.findViewById(R.id.mine_rly_change_password);
        mChangePasswordBtn.setOnClickListener(this);

        mLogoutBtn= (RelativeLayout) mView.findViewById(R.id.mine_rly_logout);
        mLogoutBtn.setOnClickListener(this);

        isPrepared= true;
    }

    @Override
    public void lazyLoad() {

        if (!isVisible || isLoadedOnce || !isPrepared)
            return;

        String headImgUrl= MyApplication.getInstance().getUserInfo().getHeadimg();
        if (headImgUrl!= null){
            if (!"null".equals(headImgUrl) && !"".equals(headImgUrl)){
//                headImgUrl= MyApplication.getInstance().getBaseUrl() + headImgUrl;
                headImgUrl= "http://192.168.1.68:80/" + headImgUrl;
                Glide.with(this)
                        .load(headImgUrl)
                        .asBitmap()
                        .placeholder(R.mipmap.user_icon)
                        .error(R.mipmap.user_icon)
                        .into(mCircleImageView);
            }
        }

        mUserNameTv.setText(MyApplication.getInstance().getUserInfo().getEmpname());

        isLoadedOnce= true;

    }

    @Override
    public void onClick(View v) {
        int vId= v.getId();
        switch (vId){
            case R.id.mine_civ_usericon:

                break;
            case R.id.mine_rly_change_password:
                startActivity(new Intent(getContext(),ChangePasswordActivity.class));
                break;
            case R.id.mine_rly_logout:
                MyApplication.getInstance().userLogout(new OnLoginListener() {
                    @Override
                    public void onSuccessed() {
                        Toast.makeText(getContext(), "注销成功！", Toast.LENGTH_SHORT).show();
                        SharedPrefUtils.put(getContext(), LoginActivity.IS_AUTO_LOGIN, false);
                        startActivity(new Intent(getContext(),LoginActivity.class));
                        getActivity().finish();
                    }

                    @Override
                    public void onFailed(String throwable) {
                        Toast.makeText(getContext(), throwable, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}
