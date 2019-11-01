// TODO:: a lot... this is still basically pseudocode
/*
    


 */

package com.ThomasTheStankEngine;

public class Minimax {


    private static int minimax(Node node, int depth, boolean maximisingPlayer) {
        if (depth == 0) {
            return Evaluator.evaluate(node.getData());
        }

        if (maximisingPlayer) {
            // set the current evaluation to be as small as possible if we wish tomaximise it
            int value = Integer.MIN_VALUE;
            for (int i = 0; i < node.getChildren().length; i++) {
                // if we can do better, then set value to the better evaluation
                value = Math.max(value, minimax(node.getChild(i), depth - 1, false));
            }
            return value;
        } else { // we are trying to minimise
            // set the current evaluation to be as large as possible if we wish tominimise it
            int value = Integer.MAX_VALUE;
            for (int i = 0; i < node.getChildren().length; i++) {
                value = Math.min(value, minimax(node.getChild(i), depth - 1, true));
            }
            return value;
        }
    }

    public static int[] minimaxStart(Node node, int depth, boolean maximisingPlayer) {
        int bestIndex = 0;
        int bestValue;
        if (maximisingPlayer) {
            bestValue = Integer.MIN_VALUE;
            for (int i = 0; i < node.getChildren().length; i++) {
                int tmpValue = minimax(node.getChild(i), depth, false);
                if (tmpValue > bestValue) { // we’re trying to maximise, so if we get abigger value, update
                    bestValue = tmpValue;
                    bestIndex = i;
                }
            }
        } else { // minimising player
            bestValue = Integer.MAX_VALUE;
            for (int i = 0; i < node.getChildren().length; i++) {
                int tmpValue = minimax(node.getChild(i), depth, true);
                if (tmpValue < bestValue) { // we’re trying to minimise, so if we get asmaller value, update
                    bestValue = tmpValue;
                    bestIndex = i;
                }
            }
        }

        int[] out = {bestIndex, bestValue};
        return out;
    }

}
