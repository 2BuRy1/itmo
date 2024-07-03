package Builders;

import Exceptions.InvalidDataException;
import MainClasses.Chapter;

/**
 * Класс для заполнения полей Chapter при помощи пользовательского ввода
 */
public class ChapterBuilder extends Builder {


    /**
     * @return возвращает объект Chapter, поля которого были введены пользователем
     * @throws InvalidDataException неверно введенные данные
     */
    public Chapter create() throws InvalidDataException {

            return new Chapter(
                    buildString("name"),
                    buildInt("marinesCount")
            );

    }
}
