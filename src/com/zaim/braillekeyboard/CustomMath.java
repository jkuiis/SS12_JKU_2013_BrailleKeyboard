package com.zaim.braillekeyboard;

public class CustomMath {
	
	// calculates the intersections area of two circles with midpoints P1(x1,y1) and P2(x2,y2)
	// and radii r1 and r2
	public static double circleIntersectionArea(float x1, float y1, float r1, float x2, float y2, float r2) {
		double r = r1;
		double R = r2;
		// x^2 + y^2 = d^2
		double d = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

		// circles do not overlap eachother at all
		if (d > r + R) {
			return 0.0d;
		}
		// circle 2 is completely inside of circle 1
		else if (d <= Math.abs(R - r) && r >= R) {
			// return area of circle 1
			return Math.PI * Math.pow(R, 2);
		}
		// circle 1 is completely inside of circle 2
		else if (d <= Math.abs(r - R) && r < R) {
			// return area of circle 1
			return Math.PI * Math.pow(r, 1);
		}

		if (R < r) {
			// swap
			r = r2;
			R = r1;
		}
		Double part1 = r * r * Math.acos((d * d + r * r - R * R) / (2 * d * r));
		Double part2 = R * R * Math.acos((d * d + R * R - r * r) / (2 * d * R));
		Double part3 = 0.5 * Math.sqrt((-d + r + R) * (d + r - R) * (d - r + R) * (d + r + R));

		return part1 + part2 - part3;
	}
	
	public static double distance(float x1, float y1, float x2, float y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

}
