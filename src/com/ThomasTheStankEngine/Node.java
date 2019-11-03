/*
    Part of the ThomasTheStankEngine connect-4 engine.

    Written by:     Mitchell wallace
    Student number: c3293398
    Subject:        COMP2230
    Last modified:  03-11-2019
    Description:    Tree structure for laying out different game states to be evaluated
                    by the minimax algorithm.

*/

//package com.ThomasTheStankEngine;

public class Node {

    Node(Game game1) {
        data = game1;
        children = new Node[7];
    }

    Node() {
        data = new Game();
        children = new Node[7];
    }

    Node(boolean b) {
        data = null;
        children = new Node[7];
    }

    private Game data;
    private Node[] children;
    private static int perftCount = 0;

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
                children[i] = new Node();
                children[i].getData().setMovesList(data.getMovesList());
                Node.copyData(data,children[i].getData());  // avoids all nodes pointing to same memory address
                if (children[i].getData().performMove(i))
                    children[i].buildTree(depth - 1);
                else children[i] = null;
            }
        }
    }

    private static void copyData(Game src, Game dst) {   //copies src into dst
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
                if (children[i] != null)
                    count += children[i].visitTree();
            }
        }
        return count;
    }

    public int[] perft(int depth) {
        Node root = new Node();
        perftCount = 0;
        root.perftIterate(depth);

        int[] out = {depth, perftCount};
        return out;
    }

    private void perftIterate(int depth) {
        if (depth == 0) {
            perftCount++;
        }
        else {
            perftCount++;
            for (int i = 0; i < 7; i++) {
                Node.copyData(this.getData(),this.children[i].getData());
                if (this.children[i].getData().performMove(i))
                    this.perftIterate(depth-1);
            }
        }
    }



}
