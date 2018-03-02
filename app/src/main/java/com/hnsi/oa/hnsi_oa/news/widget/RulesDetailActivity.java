package com.hnsi.oa.hnsi_oa.news.widget;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.app.MyApplication;
import com.hnsi.oa.hnsi_oa.beans.RuleDetailEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.app.BaseActivity;
import com.hnsi.oa.hnsi_oa.widgets.MyRuleListAdapter;

/**
 * 规章制度详情展示页
 * Created by Zheng on 2016/5/10.
 */
public class RulesDetailActivity extends BaseActivity{

    /** 规章制度内容显示区控件 */
    //规章制度标题
    private TextView mRulesTitleTv;
    //规章制度发布人
    private TextView mAuthorTv;
    //规章制度发布时间
    private TextView mTimeTv;
    //规章制度内容
    private WebView mContentWv;

    private int mRuleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_detail);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!= null){
            getSupportActionBar().setTitle("规章制度详情");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initUI();
        mRuleId= getIntent().getIntExtra(MyRuleListAdapter.RULE_ID, 0);
        if (mRuleId== 0){
            Toast.makeText(this, "获取系统信息失败，请重试！", Toast.LENGTH_LONG).show();
            finish();
        }


        MyApplication.getInstance().getRuleDetail(mRuleId, new OnRequestDataListener<RuleDetailEntity>() {
            @Override
            public void onSuccessed(RuleDetailEntity ruleDetailEntity) {
                mRulesTitleTv.setText(ruleDetailEntity.getTitle());
                mAuthorTv.setText(ruleDetailEntity.getOperatorName());
                String addTime=ruleDetailEntity.getOperationTime().substring(0, 19);
                mTimeTv.setText(addTime);

                String contentStr=ruleDetailEntity.getContent()
                        .replaceAll("img src=\"", "img style=\" width:100%; height:auto;\" src=\"" + "http://192.168.1.68:80/")
                        .replaceAll("href=\"","href=\"" + "http://192.168.1.68:80/")
                        .replaceAll("line-height:(.*?);", "line-height: 180%;")
                        .replaceAll("text-indent:(.*?);", "text-indent: 2em;")
                        .replaceAll("font-size:(.*?);", "font-size: 16px;");

                Log.e("contentStr", contentStr);
//                            mContentWv.loadData(news.getString("content"), "text/html", "UTF-8");
                mContentWv.loadDataWithBaseURL("", contentStr, "text/html", "UTF-8",null);
            }

            @Override
            public void onFailed(String throwable) {
                Toast.makeText(RulesDetailActivity.this, throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化界面
     */
    private void initUI() {
        //加载并设置公告标题
        mRulesTitleTv = (TextView) findViewById(R.id.rules_detail_tv_title);

        //加载并设置公告发布人
        mAuthorTv= (TextView) findViewById(R.id.rules_detail_tv_author);

        //加载并设置公告发布时间
        mTimeTv= (TextView) findViewById(R.id.rules_detail_tv_time);

        //加载并设置公告内容
        mContentWv= (WebView) findViewById(R.id.rules_detail_wv_content);
        // 设置WebView
        WebSettings settings = mContentWv.getSettings();
        // 设置可以运行JS脚本!!!!
        settings.setJavaScriptEnabled(true);
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return true;
    }

}
