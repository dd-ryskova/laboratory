package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FourDegreeFunctionTest {

    @Test
    void apply() {
        assertEquals(new FourDegreeFunction().apply(2.), 16.);
        assertEquals(new FourDegreeFunction().apply(3.), 81.);
        assertEquals(new FourDegreeFunction().apply(4.), 256.);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("FourDegreeFunction checked");
    }
}
