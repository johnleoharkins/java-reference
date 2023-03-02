package org.algos.chapterTwo;

public class MaxPQ <Key extends Comparable<Key>>{

    private Key[] pq; // heap-ordered complete binary tree
    private int N = 0; // in pq[1..N] with pq[0] unused

    // create a pq
    MaxPQ(){

    }

    // create a pq of initial capacity max
    MaxPQ(int max){
        pq = (Key[]) new Comparable[max+1];
    }

    // create a pq from the keys in a[]
    MaxPQ(Key[] a){
        pq = (Key[]) new Comparable[a.length];
        N = a.length;
    }

    // insert a key into the pq
    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    // return the largest key
    public Key max(){
        return pq[1];
    }

    // return and remove the largest key
    public Key delMax(){
        Key max = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    // is the pq empty
    public boolean isEmpty(){
        return N == 0;
    }

    // number of keys in the pq
    public int size(){
        return N;
    }

    private void swim(int k){
        while (k > 1 && less(k/2, k)){
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k){
        while (2*k <= N){
            int j = 2*k;
            if(j < N && less(j, j+1)) j++;
            if(!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

//    public static void sort(Comparable[] a){
//        int N = a.length;
//        for (int k = N/2; k >= 1; k--){
//            sink(a, k, N);
//        }
//        while (N > 1){
//            exch(a, 1, N--);
//            sink(a, 1, N);
//        }
//    }
}


/**
 * Given an integer M from the cmd line, an input stream where each line contains a transaction
 * This MinPQ client prints the M lines whose numbers are the highest
 * Utilizes transaction class (pg 79, ex1.2.19, 2.1.21)
 *
 */
//public class TopM {
//    public static void main(String[] args){
//        int M = Integer.parseInt(args[0]);
//        MinPQ<Transaction> pq = new MinPQ<Transaction>(M+1);
//        while (StdIn.hasNextLine()){
//            pq.insert(new Transaction(StdIn.readLine()));
//            if(pq.size() > M)
//                    pq.delMin();
//        }
//        chapterOne.Stack<Transaction> stack = new chapterOne.Stack<Transaction>();
//        while (!pq.isEmpty()) stack.push(pq.delMin());
//        for (Transaction t : stack) StdOut.println(t);
//    }
//}
