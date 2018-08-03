package com.hnsi.oa.hnsi_oa.application.interfaces;

/**
 * 异步数据加载监听
 */
public interface IAsyncLoadListener<T> {

	/**
	 * 数据获取成功时回调
	 * @param t
	 */
	void onSuccess(T t);
	
	/**
	 * 数据获取失败时回调
	 * @param msg 失败信息
	 */
	void onFailure(String msg);
	
}
