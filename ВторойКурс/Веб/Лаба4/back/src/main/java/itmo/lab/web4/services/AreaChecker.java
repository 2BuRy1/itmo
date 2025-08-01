package itmo.lab.web4.services;

import itmo.lab.web4.models.Point;
import org.springframework.stereotype.Service;

@Service
public class AreaChecker {





    public boolean isInTheSpot(Point point){
        return (checkIsCircle(point) || checkIsTriangle(point) || checkIsRectangle(point));
    }




    private boolean checkIsTriangle(Point point) {

        double equasion = -point.getX() / 2 - point.getR() / 2;

        return (point.getX() * point.getR() <= 0 &&
                point.getY() * point.getR() <= 0 &&
                point.getY() >= equasion
        );


    }


    private boolean checkIsCircle(Point point) {
        return (point.getX() * point.getR() >= 0 &&
                point.getY() * point.getR() <= 0 &&
                Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY()) <= point.getR() / 2
        );
    }


    private boolean checkIsRectangle(Point point) {
        return (point.getX() * point.getR() <= 0 &&
                point.getY() * point.getR() >= 0 &&
                point.getX() >= -point.getR() &&
                point.getY() <= point.getR() / 2);
    }


}
