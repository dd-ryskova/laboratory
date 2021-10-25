package ru.ssau.tk.DontCry.laboratory.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.DontCry.laboratory.functions.ArrayTabulatedFunction;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionFactoryTest {

    public double[] xValues = {1, 2, 3, 4};
    public double[] yValues = {2, 4, 6, 6};

    public ArrayTabulatedFunctionFactory array = new ArrayTabulatedFunctionFactory();

    @Test
    public void testCreate() {
        assertTrue(array.create(xValues, yValues) instanceof ArrayTabulatedFunction);
        assertFalse(array.create(xValues, yValues) instanceof ArrayTabulatedFunctionFactory);
    }

}