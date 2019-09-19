package org.improving.tag;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        System.out.println("Start of Main");
        Game game = new Game (100,18);
        System.out.println("Declared game");
       game.run();
       System.out.println("After Run()");

        long elapsedTicks = game.getStartTime().getTime() - game.getEndTime().getTime();
        double elapsedSeconds = elapsedTicks/1000.00;
        System.out.println("We were running for +" + elapsedSeconds + "s");
    }
}