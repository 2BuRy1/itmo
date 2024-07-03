package Builders;

import Enums.Weapon;

/**
 * Класс, осуществляющий пользовательский ввод полей Weapon:wq
 * для SpaceMarine
 */
public class WeaponBuilder extends Builder{

    /**
     * @return возвращает введенный пользователем экземпляр Weapon
     */
    public Weapon create()  {
        System.out.println("Возможные оружия: ");
        System.out.println(Weapon.names());

        while(true){
           String input = scanner.nextLine().trim();
            try{
                return Weapon.valueOf(input.toUpperCase());
            }
            catch (IllegalArgumentException e){
                System.err.println("Такого оружия нет");
            }
            
        }


    }
}
