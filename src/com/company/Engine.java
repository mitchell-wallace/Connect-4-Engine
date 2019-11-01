package com.company;

public class Engine {

    public static int[] bestMove(Game game1) {
        Node node1 = new Node(game1);
        node1.buildTree(6);
        return Minimax.minimaxStart(node1,6,true);
    }

    public static int[] perft(Game game1, int depth) {
        Node node1 = new Node(game1);
        return node1.perft(depth);
    }

}
