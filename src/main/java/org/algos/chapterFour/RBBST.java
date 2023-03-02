package org.algos.chapterFour;

/**
 * https://www.cs.usfca.edu/~galles/visualization/RedBlack.html
 *
 * Red-Black BSTs have red and black links satisfying the following 3 restrictions (page 432)
 *      1) Red links lean left
 *      2) No node has two red links connected to it
 *      3) The tree has perfect black balance: every path from the root to a null link has the same number of black links
 *
 * 3-Nodes are represented as two 2-Nodes connected by a red link that leans left, black links bind together the 2-3 tree
 *
 * There is a 1-1 correspondence between Red-Black BSTs defined this way and 2-3 trees
 *          If red-links are drawn horizontally all null links are the same distance away from the root.
 *              If nodes connected by red links are collapsed the result is a 2-3 tree.
 *          Conversely, if 3-Nodes are drawn in a 2-3 tree as two 2-Nodes connected by a red link that leans left
 *              -- then no node has 2 red links connected to it. The tree has perfect black balance
 *              since black links correspond to 2-3 tree links which are perfectly balanced by definition.
 *
 *  Rotations (page 434-438) Key to Red-Black Tree Dynamics
 *  Insert into a tree w/ two keys (3-Node) reduces to 3 subcases. Each case introduces a node with two redlinks connected that needs to be fixed.
 *       Zero, one or two rotations followed by flipping colors of two children of the root resolves violated conditions.
 *          link returned by rotateLeft() or rotateRight() used to reset appropriate link in the parent.
 *  Insert into a 3-Node at the bottom
 *      Same 3 cases arise
 *      Flipping colors makes the link to the middle node red. Pass up to parent, putting us back in the same situation w/ respect to the parent which is fixed moving up the tree
 *
 *
 *  Insert into a single 2-Node
 *      If new key is smaller, make a new red node -> red-black chapterThree.BST that is equiv to a single 3-Node
 *      New key is larger, right-leaning red link, fix through rotation to complete insertion
 *      Result in both cases is red-black representation of a single 3-Node, with two keys, one left-leaning red link and a black height of 1
 * Insert into a 2-Node at the bottom
 *      If parent is a 2-Node, then the two cases above are effective. If new node is attached to a left link, parent becomes a 3-Node. Right link is fixed by a left rotation.
 *
 *
 * Passing a red link up the tree
 *      After any necessary rotations, flip colors, middle node is red. Pass up a red link to the middle node. Process continues until reaching a 2-node or root
 *
 *      If right child is red and left child is black, rotate left
 *      If both left child and its left child are red, rotate right
 *      If both children are red, flip colors
 *
 * Keep the root black
 *         chapterFour.Color root black after ea. insertion.
 *         Black height of the tree increases by 1 whenever the root is flipped from black to red.
 *         Strictly speaking, a red root implies that the root is part of a 3-node
 *
 * __________________________________
 *          Basis of 2-3 tree insertion algo is that all transformations are purely local -> no part of the tree needs
 *          to be examined or modified other than the specified nodes and links. Number of links changed for each
 *          transformation is bounded by a small constant. Preserves the global properties that the tree is ordered
 *          and perfectly balanced. 2-3 trees grow up from the bottom.
 *                  2-3 trees can be inconvenient to implement in direct reperesentation b/c there are numerous
 *                  different cases to be handled. Would need to maintain two different types of nodes, compare search
 *                  keys against each of the keys in the nodes, copy links and other info from one
 *                  type of node to another. Convert nodes from one type to another and so forth
 *                      -> substantial amount of code involved, overhead incurred
 *                          could make the algos slower than standard chapterThree.BST search and insert.
 *
 * tldr: Review 2-3 tree notes,
 *          be sure to understand insertion, temp 4 nodes split and middle key
 *          move while preserving order and balance (crux of 2-3 tree dynamics)
 *          motivations for definition and use of RBTree
 *
 * @param <Key>
 * @param <Value>
 */
public class RBBST <Key extends Comparable<Key>, Value> {
    private Node root;

    // By convention, null links are black
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;
        boolean color;
        
        Node(Key key, Value val, int N, boolean color){
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    /**
     * Test the color of a node's link to its parent
     * (page 433)
     * @param x node to test link to parent
     * @return true if link to its (node x) parent is red
     */
    private boolean isRed(Node x){
        if (x == null) return false;
        return x.color == RED;
    }

    /**
     * 
     * (page 434)
     * @param h
     * @return
     */
    Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * 
     * (page 434)
     * @param h
     * @return
     */
    Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * 
     * (page 436)
     * @param h
     */
    void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    /**
     * 
     * (page 398)
     * @return size of tree
     */
    public int size(){
        return size(root);
    }

    /**
     * size of subtree rooted at x
     * @param x
     * @return
     */
    private int size(Node x){
        if(x == null){
            return 0;
        }else {
            return x.N;
        }
    }

    /**
     * Insert a key, val pair into the tree
     *
     * identical to put() in elementary BSTs except for the three if statements after the recursive calls
     * first rotates left any right-leaning 3-node (or a right-leaning red link at the bottom of a temp 4-Node)
     * second rotates right the top link in a temp 4-node w/ two left-leaning red links
     * third flips colors to pass a red link up the tree
     * @param key
     * @param val
     */
    
    public void put(Key key, Value val){
        // Search for key. Update value if found; grow table if new;
        root = put(root, key, val);
        root.color = BLACK;
    }
    
    private Node put(Node h, Key key, Value val){
        if (h == null) // do standard insert, with red link to parent.
            return new Node(key, val, 1, RED);
        
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if(cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        // Going back up the tree! Provide near-perfect balance maintaining a 1-1 correspondence w/ 2-3 trees
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    public void deleteMin(){}

    private Node deleteMin(Node curr){
        // Going down the tree, 1 of 3 cases must hold
        // left child of current node is not a 2-node, nothing to do (current's left child's left child would be red linked)
        if(isRed(curr.left.left)){}
        // left child is a 2-node and immediate sibling is not a 2-node, move a key from sib to left child
        else if(isRed(curr.right.left)){

        }
        // left child and sibling are 2-nodes, combine them w/ the smallest key in parent to make a 4-node
        // changing parent from a 3 node to a 2node or from 4 node to a 3 node
        else{}
    }
    
}
