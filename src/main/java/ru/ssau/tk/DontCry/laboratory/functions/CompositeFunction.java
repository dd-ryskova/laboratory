package ru.ssau.tk.DontCry.laboratory.functions;

public class CompositeFunction implements MathFunction {

    private final MathFunction firstFunction;
    private final MathFunction secondFunction;

    public CompositeFunction(MathFunction firstFunction, MathFunction secondFunction) {
        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
    }

    @Override
    public double apply(double x) {
        return secondFunction.apply(firstFunction.apply(x)); //firstFunction - внутрення, secondFunction - внешняя
    }
}