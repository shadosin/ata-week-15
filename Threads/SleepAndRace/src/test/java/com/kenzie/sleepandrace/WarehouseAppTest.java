package com.kenzie.sleepandrace;

import com.kenzie.sleepandrace.resources.WarehousePackage;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class WarehouseAppTest {

    @Test
    public void sleepThread_startTimer_mainThreadPaused() {
        // GIVEN
        WarehouseApp warehouse = new WarehouseApp();

        // WHEN
        long pastTime = System.currentTimeMillis();
        int timeToSleep = 1000;
        warehouse.sleepThread(timeToSleep);

        // THEN
        long currentTime = System.currentTimeMillis();
        assertTrue(currentTime >= (pastTime + timeToSleep), "Sleep method is not pausing thread.");
    }

    @Test
    public void main_compareLists_listsMatching() throws NoSuchFieldException, IllegalAccessException {
        // GIVEN
        boolean allClear = true;
        WarehouseApp warehouseInstance = new WarehouseApp();
        warehouseInstance.main(new String[] {"args"});

        Class<?> wareClass = warehouseInstance.getClass();

        Field remaining =  wareClass.getDeclaredField("remainingPackages");
        remaining.setAccessible(true);
        Field books = wareClass.getDeclaredField("bookPackages");
        books.setAccessible(true);
        Field hp =  wareClass.getDeclaredField("highPriorityPackages");
        hp.setAccessible(true);

        // WHEN
        for (WarehousePackage p : (List<WarehousePackage>) remaining.get(wareClass)) {
            if (p.highPriority || p.packageCategory.equals("Book")) {
                allClear = false;
            }
        }

        for (WarehousePackage p : (List<WarehousePackage>) books.get(wareClass)) {
            if (p.highPriority || !p.packageCategory.equals("Book")) {
                allClear = false;
            }
        }

        for (WarehousePackage p : (List<WarehousePackage>) hp.get(wareClass)) {
            if (!p.highPriority) {
                allClear = false;
            }
        }

        // THEN
        assertTrue(allClear, "The output is still suffering from a Race Condition");
    }
}
