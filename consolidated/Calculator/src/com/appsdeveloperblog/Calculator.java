package com.appsdeveloperblog;

public class Calculator {

    //can manually create the test method by
    //right clicking on the method
    //generate
    //then choose test.. no need
    //to manually go and create the packages
    // under test folder etc
     public int adder(int a1, int a2)
     {
         System.out.println("aloha aloha");
         return a1+a2;
     }

     public int integerDivision(int a1, int a2)
     {
         return a1/a2;
     }

    public int integerSubtraction(int minuend, int subtrahend) {
        return minuend - subtrahend;
    }

}
