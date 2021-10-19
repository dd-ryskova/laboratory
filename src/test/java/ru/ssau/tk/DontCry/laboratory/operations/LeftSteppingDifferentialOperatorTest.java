package ru.ssau.tk.DontCry.laboratory.operations;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.ssau.tk.DontCry.laboratory.functions.SqrFunction;


import static org.testng.Assert.*;

public class LeftSteppingDifferentialOperatorTest {

    private final static double DELTA = 0.001;
    private final static double step = 0.01;

    @Test
    public void testDerive() {

        SteppingDifferentialOperator differentialOperator = new LeftSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(3), 5.99, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 3.99, DELTA);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("LeftSteppingDifferentialOperator checked");
    }
}
