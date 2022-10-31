/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.we.vendingmachine.dto;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This class acts as the test suite for the DTO
 * to ensure that all of the getter and setter methods work correctly
 */
public class VendingMachineItemTest {
    
    public VendingMachineItemTest() {
    }

    @Test
    @DisplayName("Test Vending Machine Item constructors")
    public void testConstructors() {
        VendingMachineItem vmi1 = new VendingMachineItem(1, "Banana");
        VendingMachineItem vmi2 = new VendingMachineItem(2, "Banana", new BigDecimal("2.50"));
        VendingMachineItem vmi3 = new VendingMachineItem(3, "Banana", new BigDecimal("2.50"), 10);
        
        assertEquals("Banana",vmi1.getItemName(), "Item name should be 'Banana' when vending "
                + "maching item name is initialized to 'Banana' only");
        assertNull(vmi1.getItemCost(), "Item cost should be null"
                + " when only vending machine item name is initialized");
        assertEquals(0,vmi1.getNumOfItems(), "Number of items should "
                + "be 0 when only vending machine item name is initialized");
        
        assertEquals("Banana",vmi2.getItemName(), "Item name should be 'Banana' when vending "
                + "maching item name is initialized to 'Banana' and item cost is initialized to new BigDecimal('2.50')");
        assertEquals(new BigDecimal("2.50"), vmi2.getItemCost(), "Item cost should be equal to new BigDecimal('2.50') when vending "
                + "maching item name is initialized to 'Banana' and item cost is initialized to new BigDecimal('2.50')");
        assertEquals(0,vmi2.getNumOfItems(), "Number of items should "
                + "be 0 when vending maching item name is initialized to 'Banana' and "
                + "item cost is initialized to new BigDecimal('2.50')");
        
        assertEquals("Banana",vmi3.getItemName(), "Item name should be 'Banana' when vending "
                + "maching item name is initialized to 'Banana' and item cost is initialized to new BigDecimal('2.50') and number of items "
                + "is initialized to 10");
        assertEquals(new BigDecimal("2.50"), vmi3.getItemCost(), "Item cost should be equal to new BigDecimal('2.50') when vending "
                + "maching item name is initialized to 'Banana' and item cost is initialized to new BigDecimal('2.50') and number of items "
                + "is initialized to 10");
        assertEquals(10, vmi3.getNumOfItems(), "Number of items should be equal to 10 when vending "
                + "maching item name is initialized to 'Banana' and item cost is initialized to new BigDecimal('2.50') and number of items "
                + "is initialized to 10");
        
        
        
    }
    @Test
    @DisplayName("Test Vending Machine Item setter methods")
    public void testSetterMethod() {
        VendingMachineItem vmi1 = new VendingMachineItem(1, "Banana");
        VendingMachineItem vmi2 = new VendingMachineItem(2, "Banana", new BigDecimal("2.50"));
        VendingMachineItem vmi3 = new VendingMachineItem(3, "Banana", new BigDecimal("2.50"), 10);
        
        vmi1.setNumOfItems(100);
        vmi2.setNumOfItems(20);
        vmi3.setNumOfItems(175);
        
        assertEquals(100, vmi1.getNumOfItems(), "Vendimg machine item's number "
                + "of items should be 100 after setting this property to 100.");
        assertEquals(20, vmi2.getNumOfItems(), "Vendimg machine item's number "
                + "of items should be 20 after setting this property to 20.");
        assertEquals(175, vmi3.getNumOfItems(), "Vendimg machine item's number "
                + "of items should be 175 after setting this property to 175.");
    }
    
}
