package org.algos.chapterTwo;
// pg.251 chapterTwo.Insertion chapterTwo.Sort

/**
 * Running time dependent on initial order of the items in the input
 * Uses ~N^2/4 compares and ~N^2/4 exchanges to sort a randomly ordered array of length N w/ distinct keys, on the average.
 * Worst case ~N^2/2 compares and ~N^2/2 exchanges and the best case is N-1 compares and 0 exchanges
 * Works well in practice for certain types of nonrandom arrays that arise even if large
 *
 * For each i from 0 to N-1, exchange a[i] with the entries that are smaller in a[0] through a[i-1]
 * As the index i travels from left to right, the entries to its left are in sorted order in the array, the array
 * is fully sorted when i reaches the right end.
 */
public class Insertion {
    public static void sort(Comparable[] a){
        // sort a[] into increasing order.
        int N = a.length;
        for (int i = 1; i < N; i++){
            // Insert a[i] among a[i-1], a[i-2], a[i-3]...
            for (int j = i; j> 0 && less(a[j], a[j-1]); j--){
                exch(a, j, j-1);
            }
        }
    }

    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j)
    {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }

    // Print the array, on a single line
    static void show(Comparable[] a)
    {
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a)
    {
        for (int i = 1; i < a.length; i++)
            if(less(a[i], a[i-1])) return false;
        return true;
    }

    public static void runSort(String[] args){
        String[] a = StdIn.readStrings();
        Insertion.sort(a);
        assert Insertion.isSorted(a);
        Insertion.show(a);
    }

}
