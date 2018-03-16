package library.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hnsi.oa.hnsi_oa.R;
import library.utils.DensityUtil;
import library.widgets.CustomTabLayout.Tool;

/**
 * Created by Zheng on 2017/12/28.
 */

public class IconImageView extends View {

    private String text;
    private int textColor;
    private float textSize;
    private Bitmap iconImg;

    private int num;

    private Paint mPaint;
    private Rect mTextRect;
    private Rect mImageRect;

    private int dp_px_15;
    private int dp_px_10;
    private int dp_px_4;
    private int dp_px_2;

    public IconImageView(Context context) {
        this(context, null);
    }

    public IconImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array= context.getTheme().obtainStyledAttributes(attrs, R.styleable.IconImageView, defStyleAttr, 0);
//        int n= array.getIndexCount();
        text= array.getString(R.styleable.IconImageView_text);
        textColor= array.getColor(R.styleable.IconImageView_textColor, Color.BLACK);
        textSize= array.getDimension(R.styleable.IconImageView_textSize, DensityUtil.sp2px(getContext(), 13));
        iconImg= BitmapFactory.decodeResource(getResources(), array.getResourceId(R.styleable.IconImageView_icon_image, 0));

        array.recycle();

        mPaint= new Paint();
        mPaint.setAntiAlias(true);

        mTextRect= new Rect();
        mPaint.setTextSize(textSize);
        mPaint.getTextBounds(text, 0, text.length(), mTextRect);

        dp_px_10= Tool.dp2px(getContext(), 10);
        dp_px_15= Tool.dp2px(getContext(), 15);
        dp_px_4= Tool.dp2px(getContext(), 4);
        dp_px_2= Tool.dp2px(getContext(), 2);
    }


    private int mWidth;
    private int mHeigth;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        //计算宽度
//        mWidth= iconImg.getWidth();
//        int spaceWidth= Tool.getScreenWidth(getContext()) - Tool.dp2px(getContext(),28);
//        mWidth= spaceWidth/4 ;
//        Log.e("width", ""+mWidth);
        //计算高度
        mHeigth= iconImg.getHeight() + mTextRect.height() + dp_px_15;
//        Log.e("width", ""+ MeasureSpec.getSize(widthMeasureSpec));
//        Log.e("height", ""+ mHeigth);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),mHeigth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        Log.e("width", ""+ getWidth());
        Log.e("height", ""+ getHeight());

        //绘图
        mImageRect= new Rect(
                getWidth()/2- iconImg.getWidth()/2,
                dp_px_10,
                getWidth()/2+ iconImg.getWidth()/2,
                iconImg.getHeight() + dp_px_10);
        canvas.drawBitmap(iconImg, null, mImageRect, mPaint);

        //绘制文字
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        canvas.drawText(
                text,
                getWidth()/2- mTextRect.width()/2,
                getHeight()- dp_px_2,
                mPaint);

        //绘制角标底图
        if (num> 0){
            mPaint.setColor(Color.RED);
            canvas.drawCircle(
                    getWidth()/2+ iconImg.getWidth()/2- dp_px_4,
                    dp_px_10+ dp_px_4,
                    dp_px_10,
                    mPaint);
        }

        //绘制角标文字
        if (num> 0){
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(dp_px_10);
            String mNum= String.valueOf(num);
            if (num> 99) mNum= "99+";
            Rect rect= new Rect();
            mPaint.getTextBounds(mNum, 0, mNum.length(), rect);
            canvas.drawText(
                    mNum,
                    getWidth()/2+ iconImg.getWidth()/2- rect.width()/2- dp_px_4,
                    dp_px_10+ rect.height()/2+ dp_px_4,
                    mPaint);
        }
    }

    public void setNum(int number){
        num= number;
        invalidate();
    }

}
