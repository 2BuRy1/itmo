package Builders;

import Interfaces.Reader;
import Managers.FileMode;
import Managers.ManuaInput;
import Managers.ScriptExecutorManager;

/**
 * Класс для ввода определенных данных
 */
public abstract class Builder {
    protected final Reader scanner;

public Builder(){
    this.scanner= (FileMode.isFileMode) ? new ScriptExecutorManager() : new ManuaInput();
}

    /**
     * @param name запрашиваемый ввод
     * @return возвращает введенную пользователем строку
     */
    protected String buildString(String name){
        String word;
        while(true){
            System.out.println("Введите " + name);
            word = scanner.nextLine();
            if(word.isEmpty()){
                System.err.println("Строка не может быть пустой!");
            }
            else{
                return word;
            }

        }
    }


    /**
     * @param name запрашиваемый ввод
     * @return возвращает введенное пользователем число типа Double
     */
    protected Double buildDoouble(String name){
        String input;
        while(true){
            System.out.println("Введите " + name);
           input=scanner.nextLine();
           try {
               return Double.parseDouble(input);
           }
           catch (NumberFormatException e){
               System.err.println("Число должно быть Double");
           }
        }
    }


    /**
     * @param name запрашиваемый ввод
     * @return возвращает введенное пользователем число типа Long
     */
    protected Long buildLong(String name){

        String input;
        while(true){
            System.out.println("Введите " + name);
            input=scanner.nextLine();
            try{
                return  Long.parseLong(input);
            }
            catch (NumberFormatException e){
                System.err.println("Число должно быть long");
            }
        }
    }

    /**
     * @param name запрашиваемый ввод
     * @return возвращает введенное пользователем число типа int
     */
    protected Integer buildInt(String name){
        String input;
        while(true){
            System.out.println("Введите " + name);
            input=scanner.nextLine();
            try {
                return Integer.parseInt(input);
            }
            catch (NumberFormatException e){
                System.err.println("Число должно быть int");
            }
        }
    }


}
