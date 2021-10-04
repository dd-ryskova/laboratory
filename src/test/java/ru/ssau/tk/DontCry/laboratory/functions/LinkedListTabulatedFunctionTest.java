package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    private final static double DELTA = 0.0001;

    private final double[] xValues = new double[]{1, 3, 5, 7, 9};
    private final double[] yValues = new double[]{2, 4, 6, 8, 10};

    private LinkedListTabulatedFunction createFromArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private final MathFunction firstFunction = new SqrFunction();

    private LinkedListTabulatedFunction createFirstFunction() {
        return new LinkedListTabulatedFunction(firstFunction, -45, -10, 10);
    }

    private LinkedListTabulatedFunction createSecondFunction() {
        return new LinkedListTabulatedFunction(firstFunction, 1, 10, 10);
    }

    @Test
    public void testAddNode() {
        LinkedListTabulatedFunction array = createFromArray();
        array.addNode(11, 12);

        assertEquals(array.rightBound(), 11.0, DELTA);
    }

    @Test
    public void testGetCount() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();

        assertEquals(array.getCount(), 5);
        assertEquals(firstListOfFunction.getCount(), 10);
    }

    @Test
    public void testLeftBound() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();

        assertEquals(array.leftBound(), 1., DELTA);
        assertEquals(firstListOfFunction.leftBound(), -45., DELTA);
    }

    @Test
    public void testRightBound() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();

        assertEquals(array.rightBound(), 9., DELTA);
        assertEquals(firstListOfFunction.rightBound(), -10., DELTA);
    }

    @Test
    public void testGetX() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();

        assertEquals(array.getX(1), 3., DELTA);
        assertEquals(array.getX(2), 5., DELTA);
        assertEquals(array.getX(4), 9., DELTA);
        assertEquals(firstListOfFunction.getX(0), -45.0, DELTA);
        assertEquals(firstListOfFunction.getX(2), -37.2222, DELTA);
        assertEquals(firstListOfFunction.getX(9), -10.0, DELTA);

    }

    @Test
    public void testGetY() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();

        assertEquals(array.getY(1), 4., DELTA);
        assertEquals(array.getY(2), 6., DELTA);
        assertEquals(array.getY(4), 10., DELTA);

        assertEquals(firstListOfFunction.getY(0), 2025.0, DELTA);
        assertEquals(firstListOfFunction.getY(2), 1385.4938, DELTA);
        assertEquals(firstListOfFunction.getY(9), 100.0, DELTA);
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
        array.setY(2, 4);
        firstListOfFunction.setY(0, -19);

        assertEquals(array.getY(2), 4, DELTA);
        assertEquals(firstListOfFunction.getY(0), -19, DELTA);
    }

    @Test
    public void testIndexOfX() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();

        assertEquals(array.indexOfX(7.0), 3, DELTA);
        assertEquals(firstListOfFunction.indexOfX(-45.), 0, DELTA);
        assertEquals(secondListOfFunction.indexOfX(3.), 2, DELTA);
        assertEquals(secondListOfFunction.indexOfX(17.), -1, DELTA);
    }

    @Test
    public void testIndexOfY() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();

        assertEquals(array.indexOfY(8.0), 3, DELTA);
        assertEquals(firstListOfFunction.indexOfY(2025.), 0, DELTA);
        assertEquals(secondListOfFunction.indexOfY(9.), 2, DELTA);
        assertEquals(secondListOfFunction.indexOfY(334.), -1, DELTA);
    }

    @Test
    public void testFloorIndexOfX() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();

        assertEquals(array.floorIndexOfX(10.0), 5, DELTA);
        assertEquals(array.floorIndexOfX(0.0), 0, DELTA);
        assertEquals(array.floorIndexOfX(5.5), 2, DELTA);

        assertEquals(secondListOfFunction.floorIndexOfX(-23), 0, DELTA);
        assertEquals(secondListOfFunction.floorIndexOfX(4.2), 3, DELTA);
        assertEquals(secondListOfFunction.floorIndexOfX(67.8), 10, DELTA);
    }

    @Test
    public void testExtrapolateLeft() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("LinkedListTabulatedFunctionTest checked");
    }
}