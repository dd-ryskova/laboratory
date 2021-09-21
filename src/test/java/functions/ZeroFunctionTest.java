package functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class ZeroFunctionTest {
    @Test
    public void testApply() {
        assertEquals(new ZeroFunction().apply(100.0), 0.0);
        assertEquals(new ZeroFunction().apply(90.0), 0.0);
        assertEquals(new ZeroFunction().apply(667.0), 0.0);
    }
    @AfterMethod
    void afterMethod() {
        System.out.println("ZeroFunction checked");
    }
}
