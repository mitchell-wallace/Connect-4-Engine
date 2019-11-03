//package com.ThomasTheStankEngine;

public class Evaluator {

    private static Game thisGame = new Game();
    private static int score = 0;

    public static void resetScore() {score = 0;}


    public static int evaluate(Game data) {

        score = 0;

        thisGame = data;

        for (int i = 0; i < 7; i++) {
            if (thisGame.getBoardState()[i][0]!=0) {    // this prevents 3-in-a-row's being counted twice, and
                                                        // improves efficiency without reducing effectiveness too much
                for (int j = 0; j < 6; j++) {
                    int checkTemp = 0;
                    int cellScore = 0;

                    // Checking for horizontal threats
                    if (i < 4) {
                        checkTemp += Evaluator.checkCell(i, j);
                        checkTemp += Evaluator.checkCell(i + 1, j);
                        checkTemp += Evaluator.checkCell(i + 2, j);
                        checkTemp += Evaluator.checkCell(i + 3, j);

                        cellScore += scoreCell(checkTemp);
                        checkTemp = 0;
                    }

                    // Checking for vertical threats
                    if (j < 3) {
                        checkTemp += Evaluator.checkCell(i, j);
                        checkTemp += Evaluator.checkCell(i, j + 1);
                        checkTemp += Evaluator.checkCell(i, j + 2);
                        checkTemp += Evaluator.checkCell(i, j + 3);

                        cellScore += scoreCell(checkTemp);
                        checkTemp = 0;
                    }

                    // Checking for positive diagonal threats
                    if (i < 4 && j < 3) {
                        checkTemp += Evaluator.checkCell(i, j);
                        checkTemp += Evaluator.checkCell(i + 1, j + 1);
                        checkTemp += Evaluator.checkCell(i + 2, j + 2);
                        checkTemp += Evaluator.checkCell(i + 3, j + 3);

                        cellScore += scoreCell(checkTemp);
                        checkTemp = 0;
                    }

                    // Checking for negative diagonal threats
                    if (i > 2 && j < 3) {
                        checkTemp += Evaluator.checkCell(i, j);
                        checkTemp += Evaluator.checkCell(i - 1, j + 1);
                        checkTemp += Evaluator.checkCell(i - 2, j + 2);
                        checkTemp += Evaluator.checkCell(i - 3, j + 3);

                        cellScore += scoreCell(checkTemp);
                        checkTemp = 0;
                    }

                    score += cellScore;

                    if (j < 5) {
                        if (thisGame.getBoardState()[i][j + 1] == 0) {
                            break;  // skip going up columns if there's no more pieces
                        }
                    }
                }
            }
        }

        // below was a modifier I used in testing to try and skew it towards the middle,
        // before it did so on it's own. No longer necessary but I thought it was interesting
        /*int sanity = Character.getNumericValue(thisGame.getMovesList().charAt(thisGame.getMovesList().length()-1));
        sanity = 3-(Math.abs(sanity-3));
        sanity *= 25;*/

        return score;
    }

    private static int checkCell(int i, int j) {
        // returns 0, 1, or 10 - makes score calculation easier
        switch (thisGame.getBoardState()[i][j]) {
            case 0: //open
                return 0;
            case 1: //player1
                return 1;
            case 2: //player2
                return 10;
            default:
                return 0;
        }
    }

    private static int scoreCell(int check) {

        if (check == 0) return 0;   // neutral state
        if (check%10 > 0 && check/10 == 0) {    // p1 opportunity
            switch (check%10) {
                case 1:
                    return 10;
                case 2:
                    return 100;
                case 3:
                    return 1000;
                case 4:
                    return 10000;
                default:
                    return 0;
            }
        }
        if (check/10 > 0 && check%10 == 0) {    // p2 opportunity
            switch (check/10) {
                case 1:
                    return -10;
                case 2:
                    return -100;
                case 3:
                    return -1000;
                case 4:
                    return -10000;
                default:
                    return 0;
            }
        }

        return 0;   // no player opportunity
    }

}