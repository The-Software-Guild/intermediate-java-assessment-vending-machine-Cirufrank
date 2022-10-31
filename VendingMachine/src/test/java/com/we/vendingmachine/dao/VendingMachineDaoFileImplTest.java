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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This class automates the Unit tests for the 
 * VendingMachineDao using JUnit
 */

//Here just incase I'd like to run tests concurrently
//@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(VendingMachineParameterResolver.class)
@DisplayName("Vending Machine Data Access Object tests")
public class VendingMachineDaoFileImplTest {
//    inventoryFileTestName and testDao variables no longer needed due to use of Parameter Resolver
//    final private String inventoryFileTestName = "test-inventory.txt";
//    private VendingMachineDaoStubFileImpl testDao;
    
    public VendingMachineDaoFileImplTest() {
    }
    
    //Tests that the 9 items in inventory are returned in a List when using the
    //getAllItems method
    @Test
    @DisplayName("Test getAllItems method")
    public void testGetAllItems(VendingMachineDaoStubFileImpl testDao) {
        final int TOTAL_LIST_ITEMS = 9;
        final List<VendingMachineItem> allItems = testDao.getAllItems();
        for (VendingMachineItem curItem : allItems) {
            final String currentItemString = testDao.marshallItem(curItem);
            System.out.println(currentItemString);
        }
        assertEquals(TOTAL_LIST_ITEMS, allItems.size(), "Total items in inventory int shoud "
                + "be " + TOTAL_LIST_ITEMS + " when list of items is requested from the DAO");
    }
    
    //Tests the VendingMachineItem with an ID of 3 can be successfully retrieved
    //Through the getItem method
    @ParameterizedTest
    @ValueSource(ints = {3})
    @DisplayName("Test getItem method")
    public void testGetItem(int itemId, VendingMachineDaoStubFileImpl testDao) {
        final VendingMachineItem testItem = testDao.getItem(itemId);
        String accurateItemString = "3::Testy: Crunchy Oats and Honey Granola Bar::0.50::";
        final String vendingMachineItemTestString = testDao.marshallItem(testItem);
        accurateItemString += testItem.getNumOfItems();
        assertEquals(accurateItemString,vendingMachineItemTestString, "When getting item "
                + "id: " + itemId + " the string representation of the item should be equal to " + accurateItemString);
        
    }
    
    //Tests that the VendingMachineDaoFileImpl class' purchaseItem method 
    //successfully removes the number of the items from inventory and then 
    //returns the item with its inventory number now decremented
    @ParameterizedTest
    @ValueSource(ints = {7, 9})
    @DisplayName("Test purchaseItem method")
    public void testPurchaseItem(int itemId, VendingMachineDaoStubFileImpl testDao) throws VendingMachineDaoPersistenceException {
        final int ONE_ITEM = 1, NO_ITEMS = 0;
        final VendingMachineItem purchasedItem = testDao.getItem(itemId);
        final int prevNumOfItems = purchasedItem.getNumOfItems();
        final int accurateUpdatedNumOfItems = prevNumOfItems - ONE_ITEM;
        if (accurateUpdatedNumOfItems > NO_ITEMS) {
            final VendingMachineItem itemAfterPurchase = testDao.purchaseItem(itemId);
        final int testUpdateItemNum = itemAfterPurchase.getNumOfItems();
        assertEquals(accurateUpdatedNumOfItems, testUpdateItemNum, "After items is purchased the "
                + "inventory should have one less of this item");
        final VendingMachineItem testItemFromRoster = testDao.getItem(itemId);
        assertEquals(accurateUpdatedNumOfItems, testItemFromRoster.getNumOfItems(), "After items is purchased the "
                + "item should reflect this upon retrieval");
        }    
    }
    
}
