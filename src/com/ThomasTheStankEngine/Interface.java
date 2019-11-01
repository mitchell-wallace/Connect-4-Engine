package com.ThomasTheStankEngine;
import java.util.Scanner;
import java.io.*;

public class Interface {

    public static void main(String[] args) {
        //System.out.println("This is a test;");

        // game loop setup
        boolean running = true;
        Scanner sc = new Scanner(System.in);
        //InputStreamReader isr=new InputStreamReader(System.in);
        //BufferedReader br=new BufferedReader(isr);
        Node thisNode = new Node();

        // game loop
        while (running) {
            //System.out.println("waiting for input");
            String cinput = sc.nextLine();
            //try {
                //String cinput = br.readLine();

            //System.out.println(cinput);

            if (cinput.contains("quit")) {
                System.out.println("quitting");
                running = false;
            }

            else if (cinput.contains("go")) {

                // below is to determine times. I don't know what to do with this information yet.
                /*
                int[] times = new int[2];
                int i = 9;
                while (!(cinput.charAt(i) == ' ')){ i++; } // find the index for the space following x
                times[0] = Integer.parseInt(cinput.substring(9,(i-1))); // determine x
                times[1] = Integer.parseInt(cinput.substring(i+7)); // we know how far after the end of x the start if y is
                */

                thisNode.buildTree(3);
                int[] thisMove = Minimax.minimaxStart(thisNode,2,true);

                System.out.println("bestmove " + thisMove[0] + " " + thisMove[1]);
            }

            else if (cinput.contains("name")) {
                System.out.println("enginename-c3293398");
            }

            else if (cinput.contains("isready")) {

                //thisNode.buildTree(6);
                System.out.println("readyok");
            }

            else if (cinput.contains("position startpos")) {
                if (cinput.length() > 17 )
                    thisNode.getData().updateMovesList(cinput.substring(18));
                else
                    thisNode.getData().updateMovesList("");
            }

            else if (cinput.contains("perft")) {
                if (cinput.length() > 5) {
                    int x = Integer.parseInt(cinput.substring(6));
                    int[] thisPerft = thisNode.perft(x);

                    System.out.println("perft " + thisPerft[0] + " " + thisPerft[1]);
                }
            }

            else if (cinput.contains("info")) {
                System.out.println("No info output has been configured for this engine.\n");
            }

            else {System.out.println("no input received");}

            //} catch (IOException e) {System.out.println("An error occurred");}
        }
    }
}
