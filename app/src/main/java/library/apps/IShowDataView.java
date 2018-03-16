package library.apps;

/**
 * 显示数据的View公共接口
 * Created by Zheng on 2018/3/14.
 */

public interface IShowDataView extends IBaseView {
    /**
     * 显示加载框
     */
    void showProgressBar();

    /**
     * 隐藏加载框
     */
    void dismissProgressBar();

    /**
     * 显示没有数据提示
     */
    void showEmptyTip();

    /**
     * 显示错误提示
     * @param errorMsg
     */
    void showErrorTip(String errorMsg);
}
