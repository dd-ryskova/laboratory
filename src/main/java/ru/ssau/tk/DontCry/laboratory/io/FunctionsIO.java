package ru.ssau.tk.DontCry.laboratory.io;

import ru.ssau.tk.DontCry.laboratory.functions.Point;
import ru.ssau.tk.DontCry.laboratory.functions.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.PrintWriter;

final public class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());
        for (Point temp : function) {
            printWriter.printf("%f %f\n", temp.x, temp.y);
        }
        printWriter.flush();
    }
}
