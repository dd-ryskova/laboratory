package ru.ssau.tk.DontCry.laboratory.io;

import ru.ssau.tk.DontCry.laboratory.functions.*;

import java.io.*;

import static ru.ssau.tk.DontCry.laboratory.io.FunctionsIO.*;

public class TabulatedFunctionFileOutputStream {

    public static void main(String[] args) {
        File fileOfArray = new File("output/array function.bin");
        File fileOfLinkedList = new File("output/linked list function.bin");

        TabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new double[]{1., 3., 5.}, new double[]{2., 4., 6.});
        TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(new double[]{1., 2., 3.}, new double[]{10., 20., 30.});

        try (BufferedOutputStream outArray = new BufferedOutputStream(new FileOutputStream(fileOfArray));
             BufferedOutputStream outLinkedList = new BufferedOutputStream(new FileOutputStream(fileOfLinkedList))) {
            writeTabulatedFunction(outArray, arrayTabulatedFunction);
            writeTabulatedFunction(outLinkedList, linkedListTabulatedFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}