package util;
import java.util.*;

//DS that performs find in O(1)
public class QuickFind {
	public int[] colors; //why public? because this class is for quick use
	// + debugging, not production
	private static List<Integer>[] graph; //only used to store graph for dfs
	private static int[] colored; //store colors
	public QuickFind(int N) {
		colors = new int[N];
		for(int i = 0; i < N; i++) {
			colors[i] = i;
		}
	}
	
	//store adj list is a array of lists
	public QuickFind(List<Integer>[] adjList) {
		this(adjList.length);
		for(int i = 0; i < adjList.length; i++) {
			for(int j : adjList[i]) {
				unite(i, j);
			}
		}
	}
	
	public QuickFind(List<List<Integer>> adjList) {
		this(adjList.size());
		for(int i = 0; i < adjList.size(); i++) {
			for(int j : adjList.get(i)) {
				unite(i, j);
			}
		}
	}
	
	private QuickFind(int[] id) {
		this.colors = id;
	}
	
	public boolean find(int p, int q) {
		return colors[p] == colors[q];
	}
	
	public void unite(int p, int q) {
		int pid = colors[p];
		for(int i = 0; i < colors.length; i++) {
			if(colors[i] == pid) colors[i] = colors[q];
		}
	}
	
	public static QuickFind fromCycleGraph(List<Integer>[] adjList) {
		//colors quickfind graph quickly
		graph = adjList;
		colored = new int[adjList.length];
		int color = 1;
		for(int i = 0; i < adjList.length; i++) {
			if(colored[i] == 0) {
				dfs(i, color);
				color++;
			}
		}
		return new QuickFind(colored);
	}
	
	private static void dfs(int start, int color) {
		colored[start] = color;
		for(int node : graph[start]) {
			if(colored[node] == 0)
				dfs(node, color);
		}
	}
}
