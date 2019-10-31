

package com.company;

public class Evaluator {

    private Game thisGame = new Game();
    private int[][] thisBoard = thisGame.getBoardState();
    private int maximisingPlayer;
    private int longestChain = 0;
    private int longestChainOptions = 0;
    private int score = 0;

    public int evaluate() {

        //TODO:: this needs to take in a node, and set the game and board from that

        for (int i = 0; i < 7; i++) { //moving across the bottom first
            for (int j = 0; j < 6; j++) { //moving up each column
                if ( thisBoard[i][j] == maximisingPlayer ) {

                    // helper method checkCell checks i,j bounded, returns 1 if open, 
                    //      2 if open and playable,
                    //      3 if maximising player, 4 if opponent,
                    //      0 if out of bounds

                    int cellScore = 0;
                    
                    // TODO:: make checks below add points as they go
                    // how do we do this in practice? set four ints?
                    //      In that case do we want to change the outputs of checkCell to make this easier?
                    //      i.e. 10 if open, 11 if playable; use >'s to check count, %'s to check playability
                    //      We don't really care why we can't play in a cell so 0 for OOB and opponent
                    //      More elegant to look at this way

                    // Checking for horizontal threats
                    this.checkCell(i-1,j);
                    this.checkCell(i+1,j);
                    this.checkCell(i-2,j);
                    this.checkCell(i+2,j);

                    // Checking for vertical threats
                    this.checkCell(i,j+1);
                    this.checkCell(i,j+3);

                    // Checking for positive diagonal threats
                    this.checkCell(i-1,j-1);
                    this.checkCell(i+1,j+1);
                    this.checkCell(i-2,j-2);
                    this.checkCell(i+2,j+2);

                    // Checking for negative diagonal threats
                    this.checkCell(i-1,j+1);
                    this.checkCell(i+1,j-1);
                    this.checkCell(i-2,j+2);
                    this.checkCell(i+2,j-2);

                    if ( i == 3) {cellScore = (int) (cellScore * 1.3) + 3;}    // may need a parseInt or something?
                    else if ( i == 2 || i == 4 ) {cellScore = (int) (cellScore * 1.2) + 2;}
                    else if ( i == 1 || i == 5 ) {cellScore = (int) (cellScore * 1.1) + 1;}

                    score += cellScore;
                }
                if (thisBoard[i][j+1] == 0) {j = 6;} // block moving up column if it's empty above
            }
        }
        return score;

    }

    private int checkCell(int i, int j) {

        if ( i >= 7 || i < 0 || j >= 6 || j < 0 ) { return 0; } // then out of bounds

        if (thisBoard[i][j] == 0) {
            if (j==0) {return 2;}   //bottom row; playable
            if (j>0) {
                if (thisBoard[i][j-1] != 0) {
                    return 2;   //not bottom row but playable
                }
            }
            return 1;   //otherwise open
        }

        else if (thisBoard[i][j] == maximisingPlayer) {
            return 3;   // maximising player's piece
        }

        else return 4;  // opponent's piece
    }

}