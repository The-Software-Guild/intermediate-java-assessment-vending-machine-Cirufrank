/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.we.vendingmachine.dao;

import com.we.vendingmachine.dto.Coin;
import com.we.vendingmachine.dto.CoinName;
import com.we.vendingmachine.dto.UserChange;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;


/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine
 * 
 * @description This class automates the Unit tests for the 
 * VendingMachineBakDao using JUnit
 */

@ExtendWith(VendingMachineBankParameterResolver.class)
public class VendingMachineBankDaoFileImplTest {
    
    public VendingMachineBankDaoFileImplTest() {
    }

    @Test
    public void testGetAllCoins(VendingMachineBankDaoStubFileImpl testBankDao) {
        //Tests that the 4 coin types in inventory are returned in a List when using the
        //getAllCoins method
        final int TOTAL_COIN_TYPES = 4;
        final ArrayList<Coin> coinBank = new ArrayList(testBankDao.getAllCoins());
        for (Coin currentCoin : coinBank) {
            System.out.println(currentCoin.toString());
        }
        assertEquals(TOTAL_COIN_TYPES, coinBank.size());
        
    }
    
    @ParameterizedTest
    @EnumSource(value = CoinName.class, names = {"QUARTER"})
    public void testGetCoin(CoinName coinName, VendingMachineBankDaoStubFileImpl testBankDao) {
        final Coin testQuarterRecord = testBankDao.getCoin(coinName);
        String accurateQuarterRecordString = "QUARTER::" + testQuarterRecord.getCoinTotal();
        final Coin accurateQuarterRecord = testBankDao.unMarshallCoin(accurateQuarterRecordString);
        
        assertEquals(accurateQuarterRecord, testQuarterRecord);
    }
    
    @Test
    public void testGiveChange(VendingMachineBankDaoStubFileImpl testBankDao) {
        final int TOTAL_QUARTERS = 4, TOTAL_NICKELS = 2, TOTAL_PENNIES = 1, TOTAL_DIMES = 1;
        final UserChange userChange = new UserChange(new BigDecimal("1.21"));
        final ArrayList<Coin> prevCoinBank = new ArrayList(testBankDao.getAllCoins());
        userChange.setTotalQuarters(TOTAL_QUARTERS);
        userChange.setTotalNickels(TOTAL_NICKELS);
        userChange.setTotalPennies(TOTAL_PENNIES);
        userChange.setTotalDimes(TOTAL_DIMES);
        testBankDao.giveChange(userChange);
        for (Coin currentCoin : prevCoinBank) {
            final CoinName coinName = currentCoin.getCoinType();
            switch(coinName) {
                case QUARTER:
                    assertEquals(currentCoin.getCoinTotal() - TOTAL_QUARTERS, 
                            testBankDao.getCoin(CoinName.QUARTER).getCoinTotal());
                    break;
                case DIME:
                    assertEquals(currentCoin.getCoinTotal() - TOTAL_DIMES, 
                            testBankDao.getCoin(CoinName.DIME).getCoinTotal());
                    break;
                case NICKEL:
                    assertEquals(currentCoin.getCoinTotal() - TOTAL_NICKELS, 
                            testBankDao.getCoin(CoinName.NICKEL).getCoinTotal());
                    break;
                case PENNY:
                    assertEquals(currentCoin.getCoinTotal() - TOTAL_PENNIES, 
                            testBankDao.getCoin(CoinName.PENNY).getCoinTotal());
                    break;
            }
        }
        
    }
    
}
