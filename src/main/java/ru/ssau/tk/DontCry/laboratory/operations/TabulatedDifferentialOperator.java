package ru.ssau.tk.DontCry.laboratory.operations;

import ru.ssau.tk.DontCry.laboratory.functions.TabulatedFunction;
import ru.ssau.tk.DontCry.laboratory.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.DontCry.laboratory.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.DontCry.laboratory.functions.Point;

import static ru.ssau.tk.DontCry.laboratory.operations.TabulatedFunctionOperationService.asPoints;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {

    public TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = asPoints(function);
        double[] xValues = new double[points.length];
        double[] yValues = new double[points.length];

        for (int i = 0; i < points.length; i++) {
            xValues[i] = points[i].x;
        }
        for (int i = 0; i < points.length - 1; i++) {
            yValues[i] = (points[i + 1].y - points[i].y) / (xValues[i + 1] - xValues[i]);
        }
        yValues[yValues.length - 1] = yValues[yValues.length - 2];

        return factory.create(xValues, yValues);
    }
}
