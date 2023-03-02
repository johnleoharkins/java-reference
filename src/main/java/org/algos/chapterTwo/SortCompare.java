package org.algos.chapterTwo;

public class SortCompare {
    public static double time(String alg, Double[] a){
        Stopwatch timer = new Stopwatch();
        if (alg.equals("chapterTwo.Insertion")) Insertion.sort(a);
        if (alg.equals("chapterTwo.Selection")) Selection.sort(a);
//        if (alg.equals("chapterTwo.Shell")) chapterTwo.Shell.sort(a);
//        if (alg.equals("chapterTwo.Merge")) chapterTwo.Merge.sort(a);
//        if (alg.equals("chapterTwo.Quick")) chapterTwo.Quick.sort(a);
//        if (alg.equals("Heap")) Heap.sort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T){ // Use alg to sort T random arrays of length N
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t=0; t<T; t++){ // Perform one experiment (generate & sort an array)
            for (int i=0; i < N; i++){
                a[i] = StdRandom.uniform();
            }
            total += time(alg,a);
        }
        return total;
    }

}
