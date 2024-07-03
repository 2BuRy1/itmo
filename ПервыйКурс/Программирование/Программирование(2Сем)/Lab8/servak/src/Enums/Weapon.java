package Enums;

/**
 * Енам оружий
 */
public enum Weapon {
    MELTAGUN,
    BOLT_PISTOL,
    HEAVY_FLAMER,
    MULTI_MELTA;

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
}
