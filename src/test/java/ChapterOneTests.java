import org.junit.jupiter.api.Test;
import org.algos.chapterOne.ThreeSum;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChapterOneTests {


    // ch.1.4 pg 177 "DoublingTest program to perform experiments"
    public static double timeTrial(int N){
        // Time ThreeSum.count() for N random 6-digit ints
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-MAX, MAX);
        Stopwatch timer = new Stopwatch();
        int cnt = ThreeSum.count(a);
        return timer.elapsedTime();
    }

    @Test
    void DoublingTest(){
        for (int N = 250; true; N += N){
            double time = timeTrial(N);
            StdOut.printf("%7d %5.1f\n", N, time);
        }
    }

}