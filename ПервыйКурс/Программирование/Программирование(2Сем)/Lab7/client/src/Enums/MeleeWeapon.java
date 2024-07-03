package Enums;

/**
 * Енам, хранящий оружия ближнего боя
 */
public enum MeleeWeapon {
    CHAIN_AXE,
    MANREAPER,
    LIGHTING_CLAW,
    POWER_BLADE,
    POWER_FIST;


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
