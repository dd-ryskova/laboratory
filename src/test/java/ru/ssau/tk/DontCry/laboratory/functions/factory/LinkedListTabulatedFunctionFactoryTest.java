package ru.ssau.tk.DontCry.laboratory.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.DontCry.laboratory.functions.LinkedListTabulatedFunction;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionFactoryTest {

    public double[] xValues = {1, 2, 3, 4};
    public double[] yValues = {2, 4, 6, 6};

    LinkedListTabulatedFunctionFactory array = new LinkedListTabulatedFunctionFactory();

    @Test
    public void testCreate() {
        assertTrue(array.create(xValues, yValues) instanceof LinkedListTabulatedFunction);
        assertFalse(array.create(xValues, yValues) instanceof LinkedListTabulatedFunctionFactory);
    }
}