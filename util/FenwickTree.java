package util;
import java.util.*;

/**
 * Minified version of FenwickTree by ricardodpsx@gmail.com for use on programming competitions
 * @author Yolomep
 *
 */
public class FenwickTree {
    int[] array;
    public FenwickTree(int size) {
        array = new int[size + 1];
    }

    public int rsq(int ind) {
        int sum = 0;
        while (ind > 0) {
            sum += array[ind];
            
            ind -= ind & (-ind);
        }

        return sum;
    }
    //return sum(a, b)
    public int rsq(int a, int b) {
        assert b >= a && a > 0 && b > 0;

        return rsq(b) - rsq(a - 1);
    }
    //tree[ind] += value
    public void update(int ind, int value) {
        while (ind < array.length) {
            array[ind] += value;
           
            ind += ind & (-ind);
        }
    }

    public int size() {
        return array.length - 1;
    }

}