package com.zaim.braillekeyboard;

public class CalibrationPoint {
	protected float x,y;
	protected boolean occupied;
	protected TouchPoint occupyingTouchPoint;
	
	public CalibrationPoint(float x, float y) {
		this.x = x;
		this.y = y;
	}

}
