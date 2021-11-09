package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.ssau.tk.DontCry.laboratory.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.DontCry.laboratory.exceptions.InterpolationException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private static final double DELTA = 0.01;
    private final double[] xValues = new double[]{1., 3.5, 5., 7};
    private final double[] yValues = new double[]{2., 4., 6.9, 8.};

    private final MathFunction sqr = new SqrFunction();
    private final MathFunction zero = new ZeroFunction();
    private final MathFunction self = new IdentityFunction();
    private final MathFunction linear = new LinearFunction();

    private TabulatedFunction createFirstFunction() {
        return new ArrayTabulatedFunction(sqr, 1, 4, 5);
    }

    private TabulatedFunction createSecondFunction() {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    @Test
    public void testGetCount() {
        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedFunction secondFunction = createSecondFunction();

        assertEquals(firstFunction.getCount(), 5);
        assertEquals(secondFunction.getCount(), 4);
    }

    @Test
    public void testGetX() {
        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedFunction secondFunction = createSecondFunction();

        assertEquals(firstFunction.getX(2), 2.5, DELTA);
        assertEquals(firstFunction.getX(3), 3.25, DELTA);
        assertEquals(secondFunction.getX(0), 1, DELTA);
        assertEquals(secondFunction.getX(2), 5., DELTA);
    }

    @Test
    public void testGetY() {
        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedFunction secondFunction = createSecondFunction();

        assertEquals(secondFunction.getY(0), 2., DELTA);
        assertEquals(secondFunction.getY(1), 4., DELTA);
        assertEquals(firstFunction.getY(2), 6.25, DELTA);
        assertEquals(firstFunction.getY(3), 10.5625, DELTA);
    }

    @Test
    public void testSetX() {
        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedFunction secondFunction = createSecondFunction();

        firstFunction.setY(1, 3.);
        assertEquals(firstFunction.getY(1), 3., DELTA);

        secondFunction.setY(3, 7.3);
        assertEquals(secondFunction.getY(3), 7.3, DELTA);
    }

    @Test
    public void testLeftBound() {
        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedFunction secondFunction = createSecondFunction();

        assertEquals(firstFunction.leftBound(), 1., DELTA);
        assertEquals(secondFunction.leftBound(), 1., DELTA);
    }

    @Test
    public void testRightBound() {
        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedFunction secondFunction = createSecondFunction();

        assertEquals(firstFunction.rightBound(), 4, DELTA);
        assertEquals(secondFunction.rightBound(), 7., DELTA);
    }

    @Test
    public void testIndexOfX() {
        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedFunction secondFunction = createSecondFunction();

        assertEquals(firstFunction.indexOfX(1.), 0);
        assertEquals(firstFunction.indexOfX(10), -1);
        assertEquals(firstFunction.indexOfX(Double.NEGATIVE_INFINITY), -1);
        assertEquals(firstFunction.indexOfX(4), 4);
        assertEquals(secondFunction.indexOfX(5.), 2);
        assertEquals(secondFunction.indexOfX(7), 3);
        assertEquals(secondFunction.indexOfX(Double.NaN), -1);
    }

    @Test
    public void testIndexOfY() {
        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedFunction secondFunction = createSecondFunction();

        assertEquals(firstFunction.indexOfY(6.25), 2);
        assertEquals(firstFunction.indexOfY(10.5625), 3);
        assertEquals(firstFunction.indexOfY(Double.POSITIVE_INFINITY), -1);
        assertEquals(secondFunction.indexOfY(6.9), 2);
        assertEquals(secondFunction.indexOfY(Double.NEGATIVE_INFINITY), -1);
        assertEquals(secondFunction.indexOfY(8.), 3);
    }

    @Test
    public void testFloorIndexOfX() {
        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedFunction secondFunction = createSecondFunction();

        assertEquals(firstFunction.floorIndexOfX(57.), 5);
        assertEquals(firstFunction.floorIndexOfX(1.), 0);
        assertEquals(firstFunction.floorIndexOfX(3), 2);
        assertEquals(secondFunction.floorIndexOfX(57.), 4);
        assertEquals(secondFunction.floorIndexOfX(1), 0);
    }

    @Test
    public void testExtrapolateLeft() {
        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedFunction secondFunction = createSecondFunction();

        assertEquals(firstFunction.extrapolateLeft(1.25), 1.6875, DELTA);
        assertEquals(firstFunction.extrapolateLeft(1.5), 2.375, DELTA);
        assertEquals(secondFunction.extrapolateLeft(3.), 3.6, DELTA);
        assertEquals(secondFunction.extrapolateLeft(3.5), 4., DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedFunction secondFunction = createSecondFunction();

        assertEquals(firstFunction.extrapolateRight(3.5), 12.375, DELTA);
        assertEquals(firstFunction.extrapolateRight(3.75), 14.1875, DELTA);
        assertEquals(secondFunction.extrapolateRight(5), 6.9, DELTA);
        assertEquals(secondFunction.extrapolateRight(6), 7.45, DELTA);
    }

    @Test
    public void testInterpolate() {
        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedFunction secondFunction = createSecondFunction();

        assertEquals(firstFunction.interpolate(2., 1), 4.125, DELTA);
        assertEquals(firstFunction.interpolate(3, 2), 9.125, DELTA);
        assertEquals(secondFunction.interpolate(7., 2), 8., DELTA);
    }

    @Test
    public void testIllegalArgumentException() {
        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedFunction secondFunction = createSecondFunction();

        assertThrows(InterpolationException.class, () -> firstFunction.interpolate(5, 2));
        assertThrows(InterpolationException.class, () -> secondFunction.interpolate(4.9, 0));

        assertThrows(IllegalArgumentException.class, () -> {
            double[] xValues = new double[]{2};
            double[] yValues = new double[]{1};
            new ArrayTabulatedFunction(xValues, yValues);
        });
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(sqr, 55, 51, 1000));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(sqr, 50, 24, 100));
    }

    @Test
    public void testDifferentLengthOfArraysException() {
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            double[] xValues = new double[]{2, 4, 6};
            double[] yValues = new double[]{2, 8, 10, 12};
            new ArrayTabulatedFunction(xValues, yValues);
        });
    }

    @Test
    public void testIteratorWhile() {
        TabulatedFunction array = createFirstFunction();
        Iterator<Point> arrayIterator = array.iterator();

        int i = 0;
        while (arrayIterator.hasNext()) {
            Point point = arrayIterator.next();
            assertEquals(array.getX(i), point.x);
            assertEquals(array.getY(i++), point.y);

        }
        assertEquals(array.getCount(), i);
        assertThrows(NoSuchElementException.class, arrayIterator::next);

    }

    @Test
    public void testIteratorForEach() {
        TabulatedFunction tabulatedFunction = createFirstFunction();

        int i = 0;
        for (Point point : tabulatedFunction) {
            assertEquals(point.x, tabulatedFunction.getX(i), DELTA);
            assertEquals(point.y, tabulatedFunction.getY(i++), DELTA);
        }
        assertEquals(tabulatedFunction.getCount(), i);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("ArrayTabulatedFunctionTest checked");
    }
}
