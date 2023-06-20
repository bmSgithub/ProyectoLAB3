package com.mygdx.game.Utilities;
import java.util.Scanner;

public class Console {
    private static Scanner scan = new Scanner (System.in);
    public static void write(Object x){
        System.out.println(x);
    }
    public static int readInt(String msg)
    {
        Console.write(msg);
        return scan.nextInt();
    }

    public static void pause()
    {
        scan.nextLine();
        Console.write("Press enter to continue...");
        scan.nextLine();
    }
}
