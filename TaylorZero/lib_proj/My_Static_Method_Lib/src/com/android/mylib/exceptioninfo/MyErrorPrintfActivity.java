package com.android.mylib.exceptioninfo;

import com.android.mylib.staticmethod.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MyErrorPrintfActivity extends Activity {
	public final static String ERROR_PRINTF_KEY = "ERROR_PRINTF_KEY";

	/**
	 * 調用顯示error (It show exception error on screen)
	 * 
	 * @param context
	 *            activity
	 * @param e
	 *            Exception info
	 */
	public static void startMyErrorPrintfActivity(Context context, Exception e) {
		StackTraceElement[] errorStackInfo = e.getStackTrace();
		int errorInfoSize = errorStackInfo.length;
		String[] error = null;
		if (0 >= errorInfoSize) {
			error = new String[1];
			error[0] = e.toString();
		} else {
			error = new String[errorInfoSize + 1];
			error[0] = e.toString();
			int errorInfoCount = 0;
			for (errorInfoCount = 0; errorInfoCount < errorInfoSize; errorInfoCount++) {
				error[errorInfoCount + 1] = errorStackInfo[errorInfoCount]
						.toString();
			}
		}
		Intent errorActivity = new Intent(context, MyErrorPrintfActivity.class);
		Bundle bundle = new Bundle();
		bundle.putStringArray(MyErrorPrintfActivity.ERROR_PRINTF_KEY, error);
		errorActivity.putExtras(bundle);
		try {
			((Activity) context).startActivity(errorActivity);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
    /**
     * @Describe call to show one error string on screen
     *  
     * @param context : parent activity
	 * @param e : String[] type and show on screen
     */
	public static void startMyErrorStringActivity(Context context, String[] e) {
		Intent errorActivity = new Intent(context, MyErrorPrintfActivity.class);
		Bundle bundle = new Bundle();
		bundle.putStringArray(MyErrorPrintfActivity.ERROR_PRINTF_KEY, e);
		errorActivity.putExtras(bundle);
		try {
			((Activity) context).startActivity(errorActivity);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_static_method_lib_error_printf_view);
		Bundle bundle = getIntent().getExtras();
		if (null != bundle) {
			String[] error_printf = bundle.getStringArray(ERROR_PRINTF_KEY);
			TextView errorPrintfTextView = (TextView) findViewById(R.id.error_printf_tv);
			if (null != errorPrintfTextView && null != error_printf) {
				String showInfoStr = new String("");
				int totalCount = error_printf.length;
				int count = 0;
				for (count = 0; count < totalCount; count++) {
					showInfoStr += error_printf[count] + "\r\n";
				}
				errorPrintfTextView.setText(showInfoStr);
			}
		}
	}

}
