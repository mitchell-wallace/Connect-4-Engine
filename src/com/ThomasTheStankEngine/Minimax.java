/*
    Part of the ThomasTheStankEngine connect-4 engine.

    Written by:     Mitchell wallace
    Student number: c3293398
    Subject:        COMP2230
    Last modified:  03-11-2019
    Description:    Implementation of the minimax algorithm. Evaluation function is specified in the
                    Evaluator class. Tree structure is specified in the Node class.

*/

//package com.ThomasTheStankEngine;

public class Minimax {


    private static int minimax(Node node, int depth, boolean maximisingPlayer) {
        if (depth == 0) {
            Evaluator.resetScore();     // redundant but I wanted to be sure it happened
            return Evaluator.evaluate(node.getData());
        }

        if (maximisingPlayer) {
            // set the current evaluation to be as small as possible if we wish to maximise it
            int value = Integer.MIN_VALUE;
                for (int i = 0; i < node.getChildren().length; i++) {
                    // if we can do better, then set value to the better evaluation
                    if (node.getChild(i) != null)
                        value = Math.max(value, minimax(node.getChild(i), depth - 1, false));
                }

            return value;
        } else { // we are trying to minimise
            // set the current evaluation to be as large as possible if we wish to minimise it
            int value = Integer.MAX_VALUE;
                for (int i = 0; i < node.getChildren().length; i++) {
                    if (node.getChild(i) != null)
                        value = Math.min(value, minimax(node.getChild(i), depth - 1, true));
                }

            return value;
        }
    }

    public static int[] minimaxStart(Node node, int depth, boolean maximisingPlayer) {

        if (node.getData().getMovesList().equals("")) {
            int[] out = {3, 70};
            return out;     // The first move should always be in the centre, no need to do any more
                            // processing to determine that
        }
        else {
            depth -= 1;     // The algorithm practically looks depth+1 moves ahead
                            // This way, if depth = 4, the possible boardstates after 4 more moves will be evaluated
            node.buildTree(depth+1);    // depth+1 is required for the corresponding tree.

            int bestIndex = 0;
            int bestValue = 0;

            if (maximisingPlayer) {
                bestValue = Integer.MIN_VALUE;
                for (int i = 0; i < node.getChildren().length; i++) {
                    if (node.getChild(i) != null) {
                        int tmpValue = minimax(node.getChild(i), depth, false);
                        if (tmpValue > bestValue) { // we’re trying to maximise, so if we get a bigger value, update
                            bestValue = tmpValue;
                            bestIndex = i;
                        }
                    }
                }
            } else { // minimising player
                bestValue = Integer.MAX_VALUE;
                for (int i = 0; i < node.getChildren().length; i++) {
                    if (node.getChild(i) != null) {
                        int tmpValue = minimax(node.getChild(i), depth, true);
                        if (tmpValue < bestValue) { // we’re trying to minimise, so if we get a smaller value, update
                            bestValue = tmpValue;
                            bestIndex = i;
                        }
                    }
                }
            }

            int[] out = {bestIndex, bestValue};     // output the two values needed by the interface
            return out;
        }
    }

}
