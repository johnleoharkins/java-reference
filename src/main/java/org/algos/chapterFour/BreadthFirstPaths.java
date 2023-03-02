package org.algos.chapterFour;

import org.algos.chapterOne.Queue;
import org.algos.chapterOne.Stack;

/**
 * Algo 4.2 Breadth-First Search to find paths in a graph
 *
 * Often interested in solving the following problem:
 *      Single-Source Shortest Paths
 *          Given a graph and a source vertex s, support queries of the form
 *      "Is there a path from s to a given target vertex u?"
 *      "If so, find a shortest such path (one with a minimal number of edges)?"
 *
 * Whereas DFS has no relationship to the goal of finding shortest paths, BFS is based on this goal.
 *
 * Based on maintaining a queue of all vertices that have been marked but whose adjacency lists have not been checked
 *  - starts with putting source vertex on queue then
 *      + take the next vertex v from queue and mark it
 *      + Put onto the queue all unmarked vertices that are adjacent to v
 *  - rather than an implicit stack provided by recursion, uses an explicit queue
 *
 * Find paths in a graph with the fewest number of edges from the source s given in the constructor.
 * The bfs() method marks all vertices connected to s, so clients can use hasPathTo() to determine whether a given
 * vertex v is connected to s and pathTo() to get a path from s to v with the property
 * that no other such path from s to v has fewer edges.
 */
public class BreadthFirstPaths {
    private boolean[] marked; // Is a shortest path to this vertex known?
    private int[] edgeTo;     // last vertex on known path to this vertex
    private final int s;      // source

    public BreadthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s){
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true; // Mark the source
        queue.enqueue(s); // & put it on the queue.
        while (!q.isEmpty()){
            int v = queue.dequeue(); // Remove next vertex from the queue.
            for (int w : G.adj(v))
                if (!marked[w]){        // For every unmarked adjacent vertex,
                    edgeTo[w] = v;      // save last edge on a shortest path,
                    marked[w] = true;   // mark it because path is known,
                    queue.enqueue(w);   // and add it to the queue.
                }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    /**
     *
     * Same as for DFS (pg.536)
     * @param v
     * @return
     */
    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(s);
        return path;
    }
}
