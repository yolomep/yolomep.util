package util;

import java.util.*;
//import java.io.*;
import java.math.*;


public class MathEx {
	
	public static void main(String[] args) {
		BigDecimal k = new BigDecimal(10);
		System.out.println(k.subtract(new BigDecimal(1)));
	}
	
	//convertFromBaseToBase
	public static String cfBtB(String str, int fromBase, int toBase) {
	    return Long.toString(Long.parseLong(str, fromBase), toBase);
	}
		
	public static boolean isPal(String str) {
		int i = 0, j = str.length() - 1; 
		
        // While there are characters toc compare 
        while (i < j) { 
  
            // If there is a mismatch 
            if (str.charAt(i) != str.charAt(j)) 
                return false; 
  
            // Increment first pointer and 
            // decrement the other 
            i++; 
            j--; 
        } 
  
        // Given string is a palindrome 
        return true; 
	}
	
	public static int lcm(int n1, int n2) {
		int lcm =  (n1 > n2) ? n1 : n2;
		while(true) {
		    if( lcm % n1 == 0 && lcm % n2 == 0 ) {
		    	return lcm;

		    }
		    ++lcm;
		}
	}
	
	public static long lcm(long n1, long n2) {
		long lcm =  (n1 > n2) ? n1 : n2;
		while(true) {
		    if( lcm % n1 == 0 && lcm % n2 == 0 ) {
		    	return lcm;

		    }
		    ++lcm;
		}
	}
	
	public static int gcd(int n1, int n2) {
		n1 = ( n1 > 0) ? n1 : -n1;
        n2 = ( n2 > 0) ? n2 : -n2;

        while(n1 != n2) {
            if(n1 > n2)
                n1 -= n2;
            else
                n2 -= n1;
        }
        return n1;
	}
	
	public static BigInteger Cbig(int N, int K) {
	    BigInteger ret = BigInteger.ONE;
	    for (int k = 0; k < K; k++) {
	        ret = ret.multiply(BigInteger.valueOf(N-k))
	                 .divide(BigInteger.valueOf(k+1));
	    }
	    return ret;
	}
	
	public static long Clong(int n, int r) {
		long sum = n;
		for(int i = n - 1; i > n - r; i --) {
			sum *= i;
		}
		for(int i = 1; i <= r; i ++) {
			sum /= i;
		}
		return sum;
	}
	
	public static boolean isPalindrome(int z) {
		String s = z + "";
		for(int i = 0; i < s.length()/2; i++) {
			if(s.charAt(i) != s.charAt(s.length() - i - 1)) return false;
		}
		return true;
	}
	
	public static int C(int n, int r) {
	    if(r > n - r) r = n - r; // because C(n, r) == C(n, n - r)
	    int ans = 1;
	    int i;

	    for(i = 1; i <= r; i++) {
	        ans *= n - r + i;
	        ans /= i;
	    }

	    return ans;
	}
	
	public static LinkedList<Integer> sieve(int n){
        if(n < 2) return new LinkedList<Integer>();
        LinkedList<Integer> primes = new LinkedList<Integer>();
        LinkedList<Integer> nums = new LinkedList<Integer>();

        nums.add(2);
        for(int i = 3;i <= n;i += 2){
               nums.add(i);
        }

        while(nums.size() > 0){
                int nextPrime = nums.remove();
                for(int i = nextPrime * nextPrime;i <= n;i += nextPrime){
                        nums.removeFirstOccurrence(i);
                }
                primes.add(nextPrime);
        }
        return primes;
	}
	
	public static boolean isPrime(int number) {
        int sqrt = (int) Math.sqrt(number) + 1;
        for (int i = 2; i < sqrt; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
	

	private static int val2(int n) {
		  int m = 0;
		  if ((n&0xffff) == 0) {
		    n >>= 16;
		    m += 16;
		  }
		  if ((n&0xff) == 0) {
		    n >>= 8;
		    m += 8;
		  }
		  if ((n&0xf) == 0) {
		    n >>= 4;
		    m += 4;
		  }
		  if ((n&0x3) == 0) {
		    n >>= 2;
		    m += 2;
		  }
		  if (n > 1) {
		    m++;
		  }
		  return m;
	}

	// For convenience, handle modular exponentiation via BigInteger.
	private static int modPow(int base, int exponent, int m) {
		  BigInteger bigB = BigInteger.valueOf(base);
		  BigInteger bigE = BigInteger.valueOf(exponent);
		  BigInteger bigM = BigInteger.valueOf(m);
		  BigInteger bigR = bigB.modPow(bigE, bigM);
		  return bigR.intValue();
	}

	// Basic implementation.
	private static boolean isStrongProbablePrime(int n, int base) {
		  int s = val2(n-1);
		  int d = modPow(base, n>>s, n);
		  if (d == 1) {

		    return true;
		  }
		  for (int i=1; i < s; i++) {
		    if (d+1 == n) {
		      return true;
		    }
		    d = d*d % n;
		  }
		  return d+1 == n;
	}

	public static boolean isPrime2(int n) {
		  if ((n&1) == 0) {
		    return n == 2;
		  }
		  if (n < 9) {
		    return n > 1;
		  }

		  return isStrongProbablePrime(n, 2) && isStrongProbablePrime(n, 7) && isStrongProbablePrime(n, 61);
	}
	
	public static int compareTo1(int[] a, int[] b) {
		//sorts by last index
		if(a[1] == b[1]) return a[0] - b[0];
		return a[1] - b[1];
	}
	
	public static int compareTo2(int[] a, int[] b) {
		if(a[0] == b[0]) return a[1] - b[1];
		return a[0] - b[0];
	}
	
	public static int pInt(String s) {
		try {
			return Integer.parseInt(s);
		}
		catch(Exception e) {
			return Integer.MAX_VALUE;
		}
	}
	//credits: Boann in the public domain
	public static double eval(final String str) {
	    return new Object() {
	        int pos = -1, ch;

	        void nextChar() {
	            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	        }

	        boolean eat(int charToEat) {
	            while (ch == ' ') nextChar();
	            if (ch == charToEat) {
	                nextChar();
	                return true;
	            }
	            return false;
	        }

	        double parse() {
	            nextChar();
	            double x = parseExpression();
	            if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
	            return x;
	        }

	        // Grammar:
	        // expression = term | expression `+` term | expression `-` term
	        // term = factor | term `*` factor | term `/` factor
	        // factor = `+` factor | `-` factor | `(` expression `)`
	        //        | number | functionName factor | factor `^` factor

	        double parseExpression() {
	            double x = parseTerm();
	            for (;;) {
	                if      (eat('+')) x += parseTerm(); // addition
	                else if (eat('-')) x -= parseTerm(); // subtraction
	                else return x;
	            }
	        }

	        double parseTerm() {
	            double x = parseFactor();
	            for (;;) {
	                if      (eat('*')) x *= parseFactor(); // multiplication
	                else if (eat('/')) x /= parseFactor(); // division
	                else return x;
	            }
	        }

	        double parseFactor() {
	            if (eat('+')) return parseFactor(); // unary plus
	            if (eat('-')) return -parseFactor(); // unary minus

	            double x;
	            int startPos = this.pos;
	            if (eat('(')) { // parentheses
	                x = parseExpression();
	                eat(')');
	            } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
	                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
	                x = Double.parseDouble(str.substring(startPos, this.pos));
	            } else if (ch >= 'a' && ch <= 'z') { // functions
	                while (ch >= 'a' && ch <= 'z') nextChar();
	                String func = str.substring(startPos, this.pos);
	                x = parseFactor();
	                if (func.equals("sqrt")) x = Math.sqrt(x);
	                else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
	                else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
	                else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
	                else throw new RuntimeException("Unknown function: " + func);
	            } else {
	                throw new RuntimeException("Unexpected: " + (char)ch);
	            }

	            if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

	            return x;
	        }
	    }.parse();
	}
	/**
	 * new Comparator<int[]>() {
    		@Override
    		public int compare(int[] o1, int[] o2) {
    			if(o2[0] == o1[0])
    				return o1[1] - o2[1];
        		return Integer.compare(o1[0], o2[0]);
    		}
		}
	 */
	
	public static int factorial(int n) { 
        int res = 1, i; 
        for (i=2; i<=n; i++) 
            res *= i; 
        return res; 
    } 
	
	public static int Tau(int n) { 
		int count = 0;
        for(int i=1; i<=Math.sqrt(n); i++) { 
            if (n%i==0) { 
                if (n/i == i) 
                    count++;
                else 
                    count += 2;
            } 
        } 
        return count;
    } 	
	
	public static double[] lineThrough2Points(Point a, Point b) {
		double slope =  (a.y - b.y)/(a.x - b.x);
		double y_intercept = a.y - slope*a.x;
		return new double[] {slope, y_intercept};
	}
	
	public static double logBase(double argument, double base) {
		return Math.log(argument)/Math.log(base);
	}
	
	public static boolean intersect(int[] s1, int[] s2)
	{
	  Point p1 = new Point(s1[0], s1[1]), q1 = new Point(s1[2], s1[3]), p2 = new Point(s2[0], s2[1]), q2 = new Point(s2[2], s2[3]);
	  return Point.intersects(p1, q1, p2, q2);
	}
	
	public static boolean lineIntersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		double denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
		if (denom == 0.0) { // Lines are parallel.
			return false;
		}
		double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3))/denom;
		double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3))/denom;
		if (ua >= 0.0f && ua <= 1.0f && ub >= 0.0f && ub <= 1.0f) {
			// Get the intersection point.
			return true;
		}

		return false;
	}
	
	public static int littleSigma(int num) 
    { 
        // Final result of summation of divisors 
        int result = 0; 
       
        // find all divisors which divides 'num' 
        for (int i = 2; i <= Math.sqrt(num); i++) 
        { 
            // if 'i' is divisor of 'num' 
            if (num % i == 0) 
            { 
                // if both divisors are same then  
                // add it only once else add both 
                if (i == (num / i)) 
                    result += i; 
                else
                    result += (i + num / i); 
            } 
        } 
       
        // Add 1 to the result as 1 is also 
        // a divisor 
        return (result + 1 + num); 
    }
	
	/**
	 * ax + by = c
	 * dx + ey = f
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 * @return (x, y)
	 */
	public static Point cauchy(int a, int b, int c, int d, int e, int f) {
		double detA = a*e - b*d;
		double detX = c*e - b*f;
		double detY = a*f - c*d;
		return new Point(detX/detA, detY/detA);
	}
	
	public static Point cauchy(double a, double b, double c, double d, double e, double f) {
		double detA = a*e - b*d;
		double detX = c*e - b*f;
		double detY = a*f - c*d;
		return new Point(detX/detA, detY/detA);
	}
	
	public static int det(int[][] matrix) {
		return detHelper(matrix, matrix.length);
	}
	
	public static double det(double[][] matrix) {
		return detHelper(matrix, matrix.length);
	}
	
	private static int detHelper(int[][] mat, int n) {
		int D = 0;
		if(n == 1) return mat[0][0];
		int temp[][]  = new int[n][n];
		int sign = 1;
		for(int f = 0; f < n; f++) {
			getCofactor(mat, temp, 0, f, n);
			D += sign * mat[0][f] * detHelper(temp, n - 1);
			sign *= -1;
		}
		return D;
	}
	
	private static double detHelper(double[][] mat, int n) {
		double D = 0;
		if(n == 1) return mat[0][0];
		double temp[][]  = new double[n][n];
		int sign = 1;
		for(int f = 0; f < n; f++) {
			getCofactor(mat, temp, 0, f, n);
			D += sign * mat[0][f] * detHelper(temp, n - 1);
			sign *= -1;
		}
		return D;
	}
	
	public static void getCofactor(int[][] mat, int[][] temp, int p ,int q, int n) {
		int i = 0, j = 0;
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				if(row != p && col != q) {
					temp[i][j++] = mat[row][col];
					if(j == n - 1) {
						j = 0; 
						i++;
					}
				}
			}
		}
	}
	
	public static void getCofactor(double[][] mat, double[][] temp, int p ,int q, int n) {
		int i = 0, j = 0;
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				if(row != p && col != q) {
					temp[i][j++] = mat[row][col];
					if(j == n - 1) {
						j = 0; 
						i++;
					}
				}
			}
		}
	}
	
	public static double average(int[] x) {
		return (double) sum(x)/x.length;
	}
	
	public static int sum(int[] x) {
		int s = 0;
		for(int i : x) s += i;
		return s;
	}
	
	public static double average(double[] x) {
		return sum(x)/x.length;
	}
	
	public static double sum(double[] x) {
		int s = 0;
		for(double i : x) s += i;
		return s;
	}
	
	public static int sumOfAngles(int sides) {
		return 180*(sides - 2);
	}
	
	
    public static int isPower(int n) {
    	if(n < 2) return 0; //can be power technically
        for (int x = 2; x <= Math.sqrt(n); x++) {
            int y = 2;
 
            double p = Math.pow(x, y);
 
            while (p <= n && p > 0) {
                if (p == n)
                    return x;
                y++;
                p = Math.pow(x, y);
            }
        }
        return -1;
    }
	

}
