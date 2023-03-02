package org.algos.chapterTwo;

/**
 * Quicksort uses ~ 2NlnN compares (1/6 that many exchanges) on average to sort an array of length N with distinct keys.
 *      Cost of partitioning: N + 1, Cost of sorting half the array (C0:CN-1)
 *      
 */
public class Quick extends Sort{
    public static void sort(Comparable[] a){
        StdRandom.shuffle(a); // Eliminate dependence on input
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int j = partition(a, lo, hi); // Partition
        sort(a, lo, j-1); // chapterTwo.Sort left part a[lo .. j-1]
        sort(a, j+1, hi); // chapterTwo.Sort right part a[j+1 .. hi]
    }

    /**
     * rearranges array s/t
     * 1) the entry a[j] is in its final place in the array, for some j
     * 2) No entry in a[lo] through a[j-1] is greater than a[j]
     * 3) No entry in a[j+1] through a[hi} is less than a[j]
     *
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private static int partition(Comparable[] a, int lo, int hi){
        // Partition into a[lo..i-1], a[i], a[i+1..hi]
        int i = lo, j= hi +1; // left and right scan indices
        Comparable v = a[lo]; // partitioning item
        while (true){
            // Scan right, scan left, check for scan complete and exchange
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if(i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j); // put v = a[j] into position
        return j; // with a[lo..j-1] <= a[j] <= a[j+1 .. hi]
    }
}
