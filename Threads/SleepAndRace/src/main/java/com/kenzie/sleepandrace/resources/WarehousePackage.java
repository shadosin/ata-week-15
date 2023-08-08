package com.kenzie.sleepandrace.resources;

public class WarehousePackage {

    public String packageCategory;
    public boolean highPriority;

    /**
     * WarehousePackage Constructor.
     * @param cat assigned packageCategory.
     * @param high assigned highPriority.
     */
    public WarehousePackage(String cat, boolean high) {
        packageCategory = cat;
        highPriority = high;

        System.out.println("New Package. [packageCategory: " +
                packageCategory + " highPriority: " + highPriority + "]");
    }
}
