package com.android.mylib.media;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mylib.staticmethod.R;

public class PlayMp3Class {
	private Context mContext = null;
	private MediaPlayer mediaplayer_mp3 = null;

	private boolean isPlaying_pre = false;
	private int start_seekTo = 0;

	public PlayMp3Class(Context context) {
		mContext = context;
	}

	// test play Mp3
	public void initializeMp3Class(String mp3_path) {
		// LayoutInflater inflater = LayoutInflater.from(mContext);
		// View v = inflater.inflate(R.layout.taylorzero_activity_main, null);

		isPlaying_pre = false;
		String path = "/mnt/sdcard/Zero/test.mp3";
		Uri mp3_uri = Uri.fromFile(new File(mp3_path));
		mediaplayer_mp3 = MediaPlayer.create(mContext, mp3_uri);
	}

	OnClickListener mp3_play_onClick_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			doPlayMp3File();
		}
	};

	OnClickListener mp3_stop_onClick_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			doStopMp3File();
		}
	};

	OnClickListener mp3_pause_onClick_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			doPauseMp3File();
		}
	};

	public void doPlayMp3File() {
		try {
			if (mediaplayer_mp3 != null) {
				mediaplayer_mp3.stop();
			}
			mediaplayer_mp3.prepare();
			mediaplayer_mp3.start();
			isPlaying_pre = true;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doStopMp3File() {
		boolean isPlaying = mediaplayer_mp3.isPlaying();
		if (isPlaying) {
			try {
				mediaplayer_mp3.seekTo(start_seekTo);
				mediaplayer_mp3.stop();
				isPlaying_pre = false;
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
	}

	public void doPauseMp3File() {
		boolean isPlaying = mediaplayer_mp3.isPlaying();
		if (isPlaying) {
			mediaplayer_mp3.pause();
			isPlaying_pre = false;
		}
	}

	public void doResume() {
		if (isPlaying_pre) {
			doPlayMp3File();
		}
	}

	public void doPause() {
		boolean tmpPlaying = isPlaying_pre;
		doPauseMp3File();
		if (tmpPlaying) {
			isPlaying_pre = tmpPlaying;
		}
	}

	public void doDestroy() {
		doStopMp3File();
	}
}
