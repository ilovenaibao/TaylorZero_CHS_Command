package com.android.mylib.screen;

public class MyLibScreenInfo {
	public int scrWidth;
	public int scrHeight;
	public float density = 0; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
	public int densityDPI = 0; // 屏幕密度（每寸像素：120/160/240/320）
	public float xdpi = 0;
	public float ydpi = 0;

	public MyLibScreenInfo() {
		scrWidth = scrHeight = densityDPI = 0;
		density = xdpi = ydpi = 0;
	}
}
