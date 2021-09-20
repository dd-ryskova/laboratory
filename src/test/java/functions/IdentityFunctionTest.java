package functions;

import org.testng.annotations.Test;
import functions.IdentityFunction;
import static org.testng.Assert.*;

class IdentityFunctionTest {

        @Test
        public void testApply() {
            assertEquals(new IdentityFunction().apply(3.), 3.);
            assertEquals(new IdentityFunction().apply(25.), 25.);
            assertEquals(new IdentityFunction().apply(456.), 456.);
        }
    }

