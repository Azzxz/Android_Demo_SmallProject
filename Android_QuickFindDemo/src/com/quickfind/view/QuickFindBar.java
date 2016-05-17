package com.quickfind.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.AttributeSet;
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

	private int barWidth;

	private float cellHeight;

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
		paint.setTextSize(25);
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

			float letterY = cellHeight / 2 + bounds.height() / 2 + i * cellHeight;
			// 设置文本的Y坐标
			canvas.drawText(letterArr[i], letterX, letterY, paint);
		}

	}

}
