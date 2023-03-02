package org.algos.chapterOne;

/**
 * Sample chapterOne.Stack Client (pg. 127)
 */
public class SampleStackClient {
    public static void main(String[] args){
        Stack<Integer> stack;
        stack = new Stack<Integer>();
        while (!StdIn.isEmpty())
            stack.push(StdIn.readInt());
        for (int i : stack)
            StdOut.println(i);
    }
}
