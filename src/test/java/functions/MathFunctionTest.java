package functions;

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
    public void testAndThen(){
        assertNotEquals(function1.apply(1000.0), 100.0);
        assertEquals(function1.andThen(one).apply(0.0), 1.0);
        assertEquals(function1.apply(10.0), 10000.0);
        assertEquals(function2.apply(2.0), 65536.0);
    }
}
