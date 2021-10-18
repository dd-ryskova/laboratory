package ru.ssau.tk.DontCry.laboratory.functions.factory;

import ru.ssau.tk.DontCry.laboratory.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}
