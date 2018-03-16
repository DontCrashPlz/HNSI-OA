package library.apps;

import android.content.Context;

/**
 * 所有View的公共接口
 * Created by Zheng on 2018/3/14.
 */

public interface IBaseView {
    /**
     * Toast弹出提示
     * @param msg
     */
    void showToast(String msg);

    /**
     * 获取页面真实的Context
     * @return
     */
    Context getRealContext();
}
