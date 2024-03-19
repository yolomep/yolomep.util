package util;
import java.util.*;
import java.util.function.Predicate;


//import java.io.*;
//just a list of functions
public class Util {
	
	static int count;
	
	static class ReverseOrder implements Comparator<Integer> {
		public int compare(Integer o1, Integer o2) {
	        
	        return o2.compareTo(o1);
	    }
	}
	public static void permutation(String str) { 

	    permutation("", str); 
	}
	//I think I got this from a book
	private static void permutation(String prefix, String str) {
	    int n = str.length();
	    if (n == 0) {
	    	//do stuff
	    }
	    else {
	        for (int i = 0; i < n; i++)
	            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
	    }
	}
	//another permutation finding, return a set.
	public static Set<String> generatePerm(String input){
	    Set<String> set = new HashSet<String>();
	    if (input == "")
	        return set;

	    Character a = input.charAt(0);

	    if (input.length() > 1){
	        input = input.substring(1);

	        Set<String> permSet = generatePerm(input);

	        for (String x : permSet){
	            for (int i = 0; i <= x.length(); i++)
	            {
	                set.add(x.substring(0, i) + a + x.substring(i));
	            }
	        }
	    }
	    else{
	        set.add(a + "");
	    }
	    return set;
	}
	
	public static void permutation(int[] x) {
		Deque<Integer> perm = new ArrayDeque<>();
		int n = x.length;
		count = 0;
		search(x, perm, n, new boolean[n]);
	}
	public static boolean HasConsecutive(List<Integer> a){
		  for(int i = 0; i < a.size() - 2; i++) {
		    if (a.get(i + 1) > a.get(i) && a.get(i + 2) > a.get(i + 1)) return true;
		  }
		  return false;
		}
	public static boolean hasConsec(List<Integer> a) {
		for(int i = 0; i < a.size() - 2; i++) {
		    if (a.get(i + 1) < a.get(i) && a.get(i + 2) < a.get(i + 1)) return true;
		  }
		  return false;
		}
	
	static void permute(java.util.List<Integer> arr, int k){
        for(int i = k; i < arr.size(); i++){
            java.util.Collections.swap(arr, i, k);
            permute(arr, k+1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
			System.out.println(arr);
			if(!HasConsecutive(arr) && !hasConsec(arr)) {
				count++;
			}
        }
    }
	
	public static void search(int[] s, Deque<Integer> permutation, int n, boolean[] chosen) {
		
		if(permutation.size() == n) {
			List<Integer> ha = new ArrayList<>(permutation);
			//do stuff
			System.out.println(ha);
			if(!HasConsecutive(ha) && !hasConsec(ha)) {
				count++;
			}
		}
		else {
			for(int i = 0; i < n; i++) {
				if(chosen[i]) continue;
				chosen[i] = true;
				permutation.add(s[i]);
				search(s, permutation, n, chosen);
				chosen[i] = false;
				permutation.pop();
			}
		}
	}

	public static <T> void permutation(List<T> x) {
		Deque<T> perm = new ArrayDeque<>();
		int n = x.size();
		search(x, perm, n, new boolean[n]);
	}
	
	public static <T> void search(List<T> s, Deque<T> permutation, int n, boolean[] chosen) {
		
		if(permutation.size() == n) {
			//do stuff
		}
		else {
			for(int i = 0; i < n; i++) {
				if(chosen[i]) continue;
				chosen[i] = true;
				permutation.add(s.get(i));
				search(s, permutation, n, chosen);
				chosen[i] = false;
				permutation.pop();
			}
		}
	}
	
	public static boolean isPalindrome(String s) {
		for(int i = 0; i < s.length()/2; i++) {
			if(s.charAt(i) != s.charAt(s.length() - i - 1)) return false;
		}
		return true;
	}
	
	public static List<Point> find(String word, char[][] board) {
		List<Point> locations = new ArrayList<>();
        for(int r=0; r < board.length; r++){
            for(int c=0; c <= (board[r].length - word.length()); c++){
            	if (search2D(board, r, c, word)) 
            		locations.add(new Point(r, c));
            }
        }
        
        return locations;
    }
	
	static boolean search2D(char[][] grid, int row, int col, String word) { 
		if (grid[row][col] != word.charAt(0)) 
			return false; 
		
		int len = word.length(); 
		int[] x = new int[] {-1, -1, -1, 0, 0, 1, 1, 1 };
		
		int[] y = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
		
		for (int dir = 0; dir < 8; dir++) { 
			int k, rd = row + x[dir], cd = col + y[dir]; 

			for (k = 1; k < len; k++) { 

				if (rd >= grid[0].length || rd < 0 || cd >= grid.length || cd < 0) 
				    break; 

				if (grid[rd][cd] != word.charAt(k)) 
				    break; 

				rd += x[dir]; 
				cd += y[dir]; 
			} 

			if (k == len) 
				return true; 
		} 
		return false; 
	} 
	
	public static int[] getFrequency(String s) {
		int[] blocksNeeded = new int[26];
		for(int i = 0; i < s.length(); i++) {
			int index = s.charAt(i) - 'a';
			blocksNeeded[index]++;
		}
		return blocksNeeded;
	}
	//found online - needed a padEnd function like the javascript one, java didn't have it.
	public static String leftPad(String input, char ch, int L) { 
        return String.format("%" + L + "s", input) .replace(' ', ch);
    } 
  
    // lol JS padEnd copy
    public static String padEnd(String input, char ch, int L) { 
        return String.format("%" + (-L) + "s", input).replace(' ', ch); 
    } 
    
    public static String padEnd(String input, int L) { 
        return String.format("%" + (-L) + "s", input); 
    } 
    
//    public static Move[] getRandom(Move[] arr, int n) {
//	    Move[] result = new Move[n];
//	    int len = arr.length;
//	   int[] taken = new int[len];
//	    while (n-- > 0) {
//	         int x = (int) Math.floor(Math.random() * len);
//	         result[n] = arr[check(x, taken) ? taken[x] : x];
//	         taken[x] = check(--len, taken) ? taken[len] : len;
//	    }
//	    return result;
//	}
    //i forgot where is was found. I think stackoverflow
	public static int[] getRandom(int[] arr, int n) {
		int[] result = new int[n];
	    int len = arr.length;
	   int[] taken = new int[len];
	    while (n-- > 0) {
	         int x = (int) Math.floor(Math.random() * len);
	         result[n] = arr[check(x, taken) ? taken[x] : x];
	         taken[x] = check(--len, taken) ? taken[len] : len;
	    }
	    return result;
	}
	
	public static boolean check(String toCheck, String[] avalible) {
		for(String s: avalible) 
			if(s.equals(toCheck)) return true;
		
		return false;
	}
	
	public static boolean check(int toCheck, int[] avalible) {
		
		for(int s: avalible) 
			if(s == toCheck) return true;
		
		return false;
	}
	
//	public static boolean check(Move toCheck, Move[] avalible) {
//		for(Move s: avalible) 
//			if(s.equals(toCheck)) return true;
//		
//		return false;
//	}
	
	public static void dprintln(String s) throws InterruptedException {
		for(char c : s.toCharArray()) {
			System.out.print(c);
			Thread.sleep(30);
		}
		System.out.println();
		Thread.sleep(100);
	}
	/**
	 * 
	 * @param s
	 * @param delay (ms)
	 */
	public static void dprintln(String s, int delay) throws InterruptedException {
		for(char c : s.toCharArray()) {
			System.out.print(c);
			Thread.sleep(delay);
		}
		System.out.println();
		Thread.sleep(100);
	}
	
	public static void dprint(String s) throws InterruptedException {
		for(char c : s.toCharArray()) {
			System.out.print(c);
			Thread.sleep(30);
		}
	}
	
	public static void dprint(String s, int delay) throws InterruptedException {
		for(char c : s.toCharArray()) {
			System.out.print(c);
			Thread.sleep(delay);
		}

	}
	/**
	 * @author Yolomep
	 * @param enlarge
	 * @param factor
	 * @return
	 */
	public static int[] enlarge1DArray(int[] enlarge, int factor) {
		int[] enlarged = new int[enlarge.length*factor];
		int index = 0;
		for(int i = 0; i < enlarge.length; i ++) {
			for(int j = 0; j < factor; j++) {
				enlarged[index] = enlarged[i];
				index++;
			}
		}
		return enlarged;
	}
	public static char[][] enlarge2DArray(char[][] enlarge, int factor){
		char[][] enlarged = new char[enlarge.length*factor][enlarge[0].length*factor];
		int index = 0;
		for(int i = 0; i < enlarge.length; i ++) {
			for(int j = 0; j < factor; j++) {
				enlarged[index] = enlarged[i];
				index++;
			}
		}
		return enlarged;
	}
	/*
	public static <T> void rotate90 (T[][] mat) {
		final int M = mat.length;
	    final int N = mat[0].length;
	    T[][] ret = new T[N][M];
	    for (int r = 0; r < M; r++) {
	        for (int c = 0; c < N; c++) {
	            ret[c][M-1-r] = mat[r][c];
	        }
	    }
	}
	*/
	
	//not fast
	public static void swap (int[] i, int a, int b){
	    i[a] = i[a]^i[b];
	    i[b] = i[a]^i[b];
	    i[a] = i[a]^i[b];
	}
	
	public static void subset() {
		int n = 0;
		int[] var = new int[n];
		for(int b = 0; b < (1<<n); b++) {
			List<Integer> subset = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				if ((b&(1<<i)) == 1) subset.add(var[i]);
			}
			//use here
		}
	}
	//call subset(0, n, new Deque<T>, orig)
	public static <T> void subset(int k, int n, Deque<T> subset, List<T> orig) {
		if (k == n) {
			//process
		}
		else {
			subset(k + 1, n, subset, orig);
			subset.add(orig.get(k));
			subset(k + 1, n, subset, orig);
			subset.pop();
		}
	}
	
	public static int[] _2SUM(int[] arr1, int tar) {
		int[] arr = arr1.clone();
		Arrays.sort(arr);
		if(arr.length < 2) return new int[] {-1, -1}; 
		int p1 = 0;
		int p2 = arr.length - 1;
		int sum = arr[p1] + arr[p2];
		while(sum != tar && p2 != p1) {
			if(sum > tar) {
				p2--;
			}
			else {
				p1++;
			}
			sum = arr[p1] + arr[p2];
		}
		if(p1 == p2) return new int[] {-1, -1};
		else return new int[] {p1, p2};
	}
	
	public static List<Integer> KMax(int[] arr, int k){
		Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
	    List<Integer> ret = new ArrayList<>();
	    int i = 0;
	    
	    
        for (; i < k; i++)
            queue.add(arr[i]);
 
        ret.add(queue.peek());
 
        queue.remove(arr[0]);
 
        for (; i < arr.length; i++) {

            queue.add(arr[i]);
            ret.add(queue.peek());
 

            queue.remove(arr[i - k + 1]);
        }
        
	    return ret;
	}
	//not my best code, but does the job
	//merged intervals in complete
	public static int sumIntervals(int[][] intervals) {
		if(intervals == null || intervals.length == 0) return 0;
	    Arrays.sort(intervals, new Comparator<int[]>( ){
	        public int compare(int[] a, int[] b){
	          return a[0] - b[0];
	        }
	    });
        Deque<int[]> complete = new ArrayDeque<>();
        complete.push(intervals[0]);
      
        for(int[] interval : intervals){
        	int[] first = complete.peek();
        	if(first[1] < interval[0])
        		complete.push(interval);
        	else if (first[1] < interval[1]) {  
        		first[1] = interval[1];  
        	}  
        }
        int full = 0;
        for(int[] interval : complete){
        	full += interval[1] - interval[0];
        }
        return full;
    }
	
	public static int indexOf(int key, int[] A, int low, int high) {
		if(A[high]<key) return -1;
		if(A[low]>=key) return low;
		while(low<high) {
			int mid = (low+high) / 2;
			if(A[mid]<key)
				low = mid+1;
			else
				high = mid;
		}
		return high;
	}
	
	public static void binarySearch(int[] arr, int x) {
		
		int l = 0, r = arr.length - 1; 
        while (l <= r) { 
            int m = l + (r - l) / 2; 
  
            // Check if x is present at mid 
            if (arr[m] == x) 
            	break;
                //return m; 
  
            // If x greater, ignore left half 
            if (arr[m] < x) 
                l = m + 1; 
  
            // If x is smaller, ignore right half 
            else
                r = m - 1; 
        } 
  
        // if we reach here, then element was 
        // not present 
        //return -1; 
	}
	
	public static <T> T[] normalize(T[] arr) {
		return arr;
	}
	
	//this is just a base code
	public static void dijkstra() {
		int N = 10; //number of vertices
		List<Integer>[] graph = new ArrayList[N]; 
		int source = 0;
		Queue<Integer> pq = new PriorityQueue<>(); 
		int[] dis = new int[N];
		//graph = graph, source = source
		graph[0].add(1);
		graph[0].add(2);
		graph[1].add(2);
		graph[1].add(5);
		
		
		//init graph ^
	}
	
	public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
	    Set<Set<T>> sets = new HashSet<Set<T>>();
	    if (originalSet.isEmpty()) {
	        sets.add(new HashSet<T>());
	        return sets;
	    }
	    List<T> list = new ArrayList<T>(originalSet);
	    T head = list.get(0);
	    Set<T> rest = new HashSet<T>(list.subList(1, list.size())); 
	    for (Set<T> set : powerSet(rest)) {
	        Set<T> newSet = new HashSet<T>();
	        newSet.add(head);
	        newSet.addAll(set);
	        sets.add(newSet);
	        sets.add(set);
	    }       
	    return sets;
	}  
	
	/** 
	 * [a, b)
	 * @param a
	 * @param b
	 * @return
	 */
	public static int randInt(int a, int b) {
		return (int) ((b - a)*(Math.random())) + a;
	}
	
	public static int randIntFast(int a, int b) {
		return java.util.concurrent.ThreadLocalRandom.current().nextInt(a, b);
	}
	
	public boolean isForest(List<Integer> adjList[]) {
		boolean[] visited = new boolean[adjList.length];
		for(int i = 0; i < adjList.length; i++) 
			if(!visited[i])
				if(DFSRec(adjList, visited, i, -1))
					return true;
		  return false;
		
	}
	
	private boolean DFSRec(List<Integer>[] graph, boolean[] visited, int curr, int parent) {
		visited[curr] = true;
		for(int adj : graph[curr]) {
		    if(!visited[adj]) {
				if(DFSRec(graph, visited, adj, curr))
					return true;
		    }
		    else if(adj != parent)
		    	return true;
		  }
		  return false;
	}
	
	public boolean isForest(List<List<Integer>> adjList) {
		boolean[] visited = new boolean[adjList.size()];
		for(int i = 0; i < adjList.size(); i++) 
			if(!visited[i])
				if(DFSRec(adjList, visited, i, -1))
					return true;
		  return false;
		
	}
	
	private boolean DFSRec(List<List<Integer>> graph, boolean[] visited, int curr, int parent) {
		visited[curr] = true;
		for(int adj : graph.get(curr)) {
		    if(!visited[adj]) {
				if(DFSRec(graph, visited, adj, curr))
					return true;
		    }
		    else if(adj != parent)
		    	return true;
		  }
		  return false;
	}
	
    // recursive binary search
    private static int findPeakUtil(int arr[], int low, int high, int n) {
        int mid = low + (high - low) / 2;
        if ((mid == 0 || arr[mid - 1] <= arr[mid]) && (mid == n - 1 || arr[mid + 1] <= arr[mid]))
            return mid;
        else if (mid > 0 && arr[mid - 1] > arr[mid])
            return findPeakUtil(arr, low, (mid - 1), n);
        return findPeakUtil(arr, (mid + 1), high, n);
    }
 
    // return peak index of array
    public static int findPeak(int arr[]) {
        return findPeakUtil(arr, 0, arr.length - 1, arr.length);
    }
    
    // recursive binary search
    private static int findMinUtil(int arr[], int low, int high, int n) {
        int mid = low + (high - low) / 2;
        if (mid == 0 || arr[mid - 1] > arr[mid] && mid == n - 1 || arr[mid] < arr[mid + 1])
            return mid;
        else if (mid > 0 && arr[mid - 1] < arr[mid])
            return findMinUtil(arr, low, (mid - 1), n);
        else
            return findMinUtil(arr, (mid + 1), high, n);
    }
 
    // return peak index of array
    public static int findMin(int arr[]) {
        return findMinUtil(arr, 0, arr.length - 1, arr.length);
    }
    
    public static void fillPrefixSum(int arr[], int n, int prefixSum[]) {
		prefixSum[0] = arr[0];

		for (int i = 1; i < n; ++i)
			prefixSum[i] = prefixSum[i - 1] + arr[i];
	}
    
    public static int[] prefixSumArr(int arr[]) {
    	int[] prefixSum = new int[arr.length];
		prefixSum[0] = arr[0];

		for (int i = 1; i < arr.length; ++i)
			prefixSum[i] = prefixSum[i - 1] + arr[i];
		return prefixSum;
	}
    
	public static int lastTrue(int lo, int hi, Predicate<Integer> f) {
		// if none of the values in the range work, return lo - 1
		lo--;
		while (lo < hi) {
			// find the middle of the current range (rounding up)
			int mid = lo + (hi - lo + 1) / 2;
			if (f.test(mid)) {
				// if mid works, then all numbers smaller than mid also work
				lo = mid;
			} else {
				// if mid does not work, greater values would not work either
				hi = mid - 1;
			} 
		}
		return lo;
	} 
	
	public static int firstTrue(int lo, int hi, Predicate<Integer> f) {
		hi++;
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (f.test(mid)) {
				hi = mid;
			} else {
				lo = mid + 1;
			}
		}
		return lo;
	}
	
	public static int numOfConnectedComponents(List<Integer>[] graph) {
        boolean[] visited = new boolean[graph.length];
        int count = 0;
        for (int i = 0; i < graph.length; i ++) {
            if (visited[i] == false) {
                count++;
                dfsUtil(graph, i, visited);
            }
        }
        return count;
	}
	
    private static void dfsUtil(List<Integer>[] adj, int index, boolean[] visited) {
        visited[index] = true;
        for (int j : adj[index]) {
            if (visited[j] == false) {
                dfsUtil(adj, j, visited);
            }
        }
    }
    
	public static int numOfConnectedComponents(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        int count = 0;
        for (int i = 0; i < graph.size(); i ++) {
            if (visited[i] == false) {
                count++;
                dfsUtil(graph, i, visited);
            }
        }
        return count;
	}
	
    private static void dfsUtil(List<List<Integer>> adj, int index, boolean[] visited) {
        visited[index] = true;
        for (int j : adj.get(index)) {
            if (visited[j] == false) {
                dfsUtil(adj, j, visited);
            }
        }
    }
    
  //pretty much useless now
  	public static String getInput(Scanner in, String[] type, String text) {
  		System.out.println(text);
  		String input = in.nextLine();
  		while(!check(input, type)) {
  			System.out.println("This isn't valid input. Try again.");
  			System.out.println(text);
  			input = in.nextLine();
  		}
  		return input;
  	}
  	//also useless
  	public static boolean getInput(Scanner in) {
  		System.out.println("Yes or No?");
  		String input = in.nextLine();
  		String[] type = new String[] {"Yes", "No", "Y", "N", "n", "y", "YES", "NO", "yeah", "YEH", "YEAH", "Yeah", "Ya", "ya", "Nope", "nope", "Ye", "no", "yes"};
  		while(!check(input, type)) {
  			System.out.println("This isn't valid input. Try again.");
  			System.out.println("Yes or No?");
  			input = in.nextLine();
  		}
  		type = new String[] {"No", "N", "n", "NO", "nope", "no", "Nope"};
  		if(check(input, type)) return false;
  		return true;
  	}
  	//now both functions are safe lol, had to sacrifice nextInt() rip
  	public static int getInput(Scanner in, int[] type, String text) {
  		System.out.println(text);
  		int input;
  		while(true) {
  			String s = in.next();
  			try {
  		        input = Integer.parseInt(s);
  		    } catch (final NumberFormatException e) {
  		    	System.out.println("This isn't valid input. Try again.");
  				System.out.println(text);
  				continue;
  		    }
  			if(check(input, type)) break;
  			System.out.println("This isn't valid input. Try again.");
  			System.out.println(text);
  		}
  		in.nextLine();
  		return input;
  	}
  	//now both functions are safe lol, had to sacrifice nextInt() rip
  	public static int getInput(Scanner in, int min, int max, String text) {
  		System.out.println(text);
  		int input;
  		while(true) {
  			String s = in.next();
  			try {
  		        input = Integer.parseInt(s);
  		    } catch (final NumberFormatException e) {
  		    	System.out.println("This isn't valid input. Try again.");
  				System.out.println(text);
  				continue;
  		    }
  			if(input >= min && input <= max) break;
  			System.out.println("This isn't valid input. Try again.");
  			System.out.println(text);
  		}
  		in.nextLine();
  		return input;
  	}
  	
}
