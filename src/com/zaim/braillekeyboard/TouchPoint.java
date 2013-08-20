package com.zaim.braillekeyboard;

public class TouchPoint {
	protected float touchX, touchY;
	protected float liftX, liftY;
	protected int id;
	
	public TouchPoint(int id, float touchX, float touchY) {
		this.id = id;
		this.touchX = touchX;
		this.touchY = touchY;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof TouchPoint && ((TouchPoint)o).id == this.id;
	}
	

}
