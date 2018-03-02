package com.hnsi.oa.hnsi_oa.approval.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.beans.ApprovalWidgetEntity;
import com.hnsi.oa.hnsi_oa.app.BaseActivity;

/**
 * Created by Zheng on 2018/2/10.
 */

public class HtmlInfoActivity extends BaseActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_info);

        ApprovalWidgetEntity entity= (ApprovalWidgetEntity) getIntent().getSerializableExtra("htmlInfo");
        if (entity== null){
            Toast.makeText(this, "附件数据异常！", Toast.LENGTH_SHORT).show();
            finish();
        }

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(entity.getLabel());
        }

        mWebView= (WebView) findViewById(R.id.html_webview);
        WebSettings settings = mWebView.getSettings();
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

        String mHtmlStr= entity.getValue()
                .replaceAll("img src=\"", "img style=\" width:100%; height:auto;\" src=\"" + "http://192.168.1.68:80/")
                .replaceAll("href=\"","href=\"" + "http://192.168.1.68:80/")
                .replaceAll("line-height:(.*?);", "line-height: 180%;")
                .replaceAll("text-indent:(.*?);", "text-indent: 2em;")
                .replaceAll("font-size:(.*?);", "font-size: 16px;");

//        mContentWv.loadDataWithBaseURL(MyApplication.getInstance().getBaseUrl(), mHtmlStr, "text/html", "UTF-8",null);
        mWebView.loadDataWithBaseURL("", mHtmlStr, "text/html", "UTF-8",null);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return true;
    }

}
