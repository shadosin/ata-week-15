package com.kenzie.createthreads;

import com.kenzie.createthreads.resources.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryManager implements Runnable {

    public List<WarehousePackage> incomingPackages;
    public List<WarehousePackage> additionalProcessing;

    /**
     * Constructor for DeliveryManager.
     * @param packages incomingPackages.
     */
    public DeliveryManager(List<WarehousePackage> packages) {
        super();
        incomingPackages = packages;
        additionalProcessing = new ArrayList<WarehousePackage>();
    }

    /**
     * Code to be run on thread start. (Needs to be completed.)
     */
    public void run() {
        System.out.println("DeliveryManager thread started.");
        sortShipment();
        printInventory();
    }

    /**
     * Sorts out packages for additional processing.
     */
    public void sortShipment() {

        // Add marked packages.
        for (WarehousePackage p : incomingPackages) {
            if (p.isAdditionalProcessing()) {
                additionalProcessing.add(p);
            }
        }

        // Sort them out of incoming.
        incomingPackages.removeAll(additionalProcessing);
    }

    /**
     * Prints out the inventory of both lists.
     */
    public void printInventory() {
        System.out.println("\nPackages to be delivered:");
        for (WarehousePackage p : incomingPackages) {
            p.packageInfo();
        }

        System.out.println("\nPackages with additional processing:");
        for (WarehousePackage p : additionalProcessing) {
            p.packageInfo();
        }
    }
}
