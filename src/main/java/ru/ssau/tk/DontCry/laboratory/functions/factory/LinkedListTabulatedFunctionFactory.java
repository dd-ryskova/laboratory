package ru.ssau.tk.DontCry.laboratory.functions.factory;

import ru.ssau.tk.DontCry.laboratory.functions.TabulatedFunction;
import ru.ssau.tk.DontCry.laboratory.functions.LinkedListTabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}
