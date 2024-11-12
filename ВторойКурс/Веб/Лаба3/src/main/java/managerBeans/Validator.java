package managerBeans;


import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;


@Named("validator")
@SessionScoped
public class Validator implements Serializable {


    private boolean validateX(double x){
        return (x >= -5 && x <= 5 );

    }

    private boolean validateY(double y){
        return (y >= -5 && y <= 3 );
    }

    private boolean validateR(double r){
        double[] arr = new double[]{1, 2, 3, 4, 5};
        for (double value : arr){
            if(value == r) return true;
        }
        return false;
    }

    public boolean validateForm(double x, double y, double r){
        return (validateX(x) && validateY(y) && validateR(r));
    }



}
