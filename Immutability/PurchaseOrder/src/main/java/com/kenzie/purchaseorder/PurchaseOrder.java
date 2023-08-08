package com.kenzie.purchaseorder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A PurchaseOrder tracks a sales contract between Amazon and a vendor.
 */
public class PurchaseOrder {

    /**
     * Constructor.
     * @param orderDate - Date purchase was made
     * @param subtotal - Subtotal of purchase
     * @param vendor - Vendor name
     * @param items - List of items purchased.
     */
    public PurchaseOrder(ZonedDateTime orderDate, BigDecimal subtotal, String vendor, List<String> items) {

    }

    /**
     * Determine the total billable cost including taxes.
     * @param taxRate - The appropriate tax rate.
     * @return Cost including tax rate.
     */
    public BigDecimal determineBillableCost(Double taxRate) {
        return new BigDecimal("0.0");
    }

    /**
     * Getter for subtotal.
     * @return subtotal
     */
    public BigDecimal getSubtotal() {
        return new BigDecimal("0.0");
    }

    /**
     * Getter for vendor.
     * @return vendor
     */
    public String getVendor() {
        return "";
    }

    /**
     * Getter for item list.
     * @return item list
     */
    public List<String> getItems() {
        return new ArrayList<String>();
    }

    /**
     * Getter for Order Date.
     * @return Order Date
     */
    public ZonedDateTime getOrderDate() {
        return ZonedDateTime.now();
    }
}
