package nl.rug.oop.rpg.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Scan {
    private static final Scanner scanner = new Scanner(System.in);

    public static int nextInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Select one of the choices.");
                scanner.nextLine();
            }
        }
    }

    public static void closeScanner() {
        scanner.close();
    }
}
