package ru.ssau.tk.DontCry.laboratory.operations;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.ssau.tk.DontCry.laboratory.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.DontCry.laboratory.functions.*;
import ru.ssau.tk.DontCry.laboratory.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {

    private static final double DELTA = 0.001;

    private final double[] valuesX = new double[]{1, 3, 5, 7};

    private final double[] valuesYArray = new double[]{-2, -4, 7, 0};
    private final double[] valuesYList = new double[]{-1, 2, -3, 4};

    private final ArrayTabulatedFunction testArrayFunction = new ArrayTabulatedFunction(valuesX, valuesYArray);
    private final LinkedListTabulatedFunction testListFunction = new LinkedListTabulatedFunction(valuesX, valuesYList);

    private final TabulatedFunctionOperationService serviceArray = new TabulatedFunctionOperationService();
    private final TabulatedFunctionOperationService serviceList = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());

    @Test
    public void testAsPoints() {
        Point[] points = TabulatedFunctionOperationService.asPoints(testArrayFunction);
        int i = 0;
        for (Point myPoint : points) {
            assertEquals(myPoint.x, testArrayFunction.getX(i), DELTA);
            assertEquals(myPoint.y, testArrayFunction.getY(i++), DELTA);
        }
        assertEquals(testArrayFunction.getCount(), i);

        points = TabulatedFunctionOperationService.asPoints(testListFunction);
        i = 0;
        for (Point myPoint : points) {
            assertEquals(myPoint.x, testListFunction.getX(i), DELTA);
            assertEquals(myPoint.y, testListFunction.getY(i++), DELTA);
        }
        assertEquals(testListFunction.getCount(), i);
    }

    @Test
    public void testSum() {
        TabulatedFunction testArrayFunction = new ArrayTabulatedFunction(valuesX, valuesYArray);

        final double[] errorX = new double[]{0, 1, 2};
        final double[] errorY = new double[]{0, 1, 2};
        final double[] errorX2 = new double[]{-3, 6, 10};


        TabulatedFunction errorTest = new ArrayTabulatedFunction(errorX, errorY);
        assertThrows(InconsistentFunctionsException.class, () -> serviceList.sum(testListFunction, errorTest));

        TabulatedFunction errorTest2 = new ArrayTabulatedFunction(errorX2, errorY);
        assertThrows(InconsistentFunctionsException.class, () -> serviceList.sum(testListFunction, errorTest2));

        TabulatedFunction sumOfArrays = serviceArray.sum(testArrayFunction, testArrayFunction);
        assertTrue(sumOfArrays instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : sumOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYArray[i] + valuesYArray[i++]);
        }
    }

    @Test
    public void testSubtract() {
        TabulatedFunction subtractOfLists = serviceList.subtract(testListFunction, testListFunction);
        assertTrue(subtractOfLists instanceof LinkedListTabulatedFunction);
        int i = 0;
        for (Point point : subtractOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYList[i] - valuesYList[i++]);
        }
    }

    @Test
    public void testMultiplication() {
        TabulatedFunction multiplicationOfArrayAndList = serviceArray.multiplication(testArrayFunction, testListFunction);
        assertTrue(multiplicationOfArrayAndList instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : multiplicationOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYArray[i] * valuesYList[i++]);
        }
    }

    @Test
    public void testDivision() {
        TabulatedFunction divisionOfListAndArray = serviceList.division(testListFunction, testArrayFunction);
        assertTrue(divisionOfListAndArray instanceof LinkedListTabulatedFunction);
        int i = 0;
        for (Point point : divisionOfListAndArray) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYList[i] / valuesYArray[i++]);
        }
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("TabulatedFunctionOperationService checked");
    }
}

