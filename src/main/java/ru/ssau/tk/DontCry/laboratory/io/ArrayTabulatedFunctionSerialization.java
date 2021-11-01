package ru.ssau.tk.DontCry.laboratory.io;

import ru.ssau.tk.DontCry.laboratory.functions.ArrayTabulatedFunction;
import ru.ssau.tk.DontCry.laboratory.functions.TabulatedFunction;
import ru.ssau.tk.DontCry.laboratory.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.DontCry.laboratory.operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        File fileArray = new File("output/serialized array functions.bin");
        double[] x = {-5, -3, -1, 0, 1, 3, 5};
        double[] y = {-6, -4, -2, 0, 2, 4, 6};
        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(x, y);
        TabulatedFunction arrayFunction1 = differentialOperator.derive(arrayFunction);
        TabulatedFunction arrayFunction2 = differentialOperator.derive(arrayFunction1);

        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileArray));
             BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileArray))) {
            FunctionsIO.serialize(out, arrayFunction);
            FunctionsIO.serialize(out, arrayFunction1);
            FunctionsIO.serialize(out, arrayFunction2);

            TabulatedFunction resultArray = FunctionsIO.deserialize(in);
            TabulatedFunction resultArray1 = FunctionsIO.deserialize(in);
            TabulatedFunction resultArray2 = FunctionsIO.deserialize(in);

            System.out.println(resultArray.toString());
            System.out.println(resultArray1.toString());
            System.out.println(resultArray2.toString());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
