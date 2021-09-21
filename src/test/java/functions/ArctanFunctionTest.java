package functions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class ArctanFunctionTest {
        @Test
        public void testApply() {
            assertEquals(new ArctanFunction().apply(1), Math.PI / 4);
            assertEquals(new ArctanFunction().apply(-1), -Math.PI / 4);
            assertEquals(new ArctanFunction().apply(Math.sqrt(3)), Math.PI / 3);
            assertEquals(new ArctanFunction().apply(-Math.sqrt(3)/3), -Math.PI / 6);


    }
    @AfterMethod
    void afterMethod() {
        System.out.println("ArctanFunction checked");
    }

}
