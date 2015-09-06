package com.android.mylib.exceptioninfo;

import android.content.Context;

public class MyExceptionInfo {

	public MyExceptionInfo() {

	}

	public static StackTraceElement getMyTargetExceptionInfo(
			StackTraceElement[] mStackTraceElements, String indexOfStr) {
		StackTraceElement ret = null;
		int count = 0;
		int totalCount = mStackTraceElements.length;

		for (count = 0; count < totalCount; count++) {
			if (0 < mStackTraceElements[count].getClassName().indexOf(
					indexOfStr)) {
				ret = mStackTraceElements[count];
				break;
			}
		}

		return ret;
	}

	/**
	 * check library has exist
	 * 
	 * @param className
	 *            class name eg."android.app.ActionBar"
	 * @return true: exist | false: not
	 */
	public static boolean checkTargetClassExist(Context context,
			String pkgName, String className) {
		boolean bRet = false;
		try {
			Class.forName(className);
			bRet = true;
		} catch (ClassNotFoundException e) {

		}
		return bRet;
	}

	/**
	 * 获取函数名称
	 */
	public String getFunctionName() {
		StackTraceElement[] sts = Thread.currentThread().getStackTrace();
		if (sts == null) {
			return null;
		}
		for (StackTraceElement st : sts) {
			if (st.isNativeMethod()) {
				continue;
			}
			if (st.getClassName().equals(Thread.class.getName())) {
				continue;
			}
			if (st.getClassName().equals(this.getClass().getName())) {
				continue;
			}
			return "[" + Thread.currentThread().getName() + "("
					+ Thread.currentThread().getId() + "): " + st.getFileName()
					+ ":" + st.getLineNumber() + "]";
		}
		return null;
	}
}
