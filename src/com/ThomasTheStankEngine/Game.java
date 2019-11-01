package com.ThomasTheStankEngine;

public class Game {

    public Game() {
        initialiseBoard();
        movesList = "";
    }

    private int[][] boardState;     // 0 means empty, 1 means odd player, 2 means evens player
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
        /*if (moves.length() < movesList.length()) {
            buildBoard(moves);
            return true;}

        if (moves.equals(movesList)) {return true;}

        for (int i=0; i<moves.length(); i++) {
            if ((i < movesList.length()) && (!(moves.charAt(i)==movesList.charAt(i)))) {
                // if moves, as received from the coordinator, differs from the stored movesList
                // at any position, the board needs to be rebuilt and the loop can be broken
                buildBoard(moves);
                return true;
            }
            if (i > movesList.length()) {
                // if moves, as received from the coordinator, contains all of movesList plus some
                // extra characters, it simply means it contains new moves, from which the boardstate
                // needs to be updated

                // perform move
                performMove(moves.charAt(i));
            }
        }*/

        buildBoard(moves);

    }

    public void buildBoard(String moves) {
        // It should basically just work out to initialising the game and running
        // performMove for each character in moves

        movesList="";
        initialiseBoard();
        for (int i = 0; i < moves.length(); i++) {
            performMove(Character.getNumericValue(moves.charAt(i)));
        }
    }

    public void performMove(int newMove) {
        // updates boardState to contain an additional move
        // performing a move should also add it to movesList

        movesList = movesList + newMove;

        for (int j = 0; j<6; j++) {
            if (boardState[newMove][j] == 0) {
                boardState[newMove][j] = (2-((movesList.length())%2));
                break;
            }
        }

    }

    public void printGame() {
        System.out.print("\n");
        for (int i = 6; i > -1; i-- ) {
            for (int j = 0; j < 6; j++ ) {
                System.out.print("\t" + boardState[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }


    /*
        public void performMoveOld(char newMove) {
        // updates boardState to contain an additional move
        // performing a move should also add it to movesList

        if ( movesList.length() < 42 ) {
            movesList = movesList + newMove;

            int i = Character.getNumericValue(newMove);

            for (int j = 0; j < 6; j++) {
                if (boardState[i][j] == 0) {
                    boardState[i][j] = (2 - ((movesList.length()) % 2));
                    break;
                }
            }
        }

        }
    */


}
