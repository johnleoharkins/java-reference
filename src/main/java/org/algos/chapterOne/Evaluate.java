package org.algos.chapterOne;

/**
 * Dijkstra's Two-chapterOne.Stack Algorithm for Expression Evaluation (pg.129)
 *
 * interpreting a string as a program and executing that program to compute the desired result
 * w/ generics we can use the code in a single chapterOne.Stack implementation to implement one stack of String values and another stack of Double values
 * For simplicity, this code assumes that the expression is fully parenthesized, w/ numbers and characters separated by whitespace
 */
public class Evaluate {
    Stack<String> ops = new Stack<String>();
    Stack<Double> vals = new Stack<Double>();

    public void processStandardInput(In in){
        while(!StdIn.isEmpty()){
            // Read token, push if operator
            String s = StdIn.readString();
            if (s.equals("("))                 ;
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals("sqrt")) ops.push(s);
            else if (s.equals(")")){
                // Pop, evaluate, and push result if token is ")"
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+")) v = vals.pop() + v;
                else if (op.equals("-")) v = vals.pop() - v;
                else if (op.equals("*")) v = vals.pop() * v;
                else if (op.equals("/")) v = vals.pop() / v;
                else if (op.equals("sqrt")) v = Math.sqrt(v);
                vals.push(v);
            } // Token not operator or paren: push double value
            else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }
}
