package com.hnsi.oa.hnsi_oa.widgets;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * Created by Zheng on 2018/1/16.
 */

public class CleanableEditText extends EditText {
    public CleanableEditText(Context context) {
        super(context);
    }

    public CleanableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CleanableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(s.toString())){
//                    draw
                }
            }
        });
    }

    public CleanableEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction()== MotionEvent.ACTION_BUTTON_PRESS)
        return super.onTouchEvent(event);
    }
}
