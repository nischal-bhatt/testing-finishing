package com.appsdeveloperblog;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ParameterizedTestExampleTest {

    private static Stream<Arguments> testStringLengthMethod() {
        return Stream.of(
                Arguments.of("hello", 5),
                Arguments.of("yoyo", 4),
                Arguments.of("aaramnagar", 10),
                Arguments.of(null,2),
                Arguments.of("",0)
        );
    }

    @DisplayName("Test string length method")
    @ParameterizedTest
    @MethodSource
    void testStringLengthMethod(String string, int expectedSize) {

        System.out.println("Running...");
        ParameterizedTestExample parameterizedTestExample
                = new ParameterizedTestExample();

        if (string == null) {
            assertThrows(NullPointerException.class,
                    () -> {
                        parameterizedTestExample.getStringSize(string);
                    });
        }else {

            int size = parameterizedTestExample.getStringSize(string);

            assertEquals(expectedSize, size);
        }

    }

    @CsvFileSource(resources = "/integerDivision.csv")
    @ParameterizedTest
    void testAtest(String x, int b)
    {
       ParameterizedTestExample parameterizedTestExample
               = new ParameterizedTestExample();

       int r = parameterizedTestExample.getStringSize(x);
       assertEquals(b,r);
    }


}