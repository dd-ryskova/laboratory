package ru.ssau.tk.DontCry.laboratory.operations;

import ru.ssau.tk.DontCry.laboratory.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);
}
