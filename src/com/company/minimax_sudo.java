public class Minimax {

    int minimax(Node node, int depth, boolean maximisingPlayer) {
        if (depth == 0) {
            return evaluationFunction(node);
        }

        if (maximisingPlayer) {
            // set the current evaluation to be as small as possible if we wish tomaximise it
            int value = Integer.MIN_VALUE;
            for (int i = 0; i < node.children.length; i++) {
                // if we can do better, then set value to the better evaluation
                value = Math.max(value, minimax(node.children[i], depth - 1; false));
            }
            return value;
        } else { // we are trying to minimise
            // set the current evaluation to be as large as possible if we wish tominimise it
            int value = Integer.MAX_VALUE;
            for (int i = 0; i < node.children.length; i++) {
                value = Math.min(value, minimax(node.children[i], depth - 1; true));
            }
            return value;
        }
    }

    int minimaxStart(Node node, int depth, boolean maximisingPlayer) {
        int bestIndex = 0;
        if (maximisingPlayer) {
            int bestValue = Integer.MIN_VALUE;
            for (int i = 0; i < node.children.length; i++) {
                int tmpValue = minimax(node.children[i], depth, false);
                if (tmpValue > bestValue) { // we’re trying to maximise, so if we get abigger value, update
                    bestValue = tmpValue;
                    bestIndex = i;
                }
            }
        } else { // minimising player
            int bestValue = Integer.MAX_VALUE;
            for (int i = 0; i < node.children.length; i++) {
                int tmpValue = minimax(node.children[i], depth, true);
                if (tmpValue < bestValue) { // we’re trying to minimise, so if we get asmaller value, update
                    bestValue = tmpValue;
                    bestIndex = i;
                }
            }
        }

        return bestIndex;
    }
}
