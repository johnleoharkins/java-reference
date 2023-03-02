package org.algos.chapterFour;

import org.algos.chapterOne.Queue;
import org.algos.chapterOne.Stack;

/**
 * Depth-first search vertex ordering in a digraph (pg. 580)
 *
 * Enables clients to iterate through the vertices in various orders defined by depth-first search.
 * Recursive nature of the search enables us to prove properties of the
 * computation in advanced digraph-processing algorithms. (ex. Prop.F: Reverse postorder in a DAG is a topological sort)
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G){
        pre = new Queue<Integer>();
        post = new Queue<Integer>();
        reversePost = new Stack<Integer>();
        marked = new boolean[G.V()];
        for (int v=0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    private void dfs(Digraph G, int v){
        pre.enqueue(v);
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }
    public Iterable<Integer> post(){
        return post;
    }
    public Iterable<Integer> reversePost(){
        return reversePost;
    }
}
