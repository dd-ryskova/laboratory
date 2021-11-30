package ru.ssau.tk.DontCry.laboratory.operations;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.ssau.tk.DontCry.laboratory.functions.*;
import ru.ssau.tk.DontCry.laboratory.functions.factory.*;

import static java.lang.Math.pow;
import static org.testng.Assert.*;

public class TabulatedDifferentialOperatorTest {

    private TabulatedFunction linkedList = new LinkedListTabulatedFunction(new double[]{1., 2., 3., 4., 5.}, new double[]{2., 3., 4., 5., 6.});
    private final TabulatedDifferentialOperator differentialListOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());

    private TabulatedFunction array = new ArrayTabulatedFunction(new double[]{1., 2., 3., 4., 5., 6.}, new double[]{1., 4., 9, 16., 25., 36.});
    private final TabulatedDifferentialOperator differentialArrayOperator = new TabulatedDifferentialOperator();

    @Test
    public void testDeriveLinkedList() {
        for (int i = 0; i < linkedList.getCount(); ++i) {
            assertEquals(linkedList.getX(i), (double) i + 1);
        }

        for (int i = 0; i < linkedList.getCount(); ++i) {
            assertEquals(linkedList.getY(i), (double) i + 2);
        }

        linkedList = differentialListOperator.derive(linkedList);
        assertTrue(linkedList instanceof LinkedListTabulatedFunction);

        for (int i = 0; i < linkedList.getCount(); ++i) {
            assertEquals(linkedList.getY(i), (double) 1);
        }
    }

    @Test
    public void testDeriveArray() {
        for (int i = 0; i < array.getCount(); ++i) {
            assertEquals(array.getX(i), (double) i + 1);
        }

        for (int i = 0; i < array.getCount(); ++i) {
            assertEquals(array.getY(i), pow(array.getX(i), 2));
        }

        array = differentialArrayOperator.derive(array);
        assertTrue(array instanceof ArrayTabulatedFunction);

        assertEquals(array.getY(0), 3.);
        assertEquals(array.getY(1), 5.);
        assertEquals(array.getY(2), 7.);
        assertEquals(array.getY(3), 9.);
        assertEquals(array.getY(4), 11.);
        assertEquals(array.getY(5), 11.);
    }

    @Test
    public void testDeriveSynchronously() {
        TabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new double[]{2., 3., 4., 5., 6.}, new double[]{4., 9., 16., 25., 37.});
        TabulatedDifferentialOperator differentialOperatorList = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction differentialFunctionList = differentialOperatorList.deriveSynchronously(linkedListTabulatedFunction);

        assertTrue(differentialFunctionList instanceof LinkedListTabulatedFunction);

        for (int i = 0; i < differentialFunctionList.getCount(); i++) {
            assertEquals(differentialFunctionList.getX(i), (2. + (double) i));
        }

        assertEquals(differentialFunctionList.getY(0), 5.);
        assertEquals(differentialFunctionList.getY(1), 7.);
        assertEquals(differentialFunctionList.getY(2), 9.);
        assertEquals(differentialFunctionList.getY(3), 12.);
        assertEquals(differentialFunctionList.getY(4), 12.);

        TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(new double[]{4., 5., 6., 7., 8.}, new double[]{16., 25., 36., 49., 64.});
        TabulatedDifferentialOperator differentialOperatorArray = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction differentialFunctionArray = differentialOperatorArray.deriveSynchronously(arrayTabulatedFunction);
        assertTrue(differentialFunctionArray instanceof ArrayTabulatedFunction);

        for (int i = 0; i < differentialFunctionArray.getCount(); i++) {
            assertEquals(differentialFunctionArray.getX(i), (4. + (double) i));
        }

        assertEquals(differentialFunctionArray.getY(0), 9.);
        assertEquals(differentialFunctionArray.getY(1), 11.);
        assertEquals(differentialFunctionArray.getY(2), 13.);
        assertEquals(differentialFunctionArray.getY(3), 15.);
        assertEquals(differentialFunctionArray.getY(4), 15.);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("TabulatedDifferentialOperator checked");
    }
}

