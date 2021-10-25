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

    public ArrayTabulatedFunction testArrayFunction = new ArrayTabulatedFunction(valuesX, valuesYArray);
    public LinkedListTabulatedFunction testListFunction = new LinkedListTabulatedFunction(valuesX, valuesYList);

    public TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

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

        TabulatedFunction sumOfArrays = service.sum(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : sumOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYArray[i] + valuesYArray[i++]);
        }

        TabulatedFunction sumOfLists = service.sum(testListFunction, testListFunction);
        i = 0;
        for (Point point : sumOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYList[i] + valuesYList[i++]);
        }

        TabulatedFunction sumOfArrayAndList = service.sum(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : sumOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYArray[i] + valuesYList[i++]);
        }
    }

    @Test
    public void testSubtract() {
        TabulatedFunction subtractOfArrays = service.subtract(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : subtractOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYArray[i] - valuesYArray[i++]);
        }

        TabulatedFunction subtractOfLists = service.subtract(testListFunction, testListFunction);
        i = 0;
        for (Point point : subtractOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYList[i] - valuesYList[i++]);
        }

        TabulatedFunction subtractOfArrayAndList = service.subtract(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : subtractOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYArray[i] - valuesYList[i++]);
        }

        TabulatedFunction subtractOfListAndArray = service.subtract(testListFunction, testArrayFunction);
        i = 0;
        for (Point point : subtractOfListAndArray) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYList[i] - valuesYArray[i++]);
        }
    }

    @Test
    public void testMultiplication() {
        TabulatedFunction multiplicationOfArrays = service.multiplication(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : multiplicationOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYArray[i] * valuesYArray[i++]);
        }

        TabulatedFunction multiplicationOfLists = service.multiplication(testListFunction, testListFunction);
        i = 0;
        for (Point point : multiplicationOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYList[i] * valuesYList[i++]);
        }

        TabulatedFunction multiplicationOfArrayAndList = service.multiplication(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : multiplicationOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYArray[i] * valuesYList[i++]);
        }

        TabulatedFunction multiplicationOfListAndArray = service.multiplication(testListFunction, testArrayFunction);
        i = 0;
        for (Point point : multiplicationOfListAndArray) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYList[i] * valuesYArray[i++]);
        }
    }

    @Test
    public void testDivision() {
        TabulatedFunction divisionOfArrays = service.division(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : divisionOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYArray[i] / valuesYArray[i++]);
        }

        TabulatedFunction divisionOfLists = service.division(testListFunction, testListFunction);
        i = 0;
        for (Point point : divisionOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYList[i] / valuesYList[i++]);
        }

        TabulatedFunction divisionOfArrayAndList = service.division(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : divisionOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYArray[i] / valuesYList[i++]);
        }

        TabulatedFunction divisionOfListAndArray = service.division(testListFunction, testArrayFunction);
        i = 0;
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

