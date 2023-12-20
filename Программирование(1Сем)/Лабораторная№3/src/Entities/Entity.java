package Entities;


import java.util.Objects;

public abstract class Entity{
    protected String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Entity() {
    }
    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.getName();
    }

}
