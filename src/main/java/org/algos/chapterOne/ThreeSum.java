package org.algos.chapterOne;

/**
 * Three Sum (Ch.1.4 pg. 173)
 */
public class ThreeSum {
    public static int count(int[] a){
        // count triples that sum to 0.
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                for (int k = j+1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0)
                        cnt++;
        return cnt;
    }
}
