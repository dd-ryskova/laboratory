package ru.ssau.tk.DontCry.laboratory.functions;

import ru.ssau.tk.DontCry.laboratory.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.DontCry.laboratory.exceptions.DifferentLengthOfArraysException;

import java.io.Serializable;

public abstract class AbstractTabulatedFunction implements TabulatedFunction, Serializable {

    private static final long serialVersionUID = 5293658674165082760L;

    public abstract int floorIndexOfX(double x);

    public abstract double extrapolateLeft(double x);

    public abstract double extrapolateRight(double x);

    public abstract double interpolate(double x, int floorIndex);

    public double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return (leftY + (x - leftX) * (rightY - leftY) / (rightX - leftX));
    }

    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        }
        return interpolate(x, floorIndexOfX(x));
    }

    public static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("Длины массивов не совпадают!");
        }
    }

    public static void checkSorted(double[] xValues) {
        for (int i = 0; i < xValues.length - 1; i++) {
            if (xValues[i + 1] < xValues[i]) {
                throw new ArrayIsNotSortedException("Элементы массива xValues не сортированы!");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(getClass().getSimpleName()).append(" size = ").append(this.getCount()).append("\n");
        for (Point point : this) {
            string.append("[").append(point.x).append("; ").append(point.y).append("]\n");
        }
        return string.toString();
    }
}
