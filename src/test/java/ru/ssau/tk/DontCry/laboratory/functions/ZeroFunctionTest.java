package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {

    private final static double DELTA = 0.0001;

    @Test
    public void testApply() {
        assertEquals(new ZeroFunction().apply(100.0), 0.0, DELTA);
        assertEquals(new ZeroFunction().apply(90.0), 0.0, DELTA);
        assertEquals(new ZeroFunction().apply(667.0), 0.0, DELTA);
    }
    @AfterMethod
    void afterMethod() {
        System.out.println("ZeroFunction checked");
    }
}
