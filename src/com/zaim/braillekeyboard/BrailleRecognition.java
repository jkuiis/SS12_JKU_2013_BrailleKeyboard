package com.zaim.braillekeyboard;

import java.util.ArrayList;
import java.util.BitSet;

import android.util.Log;

import com.gg.brailledecoder.BrailleToRoman;
import com.gg.brailledecoder.BrailleType;
import com.gg.brailledecoder.ReturnBraille;

public class BrailleRecognition {
	private static final String TAG = "BrailleRecognition";
	
	private static final double FINGER_MOVEMENT_ERROR_THRESHOLD = 20.00; // in pixels
	private static final double MAX_CP_TP_DISTANCE = 150.00; // in pixels
	private static final int CALIBRATION_ADAPT_FACTOR = 5;
	private static final float CALIBRATION_POINT_RADIUS = 50f;
	private static final float TOUCH_POINT_RADIUS = 40f;
	
	private BrailleTouchModel model;
	
	public BrailleRecognition(BrailleTouchModel model) {
		this.model = model;
	}
	
	public ReturnBraille recognize() {
		ArrayList<TouchPoint> touchPoints = model.getCurrentTouchPoints();
		ArrayList<CalibrationPoint> calibrationPoints = model.getCalibrationPoints();
		
		ReturnBraille result;
		if (movementError()) {
			Log.v(TAG, "One or more fingers moved too much since touching the screen!");
			model.beepSemaphore = false;
			result = new ReturnBraille("invalid", "Ungültiges Zeichen");
		}
		else {
			for (TouchPoint p : touchPoints) {
				double largestIntersectionArea = 0.00;
				CalibrationPoint matchingCp = null;
				for (CalibrationPoint cp : calibrationPoints) {
					double intersectionArea = CustomMath.circleIntersectionArea(p.touchX, p.touchY, TOUCH_POINT_RADIUS, cp.x, cp.y, CALIBRATION_POINT_RADIUS);
					if (intersectionArea > largestIntersectionArea) {
						largestIntersectionArea = intersectionArea;
						matchingCp = cp;
					}
				}
				if (matchingCp == null) {
					double shortestDistance = 1000.00;
					for (CalibrationPoint cp: calibrationPoints) {
						if (!cp.occupied) {
							double distance = CustomMath.distance(p.touchX, p.touchY, cp.x, cp.y);
							if (distance < MAX_CP_TP_DISTANCE && distance < shortestDistance) {
								shortestDistance = distance;
								matchingCp = cp;
							}
						}
					}
				}
				
				if (matchingCp != null) {
					matchingCp.occupied = true;
					matchingCp.occupyingTouchPoint = p;
				}
			}
			
			result = decode();
			model.clearOccupation();
		}
		return result;
	}
	
	// When calling this method, the recognition must have finished already
	private ReturnBraille decode() {
		ArrayList<CalibrationPoint> calibrationPoints = model.getCalibrationPoints();
		BitSet bs = new BitSet(6);
		int i = 0;
		for (CalibrationPoint cp : calibrationPoints) {
			if (cp.occupied) {
				bs.set(i);
			}
			i++;
		}
		
		if (bs.cardinality() == 0) {
			return new ReturnBraille("invalid", "Ungültiges Zeichen");
		}
		else {
			BrailleToRoman brailleToRoman = new BrailleToRoman(BrailleType.Braille6);
			Log.v(TAG,bs.toString());
			ReturnBraille result = brailleToRoman.getRecognizedSign(bs);
			if (!result.getSign().equals("invalid")) {
				adaptCalibration();
			}
			
			return result;
			//return String.valueOf(brailleToRoman.getCharFromBraille(bs));
		}
		
		
	}
	
	private boolean movementError() {
		ArrayList<TouchPoint> touchPoints = model.getCurrentTouchPoints();
		for (TouchPoint p : touchPoints) {
			double error = Math.sqrt(Math.pow(p.liftX - p.touchX, 2) + Math.pow(p.liftY - p.touchY, 2));
			if (error > FINGER_MOVEMENT_ERROR_THRESHOLD) {
				return true;
			}
		}
		return false;
	}
	
	private void adaptCalibration() {
		ArrayList<CalibrationPoint> calibrationPoints = model.getCalibrationPoints();
		for (CalibrationPoint cp : calibrationPoints) {
			if (cp.occupied) {
				TouchPoint matchingPoint = cp.occupyingTouchPoint;
				float newX = cp.x + ((matchingPoint.touchX - cp.x) / CALIBRATION_ADAPT_FACTOR);
				float newY = cp.y + ((matchingPoint.touchY - cp.y) / CALIBRATION_ADAPT_FACTOR);
				cp.x = newX;
				cp.y = newY;
			}
		}
	}
}
