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


public int[] evaluate(Game thisgame, int maximisingPlayer) {

    private int longestChain = 0;
    private int longestChainOptions = 0;
    private int score = 0;
    //private int playerNumber = 1;
    //ignore playerNumber, use maximisingPlayer instead

    int[][] thisboard = thisgame.getBoardState();
    
    for (int i = 0; i < 7; i++) { //moving across the bottom first
        int j = 0;
        if ( thisboard[i][j] == playerNumber ) {
            int[] adjacencies = {0,0,0,0,0,0,0,0}
            //then we want to try each of 8 directions it could be adjacent to another piece
            // helper method checks i,j bounded, returns 1 if open, 2 if maximising player, 3 if opponent,
            //      0 if out of bounds
            
            // ACTUALLY we don't care that much about checking all adjacencies. We mostly care about playable ones.
            // That can be another optimisation for later.
            
            adjacencies[0] = this.checkCell(i-1 ,j-1,thisgame,maximisingPlayer);
            adjacencies[1] = this.checkCell(i-1, j,thisgame,maximisingPlayer);
            adjacencies[2] = this.checkCell(i-1, j+1,thisgame,maximisingPlayer);
            adjacencies[3] = this.checkCell(i, j+1,thisgame,maximisingPlayer);
            adjacencies[4] = this.checkCell(i+1, j+1,thisgame,maximisingPlayer);
            adjacencies[5] = this.checkCell(i+1, j,thisgame,maximisingPlayer);
            adjacencies[6] = this.checkCell(i+1, j-1,thisgame,maximisingPlayer);
            adjacencies[7] = this.checkCell(i, j-1,thisgame,maximisingPlayer);

        }
        // do something to increment j if we have a piece above us
        // should this be recursive?
    }
    

}

private int checkCell(int i, int j, Game thisgame, int maximisingPlayer) {
    if ( i >= 7 || i < 0 || j >= 6 || j < 0 ) // then out of bounds
       return 0;
    else int[][] thisboard = thisgame.getBoardState();
    if (thisboard[i][j] = 0) {
        TODOiterate();
        return 1;
    }
    else if (thisboard[i][j] = maximisingPlayer) {
        TODOpointsIfOpen();
        return 2;
    }
    else return 3;
}
