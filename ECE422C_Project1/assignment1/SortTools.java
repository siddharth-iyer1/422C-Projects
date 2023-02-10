// SortTools.java
/*
 * EE422C Project 1 submission by
 * Siddharth Iyer
 * ssi325
 * 17180
 * Spring 2023
 * Slip days used: 
 */


package assignment1;

import java.util.Arrays;

public class SortTools {
    /**
      * Return whether the first n elements of x are sorted in non-decreasing
      * order.
      * @param x is the array
      * @param n is the size of the input to be checked
      * @return true if array is sorted
      */
    public static boolean isSorted(int[] x, int n) {
        for(int i = 0; i < (n-1); i++){
            if(x[i] > x[i+1]){
                return false;
            }
        }
        return true;
    }

    /**
     * Return an index of value v within the first n elements of x.
     * @param x is the array
     * @param n is the size of the input to be checked
     * @param v is the value to be searched for
     * @return any index k such that k < n and x[k] == v, or -1 if no such k exists
     */
    public static int find(int[] x, int n, int v) {
    // Binary Search
    int low = 0;
    int high = n;
    if(x[n-1] < v){   // Since sorted, if v is out of bounds, must fail
        System.out.println("Fail");
        return -1;
    }
    while(low != high){
        int j = (high + low)/2;
        if(x[j] == v){
            System.out.println(j);
            System.out.println(x[j]);
            return j;
        }
        else if(x[j] > v){
            high = j;
        }
        else if(x[j] < v){
            low = j;
        }
    }
    System.out.println("Fail");
    return -1;
    }

    /**
     * Return a sorted, newly created array containing the first n elements of x
     * and ensuring that v is in the new array.
     * @param x is the array
     * @param n is the number of elements to be copied from x
     * @param v is the value to be added to the new array if necessary
     * @return a new array containing the first n elements of x as well as v
     */
    public static int[] copyAndInsert(int[] x, int n, int v) {
        int ret [] = new int[n + 1];
        boolean flag = false;

        if(x[n-1] < v){
            for(int i = 0; i < n; i++){
                ret[i] = x[i];
            }
            ret[n] = v;
        }

        if(x[n-1] > v){
            // There exists a possibility that v is in the array already
            flag = false;
        }

        for(int i = 0; i < n; i++){
            if(x[i] == v){
            flag = true;
            }
        }

        if(flag){
            for(int i = 0; i < n; i++){
                ret[i] = x[i];
            }
            ret = Arrays.copyOf(ret, ret.length-1);
        }
        else{
            int j = 0;
            int k = 0;
            while(x[j] < v){
                ret[j] = x[j];
            j++;
            }
            ret[j] = v;
            j++;
            k = j-1;
            while(k < n){
                ret[j] = x[k];
                j++;
                k++;
            }
        }
        
        System.out.println(Arrays.toString(ret));
        return ret;
    }

    /**
     * Insert the value v in the first n elements of x if it is not already
     * there, ensuring those elements are still sorted.
     * @param x is the array
     * @param n is the number of elements in the array
     * @param v is the value to be added
     * @return n if v is already in x, otherwise returns n+1
     */

    public static int insertInPlace(int[] x, int n, int v) {
        // Check for v
        for(int i = 0; i < n; i++){
            if(x[i] == v){
                return n;
            }
        }
        
        int following_val = 0;
        while(x[following_val] < v){
            following_val++;
        }

        for(int j = n; j > following_val; j--){
            x[j] = x[j-1];
        }

        x[following_val] = v;
        x = Arrays.copyOf(x, n+1);
        System.out.println(Arrays.toString(x));
        return -1;
    }

    /**
     * Sort the first n elements of x in-place in non-decreasing order using
     * insertion sort.
     * @param x is the array to be sorted
     * @param n is the number of elements of the array to be sorted
     */
    public static void insertSort(int[] x, int n) {
        for (int i = 1; i < n; i++) {
            int key = x[i];
            int j = i - 1;
            while (j >= 0 && x[j] > key) {
                x[j + 1] = x[j];
                j = j - 1;
            }
            x[j + 1] = key;
        }
        System.out.println(Arrays.toString(x));
    }
}


