package com.android.mylib.graphic;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.android.mylib.screen.MyLibScreenInfo;
import com.android.mylib.screen.MyLibScreenSetting;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class MyGraphic {

	// 从资源中获取Bitmap
	public static Bitmap getBitmapFromResources(Activity act, int resId) {
		Resources res = act.getResources();
		return BitmapFactory.decodeResource(res, resId);
	}

	// byte[] → Bitmap
	public static Bitmap convertBytes2Bimap(byte[] b) {
		if (b.length == 0) {
			return null;
		}
		return BitmapFactory.decodeByteArray(b, 0, b.length);
	}

	// Bitmap → byte[]
	public static byte[] convertBitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	// 1)Drawable → Bitmap
	public static Bitmap convertDrawable2BitmapByCanvas(Drawable drawable) {
		Bitmap bitmap = Bitmap
				.createBitmap(
						drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight(),
						drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
								: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		// canvas.setBitmap(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	// 2)Drawable → Bitmap
	public static Bitmap convertDrawable2BitmapSimple(Drawable drawable) {
		BitmapDrawable bd = (BitmapDrawable) drawable;
		return bd.getBitmap();
	}

	// Bitmap → Drawable
	public static Drawable convertBitmap2Drawable(Bitmap bitmap) {
		BitmapDrawable bd = new BitmapDrawable(bitmap);
		// 因为BtimapDrawable是Drawable的子类，最终直接使用bd对象即可。
		return bd;
	}

	/**
	 * @describe Load one bitmap from filePath
	 * 
	 * @param String
	 *            filePath : src path
	 * @param int outWidth : max width of bitmap
	 * @param int outHeight : max height of bitmap
	 * 
	 * @return Bitmap : return one Load bitmap exception null
	 */
	public static Bitmap loadBitmapAutoSize(String filePath, int outWidth,
			int outHeight) {
		// outWidth和outHeight是目标图片的最大宽度和高度，用作限制
		FileInputStream fs = null;
		BufferedInputStream bs = null;
		try {
			fs = new FileInputStream(filePath);
			bs = new BufferedInputStream(fs);
			BitmapFactory.Options options = setBitmapOption(filePath, outWidth,
					outHeight);
			return BitmapFactory.decodeStream(bs, null, options);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bs.close();
				fs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @describe When load one bitmap will out of memory, so this bitmap option
	 *           can setting bitmap to a compatible size.
	 * 
	 * @param String
	 *            file : bitmap file path
	 * @param ing
	 *            width : max bitmap width
	 * @param int height : max bitmap height
	 * 
	 * @return BitmapFactory.Options : bitmap of options
	 */
	private static BitmapFactory.Options setBitmapOption(String file,
			int width, int height) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inJustDecodeBounds = true;
		// 设置只是解码图片的边距，此操作目的是度量图片的实际宽度和高度
		BitmapFactory.decodeFile(file, opt);

		int outWidth = opt.outWidth; // 获得图片的实际高和宽
		int outHeight = opt.outHeight;
		opt.inDither = false;
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		// 设置加载图片的颜色数为16bit，默认是RGB_8888，表示24bit颜色和透明通道，但一般用不上
		opt.inSampleSize = 1;
		// 设置缩放比,1表示原比例，2表示原来的四分之一....
		// 计算缩放比
		if (outWidth != 0 && outHeight != 0 && width != 0 && height != 0) {
			int sampleSize = (outWidth / width + outHeight / height) / 2;
			opt.inSampleSize = sampleSize;
		}

		opt.inJustDecodeBounds = false;// 最后把标志复原
		return opt;
	}

	/**
	 * @describe my test load one bitmap functions
	 * 
	 * @param context
	 * @param path
	 * @return Bitmap
	 */
	public static Bitmap loadingOnePic(Context context, String path) {
		// Loading Pic
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		MyLibScreenInfo scrInfo = MyLibScreenSetting.GetScreenSize(context, 3);
		int realWidth = scrInfo.scrWidth;
		int realHeight = scrInfo.scrHeight;
		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, realWidth,
				realHeight);
		// Decode bitmap with inSampleSize set
		options.outWidth = realWidth;
		options.outHeight = realHeight;
		options.inJustDecodeBounds = false;
		Bitmap bmp = BitmapFactory.decodeFile(path, options);
		return bmp;
	}

	/**
	 * @describe my test load bitmap function 2
	 * 
	 * @param Context
	 *            context : parent activity context
	 * @param String
	 *            path : file path
	 * @param int width : max bitmap width
	 * @param int height : max bitmap height
	 * 
	 * @return Bitmap : return a bitmap exception null
	 */
	public static Bitmap loadingOnePic(Context context, String path, int width,
			int height) {
		// Loading Pic
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		MyLibScreenSetting.GetScreenSize(context);
		int realWidth = 100;
		int realHeight = 100;
		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, realWidth,
				realHeight);
		// Decode bitmap with inSampleSize set
		options.outWidth = realWidth;
		options.outHeight = realHeight;
		options.inJustDecodeBounds = false;
		Bitmap bmp = BitmapFactory.decodeFile(path, options);
		return bmp;
	}

	/**
	 * @describe calculate bitmap options can resolve out of memory.
	 * 
	 * @param BitmapFactory
	 *            .Options : bitmap options
	 * @param int reqWidth : decode bitmap max width
	 * @param int reqHeight : decode bitmap max height
	 * @return int : sampleSize if it is 1 is oragin size and if it is 2 is
	 *         width/2, height/2 calculate 1/4 of oragin bitmap
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		int outWidth = options.outWidth; // 获得图片的实际高和宽
		int outHeight = options.outHeight;
		int width = reqWidth;
		int height = reqHeight;
		int retSampleSize = 1;

		if (outWidth != 0 && outHeight != 0 && width != 0 && height != 0) {
			int sampleSize = (outWidth / width + outHeight / height) / 2;
			retSampleSize = sampleSize;
		}
		return retSampleSize;
	}

	// Matrix bmp
	/**
	 * @describe make a ration bitmap from source bitmap
	 * 
	 * @param bmp
	 *            : src bitmap
	 * @param width
	 *            : target scale width
	 * @param height
	 *            : target scale height
	 * @return Bitmap : return a matrix bitmap exception null
	 */
	public static Bitmap BitmapRatioMatrix(Bitmap bmp, float width, float height) {
		Bitmap ret = null;
		int oraginWidth = bmp.getWidth();
		int oraginHeight = bmp.getHeight();
		float scaleWidth = width / oraginWidth;
		float scaleHeight = height / oraginHeight;
		Matrix mMatrix = new Matrix();
		mMatrix.postScale(scaleWidth, scaleHeight);
		ret = Bitmap.createBitmap(bmp, 0, 0, oraginWidth, oraginHeight,
				mMatrix, true);
		return ret;
	}

	/**
	 * 将从WebView中获得的Pic存储到sdcard
	 * 
	 * @param path
	 *            存儲的路徑
	 * @param fileName
	 *            存儲的文件名
	 * @param srcBmp
	 *            存儲的目標bitmap
	 * @param width
	 *            在反饋給調用者的時候返回的html片段設置的圖片的寬高
	 * @param height
	 */
	public void SaveResultPicBuffer(String path, String fileName,
			Bitmap srcBmp, int width, int height, int now_count) {
		String tmp_name = new String(path + fileName);
		File tmp_file = new File(tmp_name);
		if (tmp_file.exists()) {
			tmp_file.delete();
		}
		File dir = new File(path);
		dir.mkdirs();
		try {
			FileOutputStream out = new FileOutputStream(tmp_file);
			srcBmp.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将从WebView中获得的Pic存储到sdcard
	 * 
	 * @param path
	 *            存儲的路徑
	 * @param fileName
	 *            存儲的文件名
	 * @param srcBmp
	 *            存儲的目標bitmap
	 */
	public static boolean SavePicture(String path, String fileName,
			Bitmap srcBmp) {
		boolean ret = false;
		String tmp_name = new String(path + fileName);
		File tmp_file = new File(tmp_name);
		if (tmp_file.exists()) {
			tmp_file.delete();
		}
		String absolutePath = path;
		char[] pathChar = absolutePath.toCharArray();
		if (File.separator.toCharArray()[0] == pathChar[pathChar.length - 1]) {
			absolutePath = absolutePath.substring(0, absolutePath.length() - 1);
		}
		File dir = new File(absolutePath);
		dir.mkdirs();
		// try {
		// tmp_file.createNewFile();
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }
		try {
			FileOutputStream out = new FileOutputStream(tmp_file);
			srcBmp.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}
