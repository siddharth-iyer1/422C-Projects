/*
 * This file is how you might test out your code.  Don't submit this, and don't
 * have a main method in SortTools.java.
 */

package assignment1;

public class Main {
    public static void main(String [] args) {
        // call your test methods here
        int[] a = {1,4,3,2,5,6,7,8,9,10};
        // SortTools.isSorted(a, 10);
        // SortTools.find(a, 2,5);
        // SortTools.insertInPlace(a, 7, 4);
        SortTools.insertSort(a, 10);
    }
}
