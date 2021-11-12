package ru.ssau.tk.DontCry.laboratory.concurrent;

import ru.ssau.tk.DontCry.laboratory.functions.TabulatedFunction;

public class ReadWriteTask implements Runnable {

    private final TabulatedFunction tabulatedFunction;

    public ReadWriteTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        double x;
        double y;
        for (int i = 0; i < tabulatedFunction.getCount(); ++i) {
            x = tabulatedFunction.getX(i);
            y = tabulatedFunction.getY(i);
            System.out.printf("%s, before write: i = %d, x = %f, y = %f", tabulatedFunction.getClass(), i, x, y);
            tabulatedFunction.setY(i, y + 1);
            y = tabulatedFunction.getY(i);
            System.out.printf("%s, after write: i = %d, x = %f, y = %f", tabulatedFunction.getClass(), i, x, y);
        }
    }
}
