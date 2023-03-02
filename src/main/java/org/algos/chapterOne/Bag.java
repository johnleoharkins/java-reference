package org.algos.chapterOne;

import java.util.Iterator;

/**
 *
 * Collection: chapterOne.Bag (page 124)
 * Removing items is not supported
 * Provides clients w/ the ability to collect items and iterate through them
 * Order of iteration is unspecified and should be immaterial to the client -> motivation to use a bag vs stack or queue
 *
 * This chapterOne.Bag implementation maintains a linked list of the items provided in calls to add().
 * List happens to be in LIFO order
 * Algo 1.4 page 155
 * @param <Item>
 */
public class Bag<Item> implements Iterable<Item>{

    private Node first; // linked-list head/first node
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public Bag() {
        first = null;
    }

    /**
     * Add a new item to the bag
     * (Same as push() in chapterOne.Stack)
     * @param item
     */
    public void add(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public int size(){
        return N;
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {}
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

//    @Override
//    public void forEach(Consumer<? super Item> action) {
//        Iterable.super.forEach(action);
//    }
//
//    @Override
//    public Spliterator<Item> spliterator() {
//        return Iterable.super.spliterator();
//    }
}
