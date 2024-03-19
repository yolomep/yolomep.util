package util;

public class Angle {
	double radians, degrees;
	//double r, d;
	public Angle(double degrees) {
		this.degrees = degrees;
		radians = Math.toRadians(degrees);
	}
	
	static Angle fromRadian(double radians) {
		return new Angle(Math.toDegrees(radians));
	}
}
