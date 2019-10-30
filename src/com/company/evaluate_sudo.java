// basically go through the gameboard and see how many in a row we have at any point?
// 10 base points for 2 in a row
//    if can't extend to 4, ignore
//    for each direction it can be extended to 4 (each side alone + both sides), +2
//        so really a single 2 in a row will be 12, 14, or 16
//        bonus points for each extra location of a 2 in a row
// 100 base points for 3 in a row
//    if can't extend to 4, ignore
//    for each option it can be extended to 4 (each side), +2
//        so really a single 3 in a row will be 104 or 108
//        bonus points for each extra location of a 3 in a row
// 1000 base points for 4 in a row
//
// we also need to deduct points for the opponent's options - but we can work on that later
//    this would be basically running evaluate again but for the other player
//
// remember this is just evaluating a given boardstate
// not figuring out what to do!

public class Evaluator {

    private Game thisgame;
    private int[][] thisboard = thisgame.getBoardState();
    private int[][] boardVisited = new int[7][6];
    private int maximisingPlayer;
    private int longestChain = 0;
    private int longestChainOptions = 0;
    private int score = 0;
    

    public int[] evaluate() {

        

        for (int i = 0; i < 7; i++) { //moving across the bottom first
            int j = 0;
            if ( thisboard[i][j] == playerNumber ) {
                int[] adjacencies = {0,0,0,0,0,0,0,0}
                //then we want to try each of 8 directions it could be adjacent to another piece
                // helper method checks i,j bounded, returns 1 if open, 2 if maximising player, 3 if opponent,
                //      0 if out of bounds

                // ACTUALLY we don't care that much about checking all adjacencies. We mostly care about playable ones.
                // That can be another optimisation for later.

                //for ( int k = 0; k < 8; k++ ) {
                //    adjacencies[k] = this.checkCell(i-1 ,j-1,thisgame,maximisingPlayer, k);
                //}
                /*
                adjacencies[0] = this.checkCell(i-1 ,j-1,thisgame,maximisingPlayer, 0);
                adjacencies[1] = this.checkCell(i-1, j,thisgame,maximisingPlayer, 1);
                adjacencies[2] = this.checkCell(i-1, j+1,thisgame,maximisingPlayer, 2);
                adjacencies[3] = this.checkCell(i, j+1,thisgame,maximisingPlayer, 3);
                adjacencies[4] = this.checkCell(i+1, j+1,thisgame,maximisingPlayer, 4);
                adjacencies[5] = this.checkCell(i+1, j,thisgame,maximisingPlayer, 5);
                adjacencies[6] = this.checkCell(i+1, j-1,thisgame,maximisingPlayer, 6);
                adjacencies[7] = this.checkCell(i, j-1,thisgame,maximisingPlayer, 7);
                */

                // really should go for horizontal/vertical/positive/negative instead of 8 directions

                /*
                check i-1, i+1  >> possible threat
                    i-2, i+2    >> potential threat or actual threat
                check j+1       >> potential threat
                    no point checking further up at this stage
                check i+1 j+1, i-1 j-1  >> possible threat
                    i+2 j+2, i-2 j-2    >> potential threat or actual threat
                check i+1 j-1, i-1 j+1  >> possible threat
                    i+2 j-2, i-2 j+2    >> potential threat or actual threat

                */


            }
            // do something to increment j if we have a piece above us
            // should this be recursive?
        }


    }

    private int checkCell(int i, int j, Game thisgame, int maximisingPlayer, int direction) {
        if ( i >= 7 || i < 0 || j >= 6 || j < 0 ) // then out of bounds
           return 0;
        if (thisgame.getBoardState()[i][j] = 0) {
            TODOiterate(i,j,thisgame,direction);
            return 1;
        }
        else if (thisgame.getBoardState()[i][j] = maximisingPlayer) {
            TODOpointsIfOpen();
            return 2;
        }
        else return 3;
    }

    private TODOiterate(int i, int j, Game thisgame, int direction) {

    }
    
}
