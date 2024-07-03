package MainClasses;

import Managers.CollectionManager;
import Enums.AstartesCategory;
import Enums.MeleeWeapon;
import Enums.Weapon;
import Interfaces.Validatable;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.lang.Long.compare;

/**
 * Класс космического корабля
 */
public class SpaceMarine implements Validatable, Comparable<SpaceMarine> {
    private CollectionManager collectionManager;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate;
    private Double health; //Поле не может быть null, Значение поля должно быть больше 0
    private AstartesCategory category; //Поле не может быть null
    private Weapon weaponType; //Поле может быть null
    private MeleeWeapon meleeWeapon; //Поле может быть null
    private Chapter chapter; //Поле не может быть null

    public SpaceMarine(String name, Coordinates coordinates, LocalDateTime creationDate, Double health, AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.id = generateId();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.category = category;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;


    }


    /**
     * @return генерирует id для космического корабля
     */
    public static Long generateId() {
        Long id;
        id = (long) (Math.random() * 1000);
        return id;
    }


    /**
     * @param id позволяет вручную установить id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return (id != null ? "id: " + id : "") + (name != null ? "\nname: " + name : "") + (coordinates != null ? "\ncoordinates: " + coordinates : "") + (creationDate != null ? "\ncreationDate: " + creationDate : "") + (health != null ? "\nhealth: " + health : "") + (category != null ? "\ncategory: " + category : "") + (weaponType != null ? "\nweaponType: " + weaponType : "") + (meleeWeapon != null ? "\nmeleeWeapon: " + meleeWeapon : "") + (chapter != null ? "\nchapter: " + chapter : "");
    }
    /*
    sdadasdad
    */

    /**
     * @return метод, валидирующий все поля класса SpaceMarine
     */
    @Override
    public boolean validate() {
        if (this.id <= 0) return false;
        if (this.name == null || name.isEmpty()) return false;
        if (this.coordinates == null || !coordinates.validate()) return false;
        if (this.health == null || this.health <= 0) return false;
        if (this.chapter == null || !chapter.validate()) return false;
        return true;
    }

    /**
     * @return Возвращает значение поля id
     */
    public Long getId() {
        return this.id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceMarine that = (SpaceMarine) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, health, category, weaponType, meleeWeapon, chapter);
    }


    /**
     * @param o объект для сравнения.
     * @return сравнивает объекты по их id
     */
    @Override
    public int compareTo(SpaceMarine o) {
        if (o == null) return -1;
        else {
            return compare(this.id, o.getId());
        }
    }

    /**
     * @return Возвращает значение поля Chapter
     */
    public Chapter getChapter() {
        return this.chapter;
    }


    /**
     * @return Возвращает значение поля MeleeWeapon
     */
    public MeleeWeapon getMeleeWeapon() {
        return this.meleeWeapon;
    }

    /**
     * @return Возвращает значение поля Category
     */
    public AstartesCategory getCategory() {
        return this.category;
    }

    /**
     * @return Возвращает marinesCount из класса Chapter
     */
    public int getMarinesCount() {
        return this.chapter.getMarinesCount();

    }
}
