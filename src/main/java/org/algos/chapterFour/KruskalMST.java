package org.algos.chapterFour;

import org.algos.chapterOne.Queue;

/**
 * Algorithm 4.8 Kruskal's MST Algorithm (pg.627)
 *
 * This implementation of Kruskal's algorithm uses a queue to hold MST edges, a priority queue to hold edges
 * not yet examined, and a union-find data structure for identifying ineligible edges. The MST edges are returned
 * to the client in increasing order of their weights.
 */
public class KruskalMST {
    private Queue<Edge> mst;
    public KruskalMST(EdgeWeightedGraph G){
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>(G.edges());
        UF uf = new UF(G.V());

        while (!pq.isEmpty() && mst.size() < G.V() - 1){
            Edge e = pq.delMin(); // Get min weight edge on pq
            int v = e.either(), w = e.other(v); // and its vertices.
            if (uf.connected(v, w)) continue; // Ignore ineligible edges.
            uf.union(v, w); // chapterTwo.Merge components.
            mst.enqueue(e); // Add edge to mst.
        }
    }
    public Iterable<Edge> edges(){
        return mst;
    }
    public double weight(){} // see Exercise 4.3.31
}
