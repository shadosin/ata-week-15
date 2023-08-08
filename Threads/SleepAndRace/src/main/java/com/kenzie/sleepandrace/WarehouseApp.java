package com.kenzie.sleepandrace;

import com.kenzie.sleepandrace.resources.DeliveryTruck;
import com.kenzie.sleepandrace.resources.WarehousePackage;

import java.util.ArrayList;
import java.util.List;

public class WarehouseApp {

    private static List<WarehousePackage> remainingPackages;
    private static List<WarehousePackage> bookPackages;
    private static List<WarehousePackage> highPriorityPackages;
    private static WarehouseManager warehouseManager;

    /**
     * Main method, sends a truck to the warehouse, which sorts the packages and prints out an output.
     * Identify where the race condition is happening and correct it with threadSleep().
     * @param args not used.
     */
    public static void main(String[] args) {
        remainingPackages = new ArrayList<WarehousePackage>();
        warehouseManager = new WarehouseManager();

        //Truck arrives, delivering packages.
        warehouseManager.receivePackages(DeliveryTruck.deliverPackages());

        //Sort out packages into new lists.
        warehouseManager.sortHighPriority();
        warehouseManager.sortBooks();

        //Printout the list of warehouseManager packages and returns them.
        printAndStoreLists();
    }

    /**
     * Makes a thread sleep.
     * @param milliseconds How many milliseconds to sleep.
     */
    public static void sleepThread(int milliseconds) {

    }

    /**
     * Prints counts of all the lists sorted by the WarehouseManager, then stores them.
     */
    public static void printAndStoreLists() {
        remainingPackages = warehouseManager.getIncomingPackages();
        bookPackages = warehouseManager.getBooks();
        highPriorityPackages = warehouseManager.getHPManager().getHighPriorityPackages();

        System.out.println("High Priority packages: " + highPriorityPackages.size());
        System.out.println("Book packages: " + bookPackages.size());
        System.out.println("Remaining incoming packages: " + remainingPackages.size());
    }
}