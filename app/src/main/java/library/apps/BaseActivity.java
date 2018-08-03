package library.apps;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import library.utils.LogUtil;

/**
 * Created by Zheng on 2017/10/16.
 */

public class BaseActivity extends AppCompatActivity implements IBaseView {

    private final String ACTIVITY_TAG= this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e(ACTIVITY_TAG, ACTIVITY_TAG + " was Created.");
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().removeActivity(this);
        LogUtil.e(ACTIVITY_TAG, ACTIVITY_TAG + " was Destroyed.");
        super.onDestroy();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getRealContext() {
        return this;
    }
}
