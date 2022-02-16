package platform;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    @DisplayName("Add 1 and 2, result should be 3")
    void add() {
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 2);

        assertEquals(3, result);
    }

    @Test
    void subtract() {
    }

    @Test
    @DisplayName("Multiply 2 and 3, result should be 6")
    void multiply() {
        Calculator calculator = new Calculator();
        int result =  calculator.multiply(2, 3);

        assertEquals(6, result);
    }

    @Test
    void divide() {
    }
}