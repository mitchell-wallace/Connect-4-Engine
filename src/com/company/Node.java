package com.company;

public class Node {

    Node(Game game1) {
        data = game1;
    }

    private Game data = new Game();
    private Node[] children = new Node[7];

    public Game getData() {return data;}
    public Node getChild(int i) {return children[i];}
    public Node[] getChildren() {return children;}

    public void setData(Game data1) {data = data1;}
    public void setChild(int index, Node child) {children[index] = child;}
    public void setChildren (Node[] children1) {    // this is unlikely to be necessary but whatever
        if (children1.length < 7) {
            for (int i = 0; i<children1.length; i++) {
                children[i] = children1[i];
            }
            for (int i = children1.length; i<7; i++) {
                children[i] = null;
            }
        }
        else if (children1.length == 7) {
            children = children1;
        }
        else {
            for (int i = 0; i<7; i++) {
                children[i] = children1[i];
            }
        }
    }

    public void buildTree(int depth) {
        if (depth > 0) {
            for (int i = 0; i<7; i++) {
                Node tempNode = this;
                tempNode.getData().performMove(i);
                children[i] = tempNode;
                tempNode.buildTree(depth - 1);
            }
        }
    }

    private int visitTree() {
        int count = 0;
        if ( this != null ) {
            count++;
            for (int i = 0; i<7; i++) {
                count += children[i].visitTree();
            }
        }
        return count;
    }

    public int[] perft(int depth) {
        buildTree(depth);
        int[] out = {depth,this.visitTree()};
        return out;
    }



}
