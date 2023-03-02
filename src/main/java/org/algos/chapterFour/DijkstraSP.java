package org.algos.chapterFour;

import org.algos.chapterTwo.IndexMinPQ;

/**
 * Algorithm 4.9 Dijkstra's Shortest-Paths Algorithm (pg. 655)
 *
 * This implementation of Dijkstra's algo grows the SPT by adding an edge at a time, always choosing
 * the edge from a tree vertex to a non-tree vertex whose destination w is closest to s
 */
public class DijkstraSP {
//    private DirectedEdge[] edgeTo;
//    private double[] distTo;
//    private IndexMinPQ<Double> pq;
//
//    public DijkstraSP(EdgeWeightedDigraph G, int s){
//        edgeTo = new DirectedEdge[G.V()];
//        distTo = new double[G.V()];
//        pq = new IndexMinPQ<Double>(G.V());
//
//        for (int v = 0; v < G.V(); v++)
//            distTo[v] = Double.POSITIVE_INFINITY;
//        distTo[s] = 0.0;
//
//        pq.insert(s, 0.0);
//        while (!pq.isEmpty())
//            relax(G, pq.delMin());
//    }
//
//    private void relax(EdgeWeightedDigraph G, int v){
//        for (DirectedEdge e : G.adj(v)){
//            int w = e.to();
//            if (distTo[w] > distTo[v] + e.weight()){
//                distTo[w] = distTo[v] + e.weight();
//                edgeTo[w] = e;
//                if (pq.contains(w)) pq.change(w, distTo[w]);
//                else pq.insert(w, distTo[w]);
//            }
//        }
//    }
//
//    public double distTo(int v){} // standard client query methods for SPT implementations (see page 649)
//    public boolean hasPathTo(int v){}
//
//    public Iterable<Edge> pathTo(int v){}

}
