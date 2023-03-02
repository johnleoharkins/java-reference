package org.algos.chapterOne;

import java.util.Iterator;

/**
 *
 * Top of the stack at the beginning of a linked-list, referenced by an instance variable first
 * push() an item to the beginning/front of linked-list; pop() item from the beginning of the list
 * size() returns value of instance variable N representing the number of items in the list updated on push/pop
 * isEmpty() null check 'first' or if N is 0
 * <Item> replaced by a client-supplied data-type name
 * Implements Iterable interface to add iteration support
 * Optimum design goals
 *  - can be used for any type of data
 *  - space required is always proportional to the size of the collection
 *  - time per operation is always independent of the size of the collection
 *
 * pushdown stack (linked-list implementation) page 149
 * @param <Item> data-type name of thing to push on the stack (generics)
 */
public class Stack<Item> implements Iterable<Item>{
    private Node first; // top of stack (most recently added node)
    private int N; // number of items
    private class Node { // nested class to define nodes for a linked-list data structure
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size(){
        return N;
    }

    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop(){ // remove item from top of stack
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    // iterator() implementation page 155
    private class ListIterator implements Iterator<Item> {
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
}
