package com.hnsi.oa.hnsi_oa.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * 单位转换工具类
 *
 */
public class DensityUtil {

	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 *
	 * @param pxValue
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int px2dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 *
	 * @param dipValue
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int dp2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 *
	 * @param pxValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 *
	 * @param spValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

//	private DensityUtil() {
//	}
//
//	/**
//	 * dp转px
//	 * @param context
//	 * @param dpVal
//	 * @return
//	 */
//	/*
//	public static int dp2px(Context context, float dpVal){
//		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                dpVal, context.getResources().getDisplayMetrics());
//	}*/
//	public static int dp2px(Context context, float dpVal){
//		float density = context.getResources().getDisplayMetrics().density;
//		return (int) (dpVal * density + 0.5f);
//	}
//
//	/**
//	 * sp转px
//	 *
//	 * @param context
//	 * @param spVal
//	 * @return
//	 */
//	public static int sp2px(Context context, float spVal) {
//		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
//				spVal, context.getResources().getDisplayMetrics());
//	}
//
//	/**
//	 * px转dp
//	 *
//	 * @param context
//	 * @param pxVal
//	 * @return
//	 */
//	public static float px2dp(Context context, float pxVal) {
//		final float scale = context.getResources().getDisplayMetrics().density;
//		return (pxVal / scale);
//	}
//
//	/**
//	 * px转sp
//	 *
//	 * @param context
//	 * @param pxVal
//	 * @return
//	 */
//	public static float px2sp(Context context, float pxVal) {
//		return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
//	}
	
}
