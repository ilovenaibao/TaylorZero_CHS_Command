package com.android.taylorzero.content;

import java.util.Vector;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.android.taylorzero.R;

public class TaylorZeroTalkTextView extends TextView {
	public static int m_iTextWidth;// 鏂囨湰鐨勫搴�	
	public static int m_iTextHeight;

	private Paint mPaint = null;
	private String string = "";
	private float LineSpace = 0;// 琛岄棿璺�	
	private int left_Margin;
	private int right_Margin;
	private int bottom_Margin;

	public TaylorZeroTalkTextView(Context context, AttributeSet set) {
		super(context, set);
		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
		TypedArray typedArray = context.obtainStyledAttributes(set,
				R.styleable.CYTextView);
		int width = displayMetrics.widthPixels;
		left_Margin = 29;
		right_Margin = 29;
		bottom_Margin = 29;
		width = width - left_Margin - right_Margin;
		float textsize = typedArray.getDimension(
				R.styleable.CYTextView_textSize, 34);
		int textcolor = typedArray.getColor(R.styleable.CYTextView_textColor,
				getResources().getColor(R.color.black));
		float linespace = typedArray.getDimension(
				R.styleable.CYTextView_lineSpacingExtra, 15);
		int typeface = typedArray.getColor(R.styleable.CYTextView_typeface, 0);

		typedArray.recycle();

		// 璁剧疆 CY TextView鐨勫搴﹀拰琛岄棿璺漺ww.linuxidc.com
		m_iTextWidth = width;
		LineSpace = linespace;

		// 鏋勫缓paint瀵硅薄
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(textcolor);
		mPaint.setTextSize(textsize);
		switch (typeface) {
		case 0:
			mPaint.setTypeface(Typeface.DEFAULT);
			break;
		case 1:
			mPaint.setTypeface(Typeface.SANS_SERIF);
			break;
		case 2:
			mPaint.setTypeface(Typeface.SERIF);
			break;
		case 3:
			mPaint.setTypeface(Typeface.MONOSPACE);
			break;
		default:
			mPaint.setTypeface(Typeface.DEFAULT);
			break;
		}

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		char ch;
		int w = 0;
		int istart = 0;
		int m_iFontHeight;
		int m_iRealLine = 0;
		int x = 2;
		int y = 30;

		Vector m_String = new Vector();

		FontMetrics fm = mPaint.getFontMetrics();
		m_iFontHeight = (int) Math.ceil(fm.descent - fm.top) + (int) LineSpace;// 璁＄畻瀛椾綋楂樺害锛堝瓧浣撻珮搴︼紜琛岄棿璺濓級

		for (int i = 0; i < string.length(); i++) {
			ch = string.charAt(i);
			float[] widths = new float[1];
			String srt = String.valueOf(ch);
			mPaint.getTextWidths(srt, widths);

			if (ch == '\n') {
				m_iRealLine++;
				m_String.addElement(string.substring(istart, i));
				istart = i + 1;
				w = 0;
			} else {
				w += (int) (Math.ceil(widths[0]));
				if (w > m_iTextWidth) {
					m_iRealLine++;
					m_String.addElement(string.substring(istart, i));
					istart = i;
					i--;
					w = 0;
				} else {
					if (i == (string.length() - 1)) {
						m_iRealLine++;
						m_String.addElement(string.substring(istart,
								string.length()));
					}
				}
			}
		}
		m_iTextHeight = m_iRealLine * m_iFontHeight + 2;
		// canvas.setViewport(m_iTextWidth, m_iTextWidth);
		for (int i = 0, j = 0; i < m_iRealLine; i++, j++) {
			canvas.drawText((String) (m_String.elementAt(i)), x, y
					+ m_iFontHeight * j, mPaint);
		}
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measuredHeight = measureHeight(heightMeasureSpec);
		int measuredWidth = measureWidth(widthMeasureSpec);
		this.setMeasuredDimension(measuredWidth, measuredHeight);
		LayoutParams layout = new LinearLayout.LayoutParams(measuredWidth,
				measuredHeight);
		layout.leftMargin = left_Margin;
		layout.rightMargin = right_Margin;
		layout.bottomMargin = bottom_Margin;
		this.setLayoutParams(layout);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	private int measureHeight(int measureSpec) {
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);
		// Default size if no limits are specified.
		initHeight();
		int result = m_iTextHeight;
		if (specMode == MeasureSpec.AT_MOST) {
			// Calculate the ideal size of your
			// control within this maximum size.
			// If your control fills the available
			// space return the outer bound.
			result = specSize;
		} else if (specMode == MeasureSpec.EXACTLY) {
			// If your control can fit within these bounds return that value.
			// result = specSize;
		}
		return result;
	}

	private void initHeight() {
		// 璁剧疆 CY TextView鐨勫垵濮嬮珮搴︿负0
		m_iTextHeight = 0;

		// 澶ф璁＄畻 CY TextView鎵�渶楂樺害
		FontMetrics fm = mPaint.getFontMetrics();
		int m_iFontHeight = (int) Math.ceil(fm.descent - fm.top)
				+ (int) LineSpace;
		int line = 0;
		int istart = 0;

		int w = 0;
		for (int i = 0; i < string.length(); i++) {
			char ch = string.charAt(i);
			float[] widths = new float[1];
			String srt = String.valueOf(ch);
			mPaint.getTextWidths(srt, widths);

			if (ch == '\n') {
				line++;
				istart = i + 1;
				w = 0;
			} else {
				w += (int) (Math.ceil(widths[0]));
				if (w > m_iTextWidth) {
					line++;
					istart = i;
					i--;
					w = 0;
				} else {
					if (i == (string.length() - 1)) {
						line++;
					}
				}
			}
		}
		m_iTextHeight = (line) * m_iFontHeight + 2;
	}

	private int measureWidth(int measureSpec) {
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		// Default size if no limits are specified.
		int result = 500;
		if (specMode == MeasureSpec.AT_MOST) {
			// Calculate the ideal size of your control
			// within this maximum size.
			// If your control fills the available space
			// return the outer bound.
			result = specSize;
		} else if (specMode == MeasureSpec.EXACTLY) {
			// If your control can fit within these bounds return that value.
			result = specSize;
		}
		return result;
	}

	public void SetText(String text) {
		string = text;
		// requestLayout();
		// invalidate();
	}
}