package com.hnsi.oa.hnsi_oa.application.widgets;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hnsi.oa.hnsi_oa.application.app.MyApplication;
import library.widgets.CustomTabLayout.Tool;

/**
 * Created by Zheng on 2017/10/27.
 */

public class MyNewsItemDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint;

    private int mLinePadding;

    public MyNewsItemDecoration() {
        mPaint= new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.rgb(173,173,173));
        mLinePadding= Tool.dp2px(MyApplication.getInstance().getApplicationContext(), 14);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view)!= 0)
            outRect.top= 1;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount= parent.getChildCount();
        for (int i= 0; i< childCount; i++){
            View view= parent.getChildAt(i);
            int position= parent.getChildAdapterPosition(view);
            if (position== 0) continue;
            c.drawLine(
                    parent.getPaddingLeft()+mLinePadding,
                    view.getTop()-1,
                    parent.getWidth()-parent.getPaddingRight()-mLinePadding,
                    view.getTop()-1,
                    mPaint);
        }
    }
}
