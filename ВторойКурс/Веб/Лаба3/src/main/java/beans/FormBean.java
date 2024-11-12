package beans;



import jakarta.ejb.EJB;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import managerBeans.AreaChecker;
import managerBeans.DataBaseManager;
import managerBeans.Validator;
import models.Point;
import java.io.Serializable;


@Named("formBean")
@SessionScoped
public class FormBean {
    @Inject
    Validator validator;

    @Inject
    PointsContainer pointsContainer;

    @Inject
    AreaChecker areaChecker;


    @EJB
    DataBaseManager database;


    private double x;

    private double y;

    private double r;

    public void setValidator(Validator validator) {this.validator = validator;}

    public void setPointsContainer(PointsContainer pointsContainer) {this.pointsContainer = pointsContainer;}

    public void setAreaChecker(AreaChecker areaChecker) {this.areaChecker = areaChecker;}




    public double getX() {
        return x;
    }


    public double getY() {
        return y;
    }

    public double getR(){
        return r;
    }

    @Transactional
    public String submit(){
        if(validator.validateForm(x, y, r)) {
           Point point = new Point(x, y, r, areaChecker.isInTheSpot(x, y, r));
            database.insertIntoTable(point);
            pointsContainer.getPoints().add(0, point);
            System.out.println(point.isStatus());
        }
        return null;
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




}
