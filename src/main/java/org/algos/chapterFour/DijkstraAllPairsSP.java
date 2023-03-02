package org.algos.chapterFour;

/**
 * Dijkstra All Pairs SP (pg. 656)
 *
 * Solves the all-pais shortest paths problem, using time and space proportional to EVlogV
 *
 * Builds an array of chapterFour.DijkstraSP objects, one for each vertex as the source. To answer a client query, it uses
 * the source to access the corresponding single-source shortest-paths object and then passes the target
 * as argument to the query.
 */
public class DijkstraAllPairsSP {
    private DijkstraSP[] all;
    DijkstraAllPairsSP(EdgeWeightedDigraph G){
        all = new DijkstraSP[G.V()];
        for (int v = 0; v < G.V(); v++)
            all[v] = new DijkstraSP(G, v);
    }

    Iterable<Edge> path(int s, int t){
        return all[s].pathTo(t);
    }

    double dist(int s, int t){
        return all[s].distTo(t);
    }
}
