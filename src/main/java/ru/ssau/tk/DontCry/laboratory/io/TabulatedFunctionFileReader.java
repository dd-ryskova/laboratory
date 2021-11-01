package ru.ssau.tk.DontCry.laboratory.io;

import ru.ssau.tk.DontCry.laboratory.functions.*;
import ru.ssau.tk.DontCry.laboratory.functions.factory.*;

import java.io.*;

public class TabulatedFunctionFileReader {

    public static void main(String[] args) {

        File myFile = new File("input/function.txt");

        try (BufferedReader inArray = new BufferedReader(new FileReader(myFile));
             BufferedReader inList = new BufferedReader(new FileReader(myFile))) {
            TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(inArray, new ArrayTabulatedFunctionFactory());
            System.out.println(arrayFunction.toString());

            TabulatedFunction listFunction = FunctionsIO.readTabulatedFunction(inList, new LinkedListTabulatedFunctionFactory());
            System.out.println(listFunction.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
