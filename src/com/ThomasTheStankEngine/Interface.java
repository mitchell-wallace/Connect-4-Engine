package com.ThomasTheStankEngine;
import java.util.Scanner;
import java.io.*;

public class Interface {

    public static void main(String[] args) {
        Node thisNode = new Node();

        // game loop setup
        boolean running = true;
        //Scanner sc = new Scanner(System.in);
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);

        // game loop
        while (running) {
            //String cinput = sc.nextLine();
            try {
                String cinput = br.readLine();

            //System.out.println(cinput);

            if (cinput.contains("quit")) {
                System.out.println("quitting");
                running = false;
            }

            else if (cinput.contains("go ftime")) {

                // below is to determine times. I don't know what to do with this information yet.
                /*
                int[] times = new int[2];
                int i = 9;
                while (!(cinput.charAt(i) == ' ')){ i++; } // find the index for the space following x
                times[0] = Integer.parseInt(cinput.substring(9,(i-1))); // determine x
                times[1] = Integer.parseInt(cinput.substring(i+7)); // we know how far after the end of x the start if y is
                */

                //thisNode.buildTree(6);
                int[] thisMove = Minimax.minimaxStart(thisNode,6,true);

                System.out.println("bestmove " + thisMove[0] + " " + thisMove[1]);
            }

            else if (cinput.contains("name")) {
                System.out.println("engine_name");
            }

            else if (cinput.contains("isready")) {
                thisNode.buildTree(6);
                System.out.println("readyok");
            }

            else if (cinput.contains("position startpos")) {
                thisNode.getData().updateMovesList(cinput.substring(18));
            }

            else if (cinput.contains("perft")) {
                int x = Integer.parseInt(cinput.substring(6));
                int[] thisPerft = thisNode.perft(x);

                System.out.println("bestmove " + thisPerft[0] + " " + thisPerft[1]);
            }

            else if (cinput.contains("info")) {
                System.out.print("No info output has been configured for this engine.\n");
            }

            } catch (IOException e) {System.out.println("An error occurred");}
        }
    }
}
