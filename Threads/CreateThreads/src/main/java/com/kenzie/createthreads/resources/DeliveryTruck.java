package com.kenzie.createthreads.resources;

import java.util.ArrayList;
import java.util.List;

public class DeliveryTruck {

    /**
     * Creates a list of packages.
     * @return package list.
     */
    public static List<WarehousePackage> deliverPackages() {
        List<WarehousePackage> packages = new ArrayList<WarehousePackage>();
        packages.add(new WarehousePackage(1234, "Books", false));
        packages.add(new WarehousePackage(4234, "Games", true));
        packages.add(new WarehousePackage(34534, "DVDs", true));
        packages.add(new WarehousePackage(45613, "Books", false));
        packages.add(new WarehousePackage(6787, "Books", false));

        return packages;
    }
}
