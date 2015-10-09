package com.android.mylib.io;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MPUStartBroadcastReceiver extends BroadcastReceiver {

	/**
	 * 接收系统启动完成广播
	 * 还要在Manifest.xml中添加接收系统启动完毕的广播
	 * 	1. RECEIVE_BOOT_COMPLETED系统启动完成广播
	 * 	2. DISABLE_KEYGUARD允许程序禁用键盘锁
	 * */
	@Override
	public void onReceive(Context context, Intent intent) {
		//取消系统锁屏	
		KeyguardManager mKeyguardManager = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
		KeyguardLock mKeyguardLock = mKeyguardManager.newKeyguardLock("MainActivity");
		mKeyguardLock.disableKeyguard();
		intent = new Intent(context, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);//启动意图
	}
}