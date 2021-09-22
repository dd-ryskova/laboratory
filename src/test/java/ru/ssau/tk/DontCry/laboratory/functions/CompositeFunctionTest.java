package ru.ssau.tk.DontCry.laboratory.functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    @Test
    public void testApply() {
        double border = 0.0001;

        MathFunction sqr = new SqrFunction();
        MathFunction self = new IdentityFunction();
        MathFunction arctg = new ArctanFunction();
        MathFunction four = new FourDegreeFunction();

        MathFunction selfArc = new CompositeFunction(self, arctg);
        MathFunction sqrArc = new CompositeFunction(sqr, arctg);
        MathFunction arcSelf = new CompositeFunction(arctg, self);
        MathFunction arcSelfFour = new CompositeFunction(arcSelf, four);

        assertEquals(selfArc.apply(1), Math.PI / 4, border);
        assertEquals(sqrArc.apply(Math.sqrt(3)), 1.2490, border);
        assertEquals(arcSelf.apply(0), 0, border);
        assertEquals(arcSelfFour.apply(Math.sqrt(3) / 3), Math.pow(Math.PI / 6, 4), border);

    }

    @AfterMethod
    void afterMethod() {
        System.out.println("CompositeFunction checked");
    }
}

