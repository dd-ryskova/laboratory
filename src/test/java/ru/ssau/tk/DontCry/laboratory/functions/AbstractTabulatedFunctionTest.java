package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {

    private static final double DELTA = 0.0001;

    MockTabulatedFunction function = new MockTabulatedFunction();

    @Test
    public void testInterpolate() {
        assertEquals(function.interpolate(0.7, function.getX(0), function.getX(1), function.getY(0), function.getY(1)), 2.4823, DELTA);
        assertEquals(function.interpolate(2.5, 0.5, 3.5, 1.0, 2.0), 1.6666, DELTA);
        assertEquals(function.interpolate(3.6, -2.4, 4.2, -1.1, 5.7), 5.0818, DELTA);
        assertEquals(function.interpolate(-1.4, -3.7, 7.6, -5.9, 8.6), -2.9486, DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(function.apply(2.9), 5.4, DELTA);
        assertEquals(function.apply(5.7), 7.0411, DELTA);
        assertEquals(function.apply(-4.6), -2.35, DELTA);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("MockTabulatedFunctionTest checked");
    }
}