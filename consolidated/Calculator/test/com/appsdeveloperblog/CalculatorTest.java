package com.appsdeveloperblog;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math Operations in calculator class")
class CalculatorTest {

    Calculator calculator;


    @BeforeAll
    static void setUp() {
        System.out.println("executing method before");
    }

    @AfterAll
    static void cleanUp() {
        System.out.println("executing method after");
    }

    @BeforeEach
    void setup() {
        calculator = new Calculator();
        System.out.println("this is a lifecycle method");
    }

    //test<System Under TEest><Condition Or state change><expected result>
    @DisplayName("Class level display name is not working")
    @Test
    void testIntegerAddition_whenTwoIsAddedToThree_ShouldReturn5() {
        //Arrange - preparation Given
        //Calculator calculator = new Calculator();
        //Act - invoke method you are testing When
        Integer result = calculator.adder(2, 3);
        //Assert - do the validation Then
        assertEquals(5, result, "failed!");
    }

    //@Disabled("TODO: STill need to work on it")
    @Test
    void testIntegerDivision_WhenDividentIsDividedByZero_ShouldThrowArithmeticException() {

        //Arrange
        int divident = 4;
        int divisor = 0;
        //Act && Assert

        ArithmeticException arithmeticException=assertThrows(ArithmeticException.class, () -> {
            calculator.integerDivision(divident, divisor);

        }, () -> "should have thrown arithmetic exception");

        assertEquals("/ by zero",arithmeticException.getMessage());

    }


    @Test
    void TestIntegerSubtraction() {
        //Arrange
        //Calculator calculator = new Calculator();
        //Act
        Integer result = calculator.integerSubtraction(3, 2);
        //Assert
        assertEquals(1, result);
    }

    @Test
    void TestIntegerSub() {
        //Calculator calculator = new Calculator();
        int minuend = 33;
        int subtrahend = 1;
        int expectedResult = 32;

        int actualResult = calculator.integerSubtraction(minuend, subtrahend);

        assertEquals(expectedResult, actualResult, minuend + "-" + subtrahend + "did not produce" + expectedResult);
        //this is lazy assert message
        //below
        assertEquals(expectedResult, actualResult,
                //this only gets executed when this assertion fails the test method
                () -> minuend + "-" + subtrahend + "did not produce" + expectedResult);
    }

    @AfterEach
    void hey() {
        System.out.println("printing after each");
    }
}