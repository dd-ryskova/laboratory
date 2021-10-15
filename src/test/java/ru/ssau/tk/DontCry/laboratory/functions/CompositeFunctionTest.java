package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    private final static double DELTA = 0.001;

    private final double[] xValues = new double[]{1, 3, 5, 7, 9};
    private final double[] yValues = new double[]{4, 6, 8, 10, 12};

    private final MathFunction sqr = new SqrFunction();
    private final MathFunction self = new IdentityFunction();
    private final MathFunction arctg = new ArctanFunction();
    private final MathFunction four = new FourDegreeFunction();
    private final MathFunction linear = new LinearFunction();

    private final MathFunction selfArc = new CompositeFunction(self, arctg);
    private final MathFunction sqrArc = new CompositeFunction(sqr, arctg);
    private final MathFunction arcSelf = new CompositeFunction(arctg, self);
    private final MathFunction arcSelfFour = new CompositeFunction(arcSelf, four);

    private TabulatedFunction createFirstFunction() {
        return new LinkedListTabulatedFunction(sqr, 2, 6, 9);
    }

    private TabulatedFunction createSecondFunction() {
        return new ArrayTabulatedFunction(linear, 11, 25, 50);
    }

    @Test
    public void testApply() {
        assertEquals(selfArc.apply(1), Math.PI / 4, DELTA);
        assertEquals(sqrArc.apply(Math.sqrt(3)), 1.2490, DELTA);
        assertEquals(arcSelf.apply(0), 0, DELTA);
        assertEquals(arcSelfFour.apply(Math.sqrt(3) / 3), Math.pow(Math.PI / 6, 4), DELTA);
    }

    @Test
    public void testCompositeFunctionForArrayAndList() {
        MathFunction listFunction = new LinkedListTabulatedFunction(xValues, yValues);
        MathFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        MathFunction arrayListSqrFunction = arrayFunction.andThen(listFunction).andThen(sqr);

        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedFunction secondFunction = createSecondFunction();

        assertEquals(arrayListSqrFunction.apply(5), 121, DELTA);
        assertEquals(arrayListSqrFunction.apply(1.5), 56.25, DELTA);
        assertEquals(arrayListSqrFunction.apply(3.5), 90.25, DELTA);
        assertEquals(sqr.andThen(firstFunction).andThen(secondFunction).apply(2), 35, DELTA);
        assertEquals(sqr.andThen(secondFunction).andThen(firstFunction).apply(4),369.5,DELTA);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("CompositeFunction checked");
    }
}

