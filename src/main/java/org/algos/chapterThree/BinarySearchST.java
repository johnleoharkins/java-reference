package org.algos.chapterThree;

import java.util.Arrays;

public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }


    // move larger keys one position to the right before growing the table as
    // in the array-based stack implementation ( array resizing omitted here)
    public void put(Key key, Value val){
        int i = rank(key, 0, N-1);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key,  0, N-1);
        if (i < N && keys[i].compareTo(key) == 0)
            return vals[i];
        else return null;
    }

    public void delete(Key key) {
        int kr = rank(key, 0, N-1);
        for (int j = kr; j < N-1; j++) {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        N--;

    }

    public boolean contains(Key key){
        for(int i = 0; i < N ; i++)
            if(keys[i].equals(key))
                return true;
        return false;
    }

    public boolean isEmpty(){
        if(N == 0) return true;
        else return false;
    }

    public int size(){
        return N;
    }

    public Key min(){
        return keys[0];
    }

    public Key max(){
        return keys[N-1];
    }

    /**
     * largest key less than or equal to key
     * TODO: Add corner case and exceptions
     * @param key compare to this key
     * @return yes read above
     */
    public Key floor(Key key){
        int kr = rank(key, 0, N-1);
        return keys[kr-1];
    }

    /**
     * smallest key greater than or equal to key
     * TODO: Add corner case and exceptions
     * @param key
     * @return
     */
    public Key ceiling(Key key){
        int kr = rank(key, 0, N-1);
        return keys[kr+1];
    }

    /**
     * Number of keys less than key
     * @param key - key to compare
     * @param lo - lower bound index
     * @param hi - upper bound index
     * @return number of keys
     */
    public int rank(Key key, int lo, int hi){
        if (hi < lo) return lo;
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0)
            return rank(key, lo, mid-1);
        else if(cmp > 0)
            return rank(key, mid+1, hi);
        else return mid;
    }

    /**
     * key of rank k
     * @param k - rank
     * @return the key that has k keys less than its key
     */
    public Key select(int k) {
        return keys[k];
    }

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

//    public int size(Key lo, Key hi) {
//        if(hi.compareTo(lo) < 0)
//            return 0;
//        else if(contains(hi))
//            return rank(hi) - rank(lo) + 1;
//        else
//            return rank(hi) - rank(lo);
//
//    }

    public Iterable<Key> keys(Key lo, Key hi) {
        return Arrays.asList(keys);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

}
