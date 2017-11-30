package demo;

public class Point {
	public static void main(String[] args) {
		
	}
	public static double[] getPoint(int x1, int y1, int x2, int y2) {
		
		double[] rel = new double[2];
		if (x2 == x1) {
			if (y1 > y2) {
				rel[0] = x1;
				rel[1] = 0;
				return rel;
			} else {
				rel[0] = x1;
				rel[1] = 100;
				return rel;
			}
		}
		double k = (y2 - y1) / (x2 - x1);
		double b = y1 - (k * x1);
		if (k == 0) {
			if (x1 > x2) {
				rel[0] = 0;
				rel[1] = y1;
				return rel;
			} else {
				rel[0] = 100;
				rel[1] = y1;
				return rel;
			}
		} else {
			if (k > 0) {
				if (x1 > x2) {
					if (b >= 0) {
						rel[0] = 0;
						rel[1] = b;return rel;
					} else {
						rel[0] = -1 * b / k; 
						rel[1] = 0;return rel;
					}
				} else {
					if ((100 * k + b) > 100) {
						rel[0] = (100 - b) / k;
						rel[1] = 100;return rel;
					} else {
						rel[0] = 100;
						rel[1] = 100 * k + b;return rel;
					}
				}
			} else {
				if ((100 * k + b) < 0) {
					rel[0] = -1 * b / k;
					rel[1] = 0;
				}
			}
		}
		
		
		
		
		return rel;
	}
}
