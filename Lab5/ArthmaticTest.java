import static org.junit.jupiter.api.Assertions.*;

class ArthmaticTest {

    //5 methods - 5 test cases each

    @org.junit.jupiter.api.Test
    void multiply()
    {
        Arthmatic test = new Arthmatic();
        assertEquals(6, test.multiply(2, 3)); // 2*3 = 6
        assertEquals(0, test.multiply(0, 0)); // 0*0 = 0
        assertEquals(-4, test.multiply(-2, 2)); // -2*2 = -4
        assertEquals(-4, test.multiply(2, -2)); // 2*-2 = -4
        assertEquals(4, test.multiply(-2, -2)); // -2*-2 = 4
    }

    @org.junit.jupiter.api.Test
    void subtract()
    {
        Arthmatic test = new Arthmatic();
        assertEquals(0, test.subtract(2, 2)); // 2-2 = 0
        assertEquals(0, test.subtract(0, 0)); // 0-0 = 0
        assertEquals(-4, test.subtract(-2, 2)); // -2-2 = -4
        assertEquals(4, test.subtract(2, -2)); // 2-(-2) = 4
        assertEquals(0, test.subtract(-2, -2)); // -2-(-2) = 0
    }

    @org.junit.jupiter.api.Test
    void add()
    {
        Arthmatic test = new Arthmatic();
        assertEquals(5, test.add(2, 3)); // 2+3 = 5
        assertEquals(0, test.add(0, 0)); // 0+0 = 0
        assertEquals(0, test.add(-2, 2)); // -2+2 = 0
        assertEquals(0, test.add(2, -2)); // 2+(-2) = 0
        assertEquals(-4, test.add(-2, -2)); // -2+(-2) = -4
    }

    @org.junit.jupiter.api.Test
    void divide()
    {
        Arthmatic test = new Arthmatic();
        assertEquals(1, test.divide(2, 2)); // 2/2 = 1
        assertEquals(0, test.divide(0, 2)); // 0/2 = 0
        assertEquals(-1, test.divide(-2, 2)); // -2/2 = -1
        assertEquals(-1, test.divide(2, -2)); // 2/-2 = -1
        assertEquals(1, test.divide(-2, -2)); // -2/-2 = 1
    }

    @org.junit.jupiter.api.Test
    void divideByZero()
    {
        Arthmatic test = new Arthmatic();
        assertTrue(test.divideByZero(0)); // 0/2 = 0
        assertTrue(test.divideByZero(-0)); // -0/2 = 0
        assertFalse(test.divideByZero(2)); // 2/2 = 1
        assertFalse(test.divideByZero(-2)); // -2/0 = 0
        assertFalse(test.divideByZero(-10)); // 10/0 = 0

    }
}