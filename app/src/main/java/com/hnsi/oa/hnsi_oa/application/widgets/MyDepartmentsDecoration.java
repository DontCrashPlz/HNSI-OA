package com.hnsi.oa.hnsi_oa.application.widgets;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hnsi.oa.hnsi_oa.application.beans.RealDepartmentEntity;

import java.util.ArrayList;

/**
 * Created by Zheng on 2017/10/27.
 */

public class MyDepartmentsDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint;

    private int mLinePadding;

    private ArrayList<RealDepartmentEntity> mData;

    public MyDepartmentsDecoration(int linePadding, ArrayList<RealDepartmentEntity> departmentEntities) {
        mPaint= new Paint();
        mPaint.setAntiAlias(true);
//        mPaint.setColor(Color.rgb(173,173,173));
        mLinePadding= linePadding;
        mData= departmentEntities;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position= parent.getChildAdapterPosition(view);

        if (position== 0)
            return;

        if (mData.get(position).getType()== 0){
            outRect.top= 8;
        }else if (mData.get(position).getType()== 1){
            outRect.top= 1;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount= parent.getChildCount();

        for (int i= 0; i< childCount; i++){
            View view= parent.getChildAt(i);
            int position= parent.getChildAdapterPosition(view);
            if (position== 0) continue;

            if (mData.get(position).getType()== 1){
                mPaint.setColor(Color.rgb(253,254,251));
                c.drawLine(
                        parent.getPaddingLeft(),
                        view.getTop()-1,
                        parent.getWidth()-parent.getPaddingRight(),
                        view.getTop()-1,
                        mPaint);

//                mPaint.setColor(Color.rgb(173,173,173));
//                c.drawLine(
//                        parent.getPaddingLeft()+mLinePadding+100,
//                        view.getTop()-1,
//                        parent.getWidth()-parent.getPaddingRight()-mLinePadding,
//                        view.getTop()-1,
//                        mPaint);
            }
//            c.drawLine(
//                    parent.getPaddingLeft()+mLinePadding,
//                    view.getTop()-1,
//                    parent.getWidth()-parent.getPaddingRight()-mLinePadding,
//                    view.getTop()-1,
//                    mPaint);
        }
    }
}
