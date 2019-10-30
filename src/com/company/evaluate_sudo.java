/* basically go through the gameboard and see how many in a row we have at any point?
** 10 base points for 2 in a row
**    if can't extend to 4, ignore
**    for each direction it can be extended to 4 (each side alone + both sides), +2
**        so really a single 2 in a row will be 12, 14, or 16
**        bonus points for each extra location of a 2 in a row
** 100 base points for 3 in a row
**    if can't extend to 4, ignore
**    for each option it can be extended to 4 (each side), +20
**        so really a single 3 in a row will be 140 or 180
**        bonus points for each extra location of a 3 in a row
** 1000 base points for 4 in a row

do this a little differently - since we're searching from a single point at a time;
We order by the number of our pieces in a given neighbourhood from a piece,
    and then the open positions next to them

Horizontal // Positive // Negative:
    Single piece:
    +1, -1, +/-2    >> 5 pts
    +1, -1, +2, -2  >> 8 pts
        +1 point for each that is not just open but playable
    
    Two pieces:
    +1, -1, +/-2    >> 50 pts
    +1, -1, +2, -2  >> 80 pts
        +15 points for each that is not just open but playable
    
    Three pieces:
    +1, -1, +/-2    >> 500 pts
    +1, -1, +2, -2  >> 1000 pts
        +200 points for each that is not just open but playable
            This means we win next turn. These points will only be given once per double threat,
                as only the middle piece will meet the criteria.
            We assign more points to having two threats in one direction because this guarantees
                we have two threats that don't rely on the same play
    
 Vertical:      // With vertical we only count up. It's just easier this way.
    Single piece:
    +1              >> 5 pts
    
    Two pieces:
    +1, +2          >> 50 pts
    
    Three pieces:
    +1, +2, +3      >> 400 pts
    
 Centralisation:	// Additional points for being towards the middle
		            as it has more openings
    i = 0|6	>> pts x 1
    i = 1|5	>> (pts x 1.1) + 1
    i = 2|4	>> (pts x 1.2) + 2
    i = 3	>> (pts x 1.3) + 3
    
  
 
 
    
    
    




** we also need to deduct points for the opponent's options - but we can work on that later
**    this would be basically running evaluate again but for the other player
**
** remember this is just evaluating a given boardstate
** not figuring out what to do!
*/

public class Evaluator {

    private Game thisgame;
    private int[][] thisboard = thisgame.getBoardState();
    private int[][] boardVisited = new int[7][6];
    private int maximisingPlayer;
    private int longestChain = 0;
    private int longestChainOptions = 0;
    private int score = 0;
    
    
    private void initialiseVisited() {
        boardVisited = new int[7][6];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                boardState[i][j] = 0;
            }
        }
    }
    

    public int[] evaluate() {

        for (int i = 0; i < 7; i++) { //moving across the bottom first
            for (int j = 0; j < 6; j++) //moving up each column
                if ( thisboard[i][j] == playerNumber ) {

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

                    if ( i == 3) {cellScore = (cellScore * 1.3) + 3;}    // may need a parseInt or something?
                    else if ( i == 2 || i == 4 ) {cellScore = (cellScore * 1.2) + 2;}
                    else if ( i == 1 || i == 5 ) {cellScore = (cellScore * 1.1) + 1;}

                    score += cellScore;
                    
                    if (thisboard[i][j+1] == 0) {j = 6;} // block moving up column if it's empty above
                    
                }
            }
        }

    }

    private int checkCell(int i, int j) {
        if ( i >= 7 || i < 0 || j >= 6 || j < 0 ) // then out of bounds
           return 0;
        if (boardState[i][j] = 0) {
            if (j>0) {
                if (boardState[i][j-1] != 0) {return 2;}
            }
            return 1;
        }
        else if (boardState[i][j] = maximisingPlayer) {
            return 3;
        }
        else return 4;
    }

 
    
}
