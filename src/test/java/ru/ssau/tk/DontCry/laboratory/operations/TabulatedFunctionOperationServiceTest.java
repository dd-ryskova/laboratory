package ru.ssau.tk.DontCry.laboratory.operations;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.ssau.tk.DontCry.laboratory.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.DontCry.laboratory.functions.*;
import ru.ssau.tk.DontCry.laboratory.functions.factory.*;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {
    private static final double DELTA = 0.001;

    private final double[] valuesX = new double[]{1, 3, 5, 7};

    private final double[] valuesYArray = new double[]{-2, -4, 7, 0};
    private final double[] valuesYList = new double[]{-1, 2, -3, 4};

    ArrayTabulatedFunction testArrayFunction = new ArrayTabulatedFunction(valuesX, valuesYArray);
    LinkedListTabulatedFunction testListFunction = new LinkedListTabulatedFunction(valuesX, valuesYList);

    TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

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
    public void testGetFactory() {
        assertTrue(service.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory()).getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        service.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(service.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSum() {
        TabulatedFunction testArrayFunction = new ArrayTabulatedFunction(valuesX, valuesYArray);
        TabulatedFunction testListFunction = new LinkedListTabulatedFunction(valuesX, valuesYList);

        final double[] errorX = new double[]{0, 1, 2};
        final double[] errorY = new double[]{0, 1, 2};
        TabulatedFunction errorTest = new ArrayTabulatedFunction(errorX, errorY);
        assertThrows(InconsistentFunctionsException.class, () -> service.sum(testListFunction, errorTest));


        TabulatedFunction testSumOfArrays = service.sum(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : testSumOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYArray[i] + valuesYArray[i++]);
        }

        TabulatedFunction testSumOfLists = service.sum(testListFunction, testListFunction);
        i = 0;
        for (Point point : testSumOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYList[i] + valuesYList[i++]);
        }

        TabulatedFunction testSumOfArrayAndList = service.sum(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : testSumOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYArray[i] + valuesYList[i++]);
        }
    }

    @Test
    public void testSubtract() {
        TabulatedFunction testSubtractOfArrays = service.subtract(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : testSubtractOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYArray[i] - valuesYArray[i++]);
        }

        TabulatedFunction testSubtractOfLists = service.subtract(testListFunction, testListFunction);
        i = 0;
        for (Point point : testSubtractOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYList[i] - valuesYList[i++]);
        }

        TabulatedFunction testSubtractOfArrayAndList = service.subtract(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : testSubtractOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYArray[i] - valuesYList[i++]);
        }

        TabulatedFunction testSubtractOfListAndArray = service.subtract(testListFunction, testArrayFunction);
        i = 0;
        for (Point point : testSubtractOfListAndArray) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYList[i] - valuesYArray[i++]);
        }
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("TabulatedFunctionOperationService checked");
    }
}

