package ru.ssau.tk.DontCry.laboratory.io;

import ru.ssau.tk.DontCry.laboratory.functions.*;
import ru.ssau.tk.DontCry.laboratory.functions.factory.*;
import ru.ssau.tk.DontCry.laboratory.operations.*;

import java.io.*;

import static ru.ssau.tk.DontCry.laboratory.io.FunctionsIO.readTabulatedFunction;

public class TabulatedFunctionFileInputStream {

    public static void main(String[] args) {

        File inFile = new File("input/binary function.bin");

        LinkedListTabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        ArrayTabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(inFile))) {
            TabulatedFunction arrayFunction = readTabulatedFunction(bufferedInputStream, arrayFactory);
            System.out.println(arrayFunction.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите размер и значения функции");
            TabulatedFunction linkedListFunction = readTabulatedFunction(bufferedReader, linkedListFactory);
            TabulatedDifferentialOperator differentialLinkedList = new TabulatedDifferentialOperator(linkedListFactory);
            System.out.println(differentialLinkedList.derive(linkedListFunction).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
