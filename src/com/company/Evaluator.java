

package com.company;

public class Evaluator {

    private static Game thisGame = new Game();
    private static int currentPlayer = 1;   //TODO:: this needs to be updated throughout evaluate
    private static int longestChain = 0;
    private static int longestChainOptions = 0;
    private static int score = 0;

    public static int evaluate(Game data) {
        thisGame = data;

        for (int i = 0; i < 7; i++) { //moving across the bottom first
            for (int j = 0; j < 6; j++) { //moving up each column
                if ( thisGame.getBoardState()[i][j] == currentPlayer) { //TODO:: this shouldn't care which player

                    // helper method checkCell checks i,j bounded, returns 1 if open, 
                    //      2 if open and playable,
                    //      3 if maximising player, 4 if opponent,
                    //      0 if out of bounds

                    int cellScore = 0;
                    int checkTemp = 0;
                    
                    // TODO:: make checks below add points as they go
                    // how do we do this in practice? set four ints?
                    //      In that case do we want to change the outputs of checkCell to make this easier?
                    //      i.e. 10 if open, 11 if playable; use >'s to check count, %'s to check playability
                    //      We don't really care why we can't play in a cell so 0 for OOB and opponent
                    //      More elegant to look at this way

                    // TODO:: make this consider both players
                    //      We just add or subtract from score depending on which player it is
                    //      maxPlyr therefore isn't a parameter

                    // Checking for horizontal threats
                    checkTemp += Evaluator.checkCell(i-1,j, false);
                    checkTemp += Evaluator.checkCell(i+1,j, false);
                    checkTemp += Evaluator.checkCell(i-2,j, true);
                    checkTemp += Evaluator.checkCell(i+2,j, true);

                    cellScore += scoreCell(checkTemp);
                    checkTemp = 0;

                    // Checking for vertical threats
                    checkTemp += Evaluator.checkCell(i,j+1, false);
                    checkTemp += Evaluator.checkCell(i,j+2, false);
                    checkTemp += Evaluator.checkCell(i,j+3, true);

                    cellScore += scoreCell(checkTemp);
                    checkTemp = 0;

                    // Checking for positive diagonal threats
                    checkTemp += Evaluator.checkCell(i-1,j-1, false);
                    checkTemp += Evaluator.checkCell(i+1,j+1, false);
                    checkTemp += Evaluator.checkCell(i-2,j-2, true);
                    checkTemp += Evaluator.checkCell(i+2,j+2, true);

                    cellScore += scoreCell(checkTemp);
                    checkTemp = 0;

                    // Checking for negative diagonal threats
                    checkTemp += Evaluator.checkCell(i-1,j+1, false);
                    checkTemp += Evaluator.checkCell(i+1,j-1, false);
                    checkTemp += Evaluator.checkCell(i-2,j+2, true);
                    checkTemp += Evaluator.checkCell(i+2,j-2, true);

                    cellScore += scoreCell(checkTemp);
                    checkTemp = 0;

                    if ( i == 3) {cellScore = (int) (cellScore * 1.3) + 3;}    // may need a parseInt or something?
                    else if ( i == 2 || i == 4 ) {cellScore = (int) (cellScore * 1.2) + 2;}
                    else if ( i == 1 || i == 5 ) {cellScore = (int) (cellScore * 1.1) + 1;}

                    score += cellScore;
                }
                if (thisGame.getBoardState()[i][j+1] == 0) {j = 6;} // block moving up column if it's empty above
            }
        }
        return score;

    }

    private static int checkCell(int i, int j, boolean outside) {

        // outputs basically four binary values: [outer edge] [this player's piece] [open cell] [playable cell]
        //  as thousands, hundreds, 10s, 1s

        if ( i >= 7 || i < 0 || j >= 6 || j < 0 ) { return 0; } // then out of bounds

        if (thisGame.getBoardState()[i][j] == 0) {
            if (j==0) {return 11;}   //bottom row; playable
            if (j>0) {
                if (thisGame.getBoardState()[i][j-1] != 0) {
                    return 11;   //not bottom row but playable
                }
            }
            return 10;   //otherwise open
        }

        else if (thisGame.getBoardState()[i][j] == currentPlayer) {
            if (outside) {
                return 1100;
            }
            return 100;   // maximising player's piece
        }

        else return 0;  // opponent's piece
    }

    private static int scoreCell(int check) {
        int score = 0;

        if (check / 100 == 0 ) {    // the centre cell has no 'owned' neighbours
            if (check % 100 >= 30 ) {
                score += 5;
                score += score%10;
            }
            if (check % 100 >= 40 ) {
                score += 3;
            }
        }

        if (check / 100 == 1 ) {    // the centre cell has 1 owned neighbour
            if (check % 100 >= 30 ) {
                score += 50;
                score += (score%10)*15;
            }
            if (check % 100 >= 40 ) {
                score += 30;
            }
        }

        if (check / 100 == 2 ) {    // the centre cell has 2 owned neighbours
            if (check % 100 >= 30 ) {
                score += 500;
                score += (score%10)*200;
            }
            if (check % 100 >= 40 ) {
                score += 300;
            }
        }

        // For checking four in a row, we need to check that it isn't just 4 out of 5
        // We could set a thousands column flag in checkCell, for marking owned outside cells
        // and check that the thousands column == 1 for giving a 4 in a row score

        return score;
    }

}