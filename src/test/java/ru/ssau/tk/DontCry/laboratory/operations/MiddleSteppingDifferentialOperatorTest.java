package ru.ssau.tk.DontCry.laboratory.operations;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.ssau.tk.DontCry.laboratory.functions.SqrFunction;

import static org.testng.Assert.assertEquals;

public class MiddleSteppingDifferentialOperatorTest {
    private final static double DELTA = 0.001;
    private final static double step = 0.01;

    @Test
    public void testDerive() {

        SteppingDifferentialOperator differentialOperator = new MiddleSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(3), 5.999, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 3.999, DELTA);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("MiddleSteppingDifferentialOperator checked");
    }
}
