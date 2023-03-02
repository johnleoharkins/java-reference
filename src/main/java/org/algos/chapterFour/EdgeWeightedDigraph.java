package org.algos.chapterFour;

import org.algos.chapterOne.Bag;

/**
 * chapterFour.Edge-Weighted chapterFour.Digraph Data Type (pg. 643)
 *
 * Implementation is an amalgam of chapterFour.EdgeWeightedGraph and chapterFour.Digraph that maintains a
 * vertex-indexed array of bags of chapterFour.DirectedEdge objects
 * As with chapterFour.Digraph, every edge appears just once: if an edge connects v to w, it appears in v's adjacency list
 * Self-loops and parallel edges are allowed
 *
 */
public class EdgeWeightedDigraph {
    private final int V; // number of vertices
    private int E; // number of edges
    private Bag<DirectedEdge>[] adj; // adjacency lists

    public EdgeWeightedDigraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }
    public EdgeWeightedDigraph(In in){
        // See Exercise 4.4.2
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    public void addEdge(DirectedEdge e){
        adj[e.from()].add(e);
        E++;
    }
    public Iterable<Edge> adj(int v){
        return adj[v];
    }
    public Iterable<DirectedEdge> edges(){
        Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++)
            for (DirectedEdge e : adj[v])
                bag.add(e);
        return bag;
    }
}
