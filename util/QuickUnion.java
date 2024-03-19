package util;
import java.util.*;

//QuickUnion performs DSU quickly
public class QuickUnion {
	private int[] roots;

	public QuickUnion(int N) {
		roots = new int[N];
		for(int i = 0; i < N; i++) {
			roots[i] = i;
		}
	}
	
	public QuickUnion(List<Integer>[] adjList) {
		this(adjList.length);
		for(int i = 0; i < adjList.length; i++) {
			for(int j : adjList[i]) {
				unite(i, j);
			}
		}
	}
	
	private int root(int i) {
		while (i != roots[i]) i = roots[i];
		return i;
	}
	
	public boolean find(int p, int q) {
		return root(p) == root(q);
	}
	
	public void unite(int p, int q) {
		 int i = root(p);
		 int j = root(q);
		 roots[i] = j;
	}
}
