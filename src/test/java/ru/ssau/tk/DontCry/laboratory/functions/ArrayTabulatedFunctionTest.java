package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

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

    private TabulatedFunction createThirdFunction() {
        return new ArrayTabulatedFunction(zero, 2, 4, 2);
    }

    private TabulatedFunction createForthFunction() {
        return new ArrayTabulatedFunction(self, 1, 2, 3);
    }

    private TabulatedFunction createFifthFunction() {
        return new ArrayTabulatedFunction(linear, 1, 4, 5);
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

        assertEquals(firstFunction.extrapolateLeft(5.), 12., DELTA);
        assertEquals(firstFunction.extrapolateLeft(8.), 20.25, DELTA);
        assertEquals(secondFunction.extrapolateLeft(5.), 5.2, DELTA);
        assertEquals(secondFunction.extrapolateLeft(8.), 7.6, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedFunction secondFunction = createSecondFunction();

        assertEquals(firstFunction.extrapolateRight(5.), 23.25, DELTA);
        assertEquals(firstFunction.extrapolateRight(8.), 45., DELTA);
        assertEquals(secondFunction.extrapolateRight(5.), 6.9, DELTA);
        assertEquals(secondFunction.extrapolateRight(8.), 8.55, DELTA);
    }

    @Test
    public void testInterpolate() {
        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedFunction secondFunction = createSecondFunction();

        assertEquals(firstFunction.interpolate(2., 1), 4.125, DELTA);
        assertEquals(firstFunction.interpolate(8., 2), 37.875, DELTA);
        assertEquals(secondFunction.interpolate(5., 2), 6.9, DELTA);
        assertEquals(secondFunction.interpolate(8., 1), 12.7, DELTA);
    }

    @Test
    public void testCompositeFunction() {
        TabulatedFunction thirdFunction = createThirdFunction();
        TabulatedFunction forthFunction = createForthFunction();
        TabulatedFunction fifthFunction = createFifthFunction();

        assertEquals(forthFunction.andThen(thirdFunction).apply(1.), 0., DELTA);
        assertEquals(forthFunction.andThen(fifthFunction).apply(4.), 11., DELTA);
        assertEquals(thirdFunction.andThen(forthFunction).apply(-12), 0., DELTA);
        assertEquals(thirdFunction.andThen(fifthFunction).apply(154.), 3., DELTA);
        assertEquals(fifthFunction.andThen(thirdFunction).apply(-3.), 0., DELTA);
        assertEquals(fifthFunction.andThen(forthFunction).apply(11.), 25., DELTA);
        assertEquals(fifthFunction.andThen(forthFunction).andThen(thirdFunction).apply(-2.5), 0., DELTA);
        assertEquals(fifthFunction.andThen(thirdFunction).andThen(forthFunction).apply(7), 0., DELTA);
        assertEquals(thirdFunction.andThen(forthFunction).andThen(fifthFunction).apply(9), 3., DELTA);
        assertEquals(thirdFunction.andThen(fifthFunction).andThen(forthFunction).apply(-3.9), 3., DELTA);
        assertEquals(forthFunction.andThen(fifthFunction).andThen(thirdFunction).apply(100.), 0., DELTA);
        assertEquals(fifthFunction.andThen(thirdFunction).andThen(fifthFunction).apply(-2.5), 3., DELTA);
        assertEquals(fifthFunction.andThen(forthFunction).andThen(thirdFunction).andThen(fifthFunction).apply(-2.5), 3., DELTA);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("ArrayTabulatedFunctionTest checked");
    }
}
