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


public int[] evaluate(Game thisgame) {

    private int longestChain = 0;
    private int longestChainOptions = 0;
    private int score = 0;

    

}
