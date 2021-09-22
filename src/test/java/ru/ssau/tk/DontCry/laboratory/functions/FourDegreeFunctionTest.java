package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FourDegreeFunctionTest {

    private final static double DELTA = 0.0001;

    @Test
    public void testApply() {
        assertEquals(new FourDegreeFunction().apply(2.), 16., DELTA);
        assertEquals(new FourDegreeFunction().apply(3.), 81., DELTA);
        assertEquals(new FourDegreeFunction().apply(4.), 256., DELTA);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("FourDegreeFunction checked");
    }
}
