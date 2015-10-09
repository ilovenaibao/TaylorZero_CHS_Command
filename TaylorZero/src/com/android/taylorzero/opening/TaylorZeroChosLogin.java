package com.android.taylorzero.opening;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.android.taylorzero.R;
import com.android.taylorzero.TaylorZeroPlayWidgetSound;
import com.android.taylorzero.login.pic.TaylorZeroPicActivity2;
import com.android.taylorzero.login.preface.TaylorZeroPreFaceActivity;
import com.android.taylorzero.login.start.TaylorZeroStartActivity;
import com.android.taylorzero.setting.TaylorZeroLoginSetting;
import com.android.taylorzero.setting.TaylorZeroOpeningSetting;
import com.android.taylorzero.setting.TaylorZeroSetting;


public class TaylorZeroChosLogin {

	public enum LOGIN_TYPE {  
		START,
		LOADING,
		PIC,
		EXIT,
		PRE_FACE,
		MAX_TYPE
	}

	private Context mContext = null;
	TextView[] tvLoginType = new TextView[LOGIN_TYPE.MAX_TYPE.ordinal()];

	public TaylorZeroChosLogin(Context context) {
		mContext = context;
	}

	public void initializeChosLogin() {
		// initialize ui for login
		tvLoginType[LOGIN_TYPE.START.ordinal()] = (TextView) ((Activity) mContext)
		.findViewById(R.id.login_tv_start);
		tvLoginType[LOGIN_TYPE.LOADING.ordinal()] = (TextView) ((Activity) mContext)
		.findViewById(R.id.login_tv_loading);
		tvLoginType[LOGIN_TYPE.PIC.ordinal()] = (TextView) ((Activity) mContext)
		.findViewById(R.id.login_tv_pic);
		tvLoginType[LOGIN_TYPE.EXIT.ordinal()] = (TextView) ((Activity) mContext)
		.findViewById(R.id.login_tv_exit);
		tvLoginType[LOGIN_TYPE.PRE_FACE.ordinal()] = (TextView) ((Activity) mContext)
		.findViewById(R.id.login_tv_preface);

		tvLoginType[LOGIN_TYPE.START.ordinal()].setOnClickListener(loginTypeListener);
		tvLoginType[LOGIN_TYPE.LOADING.ordinal()].setOnClickListener(loginTypeListener);
		tvLoginType[LOGIN_TYPE.PIC.ordinal()].setOnClickListener(loginTypeListener);
		tvLoginType[LOGIN_TYPE.EXIT.ordinal()].setOnClickListener(loginTypeListener);
		tvLoginType[LOGIN_TYPE.PRE_FACE.ordinal()].setOnClickListener(loginTypeListener);
		// setting sound effects
		tvLoginType[LOGIN_TYPE.START.ordinal()].setSoundEffectsEnabled(false);
		tvLoginType[LOGIN_TYPE.LOADING.ordinal()].setSoundEffectsEnabled(false);
		tvLoginType[LOGIN_TYPE.PIC.ordinal()].setSoundEffectsEnabled(false);
		tvLoginType[LOGIN_TYPE.EXIT.ordinal()].setSoundEffectsEnabled(false);
		tvLoginType[LOGIN_TYPE.PRE_FACE.ordinal()].setSoundEffectsEnabled(false);

		try {
			Typeface font = Typeface.createFromAsset(mContext.getAssets(),
			"font/cn_test.ttf");
			tvLoginType[LOGIN_TYPE.START.ordinal()].setTypeface(font, Typeface.BOLD);
			tvLoginType[LOGIN_TYPE.LOADING.ordinal()].setTypeface(font, Typeface.BOLD);
			tvLoginType[LOGIN_TYPE.PIC.ordinal()].setTypeface(font, Typeface.BOLD);
			tvLoginType[LOGIN_TYPE.EXIT.ordinal()].setTypeface(font, Typeface.BOLD);
			tvLoginType[LOGIN_TYPE.PRE_FACE.ordinal()].setTypeface(font, Typeface.BOLD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setOnActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case TaylorZeroLoginSetting.START_ACTIVITY_REQUESTCODE_LOGIN_START:
			break;
		case TaylorZeroLoginSetting.START_ACTIVITY_REQUESTCODE_LOGIN_LOADING:
			break;
		case TaylorZeroLoginSetting.START_ACTIVITY_REQUESTCODE_LOGIN_PIC:
			break;
		case TaylorZeroLoginSetting.START_ACTIVITY_REQUESTCODE_LOGIN_EXIT:
			break;
		case TaylorZeroLoginSetting.START_ACTIVITY_REQUESTCODE_LOGIN_PREFACE:
			break;
		}
	}
	
	OnClickListener loginTypeListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			TextView tmpV = (TextView)v;
			if (tmpV == tvLoginType[LOGIN_TYPE.START.ordinal()]) {
				onClickStart();
			} else if (tmpV == tvLoginType[LOGIN_TYPE.LOADING.ordinal()]) {
				onClickLoading();
			} else if (tmpV == tvLoginType[LOGIN_TYPE.PIC.ordinal()]) {
				onClickPic();
			} else if (tmpV == tvLoginType[LOGIN_TYPE.EXIT.ordinal()]) {
				onClickExit();
			} else if (tmpV == tvLoginType[LOGIN_TYPE.PRE_FACE.ordinal()]) {
				onClickPreface();
			}
			
		}
	};

	private void onClickStart() {
		TaylorZeroPlayWidgetSound mWidgetSound = new TaylorZeroPlayWidgetSound(
		mContext);
		mWidgetSound
		.playWidgetSoundMp3(TaylorZeroSetting.Zero_Data_Real_Path
		+ TaylorZeroOpeningSetting.click_start_ui_activity_mp3_path);
		Intent intent = new Intent(mContext, TaylorZeroStartActivity.class);
		try {
			((Activity) mContext)
			.startActivityForResult(
			intent,
			TaylorZeroLoginSetting.START_ACTIVITY_REQUESTCODE_LOGIN_START);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void onClickLoading() {
		TaylorZeroPlayWidgetSound mWidgetSound = new TaylorZeroPlayWidgetSound(
		mContext);
		mWidgetSound
		.playWidgetSoundMp3(TaylorZeroSetting.Zero_Data_Real_Path
		+ TaylorZeroOpeningSetting.click_start_ui_activity_mp3_path);
	}
	
	private void onClickPic() {
		TaylorZeroPlayWidgetSound mWidgetSound = new TaylorZeroPlayWidgetSound(
		mContext);
		mWidgetSound
		.playWidgetSoundMp3(TaylorZeroSetting.Zero_Data_Real_Path
		+ TaylorZeroOpeningSetting.click_start_ui_activity_mp3_path);
		Intent intent = new Intent(mContext, TaylorZeroPicActivity2.class);
		try {
			((Activity) mContext)
			.startActivityForResult(
			intent,
			TaylorZeroLoginSetting.START_ACTIVITY_REQUESTCODE_LOGIN_PIC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void onClickExit() {
		TaylorZeroPlayWidgetSound mWidgetSound = new TaylorZeroPlayWidgetSound(
		mContext);
		mWidgetSound
		.playWidgetSoundMp3(TaylorZeroSetting.Zero_Data_Real_Path
		+ TaylorZeroOpeningSetting.click_start_ui_activity_mp3_path);
		((Activity) mContext).finish();
	}
	
	private void onClickPreface() {
		TaylorZeroPlayWidgetSound mWidgetSound = new TaylorZeroPlayWidgetSound(
		mContext);
		mWidgetSound
		.playWidgetSoundMp3(TaylorZeroSetting.Zero_Data_Real_Path
		+ TaylorZeroOpeningSetting.click_start_ui_activity_mp3_path);
		Intent intent = new Intent(mContext,
		TaylorZeroPreFaceActivity.class);
		try {
			((Activity) mContext)
			.startActivityForResult(
			intent,
			TaylorZeroLoginSetting.START_ACTIVITY_REQUESTCODE_LOGIN_PREFACE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
