package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {

    @Test
    public void testGetNum() {
        assertEquals(new ConstantFunction(0.0).getNum(), 0.0);
        assertEquals(new ConstantFunction(Double.NEGATIVE_INFINITY).getNum(), Double.NEGATIVE_INFINITY);
        assertEquals(new ConstantFunction(-1.0).getNum(), -1.0);
    }

    @Test
    public void testApply() {
        assertEquals(new ConstantFunction(1.0).apply(5.0), 1.0);
        assertEquals(new ConstantFunction(30.0).apply(0.0), 30.0);
        assertEquals(new ConstantFunction(20.0).apply(30.0), 20.0);

    }

    @AfterMethod
    void afterMethod() {
        System.out.println("ConstantFunction checked");
    }
}