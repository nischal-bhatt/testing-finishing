package com.appsdeveloperblog;

import org.junit.jupiter.api.*;

public class DemoRepeatedTest {

    @BeforeEach
    void setUp()
    {
        System.out.println("hey");
    }

    @DisplayName("s")
    @RepeatedTest(value=3,name="{displayName}, Repetition {currentRepetition} of {totalRepetitions}")
    void repeatedTest(RepetitionInfo repetitionInfo,
                      TestInfo testInfo)
    {

        System.out.println(testInfo.getTestMethod().get().getName());
       int r = repetitionInfo.getCurrentRepetition();
        System.out.println(r);
    }

}
