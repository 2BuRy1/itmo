package services;

import itmo.lab.web4.models.Point;
import itmo.lab.web4.services.Validator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidatorTest {

    private Validator validator;

    @Before
    public void setUp() {
        validator = new Validator();
    }

    @Test
    public void testValidationSuccess() {
        Point point = new Point();
        point.setX(0);
        point.setY(0);
        point.setR(1);
        assertTrue(validator.validate(point));
    }

    @Test
    public void testValidationFailure() {
        Point point = new Point();
        point.setX(0);
        point.setY(0);
        point.setR(-3);
        assertFalse(validator.validate(point));
    }
}