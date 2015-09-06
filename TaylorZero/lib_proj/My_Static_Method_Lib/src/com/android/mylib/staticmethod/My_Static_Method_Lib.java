package com.android.mylib.staticmethod;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;
import android.widget.Toast;

import com.android.mylib.exceptioninfo.MyErrorPrintfActivity;

public class My_Static_Method_Lib {

	public My_Static_Method_Lib() {

	}

	public static int getAndroidSDKVersion() {
		int version = 0;
		try {
			version = Integer.valueOf(android.os.Build.VERSION.SDK);
		} catch (NumberFormatException e) {
			Log.e(e.toString(), null);
		}
		return version;
	}
	
	/**
	* @describe Get res absolute path
	* 
	* @param resName
	*            : res name
	* @return String : return one path of res absolue path
	*/

	public static String getResAbsolutePath(Context context, String resName,
	boolean debugFlag) {
		String ret = "";
		StorageManager mStorageManager = null;
		Method mMethodGetPaths = null;
		String[] mStoragePaths = null;

		mStorageManager = (StorageManager) ((Activity) context)
		.getSystemService(Activity.STORAGE_SERVICE);
		try {
			mMethodGetPaths = mStorageManager.getClass().getMethod(
			"getVolumePaths");
		} catch (NoSuchMethodException e) {
			if (debugFlag) {
				MyErrorPrintfActivity.startMyErrorPrintfActivity(
				((Activity) context), e);
			} else {
				e.printStackTrace();
				Toast.makeText(((Activity) context), R.string.appShutdownStr,
				Toast.LENGTH_SHORT).show();
			}
		}
		mStoragePaths = getVolumePaths(mStorageManager);
		if (null != mStoragePaths) {
			for (int count = 0; count < mStoragePaths.length; count++) {
				String tmpFilePath = mStoragePaths[count] + File.separator
				+ resName;
				File tmpFile = new File(tmpFilePath);
				if (tmpFile.exists()) {
					ret = tmpFilePath;
					break;
				}
			}
		}

		return ret;
	}

	/**
	* @describe new get flash path function
	* 
	* @param storageManager
	*            : ****
	* @return String[] : ****
	*/

	public static String[] getVolumePaths(StorageManager storageManager) {
		String[] ret = null;
		if (storageManager == null) {
			return ret;
		}
		Method getVolumePathsMethod = null;
		try {
			getVolumePathsMethod = storageManager.getClass().getDeclaredMethod(
			"getVolumePaths");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		if (getVolumePathsMethod != null) {
			getVolumePathsMethod.setAccessible(true);
			try {
				ret = (String[]) getVolumePathsMethod.invoke(storageManager);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		return ret;
	}

	public static String getVolumeState(StorageManager storageManager,
	String path) {
		String ret = Environment.MEDIA_REMOVED;
		if (storageManager == null) {
			return ret;
		}
		Method getVolumeStateMethod = null;
		try {
			getVolumeStateMethod = storageManager.getClass().getDeclaredMethod(
			"getVolumeState", String.class);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (getVolumeStateMethod != null) {
			getVolumeStateMethod.setAccessible(true);
			try {
				ret = (String) getVolumeStateMethod
				.invoke(storageManager, path);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
}