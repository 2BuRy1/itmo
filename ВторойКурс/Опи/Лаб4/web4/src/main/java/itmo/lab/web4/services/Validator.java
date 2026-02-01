package itmo.lab.web4.services;



import org.springframework.stereotype.Component;

import itmo.lab.web4.models.Point;

@Component
public class Validator {


    public boolean validate(Point point) {

        return (validateX(point) && validateY(point) && validateR(point));

    }


    private boolean validateX(Point point) {

        return point.getX() <=2 && point.getX() >=-2;
    }


    private boolean validateY(Point point) {
        return (point.getY() >= -5 && point.getY() <= 3);
    }

    private boolean validateR(Point point) {
        double[] arrayOfR = new double[]{1, 2, 3, 4, 5};
        for (double element : arrayOfR) {
            if (point.getR() == element) return true;
        }
        return false;
    }



}
