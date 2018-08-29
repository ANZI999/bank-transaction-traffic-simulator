package com.anzisolutions.bankingsimulator.util;

import java.util.concurrent.CopyOnWriteArrayList;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ReturnCaptor<T> implements Answer<T> {
    private CopyOnWriteArrayList<T> returnedValues = new CopyOnWriteArrayList<T>();
    
    public CopyOnWriteArrayList<T> getReturnedValues() {
        return returnedValues;
    }

    @Override
    public T answer(InvocationOnMock invocationOnMock) throws Throwable {
    	T returnedValue = (T) invocationOnMock.callRealMethod();
    	returnedValues.add(returnedValue); 
        return returnedValue;
    }
}