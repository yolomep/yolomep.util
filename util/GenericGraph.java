package util;
import java.util.*;
//import java.io.*;
/**
 * This is a generic undirected graph. Supports only one edge connecting two nodes
 * @author Yolomep
 *
 * @param <T>
 */
public class GenericGraph <T> {
	//use map instead of list
	private Map<T, List<T> > graph;
	private Set<T> visited;
	private int hum;
	private Map<T, Integer> color;
	
	public GenericGraph() {
		graph = new HashMap<>();
		color = new HashMap<>();
	}
	
	public GenericGraph(int eh) {
		this();
		hum = eh;
	}
	
	public void addNode(T add) {
		graph.put(add, new LinkedList<>());
	}
	
	public void addEdge(T a, T b) { 
		if(a.equals(b)) {
			if (!graph.containsKey(a)) 
				addNode(a); 
			graph.get(a).add(a);
			return;
		}
		
		if (!graph.containsKey(a)) 
			addNode(a); 
		
		if (!graph.containsKey(b)) 
			addNode(b); 
		
		graph.get(a).add(b);

		graph.get(b).add(a); 

	} 
	
	/**
	 * a -> b
	 * @param a
	 * @param b
	 */
	public void addDirectedEdge(T a, T b) {
		if(!graph.containsKey(a)) {
			addNode(a);
		}
		
		graph.get(a).add(b);
	}
	
	public int size() {
		return graph.size();
	}
	
	public boolean hasNode(T v) {
		if(graph.containsKey(v)) return true;
		return false;
	}
	
	public boolean hasEdge(T a, T b) {
		if(graph.get(a).contains(b)) return true;
		return false;
	}
	
	public int degree(T node) {
		return graph.get(node).size();
	}
	
	public Map<T, List<T>> get(int pass) throws Exception {
		if(pass == hum) return graph;
		throw new Exception("You screwed up!");
	}
	
	public List<T> getNode(T node) {
		return graph.get(node);
	}
	
	public void resetVisited() {
		visited = new HashSet<>();
	}
	
	public List<Pair<T, Integer>> visitOrder = new ArrayList<>();
	//editable
	public void dfs(T node, int dist) {
		visited.add(node);
		//add function here
		visitOrder.add(new Pair<>(node, dist));
		if(graph.containsKey(node)) {
		    for(T next : graph.get(node)) {
	            if(!visited.contains(next)) {
	                dfs(next, dist+1);
	            }
	        }
		}
		
	}
	
	public void bfs(T node) {
		Queue<T> q = new ArrayDeque<>();
		q.add(node);
		while(!q.isEmpty()) {
			T v = q.poll();
			visited.add(v);
			//edit me!
			for(T e : graph.get(v)) {
				if(!visited.contains(e)) {
					q.add(e);
				}
			}
		}
	}
	
	public void bfs2(T node) {
        Queue<Pair<T, Integer>> q = new ArrayDeque<>();
        q.add(new Pair<>(node, 0));
        while(!q.isEmpty()) {
            Pair<T, Integer> v = q.poll();
            visited.add(v.first);
            //edit me!
            visitOrder.add(v);
            if(graph.containsKey(v.first)) {
                for(T e : graph.get(v.first)) {
                    if(!visited.contains(e)) {
                        q.add(new Pair<>(e, v.second + 1));
                    }
                }
            }
        }
    }
	
	private boolean color(T pos, int c) {
		if (color.get(pos) != -1 && color.get(pos) != c) 
	            return false; 

        color.replace(pos, c); 
        boolean ans = true; 
        for(T node : graph.get(pos)) {
        	if(color.get(node) == -1)
        		ans &= color(node, 1 - c); 
        	if(color.get(node) == -1 && color.get(node) != 1 - c)
        		return false;
        }
        
        return ans;
	}
	
	@Deprecated
	public boolean isBipartite() {
		color = new HashMap<>();
		
		for(T key : graph.keySet()) {
			color.put(key, -1);
		}
		
        return color(graph.keySet().iterator().next(), 1);
	}
	
	@Override
	public String toString() {
        StringBuilder s = new StringBuilder();
        for(T v : graph.keySet()) {
        	s.append(v + ": ");
            for (T w : graph.get(v)) {
                s.append(w + " ");
            }
            s.append('\n');
        }

        return s.toString();
    }
	
	public String toInput() {
		String s = Integer.toString(graph.size()) + "\n";
		for(T v : graph.keySet()) {
			s += v + "\n";
		}
		
		for(T v : graph.keySet()) {
			for(T w : graph.get(v)) {
				s += v + " " + w + "\n";
			}
		}
		return s;
		
	}
	
	public boolean isForest() {
		resetVisited();
		for(T val : graph.keySet())
			if(!visited.contains(val))
				if(DFSRec(graph, val, null))
					return true;
		  return false;
		
	}
	
	private boolean DFSRec(Map<T, List<T>> G, T curr, T parent) {
		visited.add(curr);
		for(T adj: G.get(curr)) {
		    if(!visited.contains(adj)) {
				if(DFSRec(G, adj, curr))
					return true;
		    }
		    else if(adj != parent || !adj.equals(parent))
		    	return true;
		  }
		  return false;
	}


}
