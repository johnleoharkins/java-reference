package org.algos.chapterFour;

/**
 * Weighted edge data type (pg610)
 *
 * Allows processing chapterFour.Edge objects
 *
 * This data type provides the methods either() and other() so that such clients can use other(v) to find the other
 * vertex when it knows v. When neither vertex is known, our clients use the idiomatic code
 * int v = e.either(), w = e.other(v); to access an chapterFour.Edge e's two vertices
 *
 */
public class Edge implements Comparable<Edge>{
    private final int v; // one vertex
    private final int w; // the other vertex
    private final double weight; // edge weight

    public Edge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * Weight of this edge
     * @return
     */
    public double weight(){
        return weight;
    }

    /**
     * Either of this edge's vertices
     * @return
     */
    public int either(){
        return v;
    }

    /**
     * The other vertex
     * @param vertex
     * @return
     */
    public int other(int vertex){
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new RuntimeException("Inconsistent edge");
    }

    /**
     * Compare this edge to e
     * @param that
     * @return
     */
    public int compareTo(Edge that){
        if (this.weight() < that.weight()) return -1;
        else if (this.weight() > that.weight()) return +1;
        else return 0;
    }

    /**
     * String representation
     * @return
     */
    public String toString(){
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
