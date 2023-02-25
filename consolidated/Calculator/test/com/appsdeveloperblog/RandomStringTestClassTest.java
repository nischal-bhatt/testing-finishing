package com.appsdeveloperblog;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RandomStringTestClassTest {

    RandomStringTestClass randomStringTestClass;

    @BeforeAll
    void setUp()
    {
         randomStringTestClass
                = new RandomStringTestClass();
    }

    @Test
    void getSizeOfString() {
        int size = randomStringTestClass.getSizeOfString("hello");
        assertEquals(5,size);
    }



    @Test
    void echo() {
        String b = randomStringTestClass.echo("hey");
        assertEquals("hey",b);
    }
}