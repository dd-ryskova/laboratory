package functions;

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

        assertEquals(selfArc.apply(1), Math.PI / 4,  border);
        assertEquals(sqrArc.apply(Math.sqrt(3)), 1.2490, border);
        assertEquals(arcSelf.apply(0), 0, border);
        assertEquals(arcSelfFour.apply(Math.sqrt(3)/3),Math.pow(Math.PI/6,4), border);


    }
}


/*


public class CompositeFunctionTest {
    MathFunction arctg = new ArctanFunction();
    final double DELTA = 0.0001;

    @Test
    public void testApply() {
        MathFunction self = new IdentityFunction();
        MathFunction ln = new LgFunction();

        MathFunction selfArc = new CompositeFunction(self, arctg);
        MathFunction doubleArc = new CompositeFunction(arctg, arctg);
        MathFunction arcSelf = new CompositeFunction(arctg, self);
        MathFunction lnArc = new CompositeFunction(ln, arctg);
        MathFunction doubleLn = new CompositeFunction(ln, ln);
        MathFunction lnSelf = new CompositeFunction(ln, self);


        assertEquals(selfArc.apply(1), Math.PI / 4, 0.00001);
        assertEquals(doubleArc.apply(3), 0.90, 0.01);
        assertEquals(arcSelf.apply(0), 0, 0.00001);
        assertEquals(lnArc.apply(1), 0);
        assertEquals(doubleLn.apply(10), 0);
        assertEquals(lnSelf.apply(10), 1);
        assertEquals(lnArc.apply(100), 1.1, 0.01);
    }

}
 */





