package org.algos.chapterThree;

public class RBNode<Key extends Comparable<Key>, Value> implements Comparable<RBNode<Key,Value>> {
    private Key key;
    private Value value;
    private Color color;
    private RBNode<Key, Value> lc;
    private RBNode<Key, Value> rc;

    RBNode(Key k, Value v) {
        key = k;
        value = v;
        color = color.BLACK;
        lc = null;
        rc = null;
    }

    RBNode(Key k, Value v, Color c) {
        key = k;
        value = v;
        color = c;
        lc = null;
        rc = null;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public RBNode<Key, Value> getLc() {
        return lc;
    }

    public void setLc(RBNode<Key, Value> lc) {
        this.lc = lc;
    }

    public RBNode<Key, Value> getRc() {
        return rc;
    }

    public void setRc(RBNode<Key, Value> rc) {
        this.rc = rc;
    }

    @Override
    public int compareTo(RBNode<Key,Value> node){
        return key.compareTo(node.getKey());
    }

}


enum Color {
    RED,
    BLACK;
}