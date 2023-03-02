package org.algos.chapterFour;

/**
 * Symbol chapterFour.Graph Data Type (page 552)
 *
 * This graph client allows clients to define graphs with String vertex names instead of integer indices.
 * Maintains instance variables st (symbol table that maps names to indices), keys (an array that maps indices to names),
 * and G (a graph, with integer vertex names). To build these data structures, it makes two passes through the graph
 * definition (each line has a string and a list of adjacent strings, separated by the delimiter sp)
 */
public class SymbolGraph {
    private ST<String, Integer> st;     // String -> index
    private String[] keys;              // index -> String
    private Graph G;                    // the graph

    public SymbolGraph(String stream, String sp){
        st = new ST<String, Integer>();
        In in = new In(stream);                         // First pass
        while (in.hasNextLine()){                       // builds the index
            String[] a = in.readLine().split(sp);       // by reading strings
            for (int i = 0; i < a.length; i++)          // to associate each
                if(!st.contains(a[i]))                  // distinct string
                    st.put(a[i], st.size());            // with an index.
        }
        keys = new String[st.size()];                   // Inverted index
        for (String name : st.keys())                   // to get string keys
            keys[st.get(name)] = name;                  // is an array.

        G = new Graph(st.size());                       // Second pass
        in = new In(stream);                            // builds the graph
        while (in.hasNextLine()){
            String[] a = in.readLine().split(sp);       // by connecting the first vertex
            int v = st.get(a[0]);                       // on each line
            for (int i = 1; i < a.length; i++)          // to all the others
                G.addEdge(v, st.get(a[i]));
        }
    }
    public boolean contains(String s){
        return st.contains(s);
    }
    public int index(String s){
        return st.get(s);
    }
    public String name(int v){
        return keys[v];
    }
    public Graph G(){
        return G;
    }
}
