package Enums;

import java.util.Comparator;

/**
 * Енам, хранящий категорию
 */
public enum AstartesCategory implements Comparator<AstartesCategory> {
    SCOUT(5),
    DREADNOUGHT(10),
    ASSAULT(15),
    LIBRARIAN(24),
    APOTHECARY(30);

    private final int lvl;

    AstartesCategory(int lvl){
        this.lvl=lvl;
    }

    /**
     * @return позволяет вывести все объекты enum на отдельных строках
     */
    public static String names(){
        StringBuilder stringBuilder = new StringBuilder();
        for(var forms: values()){
            stringBuilder.append(forms.name()).append("\n");
        }
        return stringBuilder.substring(0, stringBuilder.length()-1);
    }

    public int getLvl(){
        return this.lvl;
    }


    /**
     * @param o1 первый объект для сравнения.
     * @param o2 второй объект для сравнения.
     * @return возвращает наибольшее значение поля lvl из этих двух объектов
     */
    @Override
    public int compare(AstartesCategory o1, AstartesCategory o2) {
        return o1.lvl - o2.lvl;
    }
}
