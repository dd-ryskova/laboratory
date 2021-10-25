package ru.ssau.tk.DontCry.laboratory.operations;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.ssau.tk.DontCry.laboratory.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.DontCry.laboratory.functions.*;

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
    public void testSum() {
        TabulatedFunction testArrayFunction = new ArrayTabulatedFunction(valuesX, valuesYArray);

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
    }

    @Test
    public void testSubtract() {
        TabulatedFunction subtractOfLists = service.subtract(testListFunction, testListFunction);

        int i = 0;
        for (Point point : subtractOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYList[i] - valuesYList[i++]);
        }
    }

    @Test
    public void testMultiplication() {
        TabulatedFunction multiplicationOfArrayAndList = service.multiplication(testArrayFunction, testListFunction);

        int i = 0;
        for (Point point : multiplicationOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYArray[i] * valuesYList[i++]);
        }
    }

    @Test
    public void testDivision() {
        TabulatedFunction divisionOfListAndArray = service.division(testListFunction, testArrayFunction);

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

