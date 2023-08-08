package com.kenzie.sleepandrace;

import com.kenzie.sleepandrace.resources.WarehousePackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Sorts all High Priority packages into a list.
 */
public class HighPriorityManager implements Runnable {

    private List<WarehousePackage> highPriorityPackages;
    private WarehouseManager warehouseManager;

    /**
     * Constructor.
     * @param wManager reference to WarehouseManager instance.
     */
    public HighPriorityManager(WarehouseManager wManager) {
        highPriorityPackages = new ArrayList<WarehousePackage>();
        warehouseManager = wManager;
    }

    public List<WarehousePackage> getHighPriorityPackages() {
        return highPriorityPackages;
    }

    /**
     * Run override for HighPriorityManager.
     */
    public void run() {
        //Sort any High Priority packages to a new list.
        List<WarehousePackage> newList = new ArrayList<WarehousePackage>(warehouseManager.getIncomingPackages());

        for (WarehousePackage p : newList) {
            if (p.highPriority) {
                highPriorityPackages.add(p);
            }
        }

        for (WarehousePackage p: highPriorityPackages) {
            newList.remove(p);
        }

        warehouseManager.getIncomingPackages().clear();
        warehouseManager.getIncomingPackages().addAll(newList);
    }
}
