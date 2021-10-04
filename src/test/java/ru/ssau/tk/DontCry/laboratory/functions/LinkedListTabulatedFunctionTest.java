package ru.ssau.tk.DontCry.laboratory.functions;

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
    }

    @Test
    public void testGetY() {
    }

    @Test
    public void testSetY() {
    }

    @Test
    public void testIndexOfX() {
    }

    @Test
    public void testIndexOfY() {
    }

    @Test
    public void testFloorIndexOfX() {
    }

    @Test
    public void testExtrapolateLeft() {
    }

    @Test
    public void testExtrapolateRight() {
    }

    @Test
    public void testInterpolate() {
    }
}