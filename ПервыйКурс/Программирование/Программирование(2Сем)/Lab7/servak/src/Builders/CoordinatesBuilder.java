package Builders;

import Exceptions.InvalidDataException;
import MainClasses.Coordinates;

/**
 * Класс для заполнения полей Coordinates при помощи пользовательского ввода
 */
public class CoordinatesBuilder extends Builder {


    /**
     * @return возвращает объект Coordinates, поля которого были введены пользователем
     * @throws InvalidDataException неверно введенные данные
     */
    public Coordinates create() throws InvalidDataException {
        return new Coordinates(
                buildDoouble("значение х: "), buildLong("значение y: "));
    }
}
