package Managers;

import Enums.AstartesCategory;
import Enums.MeleeWeapon;
import Exceptions.*;
import Exceptions.NoSuchElementException;
import MainClasses.SpaceMarine;

import java.time.LocalDate;
import java.util.*;


/**
 * Класс, хранящий и взаимодействующий с коллекцией
 */
public class CollectionManager {
    private Vector<SpaceMarine> marines = new Vector<SpaceMarine>();
    private LocalDate localDate;
    public CollectionManager(){
        this.localDate = LocalDate.parse(LocalDate.now().toString());
    }


    public Vector<SpaceMarine> getMarines() {
        return this.marines;
    }


    /**
     * Метод, показывающий сводку по командам
     */
    public void help() {
        System.out.println("Доступные команды:\n" +
                "show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "info: вывести в стандартный поток вывода информацию о коллекции\n" +
                "add {element}: добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "insert_at index {element} : добавить новый элемент в заданную позицию\n" +
                "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                "sort : отсортировать коллекцию в естественном порядке\n" +
                "max_by_chapter : вывести любой объект из коллекции, значение поля chapter которого является максимальным\n" +
                "count_less_than_melee_weapon meleeWeapon : вывести количество элементов, значение поля meleeWeapon которых меньше заданного\n" +
                "print_field_ascending_category : вывести значения поля category всех элементов в порядке возрастания\n");
    }


    public void addElements(Collection<SpaceMarine> collection) throws InvalidDataException {
        if (collection == null) return;
        for (SpaceMarine spaceMarine : collection) {
            add(spaceMarine);
        }
    }

    /**
     * @param spaceMarine объект, который будет добавлен в коллекцию
     * @return проверяет наличие одинакового id элементов коллекции и нового объекта перед добавлением
     */
    private boolean CheckId(SpaceMarine spaceMarine){
        for(int i =0 ; i < marines.size(); i++){
            if (spaceMarine.getId().equals(marines.get(i).getId())) return false;
        }
        return true;
    }

    /**
     * @return Проверяет наличие одинаковых id в коллекции для правильного чтения из файла
     */
    public boolean CheckSameId() {
        for (int i = 0; i < marines.size() - 1; i++) {
            if (marines.get(i).getId() == marines.get(i + 1).getId()) return false;
        }
        return true;
    }
    public LocalDate getlocalDate(){
        return this.localDate;
    }

    public void setLocalDate(LocalDate localDate){
        this.localDate=localDate;
    }

    /**
     * @throws EmptyCollectionException отсутствие элементов в коллекции
     * Метод, выводящий содержимое коллекции
     */
    public void show() throws EmptyCollectionException {
        if (!marines.isEmpty()) {
            System.out.println("Содержимое коллекции: \n");
            for (SpaceMarine i : marines) {
                {
                    System.out.println(i);
                    System.out.println("\n");
                }

            }
        } else {
            throw new EmptyCollectionException();
        }
    }


    /**
     * @param id id элемента
     * @param spaceMarine объект, на который будет заменен старый элемент
     * @throws InvalidDataException неверно введенные данные
     * @throws NoSuchElementException отсутствие элемента в коллекции
     */
    public void updateByID(int id,  SpaceMarine spaceMarine) throws InvalidDataException, NoSuchElementException {
        SpaceMarine oldElement = getById(id);
        marines.remove(oldElement);
        spaceMarine.setId((long) id);
        marines.add(spaceMarine);
    }


    /**
     * Метод, добавляющий новый элемент в коллекцию
     * @param spaceMarine объект, который будет добавлен в коллекцию
     * @throws InvalidDataException неверно введенные данные
     */
    public void add(SpaceMarine spaceMarine) throws InvalidDataException {
        if (!(spaceMarine.validate())) throw new InvalidDataException();
        if(!CheckId(spaceMarine)){
            spaceMarine.setId(SpaceMarine.generateId());
        }
        marines.add(spaceMarine);
    }


    /**
     * Позволяет найти объект, id которого равен введенному
     * @param id id, вводимый пользователем
     * @return объект, id которого равен введенному
     */



    public SpaceMarine getById(int id) {
        for (SpaceMarine spaceMarine1 : marines) {
            if (id == spaceMarine1.getId()) {
                return spaceMarine1;
            }
        }
        return null;
    }


    /**
     * Удалит элемент по его id
     * @param spaceMarine объект, который будет удален
     * @throws NoSuchElementException отсутствие элемента в коллекции
     */
    public void removeElement(SpaceMarine spaceMarine) throws NoSuchElementException {
        if (marines.contains(spaceMarine)) {
            marines.remove(spaceMarine);
        } else {
            throw new NoSuchElementException();
        }
    }


    /**
     * Очистка коллекции
     * @throws AlreadyEmptyException коллекция уже пуста
     */
    public void clear() throws AlreadyEmptyException {
        marines.clear();
    }


    /**
     * @return возвращает наименьшее поле marinesCount среди элементов коллекции
     * @throws EmptyCollectionException коллекция пуста
     */
    private int findMinMarinesCount() throws EmptyCollectionException {
        if (marines.size() >= 2) {
            for (int i = 0; i < marines.size()-1; i++) {
                if (marines.get(i).getMarinesCount() <= marines.get(i + 1).getMarinesCount()) {
                    return marines.get(i).getMarinesCount();
                }
            }

        } else if (marines.size()== 1) {
            return marines.get(0).getMarinesCount();
        } else {
            throw new EmptyCollectionException();
        }

        return 0;
    }

    /**
     * Добавит новый элемент в коллекцию, если его поле минимально
     * @param spaceMarine объект, добавляемый в коллекцию
     * @throws InvalidDataException неверно введенные данные
     * @throws EmptyCollectionException коллекция пуста
     */
    public void add_if_min(SpaceMarine spaceMarine) throws InvalidDataException, EmptyCollectionException {
        if (!marines.isEmpty()) {
            if (spaceMarine.getMarinesCount() < findMinMarinesCount()) {
                if (spaceMarine.validate()) {
                    add(spaceMarine);
                    System.out.println("Объект успешно добавлен!");
                } else {
                    throw new InvalidDataException();
                }
            } else {
                System.out.println("Не удалось добавить элемент, так как его поле не меньше, чем наименьшее поле у других объектов ");
            }
        } else {
            throw new EmptyCollectionException();
        }
    }

    /**
     * Естественная сортировка коллекции
     */
    public void sort() {
        Collections.sort(marines);
    }


    /**
     * Выведет объект, у которого поле chapter максимально
     * @throws EmptyCollectionException коллекция пуста
     */
    public void max_by_chapter() throws EmptyCollectionException {
        if (!marines.isEmpty()) {
            for (int i = 0; i < marines.size() - 1; i++) {
                if (marines.get(i).getChapter().getMarinesCount() > marines.get(i + 1).getChapter().getMarinesCount()) {
                    System.out.println(marines.get(i));
                }
            }

        } else {
            throw new EmptyCollectionException();
        }
    }

    /**
     * Вставит новый элемент на заданную позицию
     * @param id Позиция, в которую будет вставлен новый объект
     * @param spaceMarine новый объект
     * @throws EmptyCollectionException коллекция пуста
     */
    public void insertAtIndex(int id, SpaceMarine spaceMarine) throws EmptyCollectionException {
        if (marines.size() > id) {
            marines.setElementAt(spaceMarine, id);
        } else {
            marines.setSize(id + 1);
            marines.setElementAt(spaceMarine, id);
        }
    }


    /**
     * Выведет количество элементов, значение meleeweapon у которых меньше заданного
     * @param meleeWeapon енам
     * @throws IllegalArgumentException неверно введенные аргументы
     * @throws EmptyCollectionException коллекция пуста
     */
    public void countByMeleeWeapon(MeleeWeapon meleeWeapon) throws IllegalArgumentException, EmptyCollectionException {
        if (!marines.isEmpty()) {
            int count = 0;
            for (int i = 0; i < marines.size(); i++) {
                if (marines.get(i).getMeleeWeapon().ordinal() < meleeWeapon.ordinal()) {
                    count++;
                }
            }
            System.out.println("Количество объектов, поле meleeweapon у которых меньше заданного: " + count);
        }
        else {
            throw new EmptyCollectionException();
        }
    }


    /**
     * Выведет значение полей category в порядке возрастания
     * @throws EmptyCollectionException коллекция пуста
     */
    public void printFieldAscendingCategory() throws EmptyCollectionException {
        ArrayList<AstartesCategory> list = new ArrayList<>();
        for(SpaceMarine element : marines){
            list.add(element.getCategory());
        }
        Collections.sort(list);
        System.out.print("Значение полей category в порядке возрастания: ");
        for(AstartesCategory element : list){
            System.out.print(element.getLvl() + " ");
        }

    }

    /**
     * Получить информацию о коллекции
     */
    public void getInfo() {
        System.out.println("Stored type: " + SpaceMarine.class +
                "\nNumber of SpaceMarines stored: " + marines.size() +
                "\nDate of creation: " + localDate + "\n");
    }


    public SpaceMarine getLast() {
        return marines.lastElement();
    }

    public String toString() {
        if (marines.isEmpty()) return "Коллекция пуста!";
        var last = getLast();
        StringBuilder info = new StringBuilder();
        for (SpaceMarine spaceMarine1 : marines) {
            info.append(localDate);
            info.append(spaceMarine1);
            if (spaceMarine1 != last) {
                info.append("\n\n");
            }
        }
        return info.toString();
    }


}




