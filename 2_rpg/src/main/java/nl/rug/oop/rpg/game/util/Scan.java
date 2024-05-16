package nl.rug.oop.rpg.game.util;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A scanner utility class, used to take in input.
 */
public class Scan {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Scans an int from the console.
     * @return The scanner integer.
     */
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

    /**
     * Scans a string from the console.
     * @return The scanned line.
     */
    public static String nextLine() {
        while (true) {
            try {
                return scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }
    }

    public static void closeScanner() {
        scanner.close();
    }
}
