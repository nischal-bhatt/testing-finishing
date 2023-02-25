package com.appsdeveloperblog;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
public class MethodOrderedRandomlyTest {

    @Test
    void testA() {
        System.out.println("test A");
    }

    @Test
    void testB() {
        System.out.println("test B");
    }

    @Test
    void testC() {
        System.out.println("test C");
    }

    @Test
    void testD() {
        System.out.println("test D");
    }
}
