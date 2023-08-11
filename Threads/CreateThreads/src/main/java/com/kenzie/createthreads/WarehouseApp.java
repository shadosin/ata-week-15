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
        startDeliveryThread(DeliveryTruck.deliverPackages());
    }

    /**
     * Starts the delivery thread up. (Needs to be completed.)
     * @param packages Delivered packages.
     */
    public static void startDeliveryThread(List<WarehousePackage> packages) {
        deliveryWarehouse1 = new DeliveryManager(packages);
      Thread warehouseThread = new Thread(deliveryWarehouse1);

      warehouseThread.start();

    }

    public DeliveryManager getDeliveryWarehouse1() {
        return deliveryWarehouse1;
    }
}
