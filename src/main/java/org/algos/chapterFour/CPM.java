package org.algos.chapterFour;

/**
 * Critical Path Method for Parallel Precedence-Constrained Job Scheduling (pg. 665)
 *
 * Reduces the problem directly to the longest-paths problem in edge-weighted DAGs. It builds an edge-weighted digraph
 * from the job-scheduling problem spec, as prescribed by the critical path method, then uses AcyclicLP(prop T) to find
 * the longest-paths tree and to print the longest-paths lengths, which are precisely the start times for each job.
 */
public class CPM {
    public static void main(String[] args){
        int N = StdIn.readInt(); StdIn.readLine();
        EdgeWeightedDigraph G;
        G = new EdgeWeightedDigraph(2*N+2);

        int s = 2*N, t = 2*N+1;
        for (int i = 0; i < N; i++){
            String[] a = StdIn.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            G.addEdge(new DirectedEdge(i, i+N, duration));
            G.addEdge(new DirectedEdge(s, i, 0.0));
            G.addEdge(new DirectedEdge(i+N, t, 0.0));
            for(int j = 1; j < a.length; j++){
                int successor = Integer.parseInt(a[j]);
                G.addEdge(new DirectedEdge(i+N, successor, 0.0));
            }
        }
        AcyclicLP lp = new AcyclicLP(G, s);

        StdOut.println("Start times:");
        for (int i = 0; i < N; i++)
            StdOut.printf("%4d: %5.1f\n", i, lp.distTo(i));
        StdOut.printf("Finish time: %5.1f\n", lp.distTo(t));
    }
}
