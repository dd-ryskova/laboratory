package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinearFunctionTest {

    private final static double DELTA = 0.0001;

    @Test
    public void testApply() {
        assertEquals(new LinearFunction().apply(1.), 5., DELTA);
        assertEquals(new LinearFunction().apply(-2.), -1., DELTA);
        assertEquals(new LinearFunction().apply(100.), 203., DELTA);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("LinearFunction checked");
    }
}
