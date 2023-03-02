package org.algos.chapterOne;

import java.util.Iterator;

/**
 * Generic, iterable implementation of chapterOne.Stack is a model for collection ADTs that keep items in an array
 * Resizes the array to keep the array size within a constant factor of the stack size
 * Algorithm 1.1 Pushdown (LIFO) stack (resizing array implementation) page 141
 * Some push and pop operations req. resizing: this takes time proportional to the size of the stack
 * => does not achieve optimum performance goals for any collection implementation
 *
 * Loitering
 * Holding a reference to an item that is no longer needed; avoided via setting the array entry corresponding to the
 *      popped item to null, thus overwriting the unused ref and making it possible for the system to reclaim the
 *      memory associated w/ the popped item when the client is finished with it
 *
 * @param <Item> Type Parameter - a symbolic placeholder for some concrete type (reference types w/ autoboxing support on primitive types converted to wrapper type)
 *                  Generic array creation is disallowed. must use a cast; Java compiler will give warning
 */
public class ResizingArrayStack<Item> implements Iterable<Item>{
    private Item[] a = (Item[]) new Object[1]; // stack items
    private int N = 0; // number of items

    public boolean isEmpty() {
        return N == 0;
    }

    public int size(){
        return N;
    }

    private void resize(int max){
        // Move stack to a new array of size max
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
    }

    public void push(Item item){
        // Add item to top of stack
        if(N == a.length) resize(2*a.length);
        a[N++] = item;
    }

    /**
     * Delete item, halve the array size if it is too large (will be about half full & can accommodate a
     * substantial number of push/pop ops before having to change the size of the array again.
     *
     * stack never overflows and never becomes less than one-quarter full (unless stack is empty, when array size is 1)
     *
     * @return
     */
    public Item pop(){
        // Remove item from top of stack
        Item item = a[--N];
        a[N] = null; // Avoid loitering
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }

    // Iteration
    // What is an iterator? An object from a class that implements the methods hasNext() & next() as
    // defined in the java.util.Iterator interface. Iterators are generic

    // nested class enables access to instance variables of the enclosing class (a[] & N)
    // Should throw exceptions UnsupportedOperationException if a client calls remove() and a
    // NoSuchElementException if a client calls next() when i is 0
    // omitted as we only use iterators in the foreach construction where these conditions do not arise

    // a client using the foreach statement for this class will get behavior equiv to the
    // common for loop for arrays w/o needing to be aware of the array representation (implmentation detail).
    // This arrangement is of critical importance for implementations of
    // fundamental data types like the collections & those in Java libraries
    // - enable switching to a totally different representation w/o having
    //      to change any client code/clients need not know any details of the class implementation
    public Iterator<Item> iterator(){
        return new ReverseArrayIterator();
    }

    // Enable iteration in reverse order
    private class ReverseArrayIterator implements Iterator<Item>{
        // support LIFO iteration
        private int i = N;
        public boolean hasNext(){
            return i > 0;
        }
        public Item next(){
            return a[--i];
        }

        public void remove(){} // interleaving iteration w/ operations that modify the data structure is best avoided
    }

}
