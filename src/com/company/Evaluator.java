

package com.company;

public class Evaluator {

    private static Game thisGame = new Game();
    private static int currentPlayer = 1;
    private static int score = 0;

    public static int evaluate(Game data) {
        thisGame = data;

        for (int i = 0; i < 7; i++) { //moving across the bottom first
            for (int j = 0; j < 6; j++) { //moving up each column
                if ( thisGame.getBoardState()[i][j] != 0) {

                    currentPlayer = thisGame.getBoardState()[i][j];
                    int cellScore = 0;
                    int checkTemp = 0;

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
                    //checkTemp = 0;        // commented as a reminder in case I want to use it again

                    if ( i == 3) {cellScore = (int) (cellScore * 1.3) + 3;}    // may need a parseInt or something?
                    else if ( i == 2 || i == 4 ) {cellScore = (int) (cellScore * 1.2) + 2;}
                    else if ( i == 1 || i == 5 ) {cellScore = (int) (cellScore * 1.1) + 1;}

                    // evaluate maximises player 1 by default, as is typical
                    if (currentPlayer == 2) { score -= cellScore; }
                    else { score += cellScore; }
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
        if (thisGame.getBoardState()[i][j-1] != 0) {
            return 11;   //not bottom row but playable
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

        if ((check%1000) / 100 == 0 ) {    // the centre cell has no 'owned' neighbours
            if (check % 100 >= 30 ) {
                score += 5;
                score += score%10;
            }
            if (check % 100 >= 40 ) {
                score += 3;
            }
        }

        else if ((check%1000) / 100 == 1 ) {    // the centre cell has 1 owned neighbour
            if (check % 100 >= 30 ) {
                score += 50;
                score += (score%10)*15;
            }
            if (check % 100 >= 40 ) {
                score += 30;
            }
        }

        else if ((check%1000) / 100 == 2 ) {    // the centre cell has 2 owned neighbours
            if (check % 100 >= 30 ) {
                score += 500;
                score += (score%10)*300;
            }
            if (check % 100 >= 40 ) {
                score += 700;
            }
        }

        else if ((check%1000) / 100 == 3 ) {    // four in a row
            if (check / 1000 == 1) {
                score += 3000;
            }
        }

        // For checking four in a row, we need to check that it isn't just 4 out of 5 in a given direction
        // We set a thousands column flag in checkCell, for marking owned outside cells
        // and check that the thousands column == 1 for giving a 4 in a row score
        // A non-vertical four in a row will have a higher score because it can be 'seen' from two places
        // This is probably okay?
        // We don't want the 4 score to be /too/ high so that it still values threats,
        // but obviously it still needs to actually play the winning move when it has the opportunity

        return score;
    }

}