package org.algos.chapterFour;

/**
 * Algorithm 4.5 chapterFour.Topological chapterTwo.Sort (pg.581)
 *
 * This chapterFour.DepthFirstOrder and chapterFour.DirectedCycle client returns a topological order for a DAG.
 * The test client solves the precendence-constrained scheduling problem for a SymbolDigraph. The instance method
 * order() returns null if the given digraph is not a DAG and an iterator giving the vertices in topological order
 * otherwise.
 * (SymbolDigraph code is omitted b/c it is the same as for chapterFour.SymbolGraph with chapterFour.Digraph replacing chapterFour.Graph everywhere)
 */
public class Topological {
    private Iterable<Integer> order; // topological order
    public Topological(Digraph G){
        DirectedCycle cyclefinder = new DirectedCycle(G);
        if (!cyclefinder.hasCycle()){
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }
    public Iterable<Integer> order(){
        return order;
    }

    public boolean isDAG(){
        return order == null;
    }

    public static void main(String[] args){
        String filename = args[0];
        String separator = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, separator);

        Topological top = new Topological(sg.G());
        for (int v : top.order())
//            StdOut.println(sg.name(v));
    }
}
