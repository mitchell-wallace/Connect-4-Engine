package com.company;

public class Engine {

    public int[] bestMove(Game game1) {

        // TODO:: write an actual evaluation function

        int position = (game1.getMovesList().length() + 1) %7;
        int confidence = 10;

        int[] result = {position, confidence};

        return result;
    }

}
