package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        // TODO:: map to coordinator control flow
        Game thisGame = new Game();
        thisGame.initialiseBoard();

        // game loop setup
        boolean running = true;
        Scanner sc = new Scanner(System.in);


        // game loop
        while (running) {
            String cinput = sc.nextLine();

            if (cinput.equals("quit")) {
                System.out.println("quitting");
                running = false;
            }

            else if (cinput.equals("name")) {
                System.out.println("engine_name");
            }

            else if (cinput.equals("isready")) {
                // TODO:: engine.ready() or something like that
                System.out.println("readyok");
            }

            else if (cinput.contains("position startpos")) {
                thisGame.updateMovesList(cinput.substring(18));
            }

            else if (cinput.contains("go ftime")) {
                // TODO:: set up space delimited thing (??)
                int[] times = new int[2];

                int i = 9;
                while (!(cinput.charAt(i) == ' ')){ i++; } // find the index for the space following x
                times[0] = Integer.parseInt(cinput.substring(9,(i-1))); // determine x
                times[1] = Integer.parseInt(cinput.substring(i+7)); // we know how far after the end of x the start if y is

                int[] thisMove = Engine.bestMove(thisGame);
                System.out.println("bestmove " + thisMove[0] + " " + thisMove[1]);
            }

            else if (cinput.contains("perft")) {
                int x = Integer.parseInt(cinput.substring(6));
                int[] thisPerft = Engine.perft(thisGame,x);
                System.out.println("bestmove " + thisPerft[0] + " " + thisPerft[1]);
            }


        }
    }


}
