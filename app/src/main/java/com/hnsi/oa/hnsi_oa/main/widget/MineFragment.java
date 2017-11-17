package com.hnsi.oa.hnsi_oa.main.widget;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.widgets.LazyLoadFragment;

/**
 * Created by Zheng on 2017/10/24.
 */

public class MineFragment extends LazyLoadFragment {
    private View mView;

    private boolean isLoadedOnce;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView= inflater.inflate(R.layout.fragment_mine,container,false);

        lazyLoad();

        return mView;
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
}
