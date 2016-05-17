package com.quickfind.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @ClassName: QuickFindBar
 * @Description:快速查找侧栏
 * @author: iamxiarui@foxmail.com
 * @date: 2016年5月17日 下午6:59:01
 */
public class QuickFindBar extends View {
	private Paint paint;

	private String[] letterArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
			"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	private int barWidth; // 整个字母条的宽度
	private float cellHeight; // 每个字母占的格子的高度
	private int lastLetterIndex = -1; // 上一个字母，默认为-1
	private OnTouchLetterListener listener;

	public QuickFindBar(Context context) {
		super(context);
		initView();
	}

	public QuickFindBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public QuickFindBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public QuickFindBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		initView();
	}

	/**
	 * @Title: initView
	 * @Description:初始化画笔
	 * @return: void
	 */
	private void initView() {
		// 一个抗锯齿的画笔
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		// 画笔颜色
		paint.setColor(Color.GRAY);
		paint.setTextSize(30);
		// 设定字体View起始点在文本框底边的中心
		paint.setTextAlign(Align.CENTER);
	}

	/**
	 * @Title: onSizeChanged
	 * @Description:做测量工作
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		barWidth = getMeasuredWidth();
		// 每个字母所占格子的高度
		cellHeight = getMeasuredHeight() * 1f / letterArr.length;
	}

	/**
	 * @Title: onDraw
	 * @Description:绘制View
	 */
	@Override
	protected void onDraw(Canvas canvas) {

		for (int i = 0; i < letterArr.length; i++) {
			// 设置文本的X坐标
			float letterX = barWidth / 2;

			// 得到文本的高度
			Rect bounds = new Rect();
			paint.getTextBounds(letterArr[i], 0, 1, bounds);

			// 设置文本的Y坐标
			float letterY = cellHeight / 2 + bounds.height() / 2 + i * cellHeight;

			// 如果最后点击的字母是当前的i，则颜色为红色，否则默认为白色
			paint.setColor(lastLetterIndex == i ? Color.RED : Color.GRAY);
			canvas.drawText(letterArr[i], letterX, letterY, paint);
		}
	}

	/**
	 * @Title: onTouchEvent
	 * @Description:触摸事件
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			// 得到当前触摸的Y值
			float currentY = event.getY();
			// 除以每个格子的高度 得出第几个字母
			int currentLetterIndex = (int) (currentY / cellHeight);
			// 如果不等于上一个字母
			if (lastLetterIndex != currentLetterIndex) {
				// 安全性的检查
				if (currentLetterIndex >= 0 && currentLetterIndex < letterArr.length) {
					if (listener != null) {
						listener.onTouchLetter(letterArr[currentLetterIndex]);
					}
				}
			}
			// 当前字母为上一个字母
			lastLetterIndex = currentLetterIndex;
			break;

		case MotionEvent.ACTION_UP:
			// 上一个字母恢复默认
			lastLetterIndex = -1;
			break;

		}
		// 重新绘制界面
		invalidate();
		return true;
	}

	/**
	 * @Title: setOnTouchLetterListener
	 * @Description:监听方法
	 * @param listener
	 * @return: void
	 */
	public void setOnTouchLetterListener(OnTouchLetterListener listener) {
		this.listener = listener;
	}

	/**
	 * @ClassName: onTouchLetterListener
	 * @Description:监听接口
	 * @author: iamxiarui@foxmail.com
	 * @date: 2016年5月17日 下午8:07:01
	 */
	public interface OnTouchLetterListener {
		void onTouchLetter(String currentLetter);
	}

}
