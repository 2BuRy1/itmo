package itmo.lab.web4.services;


import itmo.lab.web4.models.Point;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Component
public class Validator {

@Autowired
private Logger logger;
    public boolean validate(Point point) throws BadRequestException {

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
