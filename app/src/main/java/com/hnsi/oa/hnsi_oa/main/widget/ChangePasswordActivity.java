package com.hnsi.oa.hnsi_oa.main.widget;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.widgets.BaseActivity;

/**
 * Created by Zheng on 2017/12/27.
 */

public class ChangePasswordActivity extends BaseActivity {

    private RelativeLayout lly;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        lly= (RelativeLayout) findViewById(R.id.contentView);

        RelativeLayout rly1= new RelativeLayout(this);
        rly1.setBackgroundColor(Color.RED);
        RelativeLayout.LayoutParams lp1= new RelativeLayout.LayoutParams(40, 40);
        lp1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lly.addView(rly1, lp1);

        RelativeLayout rly2= new RelativeLayout(this);
        rly2.setBackgroundColor(Color.YELLOW);
        RelativeLayout.LayoutParams lp2= new RelativeLayout.LayoutParams(40, 40);
        lp2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lly.addView(rly2, lp2);

        RelativeLayout rly3= new RelativeLayout(this);
        rly3.setBackgroundColor(Color.GREEN);
        RelativeLayout.LayoutParams lp3= new RelativeLayout.LayoutParams(40, 40);
        lp3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp3.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lly.addView(rly3, lp3);

        RelativeLayout rly4= new RelativeLayout(this);
        rly4.setBackgroundColor(Color.BLUE);
        RelativeLayout.LayoutParams lp4= new RelativeLayout.LayoutParams(40, 40);
        lp4.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lly.addView(rly4, lp4);
    }
}
