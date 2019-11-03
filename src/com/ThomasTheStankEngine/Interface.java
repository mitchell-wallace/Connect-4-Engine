//package com.ThomasTheStankEngine;         // package was required for running in IntelliJ
import java.util.Scanner;
import java.io.*;

public class Interface {

    public static void main(String[] args) {

        // game loop setup
        boolean running = true;
        Scanner sc = new Scanner(System.in);
        Node thisNode = new Node();

        // game loop
        while (running) {
            String cinput = sc.nextLine();

            if (cinput.contains("quit")) {
                System.out.println("quitting");
                running = false;
            }

            else if (cinput.contains("go")) {

                // the block below is to determine times. My engine runs well enough in short enough time
                // that I don't think it needs to adjust for time.
                // or at least, on my i5-6600k @ 4GHz at home it responds with no perceptible delay
                // at a default depth of 4
                /*
                int[] times = new int[2];
                int i = 9;
                while (!(cinput.charAt(i) == ' ')){ i++; } // find the index for the space following x
                times[0] = Integer.parseInt(cinput.substring(9,(i-1))); // determine x
                times[1] = Integer.parseInt(cinput.substring(i+7)); // we know how far after the end of x the start if y is
                */

                // depth 4 is sufficient for the engine to be able to beat me,
                // and performs fast enough that I won't need to adapt anything to account for time consumption
                int[] thisMove = Minimax.minimaxStart(thisNode,4,thisNode.getData().nextPlayer());

                System.out.println("bestmove " + thisMove[0] + " " + thisMove[1]);
            }

            else if (cinput.contains("name")) {
                System.out.println("ThomasTheStankEngine-c3293398");
            }

            else if (cinput.contains("isready")) {
                System.out.println("readyok");
            }

            else if (cinput.contains("position")) {
                if (cinput.length() > 17 )
                    thisNode.getData().updateMovesList(cinput.substring(18));
                else
                    thisNode.getData().updateMovesList("");
            }

            else if (cinput.contains("perft")) {
                if (cinput.length() > 6) {
                    int x = Integer.parseInt(cinput.substring(6));
                    int[] thisPerft = thisNode.perft(x);

                    System.out.println("perft " + thisPerft[0] + " " + thisPerft[1]);
                }
            }

            else if (cinput.contains("info")) {
                System.out.println("No info output has been configured for this engine.\n");
            }

            else if (cinput.contains("prt")) {      // used for testing to visualise the game board
                thisNode.getData().printGame();
            }

            else if (cinput.contains("mv")) {       // used for testing to play against the engine or play it against itself
                if (cinput.length()<4) {System.out.println("u r dumdum");}
                else {
                    thisNode.getData().performMove(Character.getNumericValue(cinput.charAt(3)));
                    thisNode.getData().printGame();
                }
            }

            //else {System.out.println("no input received");}
        }
    }
}
