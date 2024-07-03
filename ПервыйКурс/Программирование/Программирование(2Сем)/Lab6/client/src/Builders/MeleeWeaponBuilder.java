package Builders;

import Enums.MeleeWeapon;

/**
 * Класс, осуществляющий пользовательский ввод полей MeleeWeapon для SpaceMarine
 */
public class MeleeWeaponBuilder extends Builder {

    /**
     * @return возвращает введенный пользователем экземпляр MeleeWeapon
     */
    public MeleeWeapon create()  {
        System.out.println("Варианты ближнего оружия: ");
        System.out.println(MeleeWeapon.names());

        while(true){
            String input = scanner.nextLine().trim();
            try {
                return MeleeWeapon.valueOf(input.toUpperCase());
            }
            catch (IllegalArgumentException e){
                System.err.println("Такого ближнего оружия нет");
            }
        }
    }
}
