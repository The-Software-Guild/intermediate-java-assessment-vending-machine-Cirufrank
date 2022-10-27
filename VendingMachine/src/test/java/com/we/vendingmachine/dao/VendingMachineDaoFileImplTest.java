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
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine
 * 
 * @description This class automates the Unit tests for the 
 * VendingMachineDao using JUnit
 */

//Here just incase I'd like to run tests concurrently
//@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(VendingMachineParameterResolver.class)
public class VendingMachineDaoFileImplTest {
    final private String inventoryFileTestName = "test-inventory.txt";
    private VendingMachineDaoStubFileImpl testDao;
    
    public VendingMachineDaoFileImplTest() {
    }

    @Test
    public void testGetAllItems(VendingMachineDaoStubFileImpl testDao) {
        //Tests that the 0 items in inventory are returned in a List when using the
        //getAllItems method
        final int TOTAL_LIST_ITEMS = 9;
        final List<VendingMachineItem> allItems = testDao.getAllItems();
        for (VendingMachineItem curItem : allItems) {
            final String currentItemString = testDao.marshallItem(curItem);
            System.out.println(currentItemString);
        }
        assertEquals(TOTAL_LIST_ITEMS, allItems.size());
    }
    
    @ParameterizedTest
    @ValueSource(ints = {3})
    public void testGetItem(int itemId, VendingMachineDaoStubFileImpl testDao) {
        //Tests the VendingMachineItem with an ID of 3 can be successfully retrieved
        //Through the getItem method
        final String accurateItemString = "3::Testy: Crunchy Oats and Honey Granola Bar::0.50::3";
        final String vendingMachineItemTestString = testDao.marshallItem(testDao.getItem(itemId));
        
        assertEquals(accurateItemString,vendingMachineItemTestString);
        
    }
    
    @ParameterizedTest
    @ValueSource(ints = {7, 9})
    public void testPurchaseItem(int itemId, VendingMachineDaoStubFileImpl testDao) {
        //Tests that the VendingMachineDaoFileImpl class' purchaseItem method 
        //successfully removes the number of the items from inventory and then 
        //returns the item with its inventory number now decremented
        final int ONE_ITEM = 1, NO_ITEMS = 0;
        final VendingMachineItem purchasedItem = testDao.getItem(itemId);
        final int prevNumOfItems = purchasedItem.getNumOfItems();
        final int accurateUpdatedNumOfItems = prevNumOfItems - ONE_ITEM;
        if (accurateUpdatedNumOfItems > NO_ITEMS) {
            final VendingMachineItem itemAfterPurchase = testDao.purchaseItem(itemId);
        final int testUpdateItemNum = itemAfterPurchase.getNumOfItems();
        assertEquals(accurateUpdatedNumOfItems, testUpdateItemNum);
        final VendingMachineItem testItemFromRoster = testDao.getItem(itemId);
        assertEquals(accurateUpdatedNumOfItems, testItemFromRoster.getNumOfItems());
        }    
    }
    
}
