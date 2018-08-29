package com.anzisolutions.bankingsimulator.util;

import java.util.ArrayList;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ReturnCaptor<T> implements Answer<T> {
    private ArrayList<T> returnedValues = new ArrayList<T>();
    
    public ArrayList<T> getReturnedValues() {
        return returnedValues;
    }

    @Override
    public T answer(InvocationOnMock invocationOnMock) throws Throwable {
    	T returnedValue = (T) invocationOnMock.callRealMethod();
    	returnedValues.add(returnedValue); 
        return returnedValue;
    }
}