package managers;

import data.Dot;

import java.util.logging.Logger;

public class FunctionCalc {


   // final Logger logger = LoggerConfig.getLogger(FunctionCalc.class.getName());

    private boolean isTriangle(Dot dot) {

        double equation = -2 * dot.getX() + dot.getR();

        return (dot.getY() >= 0 && dot.getX() >= 0 && dot.getY() <= equation);
    }


    private boolean isCircle(Dot dot) {
        return dot.getX() * dot.getR() <= 0 && dot.getY() * dot.getR() >= 0 && Math.sqrt(dot.getX() * dot.getX() + dot.getY() * dot.getY()) <= dot.getR()/2;


    }


    private boolean isRectangle(Dot dot) {
        return dot.getX() * dot.getR() <= 0 && dot.getY() * dot.getR() <= 0 && dot.getY() >= -dot.getR()/2 && dot.getX() >= -dot.getR();

    }


    public boolean isInTheSpot(Dot dot) throws Exception {
        if(dot != null) {
            if (!checkY(dot) || !checkR(dot) || !checkX(dot)) {
                return false;
            }
            if (isCircle(dot) || isTriangle(dot) || isRectangle(dot)) {
                //logger.info("Returned true");
                return true;
            }

            //logger.warning("Returned false : x=%d, y=%f, r=%d".formatted(dot.getX(), dot.getY(), dot.getR()));
            return false;
        }
        throw new Exception("Invalid JSON data");
    }


    private boolean checkY(Dot dot) throws Exception {
        if (dot.getY() <= 5 && dot.getY() >= -3) {
            return true;
        }

        throw new Exception("Invalid value");
    }

    private boolean checkR(Dot dot) throws Exception {
        if(dot.getR() >=1 && dot.getR() <=5){
            return true;
        }
        System.out.println("something wrong with r");
        throw new Exception("Invalid value");
    }


    private boolean checkX(Dot dot) throws Exception {
        if(dot.getX() >= -3 && dot.getX() <= 5) {
            return true;
        }
        throw new Exception("Invalid value");

    }


}