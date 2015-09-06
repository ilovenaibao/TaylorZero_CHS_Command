package com.android.mylib.loadingview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.android.mylib.exceptioninfo.MyExceptionInfo;
import com.android.mylib.staticmethod.R;

public class MyLoadingView {
	public final static String[] exceptionCut = { "->", // 0
	};

	public final static String[] exceptionInfo = { "MyLoadingView", // 0
			"com.android.mylib.loadingview", // 1
			"MyLoadingView class", // 2
			"MyLoadingView.java", // 3
	};

	public final static int[] loadingViewKind = { R.layout.my_static_method_lib_loading_view_1 // 0
	};

	Context mContext;
	int viewKind;
	public View selectLoadingView = null;
	boolean isShowing = false;

	public MyLoadingView() {
		isShowing = false;
	}

	public MyLoadingView(Context context, int viewKind) {
		mContext = context;
		this.viewKind = viewKind;
		isShowing = false;
	}

	public void setOneLoadingView(Context context, int viewKind) {
		mContext = context;
		this.viewKind = viewKind;
		if (null != context) {
			switch (viewKind) {
			case 0:
				LayoutInflater inflater = LayoutInflater.from(context);
				selectLoadingView = inflater.inflate(loadingViewKind[viewKind],
						null);
				if (null != selectLoadingView) {
					setShowingFlag(false);
				}
				break;
			}
		}
	}

	public void setSelectViewBackgroundColor(int color) {
		if (null != selectLoadingView) {
			selectLoadingView.setBackgroundColor(color);
		}
	}

	public void setSelectViewParams(LayoutParams lp) {
		if (null != selectLoadingView) {
			selectLoadingView.setLayoutParams(lp);
		}
	}

	public boolean getShowingFlag() {
		return isShowing;
	}
	
	public boolean setShowingFlag(boolean flag) {
		return isShowing = flag;
	}

	public void showView() {
		if (null != selectLoadingView) {
			selectLoadingView.setVisibility(View.VISIBLE);
			isShowing = true;
		} else {
			Exception e = new Exception();
			StackTraceElement[] mStackTrace = Thread.currentThread()
					.getStackTrace();
			StackTraceElement[] mStackTrace2 = new StackTraceElement[1];
			mStackTrace2[0] = MyExceptionInfo.getMyTargetExceptionInfo(
					mStackTrace, exceptionInfo[0]);
			e.setStackTrace(mStackTrace2);
			e.printStackTrace();
		}
	}

	public void dissmisView() {
		if (null != selectLoadingView) {
			selectLoadingView.setVisibility(View.GONE);
			isShowing = false;
		} else {
			Exception e = new Exception();
			StackTraceElement[] mStackTrace = Thread.currentThread()
					.getStackTrace();
			StackTraceElement[] mStackTrace2 = new StackTraceElement[1];
			mStackTrace2[0] = MyExceptionInfo.getMyTargetExceptionInfo(
					mStackTrace, exceptionInfo[0]);
			e.setStackTrace(mStackTrace2);
			e.printStackTrace();
		}
	}
}
