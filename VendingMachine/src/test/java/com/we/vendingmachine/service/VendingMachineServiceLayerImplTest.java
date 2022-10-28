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
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine
 * 
 * @description This class automates the Unit tests for the 
 * VendingMachineServiceLayer using JUnit
 */

@ExtendWith(VendingMachineServiceImplParameterResolver.class)
public class VendingMachineServiceLayerImplTest {
    
    public VendingMachineServiceLayerImplTest() {
    }

    @ParameterizedTest
    @ValueSource(ints = {3})
    public void testPurchaseItem(int itemId, VendingMachineServiceLayerImpl testServiceLayer) throws VendingMachineInsufficientFundsException,
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
        assertEquals(ACCURATE_QUARTER_CHANGE_TOTAL, userQuarters.getCoinTotal());
        assertEquals(previousTotalInStock, updatedTestItem.getNumOfItems() + ONE_ITEM);
        assertEquals(previousQuartersInStock, currentQuartersInStock + ACCURATE_QUARTER_CHANGE_TOTAL);
    }
    @ParameterizedTest
    @ValueSource(ints = {9})
    public void testPurchaseItemOutOfStock(int itemId, VendingMachineServiceLayerImpl testServiceLayer) throws VendingMachineInsufficientFundsException,
            VendingMachineDaoPersistenceException {
        final CoinName QUARTER = CoinName.QUARTER;
        final VendingMachineDaoStubFileImpl publicTestDao = new VendingMachineDaoStubFileImpl("test-inventory.txt");
        final BigDecimal testChange = new BigDecimal("2.00");
        final VendingMachineItem testItem = publicTestDao.getItem(itemId);
        final int ACCURATE_QUARTER_CHANGE_TOTAL = 6, ONE_ITEM = 1;
        assertThrows(VendingMachineItemUnavailableException.class, () -> {
            UserChange totalChange = testServiceLayer.purchaseItem(testChange, testItem);
        });
        
    }
    @ParameterizedTest
    @ValueSource(ints = {9})
    public void testAttemptInsufficientFundPurchase(int itemId, VendingMachineServiceLayerImpl testServiceLayer) throws
            VendingMachineDaoPersistenceException, VendingMachineItemUnavailableException {
        final CoinName QUARTER = CoinName.QUARTER;
        final VendingMachineDaoStubFileImpl publicTestDao = new VendingMachineDaoStubFileImpl("test-inventory.txt");
        final BigDecimal testChange = new BigDecimal("0.00");
        final VendingMachineItem testItem = publicTestDao.getItem(itemId);
        final int ACCURATE_QUARTER_CHANGE_TOTAL = 6, ONE_ITEM = 1;
        assertThrows(VendingMachineInsufficientFundsException.class, () -> {
            UserChange totalChange = testServiceLayer.purchaseItem(testChange, testItem);
        });
        
    }
    
    
}
