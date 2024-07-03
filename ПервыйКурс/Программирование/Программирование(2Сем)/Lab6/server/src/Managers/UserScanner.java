package Managers;

import java.util.Scanner;

/**
 * Клас, хранящий пользовательский Scanner
 */
public class UserScanner {
    public static Scanner customScanner = new Scanner(System.in);

    public static Scanner getUserScanner() {
        return customScanner;
    }
}