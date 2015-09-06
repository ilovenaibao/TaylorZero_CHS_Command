package com.android.mylib.dbtypeconvert;

import android.content.Context;

public class MyDBTypeConvert {

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);

	}

	/**
	 * @describe string of byte[4] convert int
	 * 
	 * @param byte[] bytes4 : string of byte[4]
	 * @return int : return int of type
	 */

	public static int Str4BytesToInt(byte[] bytes4) {
		return (((bytes4[0] > 0 ? bytes4[0] : (bytes4[0] & 0x00FF)))
				| (((bytes4[1] > 0 ? bytes4[1] : (bytes4[1] & 0x00FF))) << 8)
				| (((bytes4[2] > 0 ? bytes4[2] : (bytes4[2] & 0x00FF))) << 16) | (((bytes4[3] > 0 ? bytes4[3]
				: (bytes4[3] & 0x00FF))) << 24));
	}

	/**
	 * @describe 4 byte[] convert 1 int
	 * 
	 * @return int : return one int type
	 * @throws Exception
	 */
	public static int Str4Bytes2Int(byte[] bytes4) throws Exception {
		return (int) (((int) (bytes4[0] > 0 ? bytes4[0] : (bytes4[0] & 0x00FF)))
				| (((int) (bytes4[1] > 0 ? bytes4[1] : (bytes4[1] & 0x00FF))) << 8)
				| (((int) (bytes4[2] > 0 ? bytes4[2] : (bytes4[2] & 0x00FF))) << 16) | (((int) (bytes4[3] > 0 ? bytes4[3]
				: (bytes4[3] & 0x00FF))) << 24));
	}

	/**
	 * @describe byte[n] convert (wchar_t) string, this default from byte hight
	 *           bit to string low bit
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String nBytes2Str(byte[] buffer) throws Exception {
		String ret = new String("");
		int oneCharCount = 0;
		char[] oneChar = new char[1];
		for (oneCharCount = 0; oneCharCount < buffer.length; oneCharCount += 2) {
			oneChar[0] = (char) ((buffer[oneCharCount] > 0 ? buffer[oneCharCount]
					: (buffer[oneCharCount] & 0x00FF)) | (((buffer[oneCharCount + 1] > 0 ? buffer[oneCharCount + 1]
					: (buffer[oneCharCount + 1] & 0x00FF))) << 8));
			ret = ret + oneChar[0];
		}
		return ret;
	}

	/**
	 * @describe extends over function this has a kind value for control byte[n]
	 *           convert (wchar_t) string followed : 0 -> byte[n] from hight to
	 *           low bit and string from hight to low bit; 1 -> byte[n] from low
	 *           to hight bit and string from hight to low bit;
	 * 
	 * @param kind
	 *            : order byte[n]
	 * @return String : return one string
	 * @throws Exception
	 */
	public static String nBytes2Str(byte[] buffer, int kind) throws Exception {
		String ret = new String("");
		int oneCharCount = 0;
		char[] oneChar = new char[1];
		for (oneCharCount = 0; oneCharCount < buffer.length; oneCharCount += 2) {
			switch (kind) {
			case 0:
				oneChar[0] = (char) ((buffer[oneCharCount + 1] > 0 ? buffer[oneCharCount + 1]
						: (buffer[oneCharCount + 1] & 0x00FF)) | (((buffer[oneCharCount] > 0 ? buffer[oneCharCount]
						: (buffer[oneCharCount] & 0x00FF))) << 8));
				break;
			case 1:
				oneChar[0] = (char) ((buffer[oneCharCount] > 0 ? buffer[oneCharCount]
						: (buffer[oneCharCount] & 0x00FF)) | (((buffer[oneCharCount + 1] > 0 ? buffer[oneCharCount + 1]
						: (buffer[oneCharCount + 1] & 0x00FF))) << 8));
				break;
			}

			ret = ret + oneChar[0];
		}
		return ret;
	}

	/**
	 * @describe int to byte[4], this has a kind value for control : 0 : int to
	 *           byte[0] -> byte[3]; 1 : int to byte[3] -> byte[0]
	 * 
	 * @param int oneInt : input int type value
	 * @param int kind : convert order
	 * @return byte[] : return byte[4]
	 */
	public static byte[] int2bytes4(int oneInt, int kind) {
		byte[] ret = new byte[4];
		for (int i = 0; i < ret.length; i++) {
			switch (kind) {
			case 0: // hight bit -> low bit
				ret[i] = (byte) ((oneInt >> (8 * (3 - i))) & 0x000000FF);
				break;
			case 1: // low bit -> hight bit
				ret[i] = (byte) ((oneInt >> (8 * i)) & 0x000000FF);
				break;
			}
		}
		return ret;
	}

	/**
	 * @describe byte[4] convert one int and has kind value for control convert
	 *           order 0 : byte[0] -> byte[3] to int; 1 : byte[3] -> byte[0] to
	 *           int
	 * 
	 * @param kind
	 *            : value for control convert order
	 * @return int : return one int type value
	 */
	public static int bytes4ToInt(byte[] bytes4, int kind) {
		int ret = 0;
		int j = 0;
		for (int i = 0; i < bytes4.length; i++) {
			switch (kind) {
			case 0: // order by 0 -> 3
				ret |= bytes4[i];
				ret <<= 8;
				break;
			case 1: // order by 3 -> 0
				j |= bytes4[i];
				j <<= 8 * i;
				ret |= j;
				j = 0;
				break;
			}
		}
		return ret;
	}
}
