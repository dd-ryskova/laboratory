package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    private final MathFunction sqr = new SqrFunction();
    private final MathFunction self = new IdentityFunction();
    private final MathFunction arctg = new ArctanFunction();
    private final MathFunction four = new FourDegreeFunction();

    private final MathFunction selfArc = new CompositeFunction(self, arctg);
    private final MathFunction sqrArc = new CompositeFunction(sqr, arctg);
    private final MathFunction arcSelf = new CompositeFunction(arctg, self);
    private final MathFunction arcSelfFour = new CompositeFunction(arcSelf, four);

    @Test
    public void testApply() {
        double DELTA = 0.0001;
        assertEquals(selfArc.apply(1), Math.PI / 4, DELTA);
        assertEquals(sqrArc.apply(Math.sqrt(3)), 1.2490, DELTA);
        assertEquals(arcSelf.apply(0), 0, DELTA);
        assertEquals(arcSelfFour.apply(Math.sqrt(3) / 3), Math.pow(Math.PI / 6, 4), DELTA);
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("CompositeFunction checked");
    }
}

