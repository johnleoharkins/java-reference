package org.algos.chapterFour;

import org.algos.chapterOne.Bag;

/**
 * chapterFour.Edge-Weighted chapterFour.Graph Data Type (pg. 611)
 *
 * Similar to the API for chapterFour.Graph, but based on chapterFour.Edge & it adds the edges() method at right which provides clients with
 * the ability to iterate through to all the graph's edges (ignoring any self-loops), the rest of the implementation is
 * similar to the unweighted undirected graph implementation but instead of the adjacency
 * lists of integers, it uses adjacency lists of chapterFour.Edge objects.
 *
 * This implementation maintains a vertex-indexed array of lists of edges. As with chapterFour.Graph, every edge appears twice:
 * if an edge connects v and w, it appears both in v's list and in w's list. The edges() method puts all the edges in a
 * chapterOne.Bag.
 *
 * The natural ordering for edges in an edge-weighted graph is by weight.
 *  - Implementation of compareTo() is straightforward
 * Parallel Edges are allowed, a more complicated implementation could disallow them
 *  - perhaps keeping the minimum-weight edge from a set of parallel edges
 * Self-loops are allowed
 *  - this edges() implementation does not include self-loops even if they may be present
 * in input or the d.s.
 *  - This omission has no effect on our MST algorithms.
 *  - If self-loops are significant, code may need modification.
 *
 *
 */
public class EdgeWeightedGraph {
    private final int V; // number of vertices
    private int E; // number of edges
    private Bag<Edge>[] adj; // adjacency lists

    public EdgeWeightedGraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Edge>();
    }

//    public EdgeWeightedGraph(In in){} // Exercise 4.3.9
    public int V() { return V; }
    public int E() { return E; }

    public void addEdge(Edge e){
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }
//    public Iterable<Edge> edges(){} // page 609

}
