package ru.ssau.tk.DontCry.laboratory.functions;

public class FourDegreeFunction implements MathFunction {

    @Override
    public double apply(double x) {
        return Math.pow(x, 4);
    }
}
