package MainClasses;

import Enums.AstartesCategory;
import Enums.MeleeWeapon;
import Enums.Weapon;
import Interfaces.Validatable;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.lang.Long.compare;

/**
 * Класс космического корабля
 */
public class SpaceMarine implements Validatable, Comparable<SpaceMarine>, Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate;
    private Double health; //Поле не может быть null, Значение поля должно быть больше 0
    private AstartesCategory category; //Поле не может быть null
    private Weapon weaponType; //Поле может быть null
    private MeleeWeapon meleeWeapon; //Поле может быть null
    private Chapter chapter; //Поле не может быть null
    private String userLogin;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getX() {
        return coordinates.getX();
    }

    public long getY(){
        return this.coordinates.getY();
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCreationDate() {
        return creationDate.toString();
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Double getHealth() {
        return health;
    }

    public void setHealth(Double health) {
        this.health = health;
    }

    public void setCategory(AstartesCategory category) {
        this.category = category;
    }

    public String getWeaponType() {
        return weaponType.toString();
    }

    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }

    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public SpaceMarine(String name, Coordinates coordinates, LocalDateTime creationDate, Double health, AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter, String userLogin) {
        this.id = generateId();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.category = category;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
        this.userLogin = userLogin;


    }

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
        return (id != null ? "id: " + id : "") + (name != null ? "\nname: " + name : "") + (coordinates != null ? "\ncoordinates: " + coordinates : "") + (creationDate != null ? "\ncreationDate: " + creationDate : "") + (health != null ? "\nhealth: " + health : "") + (category != null ? "\ncategory: " + category : "") + (weaponType != null ? "\nweaponType: " + weaponType : "") + (meleeWeapon != null ? "\nmeleeWeapon: " + meleeWeapon : "") + (chapter != null ? "\nchapter: " + chapter : "" ) + "\nuserlogin: " + userLogin;
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
    public String getMeleeNameWeapon() {
        return this.meleeWeapon.toString();
    }

    public MeleeWeapon getMeleeWeapon(){
        return this.meleeWeapon;
    }

    /**
     * @return Возвращает значение поля Category
     */
    public String getCategory() {
        return this.category.toString();
    }

    /**
     * @return Возвращает marinesCount из класса Chapter
     */
    public int getMarinesCount() {
        return this.chapter.getMarinesCount();

    }
}
