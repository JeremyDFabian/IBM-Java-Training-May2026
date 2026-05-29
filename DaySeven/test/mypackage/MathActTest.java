package mypackage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MathActTest {

    @Test
    public void testAdd() {
        assertEquals(5.0f, MathAct.add(2.0f, 3.0f), 0.0001f);
    }

    @Test
    public void testSubtract() {
        assertEquals(1.0f, MathAct.subtract(4.0f, 3.0f), 0.0001f);
    }

    @Test
    public void testMultiply() {
        assertEquals(12.0f, MathAct.multiply(4.0f, 3.0f), 0.0001f);
    }

    @Test
    public void testDivide() {
        assertEquals(2.0f, MathAct.divide(6.0f, 3.0f), 0.0001f);
    }

    @Test
    public void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> MathAct.divide(6.0f, 0.0f));
    }
}