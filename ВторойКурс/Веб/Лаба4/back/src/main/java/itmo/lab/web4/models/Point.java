package itmo.lab.web4.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Entity
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private double x;

    private double y;

    private double r;

    @Getter
    @Setter
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;




    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public Point() {}

    public void setUser(User user) {
        this.user = user;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }

    @Override
    public String toString(){
        return "x: %f, y: %f, r: %f".formatted(this.x,this.y,this.r);
    }
}
