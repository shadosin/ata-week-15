package com.kenzie.createthreads;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.kenzie.createthreads.resources.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WarehouseAppTest {

    @Test
    public void deliveryManager_implementsRunnable() {
        // GIVEN
        DeliveryManager deliveryObj = new DeliveryManager(DeliveryTruck.deliverPackages());

        // WHEN
        Class deliveryClass = deliveryObj.getClass();

        // THEN
        assertTrue(Runnable.class.isAssignableFrom(deliveryClass),
                "DeliveryManager doesn't have a runnable interface.");
    }

    @Test
    public void startDeliveryThread_listCheck_matchingOutput() {
        // GIVEN
        WarehouseApp warehouse = new WarehouseApp();
        List<WarehousePackage> testList = DeliveryTruck.deliverPackages();

        List<WarehousePackage> expectedResult = new ArrayList<WarehousePackage>();
        expectedResult.add(testList.get(1));
        expectedResult.add(testList.get(2));

        // WHEN
        warehouse.startDeliveryThread(testList);

        try {
            // make sure the threads have completed
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // main thread interrupted
            Assertions.fail("Threads were interrupted before a result could be attained.");
        }


        // THEN
        List<WarehousePackage> result = warehouse.getDeliveryWarehouse1().additionalProcessing;
        assertEquals(expectedResult, result, "Didn't get expected output from calling startDeliveryThread().");
    }

    @Test
    public void run_listCheck_matchingOutput() {
        // GIVEN
        List<WarehousePackage> testList = DeliveryTruck.deliverPackages();
        DeliveryManager deliveryTest = new DeliveryManager(testList);

        List<WarehousePackage> expectedResult = new ArrayList<WarehousePackage>();
        expectedResult.add(testList.get(1));
        expectedResult.add(testList.get(2));

        // WHEN
        deliveryTest.run();

        // THEN
        List<WarehousePackage> result = deliveryTest.additionalProcessing;
        assertEquals(expectedResult, result, "Didn't get expected output from calling run().");
    }
}
