package com.ThomasTheStankEngine;

import org.jetbrains.annotations.NotNull;

public class Node {

    Node(Game game1) {
        data = game1;
        children = new Node[7];
        skip = false;
    }

    Node() {
        data = new Game();
        children = new Node[7];
        skip = false;
    }

    private Game data;
    private Node[] children;
    private boolean skip;

    public Game getData() {return data;}
    public Node getChild(int i) {return children[i];}
    public Node[] getChildren() {return children;}
    public boolean getSkip() {return skip;}

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
    public void setSkip(boolean s) {skip = s;}

    public void buildTree(int depth) {
        if (depth > 0) {
            for (int i = 0; i<7; i++) {
                children[i] = new Node();
                children[i].getData().setMovesList(data.getMovesList());
                Node.copyData(data,children[i].getData());
                //System.arraycopy(data.getBoardState(),0,children[i].getData().getBoardState(),0,42);
                if (children[i].getData().getBoardState()[i][5]!=0) {
                    children[i].getData().performMove(i);
                } else skip = true;
                children[i].buildTree(depth - 1);
            }
        }
    }

    private static void copyData(@NotNull Game src, Game dst) {   //copies src into dst
        dst.setMovesList(src.getMovesList());
        int[][] tempBoard = new int[7][6];
        for ( int i = 0; i < 7; i++) {
            for ( int j = 0; j < 6; j++ ) {
                tempBoard[i][j] = src.getBoardState()[i][j];
            }
        }
        dst.setBoardState(tempBoard);
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
