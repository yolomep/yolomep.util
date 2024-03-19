package util;

public class Edge implements Comparable<Edge> {
	int a, b, w;
	public Edge(int a, int b, int w) {
		this.a = a;
		this.b = b;
		this.w = w;
	}
	/**
	 * sorts ascending
	 */
	public int compareTo(Edge e) {
		return Integer.compare(w, e.w);
	}
	
}
