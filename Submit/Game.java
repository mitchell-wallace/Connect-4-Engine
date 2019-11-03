/*
    Part of the ThomasTheStankEngine connect-4 engine.

    Written by:     Mitchell wallace
    Student number: c3293398
    Subject:        COMP2230
    Last modified:  03-11-2019
    Description:    This class stores the state of the game, both as a two-dimensional array, and
                    as a string containing only the sent of moves. It also contains methods for
                    updating and displaying the game state.

*/

//package com.ThomasTheStankEngine;

public class Game {

    public Game() {
        initialiseBoard();
        movesList = "";
    }

    private int[][] boardState;     // 0 means empty, 1 means player 1, 2 means player 2
    private String movesList;

    public int[][] getBoardState() { return boardState; }
    public String getMovesList() { return movesList; }
    public void setBoardState(int[][] boardState1) { boardState = boardState1; }
    public void setMovesList(String movesList1) { movesList = movesList1; }


    public void initialiseBoard() {
        boardState = new int[7][6];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                boardState[i][j] = 0;
            }
        }
    }

    public void updateMovesList (String moves) {

        // It should basically just work out to initialising the game and running
        // performMove for each character in moves
        // Resetting is perhaps not the most efficient approach but it's good enough

        movesList="";
        initialiseBoard();
        for (int i = 0; i < moves.length(); i++) {
            performMove(Character.getNumericValue(moves.charAt(i)));
        }
    }

    public boolean performMove(int newMove) {
        // updates boardState to contain an additional move
        // performing a move should also add it to movesList

        if (boardState[newMove][5] != 0 || newMove > 6)
            return false;

        for (int j = 0; j<6; j++) {
            if (boardState[newMove][j] == 0) {
                boardState[newMove][j] = (((movesList.length())%2)+1);
                movesList = movesList + newMove;
                return true;
            }
        }

        return false;   // it shouldn't get to this point but if it does the move didn't work
    }

    public boolean nextPlayer() {   // I don't think i need this method anymore
        if (((movesList.length()%2)+1) == 1) {return true;}
        else return false;
    }

    public void printGame() {   // used for testing to visualise the board state
        System.out.print("\n");
        for (int j = 5; j > -1; j--) {
            for (int i = 0; i < 7; i++) {
                System.out.print("\t" + boardState[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

}
