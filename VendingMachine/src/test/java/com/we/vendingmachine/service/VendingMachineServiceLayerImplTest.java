/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.we.vendingmachine.service;

import com.we.vendingmachine.dao.VendingMachineBankDaoStubFileImpl;
import com.we.vendingmachine.dao.VendingMachineDaoPersistenceException;
import com.we.vendingmachine.dao.VendingMachineDaoStubFileImpl;
import com.we.vendingmachine.dto.Coin;
import com.we.vendingmachine.dto.CoinName;
import com.we.vendingmachine.dto.UserChange;
import com.we.vendingmachine.dto.VendingMachineItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import  java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This class automates the Unit tests for the 
 * VendingMachineServiceLayer using JUnit
 */

@ExtendWith(VendingMachineServiceImplParameterResolver.class)
@DisplayName("Vending Machine Service Layer Test")
public class VendingMachineServiceLayerImplTest {
    
    public VendingMachineServiceLayerImplTest() {
    }
    //Tests that after an item is purchased the coin inventory is updated with the number of change coins withdrawn,
    //the item inventory is updated with the quantity of the item removed, and the proper change is returned to the user
    @ParameterizedTest
    @ValueSource(ints = {3})
    @DisplayName("Pruchase item test")
    public void testPurchaseItem(int itemId, VendingMachineServiceLayerStubImpl testServiceLayer) throws VendingMachineInsufficientFundsException,
            VendingMachineDaoPersistenceException, VendingMachineItemUnavailableException {
        final CoinName QUARTER = CoinName.QUARTER;
        final VendingMachineDaoStubFileImpl publicTestDao = new VendingMachineDaoStubFileImpl("test-inventory.txt");
        final VendingMachineBankDaoStubFileImpl publicTestBankDao = new VendingMachineBankDaoStubFileImpl("test-coin-inventory.txt");
        final BigDecimal testChange = new BigDecimal("2.00");
        final VendingMachineItem testItem = publicTestDao.getItem(itemId);
        final int previousTotalInStock = testItem.getNumOfItems();
        final int previousQuartersInStock = publicTestBankDao.getCoin(QUARTER).getCoinTotal();
        final int ACCURATE_QUARTER_CHANGE_TOTAL = 6, ONE_ITEM = 1;
        UserChange totalChange = testServiceLayer.purchaseItem(testChange, testItem);
        final int currentQuartersInStock = publicTestBankDao.getCoin(QUARTER).getCoinTotal();
        Coin userQuarters = totalChange.getQuarters();
        ArrayList<Coin> userCoins = totalChange.getCoins();
        for (Coin currentCoin : userCoins) {
            System.out.println(currentCoin.toString());
        }
        final VendingMachineItem updatedTestItem = publicTestDao.getItem(itemId);
        //Test that quarters given in change to user are equal to the correct amount
        assertEquals(ACCURATE_QUARTER_CHANGE_TOTAL, userQuarters.getCoinTotal(), ACCURATE_QUARTER_CHANGE_TOTAL + 
                " should be given to user through change after a $0.50 item is purchased with $1.00 given "
                        + "by the user");
        //Test that the items of that type in stock have been decremented in the inventry to 
        //reflect the purchase
        assertEquals(previousTotalInStock, updatedTestItem.getNumOfItems() + ONE_ITEM, "The number of an"
                + " item in stock should be decremented by one after a pruchase is made");
        //Test that the coin bank inventory reflects the withdrawn quarters
        assertEquals(previousQuartersInStock, currentQuartersInStock + ACCURATE_QUARTER_CHANGE_TOTAL, "The "
        + ACCURATE_QUARTER_CHANGE_TOTAL + " quarters given in change should be reflected within the "
                + "coin bank inventory after purchase");
    }
    
    //Tests that a VendingMachineItemUnavailableException is thrown when a out of stock item is attempted
    //to be purchased
    @ParameterizedTest
    @ValueSource(ints = {9})
    @DisplayName("Purchase item when item out of stock")
    public void testPurchaseItemOutOfStock(int itemId, VendingMachineServiceLayerStubImpl testServiceLayer) throws VendingMachineInsufficientFundsException,
            VendingMachineDaoPersistenceException {
        final CoinName QUARTER = CoinName.QUARTER;
        final VendingMachineDaoStubFileImpl publicTestDao = new VendingMachineDaoStubFileImpl("test-inventory.txt");
        final BigDecimal testChange = new BigDecimal("2.00");
        final VendingMachineItem testItem = publicTestDao.getItem(itemId);
        final int ACCURATE_QUARTER_CHANGE_TOTAL = 6, ONE_ITEM = 1;
        assertThrows(VendingMachineItemUnavailableException.class, () -> {
            UserChange totalChange = testServiceLayer.purchaseItem(testChange, testItem);
        }, "A VendingMachineItemUnavailableException should be thrown when sufficient funds "
                + "exist to make a purchase, but the item is not in stock");
        
    }
    
    //Tests that an VendingMachineInsufficientFundsException is thrown even when an out of stock item
    //is attempted to be purchased
    @ParameterizedTest
    @ValueSource(ints = {9})
    @DisplayName("Attempt to purchase out of stock item when funds are insufficient")
    public void testAttemptInsufficientFundPurchase(int itemId, VendingMachineServiceLayerStubImpl testServiceLayer) throws
            VendingMachineDaoPersistenceException, VendingMachineItemUnavailableException {
        final CoinName QUARTER = CoinName.QUARTER;
        final VendingMachineDaoStubFileImpl publicTestDao = new VendingMachineDaoStubFileImpl("test-inventory.txt");
        final BigDecimal testChange = new BigDecimal("0.00");
        final VendingMachineItem testItem = publicTestDao.getItem(itemId);
        final int ACCURATE_QUARTER_CHANGE_TOTAL = 6, ONE_ITEM = 1;
        assertThrows(VendingMachineInsufficientFundsException.class, () -> {
            UserChange totalChange = testServiceLayer.purchaseItem(testChange, testItem);
        }, "A VendingMachineInsufficientFundsException should be thrown when insufficient funds exist to "
                + "make a triggered purchase");
        
    }
    
    
}
