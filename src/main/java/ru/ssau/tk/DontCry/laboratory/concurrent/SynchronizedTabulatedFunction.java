package ru.ssau.tk.DontCry.laboratory.concurrent;

import ru.ssau.tk.DontCry.laboratory.functions.*;

import java.util.Iterator;

public abstract class SynchronizedTabulatedFunction implements TabulatedFunction {

    private final TabulatedFunction tabulatedFunction;
    private final Object object;

    public SynchronizedTabulatedFunction(TabulatedFunction tabulatedFunction, Object object) {
        this.tabulatedFunction = tabulatedFunction;
        this.object = object;
    }

    @Override
    public int getCount() {
        synchronized (object) {
            return tabulatedFunction.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (object) {
            return tabulatedFunction.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (object) {
            return tabulatedFunction.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (object) {
            tabulatedFunction.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (object) {
            return tabulatedFunction.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (object) {
            return tabulatedFunction.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (object) {
            return tabulatedFunction.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (object) {
            return tabulatedFunction.rightBound();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        return null;
    }

    @Override
    public double apply(double x) {
        synchronized (object) {
            return tabulatedFunction.apply(x);
        }
    }
}
