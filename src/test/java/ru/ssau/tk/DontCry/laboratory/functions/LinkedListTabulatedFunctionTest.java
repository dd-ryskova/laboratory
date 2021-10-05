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
    private final MathFunction secondFunction = new ZeroFunction();
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction self = new IdentityFunction();
    private final MathFunction selfSqr = new CompositeFunction(sqr, self);


    private LinkedListTabulatedFunction createFirstFunction() {
        return new LinkedListTabulatedFunction(firstFunction, -45, -10, 10);
    }

    private LinkedListTabulatedFunction createSecondFunction() {
        return new LinkedListTabulatedFunction(firstFunction, 1, 10, 10);
    }

    private LinkedListTabulatedFunction createThirdFunction() {
        return new LinkedListTabulatedFunction(secondFunction, 5, 15, 10);
    }

    private LinkedListTabulatedFunction createCompositeFunction() {
        return new LinkedListTabulatedFunction(selfSqr, 1, 10, 10);
    }

    private LinkedListTabulatedFunction createSqrFunction() {
        return new LinkedListTabulatedFunction(sqr, 1, 10, 10);
    }

    private LinkedListTabulatedFunction createIdentityFunction() {
        return new LinkedListTabulatedFunction(self, -10, -1, 10);
    }

    @Test
    public void testAddNode() {
        LinkedListTabulatedFunction array = createFromArray();
        array.addNode(11, 12);
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();
        secondListOfFunction.addNode(11, 121);

        assertEquals(array.rightBound(), 11.0, DELTA);
        assertEquals(secondListOfFunction.rightBound(), 11, DELTA);
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
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();

        assertEquals(array.getY(1), 4., DELTA);
        assertEquals(array.getY(2), 6., DELTA);
        assertEquals(array.getY(4), 10., DELTA);

        assertEquals(firstListOfFunction.getY(0), 2025.0, DELTA);
        assertEquals(firstListOfFunction.getY(2), 1385.4938, DELTA);
        assertEquals(firstListOfFunction.getY(9), 100.0, DELTA);
        assertEquals(secondListOfFunction.getY(2), 9.0, DELTA);
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
        LinkedListTabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.extrapolateLeft(0.0), 1.0, DELTA);
        assertEquals(array.extrapolateLeft(-54.0), -53.0, DELTA);
        assertEquals(thirdListOfFunction.extrapolateLeft(2.0), 0.0, DELTA);
        assertEquals(thirdListOfFunction.extrapolateLeft(-15567.0), 0.0, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.extrapolateRight(10.0), 11.0);
        assertEquals(array.extrapolateRight(134.0), 135.0);
        assertEquals(thirdListOfFunction.extrapolateLeft(554.4366), 0.0, DELTA);
    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction thirdListOfFunction = createThirdFunction();
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();

        assertEquals(array.interpolate(4.0, 2), 5.0);
        assertEquals(thirdListOfFunction.interpolate(5.5, thirdListOfFunction.floorIndexOfX(5.5)), 0, DELTA);
        assertEquals(secondListOfFunction.interpolate(4.2, 3), 17.8, DELTA);
    }


    @Test
    public void testCompositeFunction() {
        LinkedListTabulatedFunction compositeFunction = createCompositeFunction();
        compositeFunction.addNode(11, 144);
        compositeFunction.setY(10, 145);

        assertEquals(compositeFunction.rightBound(), 11.0, DELTA);
        assertEquals(compositeFunction.leftBound(), 1.0, DELTA);
        assertEquals(compositeFunction.getCount(), 11.0, DELTA);
        assertEquals(compositeFunction.getX(2), 3.0, DELTA);
        assertEquals(compositeFunction.getY(4), 25.0, DELTA);
        compositeFunction.setY(10, 145);
        assertEquals(compositeFunction.getY(10), 145, DELTA);
        assertEquals(compositeFunction.indexOfX(5.0), 4, DELTA);
        assertEquals(compositeFunction.indexOfY(25.0), 4, DELTA);
        assertEquals(compositeFunction.floorIndexOfX(7.3), 6, DELTA);
        assertEquals(compositeFunction.interpolate(4.2, 3), 17.8, DELTA);
    }


    @Test
    public void testCompositeFunctionFromFunction() {
        MathFunction firstListOfFunction = createIdentityFunction();
        MathFunction secondListOfFunction = createSqrFunction();

        assertEquals(firstListOfFunction.andThen(secondListOfFunction).apply(5.), 25, DELTA);
        assertEquals(firstListOfFunction.andThen(firstListOfFunction).apply(5.), 5, DELTA);
        assertEquals(firstListOfFunction.andThen(firstListOfFunction).apply(-13.), -13, DELTA);
        assertEquals(firstListOfFunction.andThen(secondListOfFunction).apply(11.2), 122.7999, DELTA);
    }

    @Test
    public void testCompositeFunctionFromArray() {
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("LinkedListTabulatedFunctionTest checked");
    }
}