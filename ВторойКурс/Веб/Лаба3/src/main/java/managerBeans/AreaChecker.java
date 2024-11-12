package managerBeans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;


@Named("checker")
@SessionScoped
public class AreaChecker implements Serializable {

    private boolean checkIsTriangle(double x, double y, double r) {
        double equation = -x  + r;
        return (x * r >=0 && y * r >=0 && y <=equation );
    }

    private boolean checkIsRectangle(double x, double y, double r){

        return (x <= 0 && y <=0 && y>=-r && x >= -r);

    }


    private boolean checkIsCircle(double x, double y, double r){

        return (x * r >= 0 && y * r <= 0 && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) <= r/2);
    }


    public boolean isInTheSpot(double x, double y, double r) {
        return checkIsRectangle(x, y, r) || checkIsTriangle(x, y, r) || checkIsCircle(x, y, r);
    }


}
