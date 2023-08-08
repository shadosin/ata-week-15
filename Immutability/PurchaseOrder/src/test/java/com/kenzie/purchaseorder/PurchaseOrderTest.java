package com.kenzie.purchaseorder;

import com.kenzie.purchaseorder.PurchaseOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurchaseOrderTest {

    @Test
    public void getClassModifiers_fromPurchaseOrder_isFinal() {
        //GIVEN
        Class purchaseOrderClass = PurchaseOrder.class;
        //WHEN
        boolean isFinal = Modifier.isFinal(purchaseOrderClass.getModifiers());
        //THEN
        Assertions.assertTrue(isFinal, "Immutable classes should not be extendable.");
    }

    @Test
    public void getFieldModifiers_fromPurchaseOrder_areFinal() {
        //GIVEN
        Class purchaseOrderClass  = PurchaseOrder.class;
        int numFields = 4;
        //WHEN & THEN
        Assertions.assertEquals(numFields, purchaseOrderClass.getDeclaredFields().length);
        for (Field field:purchaseOrderClass.getDeclaredFields()) {
            Assertions.assertTrue(Modifier.isFinal(field.getModifiers()),
                    "Some fields were not declared final.");
        }
    }

    @Test
    public void getDeclaredMethods_fromPurchaseOrder_NotFinal() {
        //GIVEN
        Class purchaseOrderClass = PurchaseOrder.class;
        //WHEN & THEN
        for (Method method:purchaseOrderClass.getDeclaredMethods()) {
            Assertions.assertFalse(Modifier.isFinal(method.getModifiers()),
                    "Some methods were incorrectly declared to be final.");
        }
    }

    @Test
    public void getMethods_retrieveClassFields_unchangedSinceConstruction() {
        //GIVEN
        ZonedDateTime now = ZonedDateTime.now();
        PurchaseOrder purchaseOrder = new PurchaseOrder(now, new BigDecimal("100.0"), "The Book Store",
                new ArrayList<String>(Arrays.asList("Outlander", "Harry Potter")));
        //WHEN
        //THEN
        Assertions.assertAll("Purchase order should be what was set",
            () -> assertEquals(now, purchaseOrder.getOrderDate(), "The orderDate value was not set correctly or it was not " +
                    "returned correctly from getOrderDate."),
            () -> assertEquals(new BigDecimal("100.0"), purchaseOrder.getSubtotal(), "The Cost value was not set correctly or " +
                    "it was not returned correctly from getCost."),
            () -> assertEquals("The Book Store", purchaseOrder.getVendor(), "Vendor was not set correctly or not " +
                    "returned correctly from getVendor."),
            () -> assertEquals(new ArrayList<String>(Arrays.asList("Outlander", "Harry Potter")), purchaseOrder.getItems(), "Items " +
                    "was not set correctly or not returned correctly from getItems.")
        );
    }

    @Test
    public void verifyConstructor_retrieveClassFields_unchangedSinceConstruction() {
        //GIVEN
        ZonedDateTime now = ZonedDateTime.now();
        List<String> items = new ArrayList<String>(Arrays.asList("Outlander", "Harry Potter"));
        PurchaseOrder purchaseOrder = new PurchaseOrder(now, new BigDecimal("100.0"), "The Book Store", items);
        //WHEN
        items.add("20,000 Leagues Under the Sea");

        //THEN
        Assertions.assertAll("Purchase order should be what was set",
            () -> assertEquals(now, purchaseOrder.getOrderDate(), "The orderDate value was not set correctly in the constructor " +
                "or it was not returned correctly from getOrderDate"),
            () -> assertEquals(new BigDecimal("100.0"), purchaseOrder.getSubtotal(), "The Cost value was not set " +
                    "correctly in the constructor or it was not returned correctly from getCost."),
            () -> assertEquals("The Book Store", purchaseOrder.getVendor(), "Vendor was incorrectly set."),
            () -> assertEquals(new ArrayList<String>(Arrays.asList("Outlander", "Harry Potter")), purchaseOrder.getItems(),
                    "ArrayList was not immutable after being set in the constructor.")
        );
    }

    @Test
    public void getItems_modifyItemsExternally_itemsNotModified() {
        //GIVEN
        ZonedDateTime now = ZonedDateTime.now();
        PurchaseOrder purchaseOrder = new PurchaseOrder(now, new BigDecimal("100.0"), "The Book Store",
                new ArrayList<String>(Arrays.asList("Outlander")));
        //WHEN
        List<String> externalItems = purchaseOrder.getItems();
        externalItems.add("Harry_Potter");
        //THEN
        assertEquals(Arrays.asList("Outlander"), purchaseOrder.getItems(), "The getItems method should use defensive " +
                "copying in order to keep the list from being modified outside of the object.");
    }

    @Test
    public void determineBillableCost_receivesTaxRate_thenExpectedAmount(){
        ZonedDateTime now = ZonedDateTime.now();
        PurchaseOrder purchaseOrder = new PurchaseOrder(now, new BigDecimal("100.0"), "The Book Store",
                new ArrayList<String>(Arrays.asList("Outlander", "Harry Potter")));

        BigDecimal result = purchaseOrder.determineBillableCost(.05);

        assertEquals(1, result.compareTo(BigDecimal.valueOf(105.0)));
    }

    @Test
    public void determineBillableCost_receivesZeroTaxRate_thenExpectedAmount(){
        ZonedDateTime now = ZonedDateTime.now();
        PurchaseOrder purchaseOrder = new PurchaseOrder(now, new BigDecimal("100.0"), "The Book Store",
                new ArrayList<String>(Arrays.asList("Outlander", "Harry Potter")));

        BigDecimal result = purchaseOrder.determineBillableCost(.00);

        assertEquals(0, result.compareTo(purchaseOrder.getSubtotal()));
    }

}
