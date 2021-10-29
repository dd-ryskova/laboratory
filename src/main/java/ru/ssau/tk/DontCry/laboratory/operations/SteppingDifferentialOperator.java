package ru.ssau.tk.DontCry.laboratory.operations;

import ru.ssau.tk.DontCry.laboratory.functions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {

    protected double step;

    public SteppingDifferentialOperator(double step) {
        if (step <= 0 || Double.isNaN(step) || Double.isInfinite(step)) {
            throw new IllegalArgumentException("Шаг не соответствует требованиям.");
        } else {
            this.step = step;
        }
    }

    public void setStep(double step) {
        this.step = step;
    }

    public double getStep() {
        return step;
    }
}
