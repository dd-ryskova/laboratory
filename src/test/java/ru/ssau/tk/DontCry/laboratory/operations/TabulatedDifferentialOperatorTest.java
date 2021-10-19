package ru.ssau.tk.DontCry.laboratory.operations;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.ssau.tk.DontCry.laboratory.functions.ArrayTabulatedFunction;
import ru.ssau.tk.DontCry.laboratory.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.DontCry.laboratory.functions.TabulatedFunction;
import ru.ssau.tk.DontCry.laboratory.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.DontCry.laboratory.functions.factory.LinkedListTabulatedFunctionFactory;

import static java.lang.Math.pow;
import static org.testng.Assert.*;

public class TabulatedDifferentialOperatorTest {

    public TabulatedFunction linkedList = new LinkedListTabulatedFunction(new double[]{1., 2., 3., 4., 5.}, new double[]{2., 3., 4., 5., 6.});
    public TabulatedDifferentialOperator differentialListOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());

    public TabulatedFunction array = new ArrayTabulatedFunction(new double[]{1., 2., 3., 4., 5., 6.}, new double[]{1., 4., 9, 16., 25., 36.});
    public TabulatedDifferentialOperator differentialArrayOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());

    public TabulatedDifferentialOperator factory = new TabulatedDifferentialOperator();

    @Test
    public void testDeriveLinkedList() {
        for (int i = 0; i < linkedList.getCount(); ++i) {
            assertEquals(linkedList.getX(i), (double) i + 1);
        }

        for (int i = 0; i < linkedList.getCount(); ++i) {
            assertEquals(linkedList.getY(i), (double) i + 2);
        }

        linkedList = differentialListOperator.derive(linkedList);

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

        assertEquals(array.getY(0), 3.);
        assertEquals(array.getY(1), 5.);
        assertEquals(array.getY(2), 7.);
        assertEquals(array.getY(3), 9.);
        assertEquals(array.getY(4), 11.);
        assertEquals(array.getY(5), 11.);
    }

    @Test
    public void testGetFactory() {
        assertTrue(factory.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory()).getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        factory.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(factory.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("TabulatedDifferentialOperator checked");
    }
}
