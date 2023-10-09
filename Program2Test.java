/*
 * your name
 * Program 2 - 
 */
/* REMOVE THIS WHEN JUNIT IS FIXED!!
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Program2Test {

    private static final double DELTA = 1. / 10000;

    @Test
    public void testZeroAddZero() {
        assertEquals(0, Program2.add(0, 0));
    }

    @Test
    public void testZeroAddPositive() {
        assertEquals(5, Program2.add(0, 5));
    }

    @Test
    public void testPositiveAddZero() {
        assertEquals(5, Program2.add(5, 0));
    }

    @Test
    public void testPositiveAddPositive() {
        assertEquals(7, Program2.add(5, 2));
        assertEquals(7, Program2.add(2, 5));
    }

    @Test
    public void testZeroAddNegative() {
        assertEquals(-5, Program2.add(0, -5));
    }

    @Test
    public void testNegativeAddZero() {
        assertEquals(-5, Program2.add(-5, 0));
    }

    @Test
    public void testNegativeAddNegative() {
        assertEquals(-7, Program2.add(-5, -2));
        assertEquals(-7, Program2.add(-2, -5));
    }

    @Test
    public void testPositiveAddNegative() {
        assertEquals(3, Program2.add(5, -2));
    }

    @Test
    public void testNegativeAddPositive() {
        assertEquals(-3, Program2.add(-5, 2));
    }

    @Test
    public void testZeroSubtractZero() {
        assertEquals(0, Program2.subtract(0, 0));
    }

    @Test
    public void testZeroSubtractPositive() {
        assertEquals(-5, Program2.subtract(0, 5));
    }

    @Test
    public void testPositiveSubtractZero() {
        assertEquals(5, Program2.subtract(5, 0));
    }

    @Test
    public void testPositiveSubtractPositive() {
        assertEquals(3, Program2.subtract(5, 2));
        assertEquals(-3, Program2.subtract(2, 5));
    }

    @Test
    public void testZeroSubtractNegative() {
        assertEquals(5, Program2.subtract(0, -5));
    }

    @Test
    public void testNegativeSubtractZero() {
        assertEquals(-5, Program2.subtract(-5, 0));
    }

    @Test
    public void testNegativeSubtractNegative() {
        assertEquals(-3, Program2.subtract(-5, -2));
        assertEquals(3, Program2.subtract(-2, -5));
    }

    @Test
    public void testPositiveSubtractNegative() {
        assertEquals(7, Program2.subtract(5, -2));
    }

    @Test
    public void testNegativeSubtractPositive() {
        assertEquals(-7, Program2.subtract(-5, 2));
    }

    @Test
    public void testZeroMultiplyZero() {
        assertEquals(0, Program2.multiply(0, 0));
    }

    @Test
    public void testZeroMultiplyPositive() {
        assertEquals(0, Program2.multiply(0, 5));
    }

    @Test
    public void testPositiveMultiplyZero() {
        assertEquals(0, Program2.multiply(5, 0));
    }

    @Test
    public void testPositiveMultiplyPositive() {
        assertEquals(10, Program2.multiply(5, 2));
        assertEquals(10, Program2.multiply(2, 5));
    }

    @Test
    public void testZeroMultiplyNegative() {
        assertEquals(0, Program2.multiply(0, -5));
    }

    @Test
    public void testNegativeMultiplyZero() {
        assertEquals(0, Program2.multiply(-5, 0));
    }

    @Test
    public void testNegativeMultiplyNegative() {
        assertEquals(10, Program2.multiply(-5, -2));
        assertEquals(10, Program2.multiply(-2, -5));
    }

    @Test
    public void testPositiveMultiplyNegative() {
        assertEquals(-10, Program2.multiply(5, -2));
    }

    @Test
    public void testNegativeMultiplyPositive() {
        assertEquals(-10, Program2.multiply(-5, 2));
    }

    @Test
    public void testZeroDivideZero() {
        assertThrows(ArithmeticException.class, () -> Program2.divide(0, 0));
    }

    @Test
    public void testZeroDividePositive() {
        assertEquals(0, Program2.divide(0, 5), DELTA);
    }

    @Test
    public void testPositiveDivideZero() {
        assertThrows(ArithmeticException.class, () -> Program2.divide(5, 0));
    }

    @Test
    public void testPositiveDividePositive() {
        assertEquals(2.5, Program2.divide(5, 2), DELTA);
        assertEquals(0.4, Program2.divide(2, 5), DELTA);
    }

    @Test
    public void testZeroDivideNegative() {
        assertEquals(0, Program2.divide(0, -5), DELTA);
    }

    @Test
    public void testNegativeDivideZero() {
        assertThrows(ArithmeticException.class, () -> Program2.divide(-5, 0));
    }

    @Test
    public void testNegativeDivideNegative() {
        assertEquals(2.5, Program2.divide(-5, -2), DELTA);
        assertEquals(0.4, Program2.divide(-2, -5), DELTA);
    }

    @Test
    public void testPositiveDivideNegative() {
        assertEquals(-2.5, Program2.divide(5, -2), DELTA);
    }

    @Test
    public void testNegativeDividePositive() {
        assertEquals(-2.5, Program2.divide(-5, 2), DELTA);
    }
    
    @Test
    public void testGoodSine() {
      assertEquals(0.73, Program2.sinOfAngle(3.0, 4.1), DELTA);
    }
    
    @Test
    public void testBadSine() {
      assertEquals(0.44, Program2.sinOfAngle(3.0, 4.1), DELTA);
    }
    
    @Test
    public void testGoodHyp() {
      assertEquals(1.41, Program2.hypOfTriangle(1.0, 1.0), DELTA);  
    }
     
    @Test
    public void testBadHyp() {
      assertEquals(0.44, Program2.hypOfTriangle(1.0, 2.0), DELTA);
    }


}
REMOVE THIS WHEN YOU FIX JUNIT!!!!*/