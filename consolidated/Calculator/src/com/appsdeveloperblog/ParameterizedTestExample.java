package com.appsdeveloperblog;

public class ParameterizedTestExample {

    public int getStringSize(String x) throws NullPointerException
    {
        if (x == null )
        {
            throw new NullPointerException();
        }
        return x.length();
    }
}
