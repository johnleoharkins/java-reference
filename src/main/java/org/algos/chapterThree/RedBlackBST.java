package org.algos.chapterThree;

public class RedBlackBST<K extends Comparable<K>, V> {
    private RBNode<K, V> root;

    RedBlackBST(){
        root = null;
    }

    /**
     * Insert a new Key,Value pair into the RedBlack chapterThree.BST
     * Throws exception if given a duplicate Key.
     * @param key new key
     * @param value new value
     */
    public void insert(K key, V value){
        // case 1: root is null
        if(root == null){
            System.out.println("root is null... new tree");
            root = new RBNode<>(key, value);
            return;
        }
        // case 2: root is not null, call helper method
        System.out.println("inserting key " + key);
        RBNode<K, V> newNode = new RBNode<>(key, value, Color.RED);
        root = insertHelper(root, newNode, root);
    }

    /**
     * Insert helper method
     * @param curr
     * @param newNode
     * @return
     */
    private RBNode<K, V> insertHelper(RBNode<K,V> curr, RBNode<K,V> newNode, RBNode<K,V> g){
        // base case
        if(curr == null){
            return newNode;
        }
        // recursive cases
        // current key greater than new key, go left
        if(curr.compareTo(newNode) > 0){
            System.out.println("going left");
            RBNode<K, V> fuck = insertHelper(curr.getLc(), newNode, curr);

            System.out.println("back from left: p: " + curr.getKey() + " g: " + g.getKey() + " s: " + (g.getRc() == null) + " comp " + curr.compareTo(newNode));

            curr.setLc(fuck);

            // Left-Left
            if(curr.getLc() != null && curr.getLc().getColor() == Color.RED && curr.getColor() == Color.RED){
                System.out.println("red prop violation");
                // tnr
                if(g.getRc() == null || g.getRc().getColor() == Color.BLACK){ // is P's sibling black or null
                    System.out.println("ll tnr");
                    RBNode<K, V> st = curr.getRc();
                    curr.setRc(g);
                    g.setLc(st);
//                    return
                }
                // recolor
                else{
                    System.out.println("ll recolor");
                    curr.setColor(Color.BLACK);
                    g.getRc().setColor(Color.BLACK);
                    if(curr.compareTo(g) != 0){
                        g.setColor(Color.RED);
                    }
                }
                System.out.println("rp partial");
                return curr;
            }
            // Left-Right
            else if(curr.getRc() != null && curr.getRc().getColor() == Color.RED && curr.getColor() == Color.RED){
                System.out.println("red prop violation");
                // tnr
                if(g.getRc() == null || g.getRc().getColor() == Color.BLACK){ // is P's sibling black or null
                    System.out.println("lr tnr");
                    RBNode<K,V> currRightChild = curr.getRc();
                    currRightChild.setLc(curr);
                    currRightChild.setRc(g);
                    curr.setRc(null);
                    return currRightChild;
                }
                // recolor
                else{
                    System.out.println("lr recolor");
                    curr.setColor(Color.BLACK);
                    g.getRc().setColor(Color.BLACK);
                    if(curr.compareTo(g) != 0){
                        g.setColor(Color.RED);
                    }
                }
                return curr;
            }
            return curr;
        }






        // current key less than new key, go right
        else if(curr.compareTo(newNode) < 0){
            System.out.println("Going right");
            curr.setRc(insertHelper(curr.getRc(), newNode, curr));
            System.out.println("back from right: p: " + curr.getKey() + " g: " + g.getKey() + " s: " + (g.getLc() == null) + " comp " + curr.compareTo(newNode));
            // right-right
            if(curr.getRc() != null && curr.getRc().getColor() == Color.RED && curr.getColor() == Color.RED){
                System.out.println("red prop violation");
                // tnr
                if(g.getLc().getColor() == Color.BLACK || g.getLc() == null){
                    System.out.println("rr tnr");
                    RBNode<K, V> st = curr.getLc();
                    curr.setLc(g);
                    g.setRc(st);
                } // recolor
                else{
                    System.out.println("rr recolor");
                    curr.setColor(Color.BLACK);
                    g.getLc().setColor(Color.BLACK);
                    if(curr.compareTo(g) != 0){
                        g.setColor(Color.RED);
                    }
                }
                return curr;
            }
            // right-left
            if(curr.getLc() != null && curr.getLc().getColor() == Color.RED && curr.getColor() == Color.RED){
                System.out.println("red prop violation");
                // tnr
                if(g.getLc().getColor() == Color.BLACK || g.getLc() == null){
                    System.out.println("rl tnr");
                    RBNode<K,V> currLeftChild = curr.getLc();
                    currLeftChild.setRc(curr);
                    currLeftChild.setLc(g);
                    curr.setLc(null);
                    return currLeftChild;
                }
                // recolor
                else{
                    System.out.println("rl recolor");
                    curr.setColor(Color.BLACK);
                    g.getLc().setColor(Color.BLACK);
                    if(curr.compareTo(g) != 0){
                        g.setColor(Color.RED);
                    }
                }
            }
            return curr;
        }
        // current key equals new key, throw error duplicate key exception
        else if(curr.compareTo(newNode) == 0){
            System.out.println("duplicate key exception.... [implement later gator]");
        }
        return curr;
    }


    // _________________________________________________ sucks to suck john - levels to this shit bitch
    //              https://www.geeksforgeeks.org/print-level-order-traversal-line-line/
    public int height(RBNode<K,V> curr){
        if(curr == null){
            return 0;
        }else{
            int heightLeft = height(curr.getLc());
            int heightRight = height(curr.getRc());
            return Math.max(heightLeft, heightRight) + 1;
        }

    }
    /* Print nodes at a given level */
    void printGivenLevel(RBNode<K,V> curr, int level)
    {
        if (curr == null){
            System.out.print("null");
            return;
        }

        if (level == 1) {
            if(curr.getColor() == Color.RED){
                System.out.print("|");
                System.out.print(curr.getKey());
                System.out.print("|");
                System.out.print(curr.getValue());
                System.out.print(" ");
            }else{
                System.out.print("(");
                System.out.print(curr.getKey());
                System.out.print(")");
                System.out.print(curr.getValue());
                System.out.print(" ");
            }
//            System.out.print(curr.getKey() + " ");
        }
        else if (level > 1) {
            printGivenLevel(curr.getLc(), level - 1);
            printGivenLevel(curr.getRc(), level - 1);
        }
    }

    /* function to print level order traversal of tree*/
    void printLevelOrder()
    {
        int h = height(root);
        int i;
        for (i = 1; i <= h; i++) {
            printGivenLevel(root, i);
            System.out.print(System.lineSeparator());
        }
    }
    // ___________________________________________________________

//    public void delete(K key){}
//    public chapterFour.RBNode<K, V> deleteHelper(chapterFour.RBNode<K,V> curr, K key){}

//    public V lookup(K key){}
//    public V lookupHelper(K key){}




}
