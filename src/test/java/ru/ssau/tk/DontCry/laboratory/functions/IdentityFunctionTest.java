package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {
    private final double DELTA = 0.0001;
    @Test
    public void testApply() {
        assertEquals(new IdentityFunction().apply(3.), 3., DELTA);
        assertEquals(new IdentityFunction().apply(25.), 25., DELTA);
        assertEquals(new IdentityFunction().apply(456.), 456.,DELTA);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("IdentityFunction checked");
    }
}

