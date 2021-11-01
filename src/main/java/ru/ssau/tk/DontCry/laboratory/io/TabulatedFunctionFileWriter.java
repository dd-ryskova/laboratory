package ru.ssau.tk.DontCry.laboratory.io;

import ru.ssau.tk.DontCry.laboratory.functions.ArrayTabulatedFunction;
import ru.ssau.tk.DontCry.laboratory.functions.*;

import static ru.ssau.tk.DontCry.laboratory.io.FunctionsIO.*;

import java.io.*;

public class TabulatedFunctionFileWriter {

    public static void main(String[] args) {

        TabulatedFunction listFunction = new LinkedListTabulatedFunction(new double[]{1.2, 2.3, 3.6}, new double[]{4, 5, 6});
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(new double[]{1, 4, 6}, new double[]{16, 25, 36});

        try (BufferedWriter outArray = new BufferedWriter(new FileWriter("output/array function.txt"));
             BufferedWriter outList = new BufferedWriter(new FileWriter("output/linked list function.txt"))) {

            writeTabulatedFunction(outList, listFunction);
            writeTabulatedFunction(outArray, arrayFunction);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
