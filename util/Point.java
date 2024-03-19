package util;
import java.util.*;
//import java.io.*;
/**
 * super comprehensive point class
 * @author Yolomep
 *
 */
public class Point implements Comparable<Point>, Cloneable {
	//#######################GENERAL FORM IS ACTUALLY STANDARD FORM
	public static final Point origin = new Point(0, 0);
	public double x;
	public double y;
	
	public Point() {
		x = 0;
		y = 0;
	}
	
	public Point(double x1, double y1) {
		x = x1;
		y = y1;
	}
	
//	public static void main(String[] args) {
//		Point[] idk = new Point[] {new Point(1,2), new Point(23, 3), new Point(43, 95)};
//		Arrays.sort(idk);
//		System.out.println(Arrays.toString(idk));
//	}
	
	public double dist(Point b) {
		return Math.hypot(x-b.x, y-b.y);
	}
	
	public static double dist(Point a, Point b) {
		return Math.hypot(a.x-b.x, a.y-b.y);
	}
	
	public boolean equals(Object o) {
		Point p = (Point) o;
		if(p.x == x && p.y == y) return true;
		return false;
	}
	
	public Point clone() {
		return new Point(x, y);
	}
	
	public static double area(List<Point> list) {
		double area = 0.0; 
		
	    int n = list.size();
	    
        int j = n - 1; 
        for (int i = 0; i < n; i++) { 
            area += (list.get(j).x + list.get(i).x) * (list.get(j).y - list.get(i).y); 

            j = i;  
        } 

        return Math.abs(area / 2.0); 
	}
	
	public static double area(Point... p) {
		double area = 0.0; 
	    int n = p.length;
	    
        int j = n - 1; 
        for (int i = 0; i < n; i++) { 
            area += (p[j].x + p[i].x) * (p[j].y - p[i].y); 

            j = i;  
        } 
 
        return Math.abs(area / 2.0); 
	}
	
	
	public static double perimeter(List<Point> coordinates) {
	    double distance = 0;
	    int len = coordinates.size();
	    for(int i = 0; i < len; i++) 
	    	distance += Point.dist(coordinates.get(i), coordinates.get((i + 1)%len));
	    
	    return distance;
	}
	
	public static double perimeter(Point... coordinates) {
	    double distance = 0;
	    int len = coordinates.length;
	    for(int i = 0; i < len; i++) 
	    	distance += Point.dist(coordinates[i], coordinates[(i + 1)%len]);
	    
	    return distance;
	}

	/**
	 * String[] strings = list.toArray(String[]::new);
	 * for primitives
	 * @param p
	 * @return
	 */
	public static Point[] fitRectangle(Point... p1) {
		
		double Xmax = Integer.MIN_VALUE;
		double Xmin = Integer.MAX_VALUE;
		double Ymax = Integer.MIN_VALUE;
		double Ymin = Integer.MAX_VALUE;
		
		for(Point p : p1) {
			Xmax = Math.max(p.x, Xmax);
			Xmin = Math.min(p.x, Xmin);
			Ymax = Math.max(p.y, Xmax);
			Ymin = Math.min(p.y, Ymin);
		}
		//returns clockwise
		//*    *
		//^    V
		//* <- *
		return new Point[] {new Point(Xmax, Ymax), new Point(Xmax, Ymin), new Point(Xmin, Ymin), new Point(Xmin, Ymax)};

	}
	//Math.max(Point.dist(edges[0], edges[1]), Point.dist(edges[1], edges[2])
	public static double fitSquareArea(Point... p1) {
		Point[] edges = fitRectangle(p1);
		return Math.pow(Math.max(Point.dist(edges[0], edges[1]), Point.dist(edges[1], edges[2])), 2);
	}
	//sorts by x then y - ascending
	public int compareTo(Point p) {
		if(x == p.x) {
			return (int) (y - p.y);
		}
		else {
			return(int) (x - p.x);
		}
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public int hashCode() {
		return (int) (31 * x + y);
	}
	
	public double mul(Point p) {
		return (x * p.y - y * p.x);
	}
	
	public void translate(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	public void translate(Point p) {
		x += p.x;
		y += p.y;
	}
	
	//check if p1 - p2 intersects q1 - q2
	public static boolean intersects(Point p1, Point p2, Point q1, Point q2) {
		try {
			double[] line1 = generalFormLine(p1, p2);
			double[] line2 = generalFormLine(q1, q2);
			MathEx.cauchy(line1[0], line1[1], line1[2], line2[0], line2[1], line2[2]);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public static Point intersection(Point p1, Point p2, Point q1, Point q2) throws ParallelLineError {
		if(!intersects(p1, p2, q1, q2)) throw new ParallelLineError();
		double[] line1 = generalFormLine(p1, p2);
		double[] line2 = generalFormLine(q1, q2);
		return MathEx.cauchy(line1[0], line1[1], line1[2], line2[0], line2[1], line2[2]);
	}
	public static Point average(Point... points) {
		double sumX = 0;
		double sumY = 0;
		for(Point p : points) {
			sumX += p.x;
			sumY += p.y;
		}
		return new Point(sumX/points.length, sumY/points.length);
	}
	//mx + b = y returns {m, b}
	public static double[] slopeIntersectLine(Point a, Point b) {
		double slope = (a.y - b.y)/(a.x - b.x);
		double intercept = a.y - slope*a.x;
		return new double[] {slope, intercept};
	}
	
	//ax + by = c returns {a, b, c} 
	public static double[] generalFormLine(Point a, Point b) {
		double A = a.y - b.y;
		double B = a.x - b.x;
		double C = A * a.x + B * a.y;
		return new double[] {A, B, C};
	}
	
	//input is two points and the line returns line in generalForm
	public static double[] perpendicularBisector(Point a, Point b, double[] line) {
		Point midPoint = Point.average(a, b);
		double c = -line[1] * (midPoint.x) + line[0] * midPoint.y;
		double A = -line[1];
		double B = line[0];
		return new double[] {A, B, c};
	}
	
	public static Point lineLineIntersection(double[] line1, double[] line2) {
		double det = line1[0]*line2[1] - line1[1]*line2[0];
		if(det == 0) {
			System.out.println("ERR - LINE DOES NOT INTERSECT");
			throw new ParallelLineError();
		}
		return MathEx.cauchy(line1[0], line1[1], line1[2], line2[0], line2[1], line2[2]);
				
	}
	
	//angle is in degrees
	public static double[] lineFromAngle(Point a, double angle) {
		double slope = Math.tan(Math.toDegrees(angle));
		double intercept = a.y - slope*a.x;
		return new double[] {slope, intercept};
	}
	
	/**
	 * Radius is the distance to vertex
	 * orientation should be the unit circle 
	 * @param center
	 * @param radius
	 * @param orientation
	 * @param sides
	 * @return coordinates of the polygon
	 */
	public static Point[] regPolygon(Point center, double radius, Angle orientation, int sides) {
		//set zero 0 clock position
		Point[] points = new Point[sides];
		//set 0 as the first clock position
		points[0] = new Point(radius, 0);
		//then rotate the point
		for(int i = 1; i < sides; i++) {
			points[i] = points[i - 1].clone();
			points[i].rotate(Point.origin, new Angle(360.0/sides));
		}
		for(Point p : points) {
			p.rotate(Point.origin, orientation);
			p.translate(center.x, center.y);
		}
		return points;
	}
	
	//this is only for regular polygons
	public static double distBisectorToVertex(int sides, double bisector) {
		//angle between bisector and vertex is 360/sides
		Angle angleBetween = new Angle(180/sides);
		return bisector/Math.cos(angleBetween.radians);
	}
	
	
	public void rotate(Point center, Angle angle) {
		translate(-center.x, -center.y);
		double tempX = x*Math.cos(angle.radians) - y*Math.sin(angle.radians);
		double tempY = y*Math.cos(angle.radians) + x*Math.sin(angle.radians);
		x = tempX;
		y = tempY;
		translate(center.x, center.y);
	}
	
	//Angle is a, b, c
	public static double[] angleBisectorPos(Point a, Point b, Point c) {
		double[] eq1 = generalFormLine(a, b); 
		double[] eq2 = generalFormLine(b, c);
		return new double[] {eq1[0]*Math.sqrt(eq2[0]*eq2[0]+eq2[1]*eq2[1]) - eq2[0]*Math.sqrt(eq1[0]*eq1[0]+eq1[1]*eq1[1]),
				eq1[1]*Math.sqrt(eq2[0]*eq2[0]+eq2[1]*eq2[1]) - eq2[1]*Math.sqrt(eq1[0]*eq1[0]+eq1[1]*eq1[1]),
				eq1[2]*Math.sqrt(eq2[0]*eq2[0]+eq2[1]*eq2[1]) - eq2[2]*Math.sqrt(eq2[0]*eq2[0]+eq2[1]*eq2[1])};
		
	}
	
	public static double[] angleBisectorNeg(Point a, Point b, Point c) {
		double[] eq1 = generalFormLine(a, b); 
		double[] eq2 = generalFormLine(b, c);
		return new double[] {eq1[0]*Math.sqrt(eq2[0]*eq2[0]+eq2[1]*eq2[1]) + eq2[0]*Math.sqrt(eq1[0]*eq1[0]+eq1[1]*eq1[1]),
				eq1[1]*Math.sqrt(eq2[0]*eq2[0]+eq2[1]*eq2[1]) + eq2[1]*Math.sqrt(eq1[0]*eq1[0]+eq1[1]*eq1[1]),
				eq2[2]*Math.sqrt(eq2[0]*eq2[0]+eq2[1]*eq2[1])+ eq1[2]*Math.sqrt(eq2[0]*eq2[0]+eq2[1]*eq2[1])};
		
	}
	
	static class ParallelLineError extends Error {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ParallelLineError() {
			super("THE LINE GIVEN IS PARALLEL AND DOES NOT INTERSECT.");
		}
	}
	
	
}

