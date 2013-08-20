package com.zaim.braillekeyboard;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class BrailleView extends View implements Observer {
	private static final int CALIBRATION_POINT_COLOR = Color.GREEN;
	private static final float CALIBRATION_POINT_RADIUS = 50f;
	private static final int TOUCH_POINT_COLOR = Color.RED;
	private static final float TOUCH_POINT_RADIUS = 40f;
	
//	private static final float ACTIVE_CORNER_RADIUS = 150f;
//	private static final int ACTIVE_CORNER_COLOR = Color.BLUE;
//	
//	private static final int TEXT_COLOR = Color.WHITE;
	
	private BrailleTouchModel brailleTouchModel;
	private BrailleInputModel brailleInputModel;
	
	private Paint paint;

	public BrailleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public BrailleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public BrailleView(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		paint = new Paint();
		
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		if (!isInEditMode()) { // we need this so the eclipse layout editor doesn't throw errors
			// paint the background grey
			canvas.drawColor(Color.DKGRAY);
			
			Context ctx = this.getContext();
			WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			int screenWidth = size.x;
			int screenHeight = size.y;
			
//			//draw active corners
//			// Top left - READ
//			paint.setColor(ACTIVE_CORNER_COLOR);
//			canvas.drawCircle(0, 0, ACTIVE_CORNER_RADIUS, paint);
//			//paint.setColor(TEXT_COLOR);
//			//canvas.drawText("READ", 10, 30, paint);
//			//Top right - BACKSPACE
//			paint.setColor(ACTIVE_CORNER_COLOR);
//			canvas.drawCircle(screenWidth, 0, ACTIVE_CORNER_RADIUS, paint);
//			//paint.setColor(TEXT_COLOR);
//			//canvas.drawText("BACKSPACE", 0, 0, paint);
//			// Bottom left - CHANGE MODE
//			paint.setColor(ACTIVE_CORNER_COLOR);
//			canvas.drawCircle(0, screenHeight, ACTIVE_CORNER_RADIUS, paint);
//			//paint.setColor(TEXT_COLOR);
//			//canvas.drawText("CHANGE MODE", 0, 0, paint);
//			// Bottom right - ENTER
//			paint.setColor(ACTIVE_CORNER_COLOR);
//			canvas.drawCircle(screenWidth, screenHeight, ACTIVE_CORNER_RADIUS, paint);
//			//paint.setColor(TEXT_COLOR);
//			//canvas.drawText("CHANGE MODE", 0, 0, paint);
			
			
			boolean calibrated = brailleTouchModel.isCalibrated();
			ArrayList<TouchPoint> touchPoints = brailleTouchModel.getCurrentTouchPoints();
			ArrayList<CalibrationPoint> calibrationPoints = brailleTouchModel.getCalibrationPoints();
			
			
			if (calibrated) {
				paint.setColor(CALIBRATION_POINT_COLOR);
				for (CalibrationPoint cp : calibrationPoints) {
					canvas.drawCircle(cp.x, cp.y, CALIBRATION_POINT_RADIUS, paint);
				}
			}
			
			paint.setColor(TOUCH_POINT_COLOR);
			for (TouchPoint p : touchPoints) {
				canvas.drawCircle(p.touchX, p.touchY, TOUCH_POINT_RADIUS, paint);
			}
			
//			paint.setColor(INPUT_COLOR);
//			paint.setTextSize(36);
//			canvas.drawText(lastChar, getWidth()/2, getHeight()/2, paint);
		}
		
	}
	
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	} 
	
	/* -----	------------------------------------------------------------------------------------------- */
	public void setBrailleTouchModel(BrailleTouchModel brailleTouchModel) {
		this.brailleTouchModel = brailleTouchModel;
	}

	public void setBrailleInputModel(BrailleInputModel brailleInputModel) {
		this.brailleInputModel = brailleInputModel;
	}

	@Override
	public void update(Observable observable, Object data) {
		if (observable instanceof BrailleTouchModel) {
			brailleTouchModel = (BrailleTouchModel) observable;
		}
//		else if (observable instanceof BrailleInputModel) {
//			brailleInputModel = (BrailleInputModel) observable;
//		}
		invalidate();
	}
	
	
}
