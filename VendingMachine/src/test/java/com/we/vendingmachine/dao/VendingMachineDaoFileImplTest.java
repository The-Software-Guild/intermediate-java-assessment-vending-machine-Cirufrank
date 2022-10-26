/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.we.vendingmachine.dao;

import com.we.vendingmachine.dto.VendingMachineItem;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine
 * 
 * @description This class automates the Unit tests for the 
 * VendingMachineDao using JUnit
 */

public class VendingMachineDaoFileImplTest {
    final private String inventoryFileTestName = "test-inventory.txt";
    private VendingMachineDaoFileImpl testDao;
    
    public VendingMachineDaoFileImplTest() {
    }

    @BeforeEach
    public void setUp() {
        testDao = new VendingMachineDaoFileImpl(inventoryFileTestName);
    }

    @Test
    public void testGetAllItems() {
        final int TOTAL_LIST_ITEMS = 9;
        final List<VendingMachineItem> allItems = testDao.getAllItems();
        for (VendingMachineItem curItem : allItems) {
            final String currentItemString = testDao.marshallItem(curItem);
            System.out.println(currentItemString);
        }
        assertEquals(TOTAL_LIST_ITEMS, allItems.size());
    }
    
}
