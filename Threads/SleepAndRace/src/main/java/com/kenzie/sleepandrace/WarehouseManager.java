package com.kenzie.sleepandrace;

import com.kenzie.sleepandrace.resources.WarehousePackage;

import java.util.ArrayList;
import java.util.List;

public class WarehouseManager {
    private List<WarehousePackage> incomingPackages;
    private List<WarehousePackage> books;
    private HighPriorityManager hPManager;

    /**
     * Constructor initializing objects.
     */
    public WarehouseManager() {
        books = new ArrayList<WarehousePackage>();
        incomingPackages = new ArrayList<WarehousePackage>();
        hPManager = new HighPriorityManager(this);
    }

    public List<WarehousePackage> getIncomingPackages() {
        return incomingPackages;
    }

    public List<WarehousePackage> getBooks() {
        return books;
    }

    public HighPriorityManager getHPManager() {
        return hPManager;
    }

    /**
     * Add all incoming packages.
     * @param packages list of packages being delivered.
     */
    public void receivePackages(List<WarehousePackage> packages) {
        incomingPackages.addAll(packages);
    }

    /**
     * Start thread to sort all High Priority packages out of incomingPackages.
     */
    public void sortHighPriority() {
        Thread hpThread = new Thread(hPManager);
        hpThread.start();
    }

    /**
     * Sort any books to a new list.
     */
    public void sortBooks() {
        List<WarehousePackage> newList = new ArrayList<WarehousePackage>(incomingPackages);

        for (WarehousePackage p : newList) {
            if (p.packageCategory.equals("Book")) {
                books.add(p);
                incomingPackages.remove(p);
            }
        }
    }
}
