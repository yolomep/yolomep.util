package util;
import java.util.*;
import java.io.*;

public class Triangle {
	public Point A, B, C;
	public Point[] points;
	//BC, AC, AB
	public double[] dists;
	double BC, AC, AB;
	Angle[] angles;
	Angle angleA, angleB, angleC;
	public Triangle(Point a, Point b, Point c) {
		A = a;
		B = b;
		C = c;
		points = new Point[] {A, B, C};
		calcDist();
		calcAngle();
	}
	
//	//ASA
//	public Triangle(Angle A, double AB, Angle B, Point pointA, Point pointB) {
//		angleA = A;
//		angleB = B;
//		angleC = new Angle(180 - A.degrees - B.degrees);
//		this.AB = AB;
//		this.pointA
//		//A is the anchor for point A
//		
//	}
	
//	public Triangle(Angle A, double AB, Angle B) {
//		this(A, AB, B, Point.origin, new Point(AB, 0));
//	}
	
	public Triangle(int ax, int ay, int bx, int by, int cx, int cy) {
		A = new Point(ax, ay);
		B = new Point(bx, by);
		C = new Point(cx, cy);
		points = new Point[] {A, B, C};
		calcDist();
		calcAngle();
	}
	
	private void calcDist() {
		dists = new double[3];
		dists[0] = Point.dist(B, C);
		dists[1] = Point.dist(A, C);
		dists[2] = Point.dist(A, B);
		BC = dists[0];
		AC = dists[1];
		AB = dists[2];
	}
	
	private void calcAngle() {
		double distA = dists[0];
		double distB = dists[1];
		double distC = dists[2];
		
		double angle1R = Math.acos((Math.pow(distA, 2) + Math.pow(distB, 2) - Math.pow(distC, 2))/(2*distA*distB));
		double angle1D = Math.toDegrees(angle1R);
		
		double angle2R = Math.acos((Math.pow(distA, 2) + Math.pow(distC, 2) - Math.pow(distB, 2))/(2*distA*distC));
		double angle2D = Math.toDegrees(angle2R);
		
		double angle3R = Math.acos((Math.pow(distC, 2) + Math.pow(distB, 2) - Math.pow(distA, 2))/(2*distC*distB));
		double angle3D = Math.toDegrees(angle3R);
		angles = new Angle[] {new Angle(angle1D), new Angle(angle2D), new Angle(angle3D)};
		angleA = angles[0];
		angleB = angles[1];
		angleC = angles[2];
	}
	
	public Point centriod() {
		return Point.average(points);
	}
	
	public Point incenter() {
		return new Point((AB*A.x + BC*B.x + AC*C.x)/MathEx.sum(dists), (AB*A.y + BC*B.y + AC*C.y)/MathEx.sum(dists));
	}
	
	public Point circumcenter() {
		double[] lineA = Point.generalFormLine(A, B);
		double[] lineB = Point.generalFormLine(B, C);
		
		double[] perpA = Point.perpendicularBisector(A, B, lineA);
		double[] perpB = Point.perpendicularBisector(B, C, lineB);
		
		return Point.lineLineIntersection(perpA, perpB);
	}
	
	
}
