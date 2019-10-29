package com.company;

public class Engine {

    public static int[] bestMove(Game game1) {

        // TODO:: write an actual evaluation function

        int position = (game1.getMovesList().length() + 1) %7;
        int confidence = 10;

        int[] result = {position, confidence};

        return result;
    }

    public static int[] perft(Game game1, int depth) {

        // TODO:: this method lmao
        // if I'm lazy, just dump something that looks like my eval function that just makes it wait and
        // spit out something roughly in the class of n^2

        int[] dummy_do_not_use = {depth,depth*2};

        return dummy_do_not_use;
    }

}
