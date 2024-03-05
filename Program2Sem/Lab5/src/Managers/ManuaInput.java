package Managers;

import Interfaces.Reader;

import java.util.Scanner;

/**
 * Класс для ввода через консоль
 */
public class ManuaInput implements Reader {
    private static final Scanner customScanner = UserScanner.getScanner();

    @Override
    public String nextLine() {
        return customScanner.nextLine();
    }
}
