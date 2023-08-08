package com.kenzie.immutabletime;

import com.kenzie.test.infrastructure.reflect.MethodInvoker;
import com.kenzie.test.infrastructure.reflect.MethodQuery;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class ImmutableTimeTest {

    @Test
    public void immutableTimeTest_addMinutes_noHourChange() {
        Method method = assertDoesNotThrow(() ->
                        MethodQuery.inType(ImmutableTime.class)
                                .withExactName("plusMinutes")
                                .findMethodOrFail(),
                "The Method plusMinutes method must exist");


        ImmutableTime startTime = new ImmutableTime(12,0);

        ImmutableTime newTime = (ImmutableTime) MethodInvoker.invokeInstanceMethodWithReturnValue(
                startTime,
                method,
                30);
        ImmutableTime correctNewTime = new ImmutableTime(12,30);
        assertEquals(newTime.getHour(), correctNewTime.getHour());
        assertEquals(newTime.getMinute(), correctNewTime.getMinute());
    }

    @Test
    public void immutableTimeTest_addMinutes_oneHourChange() {
        Method method = assertDoesNotThrow(() ->
                        MethodQuery.inType(ImmutableTime.class)
                                .withExactName("plusMinutes")
                                .findMethodOrFail(),
                "The Method plusMinutes method must exist");

        ImmutableTime startTime = new ImmutableTime(12,0);
        ImmutableTime newTime = (ImmutableTime) MethodInvoker.invokeInstanceMethodWithReturnValue(
                startTime,
                method,
                65);
        ImmutableTime correctNewTime = new ImmutableTime(13,5);
        assertEquals(newTime.getHour(), correctNewTime.getHour());
        assertEquals(newTime.getMinute(), correctNewTime.getMinute());
    }

    @Test
    public void immutableTimeTest_addMinutes_multiHourChange() {
        Method method = assertDoesNotThrow(() ->
                        MethodQuery.inType(ImmutableTime.class)
                                .withExactName("plusMinutes")
                                .findMethodOrFail(),
                "The Method plusMinutes method must exist");

        ImmutableTime startTime = new ImmutableTime(12,55);
        ImmutableTime newTime = (ImmutableTime) MethodInvoker.invokeInstanceMethodWithReturnValue(
                startTime,
                method,
                130);
        ImmutableTime correctNewTime = new ImmutableTime(15,5);
        assertEquals(newTime.getHour(), correctNewTime.getHour());
        assertEquals(newTime.getMinute(), correctNewTime.getMinute());
    }

    @Test
    public void immutableTimeTest_addHours_oneHourChange() {
        Method method = assertDoesNotThrow(() ->
                        MethodQuery.inType(ImmutableTime.class)
                                .withExactName("plusHours")
                                .findMethodOrFail(),
                "The Method plusHours method must exist");

        ImmutableTime startTime = new ImmutableTime(12,0);
        ImmutableTime newTime = (ImmutableTime) MethodInvoker.invokeInstanceMethodWithReturnValue(
                startTime,
                method,
                1);
        ImmutableTime correctNewTime = new ImmutableTime(13,0);
        assertEquals(newTime.getHour(), correctNewTime.getHour());
        assertEquals(newTime.getMinute(), correctNewTime.getMinute());
    }

    @Test
    public void immutableTimeTest_addHours_multiHourChange() {
        Method method = assertDoesNotThrow(() ->
                        MethodQuery.inType(ImmutableTime.class)
                                .withExactName("plusHours")
                                .findMethodOrFail(),
                "The Method plusHours method must exist");

        ImmutableTime startTime = new ImmutableTime(12,0);
        ImmutableTime newTime = (ImmutableTime) MethodInvoker.invokeInstanceMethodWithReturnValue(
                startTime,
                method,
                13);
        ImmutableTime correctNewTime = new ImmutableTime(1,0);
        assertEquals(newTime.getHour(), correctNewTime.getHour());
        assertEquals(newTime.getMinute(), correctNewTime.getMinute());
    }
    @Test
    public void immutableTimeTest_minusMinutes_noHourChange() {
        Method method = assertDoesNotThrow(() ->
                        MethodQuery.inType(ImmutableTime.class)
                                .withExactName("minusMinutes")
                                .findMethodOrFail(),
                "The Method minusMinutes method must exist");

        ImmutableTime startTime = new ImmutableTime(12,30);
        ImmutableTime newTime = (ImmutableTime) MethodInvoker.invokeInstanceMethodWithReturnValue(
                startTime,
                method,
                30);
        ImmutableTime correctNewTime = new ImmutableTime(12,0);
        assertEquals(newTime.getHour(), correctNewTime.getHour());
        assertEquals(newTime.getMinute(), correctNewTime.getMinute());
    }

    @Test
    public void immutableTimeTest_minusMinutes_oneHourChange() {
        Method method = assertDoesNotThrow(() ->
                        MethodQuery.inType(ImmutableTime.class)
                                .withExactName("minusMinutes")
                                .findMethodOrFail(),
                "The Method minusMinutes method must exist");

        ImmutableTime startTime = new ImmutableTime(12,30);
        ImmutableTime newTime = (ImmutableTime) MethodInvoker.invokeInstanceMethodWithReturnValue(
                startTime,
                method,
                35);
        ImmutableTime correctNewTime = new ImmutableTime(11,55);
        assertEquals(newTime.getHour(), correctNewTime.getHour());
        assertEquals(newTime.getMinute(), correctNewTime.getMinute());
    }

    @Test
    public void immutableTimeTest_minusMinutes_multiHourChange() {
        Method method = assertDoesNotThrow(() ->
                        MethodQuery.inType(ImmutableTime.class)
                                .withExactName("minusMinutes")
                                .findMethodOrFail(),
                "The Method minusMinutes method must exist");

        ImmutableTime startTime = new ImmutableTime(12,0);
        ImmutableTime newTime = (ImmutableTime) MethodInvoker.invokeInstanceMethodWithReturnValue(
                startTime,
                method,
                125);
        ImmutableTime correctNewTime = new ImmutableTime(9,55);
        assertEquals(newTime.getHour(), correctNewTime.getHour());
        assertEquals(newTime.getMinute(), correctNewTime.getMinute());
    }

    @Test
    public void immutableTimeTest_minusHours_oneHourChange() {
        Method method = assertDoesNotThrow(() ->
                        MethodQuery.inType(ImmutableTime.class)
                                .withExactName("minusHours")
                                .findMethodOrFail(),
                "The Method minusHours method must exist");

        ImmutableTime startTime = new ImmutableTime(12,0);
        ImmutableTime newTime = (ImmutableTime) MethodInvoker.invokeInstanceMethodWithReturnValue(
                startTime,
                method,
                1);
        ImmutableTime correctNewTime = new ImmutableTime(11,0);
        assertEquals(newTime.getHour(), correctNewTime.getHour());
        assertEquals(newTime.getMinute(), correctNewTime.getMinute());
    }

    @Test
    public void immutableTimeTest_minusHours_multiHourChange() {
        Method method = assertDoesNotThrow(() ->
                        MethodQuery.inType(ImmutableTime.class)
                                .withExactName("minusHours")
                                .findMethodOrFail(),
                "The Method minusHours method must exist");

        ImmutableTime startTime = new ImmutableTime(12,0);
        ImmutableTime newTime = (ImmutableTime) MethodInvoker.invokeInstanceMethodWithReturnValue(
                startTime,
                method,
                13);
        ImmutableTime correctNewTime = new ImmutableTime(23,0);
        assertEquals(newTime.getHour(), correctNewTime.getHour());
        assertEquals(newTime.getMinute(), correctNewTime.getMinute());
    }

    @Test
    public void immutableTimeTest_invalidAmountOfHours() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ImmutableTime immutableTime = new ImmutableTime(24, 0);
        });
    }

    @Test
    public void immutableTimeTest_invalidAmountOfMinutes() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ImmutableTime immutableTime = new ImmutableTime(23, 60);
        });
    }

    @Test
    public void immutableTimeTest_validAmountOfHoursAndMinutes() {
        try {
            ImmutableTime immutableTime = new ImmutableTime(23,59);
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void immutableTimeTest_validAmountOfHoursAndMinutes2() {
        try {
            ImmutableTime immutableTime = new ImmutableTime(0,0);
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
