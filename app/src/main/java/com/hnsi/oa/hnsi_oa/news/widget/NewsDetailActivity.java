package com.hnsi.oa.hnsi_oa.news.widget;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hnsi.oa.hnsi_oa.BuildConfig;
import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.beans.NewsDetailEntity;
import com.hnsi.oa.hnsi_oa.news.presenter.NewsDetailPresenter;
import com.hnsi.oa.hnsi_oa.news.view.INewsView;
import com.hnsi.oa.hnsi_oa.app.BaseActivity;

/**
 * Created by Zheng on 2017/11/2.
 */

public class NewsDetailActivity extends BaseActivity implements INewsView {

    public static final String NEWS_ID="NEWS_ID";

    private int newsId;

    private NewsDetailPresenter mPresenter;

    private TextView mTitleTv;
    private TextView mDepartmentTv;
    private TextView mAuthorTv;
    private TextView mTimeTv;
    private WebView mContentWv;

    private ProgressBar mProgressBar;

//    private int mTextSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("公告");

        newsId=getIntent().getIntExtra(NEWS_ID,0);
        if (BuildConfig.DEBUG)
            Log.e("id",String.valueOf(newsId));

//        mTextSize= DensityUtil.dp2px(this,12);

        findViews();

        mPresenter= new NewsDetailPresenter(this);
        mPresenter.loadData(newsId);
    }

    private void findViews() {
        mTitleTv= (TextView) findViewById(R.id.notice_detail_tv_title);
        mDepartmentTv= (TextView) findViewById(R.id.notice_detail_tv_department);
        mAuthorTv= (TextView) findViewById(R.id.notice_detail_tv_author);
        mTimeTv= (TextView) findViewById(R.id.notice_detail_tv_time);

        mContentWv= (WebView) findViewById(R.id.notice_detail_wv_content);
        WebSettings settings = mContentWv.getSettings();
        // 设置可以运行JS脚本!!!!
        settings.setJavaScriptEnabled(false);
        settings.setDefaultFontSize(16);
        // 设置文本编码
        settings.setDefaultTextEncodingName("UTF-8");
		/*
		 * LayoutAlgorithm是一个枚举用来控制页面的布局，有三个类型：
		 * 1.NARROW_COLUMNS：可能的话使所有列的宽度不超过屏幕宽度
		 * 2.NORMAL：正常显示不做任何渲染
		 * 3.SINGLE_COLUMN：把所有内容放大webview等宽的一列中
		 * 用SINGLE_COLUMN类型可以设置页面居中显示，
		 * 页面可以放大缩小，但这种方法不怎么好，
		 * 有时候会让你的页面布局走样而且我测了一下，只能显示中间那一块，超出屏幕的部分都不能显示。
		 */
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(false);// 用于设置webview放大
        settings.setBuiltInZoomControls(false);

        mProgressBar= (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home== item.getItemId()){
            onBackPressed();
        }
        return false;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void dismissProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void loadData(NewsDetailEntity entity) {
        mTitleTv.setText(entity.getTitle());
        mDepartmentTv.setText(entity.getOperationDeptName());
        mAuthorTv.setText(entity.getOperatorName());
        mTimeTv.setText(entity.getAddTime());

//        String fortSizeStr= "font-size: "+mTextSize+"px;";
//        Log.e("fortSizeStr",fortSizeStr);

//        String mHtmlStr= entity.getContent()
//                .replaceAll("img src=\"", "img style=\" width:100%; height:auto;\" src=\"")
////                .replaceAll("img src=\"", "img style=\" width:100%; height:auto;\" src=\"" + MyApplication.getInstance().getBaseUrl())
////                .replaceAll("href=\"","href=\""+ MyApplication.getInstance().getBaseUrl())
//                .replaceAll("line-height:(.*?);", "line-height: 180%;")
//                .replaceAll("text-indent:(.*?);", "text-indent: 2em;")
//                .replaceAll("font-size:(.*?);", "font-size:16px;");

        String mHtmlStr= entity.getContent()
                .replaceAll("img src=\"", "img style=\" width:100%; height:auto;\" src=\"" + "http://192.168.1.68:80/")
                .replaceAll("href=\"","href=\"" + "http://192.168.1.68:80/")
                .replaceAll("line-height:(.*?);", "line-height: 180%;")
                .replaceAll("text-indent:(.*?);", "text-indent: 2em;")
                .replaceAll("font-size:(.*?);", "font-size: 16px;");

//        mContentWv.loadDataWithBaseURL(MyApplication.getInstance().getBaseUrl(), mHtmlStr, "text/html", "UTF-8",null);
        mContentWv.loadDataWithBaseURL("", mHtmlStr, "text/html", "UTF-8",null);
//        mContentWv.loadData(mHtmlStr, "text/html", "UTF-8");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!= null){
            mPresenter.dettachView();
        }
    }
}
