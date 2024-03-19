# yolomep.util

Note: its named util instead of utils because StackOverflow said so and util is a word. 
This repostory is a combination of my own work and some code from:

* Fraction.java: Diane Kramer
* FrenwickTree.java: ricardodpsx@gmail.com 
* Math.eval: Boann 
* SegmentTree.java: https://algs4.cs.princeton.edu/99misc/SegmentTree.java

##### Table of Contents  

## Math

### Angle

Stores an angle without ambiguity of whether it is in degrees or radians.

#### Fields:

**double degrees** stores value of angle in degrees

**double radians** stores value of angle in radians

#### Functions:

**Angle(double)** default constructor, takes angle in degrees. Used fromRadians for radians

**fromRadians(double)** returns new angle from radians

### Decimal

Basically a custom class of BigDecimal in which there are some extra functions and a shorter name.
Only custom functions are explained. Since this class extends BigDecimal, all functions of BigDecimal is implemented. Please see [Java 14 BigDecimal Docs](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/math/BigDecimal.html).

#### Fields:

**MathContext DEFAULT_CONTEXT** IDK what this really does but I know it will make the decimal round up for certain stuff. I forgot what I was thinking when I did this

#### Functions:

Since 90% of functions are the same, I will list each with the corresponding BigDecimal function. Right is the BigDecimal name.

**div** divide

**divToIntegeralValue** divideToIntegralValue

**mod** remainder

**sub** subtract

**mul** multiply

### Fraction

Author: Diane Kramer 
Created: 9/25/01 - 10/16/01
GCD method: 02/19/06

This isn't my class so I'm not explaining the functions. The documentation that came with this is sufficient

### Log

Stores a logarithm with integer values in the form log_base(argument)

#### Fields:

**int argument** the argument of the log value

**int base** the base of the log value

#### Functions:

**constructor(base, argument)**

**add(log)** adds another log to this log. Returns a new log as the log class is immutable

**subtract(log)** subtracts another log. Same return as add.

**eval()** evaluates the log.

**toString()** prints the log

### MathEx

This class contains all the math functions that don't belong to a particular class. There are no fields

#### Functions: 

**average()** returns the average of an array of doubles or ints

**int C(n, k)** computes (n, k) returns an int

**BigInteger CBig(n, k)** computes (n, k) returns an BigDecimal

**long Clong(n, k)** computes (n, k) returns a long

**Point cauchy(a, b, c, d, e, f)** finds the solution of ax + by = c and dx + ey = f

**String cfBtB(number, b1, b2)** returns num_b2 from number_b1 -> converts the the first number in the first base to the second base

**compareTo1(int[], int[])** does compare to based on the second index

**compareTo2(int[], int[])** does compare to based on the first index

**det([][])** returns the determinant of the matrix

**eval(String expression)** computes the stuff in the expression. Similiar to javascript eval

**int factorial(int n)** returns n!

**int gcd(int, int)** returns the greatest common denominator of the two values


## Algorithm

### BinaryTree

### AVL

### Edge

### FenwickTree

### GenericGraph

### GenericTree

### Pair

### QuickFind

### QuickUnion

### SegmentTree

### WQuickUnionPC

### Util

**ReverseOrder** Comparator for reverse. We can use lambdas now.

#### Functions:


