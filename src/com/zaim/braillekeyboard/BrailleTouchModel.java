package com.zaim.braillekeyboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

import android.view.MotionEvent;

public class BrailleTouchModel extends Observable {
	private ArrayList<TouchPoint> currentTouchPoints;
	private ArrayList<CalibrationPoint> calibrationPoints;
	private boolean calibrated;
	
	// this is really a bad idea
	// it should never be in the model, it's not thread safe, etc...
	protected boolean beepSemaphore = true;
	
	
	public BrailleTouchModel() {
		currentTouchPoints = new ArrayList<TouchPoint>();
		calibrationPoints = new ArrayList<CalibrationPoint>();
		calibrated = false;
	}
	
	public void addTouchPoint(MotionEvent e) {
		int numOfTouchPoints = e.getPointerCount();
		for (int i = 0; i < numOfTouchPoints; i++) {
			TouchPoint touchPoint = new TouchPoint(e.getPointerId(i), e.getX(i), e.getY(i));
			if (!currentTouchPoints.contains(touchPoint)) {
				currentTouchPoints.add(touchPoint);
			}
		}
		Collections.sort(currentTouchPoints, new TouchPointComparator());
		
		setChanged();
		notifyObservers();
	}
	
	public void fingerLifted(MotionEvent e) {
		int pointerIndex = e.getActionIndex();
		int pointerId = e.getPointerId(pointerIndex);
		float liftX = e.getX(pointerIndex);
		float liftY = e.getY(pointerIndex);
		
		for (TouchPoint p : currentTouchPoints) {
			if (p.id == pointerId) {
				p.liftX = liftX;
				p.liftY = liftY;
			}
		}
		
		setChanged();
		notifyObservers();
	}
	

	public void calibrate() {
		calibrationPoints.clear();
		for (TouchPoint p : currentTouchPoints) {
			CalibrationPoint cp = new CalibrationPoint(p.touchX, p.touchY);
			calibrationPoints.add(cp);
		}
		calibrated = true;
		
		setChanged();
		notifyObservers();
	}

	public ArrayList<TouchPoint> getCurrentTouchPoints() {
		return currentTouchPoints;
	}
	
	public int numOfTouchPoints() {
		return currentTouchPoints.size();
	}
	
	public void clear() {
		currentTouchPoints.clear();
		
		setChanged();
		notifyObservers();
	}
	
	public void clearOccupation() {
		for (CalibrationPoint cp : calibrationPoints) {
			cp.occupied = false;
			cp.occupyingTouchPoint = null;
		}
	}

	public ArrayList<CalibrationPoint> getCalibrationPoints() {
		return calibrationPoints;
	}

	public boolean isCalibrated() {
		return calibrated;
	}
	
	

}
