package Builders;

import Enums.AstartesCategory;

import java.util.Locale;

/**
 * Класс, осуществляющий пользовательский ввод полей AstartesCategory для SpaceMarine
 *
 */
public class AstartesCategoryBuilder extends Builder {

    /**
     * @return возвращает введенный пользователем экземпляр AstartesCategory
     */
    public AstartesCategory create() {
        System.out.println("Возможные категории: ");
        System.out.println(AstartesCategory.names());

        while(true){
            String input=scanner.nextLine();
            try{
                return AstartesCategory.valueOf(input.toUpperCase(Locale.ROOT));

            }
            catch (IllegalArgumentException e){
                System.err.println("Такой категории нет в списке");
            }
        }
    }

}
