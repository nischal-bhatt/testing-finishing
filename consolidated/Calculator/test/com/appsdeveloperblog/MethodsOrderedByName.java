package com.appsdeveloperblog;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class MethodsOrderedByName {

    @Test
    void testB() {
        System.out.println("test B");
    }

    @Test
    void testA() {
        System.out.println("test A");
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
