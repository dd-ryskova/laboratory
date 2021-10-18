package ru.ssau.tk.DontCry.laboratory.operations;

import ru.ssau.tk.DontCry.laboratory.functions.Point;
import ru.ssau.tk.DontCry.laboratory.functions.TabulatedFunction;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] points = new Point[tabulatedFunction.getCount()];
        for (Point myPoint : tabulatedFunction) {
            points[i++] = myPoint;
        }
        return points;
    }
}
