package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {

    @Test
    public void testApply() {
        assertEquals(new UnitFunction().apply(100.0), 1.0);
        assertEquals(new UnitFunction().apply(10.0), 1.0);
        assertEquals(new UnitFunction().apply(9.0), 1.0);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("UnitFunction checked");
    }
}
