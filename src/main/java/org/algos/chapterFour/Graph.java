package org.algos.chapterFour;

import org.algos.chapterOne.Bag;

public class Graph {

    private final int V; // number of vertices
    private int E; // number of edges
    private Bag<Integer>[] adj; // adjacency lists


    /**
     * create a v-vertex graph with no edges
     * @param V
     */
    public Graph(int V){
        this.V = V; this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V]; // Create array of lists.
        for (int v = 0; v < V; v++) // Init all lists to empty
            adj[v] = new Bag<Integer>();
    }

    /**
     * read a graph from input stream in
     * @param in
     */
    public Graph(In in){
        this(in.readInt()); // Read V and construct this graph
        int E = in.readInt(); // Read E
        for (int i = 0; i < E; i++){
            // Add an edge
            int v = in.readInt(); // read a vertex
            int w = in.readInt(); // read another vertex
            addEdge(v, w); // and add edge connecting them
        }
    }

    /**
     * number of vertices
     * @return
     */
    public int V(){
        return V;
    }

    /**
     * number of edges
     * @return
     */
    public int E(){
        return E;
    }

    /**
     * add edge v-w to this graph
     * @param v
     * @param w
     */
    public void addEdge(int v, int w){
        adj[v].add(w); // Add w to v's list
        adj[w].add(v); // Add v to w's list
        E++;
    }

    /**
     * vertices adjacent to v
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    // typical graph processing code (page 523)

    /**
     * string representation of the graph's adjacency lists
     * @return
     */
    public String toString(){
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++){
            s += v + ": ";
            for (int w : this.adj(v))
                s += w + " ";
            s += "\n";
        }
        return s;
    }

    /**
     * compute the degree of v
     * @param G
     * @param v
     * @return
     */
    public static int degree(Graph G, int v){
        int degree = 0;
        for (int w : G.adj(v)) degree++;
        return degree;
    }

    /**
     * compute maximum degree
     * @param G
     * @return
     */
    public static int maxDegree(Graph G){
        int max = 0;
        for (int v = 0; v < G.V(); v++)
            if (degree(G, v) > max)
                max = degree(G, v);
        return max;
    }

    /**
     * compute average degree
     * @param G
     * @return
     */
    public static int avgDegree(Graph G){
        return 2 * G.E() / G.V();
    }

    /**
     * count self-loops
     * @param G
     * @return
     */
    public static int numberOfSelfLoops(Graph G){
        int count = 0;
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v))
                if (v == w) count++;
        return count/2; // each edge counted twice
    }

}
