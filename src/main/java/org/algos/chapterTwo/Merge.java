package org.algos.chapterTwo;

public class Merge extends Sort {

    public static void merge(Comparable[] a, int lo, int mid, int hi){
        // chapterTwo.Merge a[lo..mid] with a[mid+1..hi]
        int i = lo, j = mid+1;
        for (int k = lo; k<= hi; k++) // Copy a[lo..hi] to aux[lo...hi]
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++) // chapterTwo.Merge back to a[lo..hi]
            if (i > mid)                a[k] = aux[j++];
            else if (j > hi)            a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                        a[k] = aux[i++];
    }

    private static Comparable[] aux; // auxiliary array for merges
    public static void sort(Comparable[] a){
        aux = new Comparable[a.length]; // allocate space just once.
        sort(a, 0, a.length-1);
    }
    private static void sort(Comparable[] a, int lo, int hi){
        // chapterTwo.Sort a[lo..hi]
        if (hi <= lo) return;
        int mid = lo + (hi -lo)/2;
        sort(a, lo, mid); // chapterTwo.Sort left half
        sort(a, mid+1, hi); // chapterTwo.Sort right half
        merge(a, lo, mid, hi);
    }

    // sequence of passes over the whole array, doing sz-by-sz merges
    // starting with sz equal to 1 & doubling sz on each pass
    // final subarray is of size sz only when the array size is an even multiple of sz (else less than sz)
    // Prop. H: Uses between 1/2NlgN and NlgN compares and at most 6NlgN array accesses to sort an array of length N
    // -> num passes through the array is precisely floor(lgN); for each pass num of array accesses is exactly 6N and the number of compares is at most N, no less than N/2
    private static void bottomUpSort(Comparable[] a){
        // Do lg N passes of pairwise merges
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz+sz) { // sz: subarray size
            for (int lo = 0; lo < N -sz; lo += sz+sz){ // lo: subarray index
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
    }

    public static void runSort(String[] args){
        String[] a = StdIn.readStrings();
        Merge.sort(a);
        assert Merge.isSorted(a);
        Merge.show(a);
    }
}
