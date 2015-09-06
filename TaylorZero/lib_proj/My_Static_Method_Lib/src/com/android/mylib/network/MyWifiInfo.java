package com.android.mylib.network;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.provider.Settings;

public class MyWifiInfo {

	// check android device wifi connect
	public static boolean checkWifiConnect(Context context) {
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// Check network
	public void CheckNetworkState(Context mContext) {
		boolean flag = false;
		ConnectivityManager manager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		State mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();
		State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		// 如果3G、wifi、2G等网络状态是连接的，则退出，否则显示提示信息进入网络设置界面
		if (mobile == State.CONNECTED || mobile == State.CONNECTING) {
			return;
		} else if (wifi == State.CONNECTED || wifi == State.CONNECTING) {
			return;
		}

		showTips(mContext);
	}

	private void showTips(final Context mContext) {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setTitle("netstate");
		builder.setMessage("setnetwork");
		builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 如果没有网络连接，则进入网络设置界面
				try {
					mContext.startActivity(new Intent(
							Settings.ACTION_WIRELESS_SETTINGS));
				} catch (Exception e) {

				}
			}
		});
		builder.setNegativeButton("cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		builder.create();
		builder.show();
	}
}
