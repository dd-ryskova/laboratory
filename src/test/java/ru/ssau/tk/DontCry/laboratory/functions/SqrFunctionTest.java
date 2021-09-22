package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @Test
    void TestApply() {
        double DELTA = 0.0001;
        assertEquals(new SqrFunction().apply(3.), 9., DELTA);
        assertEquals(new SqrFunction().apply(2.), 4., DELTA);
        assertEquals(new SqrFunction().apply(6.), 36., DELTA);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("SqrFunction checked");
    }
}
