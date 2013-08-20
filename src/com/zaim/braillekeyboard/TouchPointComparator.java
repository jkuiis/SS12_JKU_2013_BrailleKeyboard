package com.zaim.braillekeyboard;

import java.util.Comparator;

public class TouchPointComparator implements Comparator<TouchPoint> {

	@Override
	public int compare(TouchPoint point1, TouchPoint point2) {
		if (point1.touchX > point2.touchX) return 1;
		else if (point1.touchX < point2.touchX) return -1;
		else return 0;
	}
}
