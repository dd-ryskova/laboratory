package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @Test
    void TestApply() {
        assertEquals(new SqrFunction().apply(3.), 9.);
        assertEquals(new SqrFunction().apply(2.), 4.);
        assertEquals(new SqrFunction().apply(6.), 36.);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("SqrFunction checked");
    }
}
