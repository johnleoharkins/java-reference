package org.algos.chapterOne;

/**
 * An abstract data type for a fixed-capacity generic stack (pg 135)
 * @param <Item> Some Item on the stack
 */
public class FixedCapacityStack<Item> {
    private Item[] a; // stack entries
    private int N; // size

    public FixedCapacityStack(int cap){
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }

    private void resize(int max){
        // Move stack of size N <= max to a new array of size max.
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
    }

    public void push(Item item){
        // Add item to top of stack.
        if (N == a.length) resize(2*a.length);
        a[N++] = item;
    }

    public Item pop(){
        // Remove item from top of stack.
        Item item = a[--N];
        a[N] = null; // Avoid loitering
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }


}
