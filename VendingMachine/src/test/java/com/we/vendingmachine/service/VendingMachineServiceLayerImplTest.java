/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.we.vendingmachine.service;

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
 * @author ciruf
 */
@ExtendWith(VendingMachineServiceImplParameterResolver.class)
public class VendingMachineServiceLayerImplTest {
    
    public VendingMachineServiceLayerImplTest() {
    }

    @ParameterizedTest
    @ValueSource(ints = {3})
    public void testPurchaseItem(int itemId, VendingMachineServiceLayerImpl testServiceLayer) throws VendingMachineInsufficientFundsException {
        final VendingMachineDaoStubFileImpl publicTestDao = new VendingMachineDaoStubFileImpl("test-inventory.txt");
        final BigDecimal testChange = new BigDecimal("2.00");
        final VendingMachineItem testItem = publicTestDao.getItem(itemId);
        final int accurateChangeNum = 6;
        final CoinName QUARTER = CoinName.QUARTER;
        UserChange totalChange = testServiceLayer.purchaseItem(testChange, testItem);
        Coin userQuarters = totalChange.getQuarters();
        ArrayList<Coin> userCoins = totalChange.getCoins();
        for (Coin currentCoin : userCoins) {
            System.out.println(currentCoin.toString());
        }
        assertEquals(accurateChangeNum, userQuarters.getCoinTotal());
    }
    
    
}
