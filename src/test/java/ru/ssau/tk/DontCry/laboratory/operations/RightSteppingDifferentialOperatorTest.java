package ru.ssau.tk.DontCry.laboratory.operations;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.ssau.tk.DontCry.laboratory.functions.SqrFunction;

import static org.testng.Assert.assertEquals;

public class RightSteppingDifferentialOperatorTest {
    private final static double DELTA = 0.001;
    private final static double step = 0.01;

    @Test
    public void testDerive() {

        SteppingDifferentialOperator differentialOperator = new RightSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(3), 6.01, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 4.01, DELTA);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("RightSteppingDifferentialOperator checked");
    }
}
