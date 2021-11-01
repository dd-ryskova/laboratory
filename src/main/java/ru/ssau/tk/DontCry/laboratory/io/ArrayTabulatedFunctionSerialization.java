package ru.ssau.tk.DontCry.laboratory.io;

import ru.ssau.tk.DontCry.laboratory.functions.*;
import ru.ssau.tk.DontCry.laboratory.functions.factory.*;
import ru.ssau.tk.DontCry.laboratory.operations.*;

import static ru.ssau.tk.DontCry.laboratory.io.FunctionsIO.*;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {

    public static void main(String[] args) {

        File fileOfArray = new File("output/serialized array functions.bin");

        double[] x = {-5, -3, -1, 0, 1, 3, 5};
        double[] y = {-6, -4, -2, 0, 2, 4, 6};

        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(x, y);
        TabulatedFunction arrayDifferentialFunctionFirst = differentialOperator.derive(arrayFunction);
        TabulatedFunction arrayDifferentialFunctionSecond = differentialOperator.derive(arrayDifferentialFunctionFirst);

        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileOfArray));
             BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileOfArray))) {
            serialize(out, arrayFunction);
            serialize(out, arrayDifferentialFunctionFirst);
            serialize(out, arrayDifferentialFunctionSecond);

            TabulatedFunction resultDifferential = deserialize(in);
            TabulatedFunction resultDifferentialFirst = FunctionsIO.deserialize(in);
            TabulatedFunction resultDifferentialSecond = FunctionsIO.deserialize(in);

            System.out.println(resultDifferential.toString());
            System.out.println(resultDifferentialFirst.toString());
            System.out.println(resultDifferentialSecond.toString());

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
