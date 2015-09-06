package com.android.taylorzero.setting;

public class TaylorZeroOpeningSetting {
	public static final int MSG_PLAY_OPENING_END = 0x0001;
	public static final int MSG_START_NEW_LAYOUT_ANIMATION = 0x0002;

	public static final String mp4_path = TaylorZeroSetting.Zero_Res_Path
			+ "mp4/";
	public static final String opening_mp4_path = mp4_path + ".zero_opening";
	public static final String preface_mp4_path = mp4_path + ".zero_preface";
	public static final String mp3_path = TaylorZeroSetting.Zero_Res_Path
			+ "sound/";
	public static final String opening_mp3_bg_path = mp3_path
			+ "outer/Zero_start_ui_bg.mp3"; // .Zero_start_ui_bg
	public static final String touch_start_ui_activity_mp3_path = mp3_path
			+ "inter/chos_ui_activity_touch.mp3";
	public static final String click_start_ui_activity_mp3_path = mp3_path
			+ "inter/chos_ui_activity_click_new.mp3";

	public static final String[] preface_mp4_caption = { "�ս�����磬��ʼ�����硣",
		"���紦������֮ʱ��������һ�����硣", "�˷��ܣ�����е����磬������Ϊ���ǲ���������Ե��",
		"������Ϊ�����Ե���������������Զ��ƽ������ȥ��",

		"���������������ʵ�ˮԴ��", "�ܹ��������������壬��ʹת������ʹ����ͬ��������",
		"��ʱ�վ�ͷƮ�����ˣ�ת����������������Ƶģ�Ů��", "��ѡ�е��ˣ�Ů���������������ʹ��������ǰ���Ļ��䡣",
		"���Ǽ̳�����Եƽ���������ţ�������Ҳ�������ܵġ�", "�మ���˱˴˼���ٻ�����˿��֣���ͬʱ��Ԫ��֮���Ĺ������������ӵĻ�����",
		"���ǿ�ʼ�����Ǹ��������е����������Ǹ��������������Ǹ����䡣",

		"����ı����ߴΣ�����������֮ʱ��û�˼ǵ��Ǹ�������", "���绹û��������ʱ�������һ�����硣",
		"�Ǹ���������మ���˿����ٻ��������", "�Ǹ�������Դͷ��", };
}
