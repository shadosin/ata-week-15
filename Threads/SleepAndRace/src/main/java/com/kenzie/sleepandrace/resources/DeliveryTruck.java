package com.kenzie.sleepandrace.resources;

import java.util.ArrayList;
import java.util.List;

public class DeliveryTruck {

    /**
     * Create a list of packages to deliver.
     * @return List of packages.
     */
    public static List<WarehousePackage> deliverPackages() {
        List<WarehousePackage> packagesOnTruck = new ArrayList<WarehousePackage>();

        packagesOnTruck.add(new WarehousePackage("DVD", true));
        packagesOnTruck.add(new WarehousePackage("Book", true));
        packagesOnTruck.add(new WarehousePackage("Game", false));
        packagesOnTruck.add(new WarehousePackage("Book", false));
        packagesOnTruck.add(new WarehousePackage("Book", true));

        return packagesOnTruck;
    }
}
