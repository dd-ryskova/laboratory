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
    private final MathFunction linear = new LinearFunction();

    private LinkedListTabulatedFunction createFromArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private LinkedListTabulatedFunction createFirstFunction() {
        return new LinkedListTabulatedFunction(sqr, 1, 10, 10);
    }

    private LinkedListTabulatedFunction createSecondFunction() {
        return new LinkedListTabulatedFunction(zero, -50, 0, 21);
    }

    private LinkedListTabulatedFunction createThirdFunction() {
        return new LinkedListTabulatedFunction(self, -5.4, 5.4, 100);
    }

    private LinkedListTabulatedFunction createFourthFunction() {
        return new LinkedListTabulatedFunction(linear, -12, 12, 25);
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
        LinkedListTabulatedFunction secondFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdFunction = createThirdFunction();
        LinkedListTabulatedFunction fourthFunction = createFourthFunction();

        assertEquals(array.getCount(), 5);
        assertEquals(firstFunction.getCount(), 10);
        assertEquals(secondFunction.getCount(), 21);
        assertEquals(thirdFunction.getCount(), 100);
        assertEquals(fourthFunction.getCount(), 25);
    }

    @Test
    public void testLeftBound() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();
        LinkedListTabulatedFunction secondFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdFunction = createThirdFunction();
        LinkedListTabulatedFunction fourthFunction = createFourthFunction();

        assertEquals(array.leftBound(), 1., DELTA);
        assertEquals(firstFunction.leftBound(), 1., DELTA);
        assertEquals(secondFunction.leftBound(), -50., DELTA);
        assertEquals(thirdFunction.leftBound(), -5.4, DELTA);
        assertEquals(fourthFunction.leftBound(), -12., DELTA);
    }

    @Test
    public void testRightBound() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();
        LinkedListTabulatedFunction secondFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdFunction = createThirdFunction();
        LinkedListTabulatedFunction fourthFunction = createFourthFunction();

        assertEquals(array.rightBound(), 9., DELTA);
        assertEquals(firstFunction.rightBound(), 10., DELTA);
        assertEquals(secondFunction.rightBound(), 0., DELTA);
        assertEquals(thirdFunction.rightBound(), 5.4, DELTA);
        assertEquals(fourthFunction.rightBound(), 12., DELTA);
    }

    @Test
    public void testGetX() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();
        LinkedListTabulatedFunction secondFunction = createSecondFunction();

        assertEquals(array.getX(1), 3., DELTA);
        assertEquals(array.getX(2), 5., DELTA);
        assertEquals(array.getX(4), 9., DELTA);
        assertEquals(firstFunction.getX(0), 1., DELTA);
        assertEquals(firstFunction.getX(2), 3., DELTA);
        assertEquals(firstFunction.getX(9), 10., DELTA);
        assertEquals(secondFunction.getX(18), -5.0, DELTA);
    }

    @Test
    public void testGetY() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();
        LinkedListTabulatedFunction secondFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdFunction = createThirdFunction();

        assertEquals(array.getY(1), 4., DELTA);
        assertEquals(array.getY(2), 6., DELTA);
        assertEquals(array.getY(4), 10., DELTA);
        assertEquals(firstFunction.getY(0), 1, DELTA);
        assertEquals(firstFunction.getY(9), 100., DELTA);
        assertEquals(secondFunction.getY(2), 0., DELTA);
        assertEquals(thirdFunction.getY(3), -5.0727, DELTA);
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();
        LinkedListTabulatedFunction thirdFunction = createThirdFunction();
        array.setY(2, 4);
        firstFunction.setY(0, -19);
        thirdFunction.setY(3, 2342.232);

        assertEquals(array.getY(2), 4, DELTA);
        assertEquals(firstFunction.getY(0), -19, DELTA);
        assertEquals(thirdFunction.getY(3), 2342.232, DELTA);
    }

    @Test
    public void testIndexOfX() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();
        LinkedListTabulatedFunction secondFunction = createSecondFunction();

        assertEquals(array.indexOfX(7.0), 3, DELTA);
        assertEquals(firstFunction.indexOfX(-345.4), -1, DELTA);
        assertEquals(secondFunction.indexOfX(-12.5), 15, DELTA);
        assertEquals(secondFunction.indexOfX(-50.), 0, DELTA);
    }

    @Test
    public void testIndexOfY() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();
        LinkedListTabulatedFunction secondFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdFunction = createThirdFunction();
        LinkedListTabulatedFunction fourthFunction = createFourthFunction();


        assertEquals(array.indexOfY(8.0), 3, DELTA);
        assertEquals(firstFunction.indexOfY(100.), 9, DELTA);
        assertEquals(secondFunction.indexOfY(0.), 0, DELTA);
        assertEquals(thirdFunction.indexOfY(334.), -1, DELTA);
        assertEquals(thirdFunction.indexOfY(-5.4), 0, DELTA);
        assertEquals(fourthFunction.indexOfY(-17.), 2, DELTA);
    }

    @Test
    public void testFloorIndexOfX() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();
        LinkedListTabulatedFunction fourthFunction = createFourthFunction();

        assertEquals(array.floorIndexOfX(10.0), 5, DELTA);
        assertEquals(array.floorIndexOfX(0.0), 0, DELTA);
        assertEquals(array.floorIndexOfX(5.5), 2, DELTA);
        assertEquals(firstFunction.floorIndexOfX(-36), 0, DELTA);
        assertEquals(fourthFunction.floorIndexOfX(4.2), 16, DELTA);
        assertEquals(fourthFunction.floorIndexOfX(-8.456), 3, DELTA);
    }

    @Test
    public void testExtrapolateLeft() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();
        LinkedListTabulatedFunction secondFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdFunction = createThirdFunction();
        LinkedListTabulatedFunction fourthFunction = createFourthFunction();

        assertEquals(array.extrapolateLeft(0.0), 1.0, DELTA);
        assertEquals(array.extrapolateLeft(-54.0), -53.0, DELTA);
        assertEquals(firstFunction.extrapolateLeft(-4.0), -14.0, DELTA);
        assertEquals(secondFunction.extrapolateLeft(-6782.9765), 0.0, DELTA);
        assertEquals(thirdFunction.extrapolateLeft(-5.6), -5.6, DELTA);
        assertEquals(fourthFunction.extrapolateLeft(-120.0), -237.0, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();
        LinkedListTabulatedFunction secondFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdFunction = createThirdFunction();
        LinkedListTabulatedFunction fourthFunction = createFourthFunction();

        assertEquals(array.extrapolateRight(10.0), 11.0);
        assertEquals(array.extrapolateRight(134.0), 135.0);
        assertEquals(firstFunction.extrapolateRight(11.), 119.0, DELTA);
        assertEquals(secondFunction.extrapolateRight(554.4366), 0.0, DELTA);
        assertEquals(thirdFunction.extrapolateRight(141.584), 141.584, DELTA);
        assertEquals(fourthFunction.extrapolateRight(92.12), 187.24, DELTA);
    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstFunction = createFirstFunction();
        LinkedListTabulatedFunction secondFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdFunction = createThirdFunction();
        LinkedListTabulatedFunction fourthFunction = createFourthFunction();

        assertEquals(array.interpolate(4.0, 2), 5.0);
        assertEquals(firstFunction.interpolate(5.5, 4), 30.5, DELTA);
        assertEquals(secondFunction.interpolate(-25.01, secondFunction.floorIndexOfX(-25.01)), 0, DELTA);
        assertEquals(thirdFunction.interpolate(-3.23, thirdFunction.floorIndexOfX(-3.23)), -3.23, DELTA);
        assertEquals(fourthFunction.interpolate(-3.09422, fourthFunction.floorIndexOfX(-3.09422)), -3.18844, DELTA);
    }

    @Test
    public void testCompositeFunction() {
        MathFunction firstFunction = createFirstFunction();
        MathFunction secondFunction = createSecondFunction();
        MathFunction thirdFunction = createThirdFunction();
        MathFunction fourthFunction = createFourthFunction();

        assertEquals(firstFunction.andThen(secondFunction).apply(2), 0., DELTA);
        assertEquals(firstFunction.andThen(thirdFunction).apply(12.5), 147.5, DELTA);
        assertEquals(firstFunction.andThen(fourthFunction).apply(-3), -19., DELTA);
        assertEquals(secondFunction.andThen(firstFunction).apply(6), -2., DELTA);
        assertEquals(secondFunction.andThen(thirdFunction).apply(-5.7), 0., DELTA);
        assertEquals(secondFunction.andThen(fourthFunction).apply(0), 3., DELTA);
        assertEquals(thirdFunction.andThen(fourthFunction).apply(43463.5), 86930., DELTA);
        assertEquals(thirdFunction.andThen(firstFunction).andThen(fourthFunction).apply(1.1), 5.6, DELTA);
        assertEquals(fourthFunction.andThen(thirdFunction).andThen(fourthFunction).andThen(firstFunction).apply(-12.3), -122.6, DELTA);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("LinkedListTabulatedFunctionTest checked");
    }
}