package functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArctanFunctionTest {
        @Test
        public void testApply() {
        assertEquals(new ArctanFunction().apply(0), 0);


    }
    @AfterMethod
    void afterMethod() {
        System.out.println("ArctanFunction checked");
    }

}
