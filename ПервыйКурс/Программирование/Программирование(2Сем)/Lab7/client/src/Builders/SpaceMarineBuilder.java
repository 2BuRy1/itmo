package Builders;

import Exceptions.InvalidDataException;
import MainClasses.SpaceMarine;

import java.time.LocalDateTime;

/**
 * Класс, создающий объект SpaceMarine при помощи пользовательского ввода
 */
public class SpaceMarineBuilder extends Builder {

    /**
     * @return собирает полноценный объект SpaceMarine из других Builders
     * @throws InvalidDataException неверно введенные данные
     */
    public SpaceMarine create() throws InvalidDataException {
        return new SpaceMarine(
                buildString("name"),
                new CoordinatesBuilder().create(),
                LocalDateTime.now(),
                buildDoouble("health"),
                new AstartesCategoryBuilder().create(),
                new WeaponBuilder().create(),
                new MeleeWeaponBuilder().create(),
                new ChapterBuilder().create());
    }
}
