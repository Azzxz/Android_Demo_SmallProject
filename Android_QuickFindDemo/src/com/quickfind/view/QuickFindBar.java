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
 * @Description:���ٲ��Ҳ���
 * @author: iamxiarui@foxmail.com
 * @date: 2016��5��17�� ����6:59:01
 */
public class QuickFindBar extends View {
	private Paint paint;

	private String[] letterArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
			"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	private int barWidth; // ������ĸ���Ŀ��
	private float cellHeight; // ÿ����ĸռ�ĸ��ӵĸ߶�
	private int lastLetterIndex = -1; // ��һ����ĸ��Ĭ��Ϊ-1
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
	 * @Description:��ʼ������
	 * @return: void
	 */
	private void initView() {
		// һ������ݵĻ���
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		// ������ɫ
		paint.setColor(Color.GRAY);
		paint.setTextSize(30);
		// �趨����View��ʼ�����ı���ױߵ�����
		paint.setTextAlign(Align.CENTER);
	}

	/**
	 * @Title: onSizeChanged
	 * @Description:����������
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		barWidth = getMeasuredWidth();
		// ÿ����ĸ��ռ���ӵĸ߶�
		cellHeight = getMeasuredHeight() * 1f / letterArr.length;
	}

	/**
	 * @Title: onDraw
	 * @Description:����View
	 */
	@Override
	protected void onDraw(Canvas canvas) {

		for (int i = 0; i < letterArr.length; i++) {
			// �����ı���X����
			float letterX = barWidth / 2;

			// �õ��ı��ĸ߶�
			Rect bounds = new Rect();
			paint.getTextBounds(letterArr[i], 0, 1, bounds);

			// �����ı���Y����
			float letterY = cellHeight / 2 + bounds.height() / 2 + i * cellHeight;

			// ������������ĸ�ǵ�ǰ��i������ɫΪ��ɫ������Ĭ��Ϊ��ɫ
			paint.setColor(lastLetterIndex == i ? Color.RED : Color.GRAY);
			canvas.drawText(letterArr[i], letterX, letterY, paint);
		}
	}

	/**
	 * @Title: onTouchEvent
	 * @Description:�����¼�
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			// �õ���ǰ������Yֵ
			float currentY = event.getY();
			// ����ÿ�����ӵĸ߶� �ó��ڼ�����ĸ
			int currentLetterIndex = (int) (currentY / cellHeight);
			// �����������һ����ĸ
			if (lastLetterIndex != currentLetterIndex) {
				// ��ȫ�Եļ��
				if (currentLetterIndex >= 0 && currentLetterIndex < letterArr.length) {
					if (listener != null) {
						listener.onTouchLetter(letterArr[currentLetterIndex]);
					}
				}
			}
			// ��ǰ��ĸΪ��һ����ĸ
			lastLetterIndex = currentLetterIndex;
			break;

		case MotionEvent.ACTION_UP:
			// ��һ����ĸ�ָ�Ĭ��
			lastLetterIndex = -1;
			break;

		}
		// ���»��ƽ���
		invalidate();
		return true;
	}

	/**
	 * @Title: setOnTouchLetterListener
	 * @Description:��������
	 * @param listener
	 * @return: void
	 */
	public void setOnTouchLetterListener(OnTouchLetterListener listener) {
		this.listener = listener;
	}

	/**
	 * @ClassName: onTouchLetterListener
	 * @Description:�����ӿ�
	 * @author: iamxiarui@foxmail.com
	 * @date: 2016��5��17�� ����8:07:01
	 */
	public interface OnTouchLetterListener {
		void onTouchLetter(String currentLetter);
	}

}
