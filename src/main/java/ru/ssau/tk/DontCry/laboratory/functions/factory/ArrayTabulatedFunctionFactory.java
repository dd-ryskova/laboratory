package ru.ssau.tk.DontCry.laboratory.functions.factory;

import ru.ssau.tk.DontCry.laboratory.functions.TabulatedFunction;
import ru.ssau.tk.DontCry.laboratory.functions.ArrayTabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }
}
