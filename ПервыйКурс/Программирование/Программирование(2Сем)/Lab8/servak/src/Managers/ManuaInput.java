package Managers;

import Interfaces.Reader;

import java.util.Scanner;

/**
 * Класс для ввода через консоль
 */
public class ManuaInput implements Reader {
    private static final Scanner userScanner = UserScanner.getUserScanner();

    @Override
    public String nextLine() {
        return userScanner.nextLine();
    }
}
