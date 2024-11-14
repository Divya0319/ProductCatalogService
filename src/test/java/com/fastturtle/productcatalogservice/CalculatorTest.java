package com.fastturtle.productcatalogservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void test_AddWithTwoIntegers_RunSuccessfully() {

        // Arrange
        Calculator calc = new Calculator();

        // Act
        int result = calc.add(21, 51);

        // Assert
        assert(result == 72);
    }

    @Test
    void test_DivideByZero_ThrowsArithmeticException() {
        // Arrange
        Calculator calc = new Calculator();

        // Act
        //int res = calc.divide(1, 0);
        assertThrows(ArithmeticException.class, () -> calc.divide(1,0));
    }
}