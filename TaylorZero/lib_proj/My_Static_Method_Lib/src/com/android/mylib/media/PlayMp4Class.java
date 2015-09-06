package com.android.mylib.media;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.mylib.staticmethod.R;

public class PlayMp4Class {
	private Context mContext = null;
	/* 创建VideoView对象 */
	private VideoView videoView = null;
	private boolean isPlaying_pre = false;

	public PlayMp4Class(Context context) {
		mContext = context;
	}

	// test play Mp4
	public void initializeMp4Class() {
		// LayoutInflater inflater = LayoutInflater.from(mContext);
		// View v = inflater.inflate(R.layout.taylorzero_activity_main, null);

		String mp4_path = "/mnt/sdcard/Zero/zero_opening.mp4";
		videoView = (VideoView) ((Activity) mContext)
				.findViewById(R.id.my_static_method_lib_media_mp4_view);
		/* 设置路径 */
		videoView.setVideoPath(mp4_path);
		videoView.requestFocus();

		videoView.setOnPreparedListener(mp4_prepare_listener);
	}

	OnPreparedListener mp4_prepare_listener = new OnPreparedListener() {

		@Override
		public void onPrepared(MediaPlayer mp) {
			// TODO Auto-generated method stub
			int a = 0;
			a = 1;
		}
	};

	OnClickListener mp4_play_onClick_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			doPlayMp4File();
		}
	};

	OnClickListener mp4_stop_onClick_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			doStopMp4File();
		}
	};

	OnClickListener mp4_pause_onClick_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			doPauseMp4File();
		}
	};

	public void doPlayMp4File() {
		videoView.start();
		isPlaying_pre = true;
	}

	public void doStopMp4File() {
		videoView.seekTo(0);
		videoView.pause();
		isPlaying_pre = false;
	}

	public void doPauseMp4File() {
		videoView.pause();
		isPlaying_pre = false;
	}

	public void doResume() {
		if (isPlaying_pre) {
			doPlayMp4File();
		}
	}

	public void doPause() {
		boolean tmpPlaying = isPlaying_pre;
		doPauseMp4File();
		if (tmpPlaying) {
			isPlaying_pre = tmpPlaying;
		}
	}

	public void doDestroy() {
		doStopMp4File();
	}
}
