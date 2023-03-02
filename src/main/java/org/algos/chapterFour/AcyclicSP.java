package org.algos.chapterFour;

/**
 * Algorithm 4.10 Shortest Paths in chapterFour.Edge-Weighted DAGs (pg. 660)
 *
 */
public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        Topological top = new Topological(G);

        for (int v : top.order())
            relax(G, v);
    }

    private void relax(EdgeWeightedDigraph G, int v) {} // See page 648

    public double distTo(int v){} // standard client query methods for SPT implementations (see page 649)
    public boolean hasPathTo(int v){}
    public Iterable<Edge> pathTo(int v){}
}
