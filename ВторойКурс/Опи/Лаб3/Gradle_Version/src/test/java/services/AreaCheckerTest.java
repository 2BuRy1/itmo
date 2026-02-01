package services;

import itmo.lab.web4.models.Point;
import itmo.lab.web4.services.AreaChecker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AreaCheckerTest {

    private AreaChecker areaChecker;

    @Before
    public void setUp() {
        areaChecker = new AreaChecker();
    }


    @Test
    public void checkIfInTheSpot() {
        Point point = new Point();
        point.setX(-1);
        point.setY(0.5);
        point.setR(1);
        assertTrue(areaChecker.isInTheSpot(point));
    }


    @Test
    public void checkIfNotInTheSpot() {
        Point point = new Point();
        point.setX(-0.1);
        point.setY(2);
        point.setR(2);
        assertFalse(areaChecker.isInTheSpot(point));
    }
}