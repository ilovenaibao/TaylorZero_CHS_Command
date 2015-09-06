package com.android.mylib.screen;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MyLibScreenSetting {
	public static final int SCREEN_SHOW_THEME_FULL_SCREEN = 0x0001;
	public static final int SCREEN_SHOW_THEME_NO_TITLE = 0x0002;

	/**
	 * @describ setting screen horizontal
	 * 
	 * @param context
	 *            : Context
	 */
	public static void SettingScreenHorizontal(Context context) {
		if (((Activity) context).getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
			((Activity) context)
					.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
	}

	public static void SettingScreenShowTheme(Context context, int kind) {
		switch (kind) {
		case SCREEN_SHOW_THEME_FULL_SCREEN:
			((Activity) context).requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
			((Activity) context).getWindow().setFlags(
					WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);// FULL SCREEN
			break;
		case SCREEN_SHOW_THEME_NO_TITLE:
			break;
		}
	}

	public static MyLibScreenInfo GetScreenSize(Context context) {
		MyLibScreenInfo scrInfo = new MyLibScreenInfo();
		DisplayMetrics DM = new DisplayMetrics();
		// 获取窗口管理器,获取当前的窗口,调用getDefaultDisplay()后，其将关于屏幕的一些信息写进DM对象中,最后通过getMetrics(DM)获取
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(DM);
		// 打印获取的宽和高
		scrInfo.scrWidth = DM.widthPixels;// 屏幕宽度 (px)
		scrInfo.scrHeight = DM.heightPixels;// 屏幕高度 (px)
		return scrInfo;
	}

	@SuppressWarnings("deprecation")
	public static MyLibScreenInfo GetScreenSize(Context context, int kind) {
		MyLibScreenInfo ret = new MyLibScreenInfo();
		int screenWidth = 0; // 屏幕宽（像素，如：480px）
		int screenHeight = 0; // 屏幕高（像素，如：800p）
		DisplayMetrics dm = new DisplayMetrics();
		float density = 0; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
		int densityDPI = 0; // 屏幕密度（每寸像素：120/160/240/320）
		float xdpi = 0;
		float ydpi = 0;
		switch (kind) {
		case 1:
			// 获取屏幕密度（方法1）
			screenWidth = ((Activity) context).getWindowManager()
					.getDefaultDisplay().getWidth(); // 屏幕宽（像素，如：480px）
			screenHeight = ((Activity) context).getWindowManager()
					.getDefaultDisplay().getHeight(); // 屏幕高（像素，如：800p）

			Log.e("  getDefaultDisplay", "screenWidth=" + screenWidth
					+ "; screenHeight=" + screenHeight);
			break;
		case 2:
			// 获取屏幕密度（方法2）
			dm = ((Activity) context).getResources().getDisplayMetrics();
			density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
			densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
			xdpi = dm.xdpi;
			ydpi = dm.ydpi;

			Log.e("  DisplayMetrics", "xdpi=" + xdpi + "; ydpi=" + ydpi);
			Log.e("  DisplayMetrics", "density=" + density + "; densityDPI="
					+ densityDPI);

			screenWidth = dm.widthPixels; // 屏幕宽（像素，如：480px）
			screenHeight = dm.heightPixels; // 屏幕高（像素，如：800px）

			Log.e("  DisplayMetrics(111)", "screenWidth=" + screenWidth
					+ "; screenHeight=" + screenHeight);
			break;
		case 3:
			// 获取屏幕密度（方法3）
			dm = new DisplayMetrics();
			((Activity) context).getWindowManager().getDefaultDisplay()
					.getMetrics(dm);

			density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
			densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
			xdpi = dm.xdpi;
			ydpi = dm.ydpi;

			Log.e("  DisplayMetrics", "xdpi=" + xdpi + "; ydpi=" + ydpi);
			Log.e("  DisplayMetrics", "density=" + density + "; densityDPI="
					+ densityDPI);

			int screenWidthDip = dm.widthPixels; // 屏幕宽（dip，如：320dip）
			int screenHeightDip = dm.heightPixels; // 屏幕宽（dip，如：533dip）

			Log.e("  DisplayMetrics(222)", "screenWidthDip=" + screenWidthDip
					+ "; screenHeightDip=" + screenHeightDip);

			screenWidth = (int) (dm.widthPixels * density + 0.5f); // 屏幕宽（px，如：480px）
			screenHeight = (int) (dm.heightPixels * density + 0.5f); // 屏幕高（px，如：800px）

			Log.e("  DisplayMetrics(222)", "screenWidth=" + screenWidth
					+ "; screenHeight=" + screenHeight);
			break;
		}
		ret.scrWidth = screenWidth;
		ret.scrHeight = screenHeight;

		return ret;
	}
}
