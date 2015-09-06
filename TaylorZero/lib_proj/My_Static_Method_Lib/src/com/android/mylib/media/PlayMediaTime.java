package com.android.mylib.media;

public class PlayMediaTime {

	public static class MediaTimer {
		public int hour;
		public int minute;
		public int second;

		public MediaTimer() {
			hour = minute = second = 0;
		}
	}

	public static MediaTimer getCurrentPlayTime(int time) {
		MediaTimer ret = new MediaTimer();
		time /= 1000;
		int tmpMinute = time / 60;
		int tmpHour = tmpMinute / 60;
		int tmpSecond = time % 60;
		tmpMinute %= 60;
		ret.hour = tmpHour;
		ret.minute = tmpMinute;
		ret.second = tmpSecond;
		return ret;
	}
}
