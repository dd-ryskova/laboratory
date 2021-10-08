package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    private final static double DELTA = 0.0001;

    private final double[] xValues = new double[]{1, 3, 5, 7, 9};
    private final double[] yValues = new double[]{2, 4, 6, 8, 10};

    private final MathFunction sqr = new SqrFunction();
    private final MathFunction zero = new ZeroFunction();
    private final MathFunction self = new IdentityFunction();
    private final MathFunction selfSqr = new CompositeFunction(sqr, self);

    private LinkedListTabulatedFunction createFromArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private LinkedListTabulatedFunction createFirstFunction() {
        return new LinkedListTabulatedFunction(sqr, -45, -10, 10);
    }

    private LinkedListTabulatedFunction createSecondFunction() {
        return new LinkedListTabulatedFunction(sqr, 1, 10, 10);
    }

    private LinkedListTabulatedFunction createThirdFunction() {
        return new LinkedListTabulatedFunction(zero, 5, 15, 10);
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
        LinkedListTabulatedFunction secondFunction = createSecondFunction();
        secondFunction.addNode(11, 121);

        assertEquals(array.rightBound(), 11.0, DELTA);
        assertEquals(secondFunction.rightBound(), 11, DELTA);
    }

    @Test
    public void testGetCount() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();

        assertEquals(array.getCount(), 5);
        assertEquals(firstFunction.getCount(), 10);
    }

    @Test
    public void testLeftBound() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();

        assertEquals(array.leftBound(), 1., DELTA);
        assertEquals(firstFunction.leftBound(), -45., DELTA);
    }

    @Test
    public void testRightBound() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();

        assertEquals(array.rightBound(), 9., DELTA);
        assertEquals(firstFunction.rightBound(), -10., DELTA);
    }

    @Test
    public void testGetX() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();

        assertEquals(array.getX(1), 3., DELTA);
        assertEquals(array.getX(2), 5., DELTA);
        assertEquals(array.getX(4), 9., DELTA);
        assertEquals(firstFunction.getX(0), -45.0, DELTA);
        assertEquals(firstFunction.getX(2), -37.2222, DELTA);
        assertEquals(firstFunction.getX(9), -10.0, DELTA);
    }

    @Test
    public void testGetY() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();
        LinkedListTabulatedFunction secondFunction = createSecondFunction();

        assertEquals(array.getY(1), 4., DELTA);
        assertEquals(array.getY(2), 6., DELTA);
        assertEquals(array.getY(4), 10., DELTA);

        assertEquals(firstFunction.getY(0), 2025.0, DELTA);
        assertEquals(firstFunction.getY(2), 1385.4938, DELTA);
        assertEquals(firstFunction.getY(9), 100.0, DELTA);
        assertEquals(secondFunction.getY(2), 9.0, DELTA);
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();
        array.setY(2, 4);
        firstFunction.setY(0, -19);

        assertEquals(array.getY(2), 4, DELTA);
        assertEquals(firstFunction.getY(0), -19, DELTA);
    }

    @Test
    public void testIndexOfX() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();
        LinkedListTabulatedFunction secondFunction = createSecondFunction();

        assertEquals(array.indexOfX(7.0), 3, DELTA);
        assertEquals(firstFunction.indexOfX(-45.), 0, DELTA);
        assertEquals(secondFunction.indexOfX(3.), 2, DELTA);
        assertEquals(secondFunction.indexOfX(17.), -1, DELTA);
    }

    @Test
    public void testIndexOfY() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();
        LinkedListTabulatedFunction secondFunction = createSecondFunction();

        assertEquals(array.indexOfY(8.0), 3, DELTA);
        assertEquals(firstFunction.indexOfY(2025.), 0, DELTA);
        assertEquals(secondFunction.indexOfY(9.), 2, DELTA);
        assertEquals(secondFunction.indexOfY(334.), -1, DELTA);
    }

    @Test
    public void testFloorIndexOfX() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction secondFunction = createSecondFunction();

        assertEquals(array.floorIndexOfX(10.0), 5, DELTA);
        assertEquals(array.floorIndexOfX(0.0), 0, DELTA);
        assertEquals(array.floorIndexOfX(5.5), 2, DELTA);

        assertEquals(secondFunction.floorIndexOfX(-23), 0, DELTA);
        assertEquals(secondFunction.floorIndexOfX(4.2), 3, DELTA);
        assertEquals(secondFunction.floorIndexOfX(67.8), 10, DELTA);
    }

    @Test
    public void testExtrapolateLeft() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction thirdFunction = createThirdFunction();

        assertEquals(array.extrapolateLeft(0.0), 1.0, DELTA);
        assertEquals(array.extrapolateLeft(-54.0), -53.0, DELTA);
        assertEquals(thirdFunction.extrapolateLeft(2.0), 0.0, DELTA);
        assertEquals(thirdFunction.extrapolateLeft(-15567.0), 0.0, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction thirdFunction = createThirdFunction();

        assertEquals(array.extrapolateRight(10.0), 11.0);
        assertEquals(array.extrapolateRight(134.0), 135.0);
        assertEquals(thirdFunction.extrapolateLeft(554.4366), 0.0, DELTA);
    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction thirdFunction = createThirdFunction();
        LinkedListTabulatedFunction secondFunction = createSecondFunction();

        assertEquals(array.interpolate(4.0, 2), 5.0);
        assertEquals(thirdFunction.interpolate(5.5, thirdFunction.floorIndexOfX(5.5)), 0, DELTA);
        assertEquals(secondFunction.interpolate(4.2, 3), 17.8, DELTA);
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
        MathFunction firstFunction = createIdentityFunction();
        MathFunction secondFunction = createSqrFunction();

        assertEquals(firstFunction.andThen(secondFunction).apply(5.), 25, DELTA);
        assertEquals(firstFunction.andThen(firstFunction).apply(5.), 5, DELTA);
        assertEquals(firstFunction.andThen(firstFunction).apply(-13.), -13, DELTA);
        assertEquals(firstFunction.andThen(secondFunction).apply(11.2), 122.7999, DELTA);
    }

    @Test
    public void testTabulatedFunctionFromArray() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createSqrFunction();
        LinkedListTabulatedFunction secondFunction = createIdentityFunction();

        assertEquals(firstFunction.apply(4), 16, DELTA);
        assertEquals(firstFunction.apply(1.5), 2.5, DELTA);
        assertEquals(firstFunction.apply(8), 64, DELTA);

        assertEquals(secondFunction.apply(-10), -10, DELTA);
        assertEquals(secondFunction.apply(2.5), 2.5, DELTA);
        assertEquals(secondFunction.apply(Double.NEGATIVE_INFINITY), Double.NEGATIVE_INFINITY, DELTA);

        assertEquals(array.apply(1), 2, DELTA);
        assertEquals(array.apply(-5), -4, DELTA);
        assertEquals(array.apply(3.5), 4.5, DELTA);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("LinkedListTabulatedFunctionTest checked");
    }
}