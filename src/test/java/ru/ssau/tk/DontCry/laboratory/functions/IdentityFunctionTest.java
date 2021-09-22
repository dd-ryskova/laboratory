package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {

    @Test
    public void testApply() {
        assertEquals(new IdentityFunction().apply(3.), 3.);
        assertEquals(new IdentityFunction().apply(25.), 25.);
        assertEquals(new IdentityFunction().apply(456.), 456.);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("IdentityFunction checked");
    }
}

