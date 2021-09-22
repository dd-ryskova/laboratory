package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {

    private final MathFunction x = new IdentityFunction();
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction one = new UnitFunction();
    private final MathFunction four = new FourDegreeFunction();
    private final MathFunction function1 = (sqr).andThen(sqr).andThen(x);
    private final MathFunction function2 = (four).andThen(sqr).andThen(sqr).andThen(x);

    @Test
    public void testAndThen() {
        double DELTA = 0.0001;
        assertNotEquals(function1.apply(1000.0), 100.0, DELTA);
        assertEquals(function1.andThen(one).apply(0.0), 1.0, DELTA);
        assertEquals(function1.apply(10.0), 10000.0, DELTA);
        assertEquals(function2.apply(2.0), 65536.0, DELTA);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("MathFunction checked");
    }
}
