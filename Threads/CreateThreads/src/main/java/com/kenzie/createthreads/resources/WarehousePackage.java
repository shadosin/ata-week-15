package com.kenzie.createthreads.resources;

public class WarehousePackage {

    private int packageID;
    private String packageContents;
    private boolean additionalProcessing;

    /**
     * Constructor for WarehousePackage.
     * @param id packageID.
     * @param contents packageContents.
     * @param processing additionalProcessing.
     */
    public WarehousePackage(int id, String contents, boolean processing) {
        packageID = id;
        packageContents = contents;
        additionalProcessing = processing;
    }

    public boolean isAdditionalProcessing() {
        return additionalProcessing;
    }

    /**
     * Prints info about package to console.
     */
    public void packageInfo() {
        System.out.println("PackageID: [" + packageID + "] Contents: [" + packageContents +
                "] Additional Processing: [" + additionalProcessing + "]");
    }
}
