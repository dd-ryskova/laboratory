package ru.ssau.tk.DontCry.laboratory.io;

import ru.ssau.tk.DontCry.laboratory.functions.*;
import ru.ssau.tk.DontCry.laboratory.functions.factory.*;
import ru.ssau.tk.DontCry.laboratory.operations.*;

import java.io.*;

import static ru.ssau.tk.DontCry.laboratory.io.FunctionsIO.*;

public class LinkedListTabulatedFunctionSerialization {

    public static void main(String[] args) {

        File outFile = new File("output/serialized linked list functions.bin");

        double[] x = {1, 2, 3, 4};
        double[] y = {1, 4, 9, 16};

        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        LinkedListTabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(x, y);
        TabulatedFunction linkedListDifferentialFirstFunction = differentialOperator.derive(linkedListFunction);
        TabulatedFunction linkedListDifferentialSecondFunction = differentialOperator.derive(linkedListDifferentialFirstFunction);

        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFile));
             BufferedInputStream in = new BufferedInputStream(new FileInputStream(outFile))) {

            serialize(out, linkedListFunction);
            serialize(out, linkedListDifferentialFirstFunction);
            serialize(out, linkedListDifferentialSecondFunction);

            TabulatedFunction resultLinkedList = deserialize(in);
            TabulatedFunction resultDifferentialFirst = deserialize(in);
            TabulatedFunction resultDifferentialSecond = deserialize(in);

            System.out.println(resultLinkedList.toString());
            System.out.println(resultDifferentialFirst.toString());
            System.out.println(resultDifferentialSecond.toString());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
