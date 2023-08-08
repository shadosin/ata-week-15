package com.kenzie.createthreads;

import java.util.List;
import com.kenzie.createthreads.resources.*;
public class WarehouseApp {

    private static DeliveryManager deliveryWarehouse1;

    /**
     * Main method that starts the application.
     * @param args not used.
     */
    public static void main(String [] args) {
        System.out.println("WarehouseApp thread started.");

        deliveryWarehouse1 = new DeliveryManager(DeliveryTruck.deliverPackages());
        deliveryWarehouse1.sortShipment();
        deliveryWarehouse1.printInventory();
    }

    /**
     * Starts the delivery thread up. (Needs to be completed.)
     * @param packages Delivered packages.
     */
    public static void startDeliveryThread(List<WarehousePackage> packages) {

    }

    public DeliveryManager getDeliveryWarehouse1() {
        return deliveryWarehouse1;
    }
}
