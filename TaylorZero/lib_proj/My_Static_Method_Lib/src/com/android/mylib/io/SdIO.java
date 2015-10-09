package com.android.mylib.io;

import java.io.File;
import java.util.List;
import android.os.StatFs;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import android.os.Environment;
import android.content.Context;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;

public class StorageUtil {
	private static final int ERROR = -1;
	public static int save_dir = 1;

	
	//判断是否已经安装SD卡
	public static boolean isSDCardExist() {
		return android.os.Environment.getExternalStorageState().equals(
		android.os.Environment.MEDIA_MOUNTED);
	}
	
	
	
	//内存清理
	//注意权限:
	//
	public static void cleanMemory(Context context){
		System.out.println("---> 清理前可用内存:"+getAvailMemory(context)+"MB");
		ActivityManager activityManager=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		RunningAppProcessInfo runningAppProcessInfo=null;
		List  runningAppProcessInfoList= activityManager.getRunningAppProcesses();
		//List serviceInfos = mActivityManager.getRunningServices(100);

		if (runningAppProcessInfoList != null) {
			for (int i = 0; i  RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
				String[] pkgList = runningAppProcessInfo.pkgList;
				for (int j = 0; j  正在清理:"+pkgList[j]);
						activityManager.killBackgroundProcesses(pkgList[j]);
					}
				}

			}
		}
		System.out.println("---> 清理后可用内存:"+getAvailMemory(context)+"MB");
	}

	
	
	//获取内存总大小
	public static long getTotalMemory() {
		//系统的内存信息文件
		String filePath = "/proc/meminfo";
		String lineString;
		String[] stringArray;
		long totalMemory = 0;
		try {
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader,1024 * 8);
			//读取meminfo第一行,获取系统总内存大小
			lineString = bufferedReader.readLine();
			//按照空格拆分
			stringArray = lineString.split("\\s+");
			//获得系统总内存,单位KB
			totalMemory = Integer.valueOf(stringArray[1]).intValue();
			bufferedReader.close();
		} catch (IOException e) {
		}
		return totalMemory / 1024;
	}
	
	
	
	//获取可用内存大小
	public static long getAvailMemory(Context context) {
		ActivityManager activityManager=(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo memoryInfo = new MemoryInfo();
		activityManager.getMemoryInfo(memoryInfo);
		return memoryInfo.availMem / (1024 * 1024);
	}

	
	
	//SD卡剩余空间
	public static long getAvailableExternalMemorySize() {
		if (isSDCardExist()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return availableBlocks * blockSize;
		} else {
			return ERROR;
		}
	}

	
	
	//SD卡总空间
	public static long getTotalExternalMemorySize() {
		if (isSDCardExist()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long totalBlocks = stat.getBlockCount();
			return totalBlocks * blockSize;
		} else {
			return ERROR;
		}
	}

	
	
	//判断SD卡下external_sd文件夹的总大小
	public static long getTotalExternal_SDMemorySize() {
		if (isSDCardExist()) {
			File path = Environment.getExternalStorageDirectory();
			File externalSD = new File(path.getPath() + "/external_sd");
			if (externalSD.exists() && externalSD.isDirectory()) {
				StatFs stat = new StatFs(path.getPath() + "/external_sd");
				long blockSize = stat.getBlockSize();
				long totalBlocks = stat.getBlockCount();
				if (getTotalExternalMemorySize() != -1
						&& getTotalExternalMemorySize() != totalBlocks
								* blockSize) {
					return totalBlocks * blockSize;
				} else {
					return ERROR;
				}
			} else {
				return ERROR;
			}

		} else {
			return ERROR;
		}
	}
	
	
	
	//判断SD卡下external_sd文件夹的可用大小
	public static long getAvailableExternal_SDMemorySize() {
		if (isSDCardExist()) {
			File path = Environment.getExternalStorageDirectory();
			File externalSD = new File(path.getPath() + "/external_sd");
			if (externalSD.exists() && externalSD.isDirectory()) {
				StatFs stat = new StatFs(path.getPath() + "/external_sd");
				long blockSize = stat.getBlockSize();
				long availableBlocks = stat.getAvailableBlocks();
				if (getAvailableExternalMemorySize() != -1
						&& getAvailableExternalMemorySize() != availableBlocks
								* blockSize) {
					return availableBlocks * blockSize;
				} else {
					return ERROR;
				}

			} else {
				return ERROR;
			}

		} else {
			return ERROR;
		}
	}
}