package org.algos.chapterTwo;// page 249



public class Selection {
    public static void sort(Comparable[] a)
    {   // chapterTwo.Sort a[] into increasing order
        int N = a.length; // array length
        for (int i = 0; i < N; i++)
        { // Exchange a[i] with smallest entry in a[i+1...N).
            int min = i; // index of minimal entr.
            for (int j = i+1; j < N; j++)
                if (less(a[j], a[min])) min = j;
            exch(a, i, min);
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

    // Test whether the array entries are in order;
    public static boolean isSorted(Comparable[] a)
    {
        for (int i = 1; i < a.length; i++)
            if(less(a[i], a[i-1])) return false;
        return true;
    }

    public static void runSelection(String[] args) {
        String[] a = StdIn.readStrings();
        Selection.sort(a);
        assert Selection.isSorted(a);
        Selection.show(a);
    }

}
