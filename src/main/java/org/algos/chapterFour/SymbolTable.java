package org.algos.chapterFour;

import java.util.LinkedList;
import java.util.List;

public class SymbolTable<Key extends Comparable<Key>, Value> {

    private Node first; // first node in linkedlist
    private Integer size;

    private class Node {
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    SymbolTable(){
        first = null;
        size = 0;
    }

    public void put(Key key, Value val){
        // Search for key. Update value if found; grow table if new.
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)){
                x.val = val;
                return;
            } // Search hit: update val
        first = new Node(key, val, first); // Search miss: add new node
        size = size + 1;
    }

    public Value get(Key key){
        // Search for key, return associated value
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.val; // Search hit
        return null; // Search miss
    }

    public void delete(Key key){
        if(first.key.equals(key)){
            first = first.next; // double check g.c....
        }else{
            for(Node x = first; x != null; x = x.next)
                if(x.next.key.equals(key))
                    x.next = x.next.next; // double check g.c.
        }

    }

    public boolean contains(Key key){
        for(Node x = first; x != null; x = x.next)
            if(x.key.equals(key))
                return true;
        return false;
    }

    public boolean isEmpty(){
        if(first == null){
            return true;
        }else{
            return false;
        }
    }

    public int size(){
        int c = 0;
        for(Node x = first; x != null; x = x.next)
            c++;
        return c;
    }

    public int sizeOH(){
        return size;
    }

    public Key min(){
        Key min = first.key;
        for(Node x = first.next; x != null; x = x.next)
            if(x.key.compareTo(min) < 0)
                min = x.key;
        return min;
    }

    public Key max(){
        Key max = first.key;
        for(Node x = first.next; x != null; x = x.next)
            if(x.key.compareTo(max) > 0)
                max = x.key;
        return max;
    }

    public Key floor(Key key){
        Key floor = key;
        for(Node x = first; x != null; x = x.next)
            if(x.key.compareTo(floor) > 0 && x.key.compareTo(key) <= 0)
                floor = x.key;
        return floor;
    }

    public Key ceiling(Key key){
        Key ceil = key;
        for(Node x = first; x != null; x = x.next)
            if(x.key.compareTo(ceil) < 0 && x.key.compareTo(key) >= 0)
                ceil = x.key;
        return ceil;
    }

    public int rank(Key key){
        int num = 0;
        for(Node x = first; x != null; x = x.next)
            if(x.key.compareTo(key) < 0)
                num++;
        return num;
    }

    public Key select(int k){
        return null;
    }

    public void deleteMin(){
        delete(min());
    }

    public void deleteMax(){
        delete(max());
    }

    public int size(Key lo, Key hi){
        if(hi.compareTo(lo) < 0)
            return 0;
        else if(contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);

    }

    public Iterable<Key> keys(Key lo, Key hi){
        List<Key> l = new LinkedList<Key>();
        for(Node x = first; x != null; x = x.next)
            if(x.key.compareTo(lo) >= 0 && x.key.compareTo(hi) <= 0)
                l.add(x.key);
        l.sort(null);
        return l;
    }

    public Iterable<Key> keys(){
        return keys(min(), max());
    }

}

