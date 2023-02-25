package com.appsdeveloperblog;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MethodsOrderedByOrderIndex {

    StringBuilder completed = new StringBuilder("");

    @Order(1)
    @Test
    void testB() {
        System.out.println("test B");
        completed.append("1");
    }

    @Order(2)
    @Test
    void testA() {
        System.out.println("test A");
        completed.append("2");
    }


    @Order(3)
    @Test
    void testC() {
        System.out.println("test C");
        completed.append("3");
    }

    @Order(4)
    @Test
    void testD() {
        System.out.println("test D");
        System.out.println(completed);
    }
}
