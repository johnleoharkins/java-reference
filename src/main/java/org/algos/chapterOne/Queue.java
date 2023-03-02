package org.algos.chapterOne;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

// i mean i guess why not fuck it m8
// https://www.geeksforgeeks.org/java-implementing-iterator-and-iterable-interface/
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
// https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html
public class Queue<Item> implements Iterable<Item> {

    private Node head;

    public Queue(){
        head = null;
    }

    public void enqueue(Item item){
        if(head == null){
            head = new Node(item);
        }else{
            Node pointer = head;
            while(pointer.next != null){
                pointer = pointer.next;
            }
            pointer.next = new Node(item);
        }
    }

    public Item dequeue(){
        Item data = head.item;
        head = head.next;
        return data;
    }

    private Node getHead(){
        return head;
    }

    @Override
    public Iterator<Item> iterator() {
        return new CustomIterator<>(this);
    }

    @Override
    public void forEach(Consumer<? super Item> action) {
        Iterable.super.forEach(action); // what does this mean
    }

    @Override
    public Spliterator<Item> spliterator() {
        return Iterable.super.spliterator(); // what does this mean
    }

    class CustomIterator<Item> implements Iterator<Item> { // what do
        Queue<Item>.Node current;

        CustomIterator(Queue<Item> queue){
            current = queue.getHead();
        }

        @Override
        public boolean hasNext() {
            if (current.next != null) return true;
            else return false;
        }

        @Override
        public Item next() {
            Item data = current.item;
            current = current.next;
            return data;
        }

        @Override
        public void remove() {
            Iterator.super.remove(); // what does this mean
        }

        @Override
        public void forEachRemaining(Consumer<? super Item> action) { // what does this mean
            Iterator.super.forEachRemaining(action); // what does this mean
        }
    }

    class Node{ // why not Node<Item>
        Item item;
        Node next;
        Node(Item item){
            this.item = item;
            this.next = null;
        }
    }
}