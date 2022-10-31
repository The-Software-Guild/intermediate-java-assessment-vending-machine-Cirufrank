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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;


/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This class automates the Unit tests for the 
 * VendingMachineBakDao using JUnit
 */

@ExtendWith(VendingMachineBankParameterResolver.class)
@DisplayName("Vending Machine Bank Data Access Objects Test")
public class VendingMachineBankDaoFileImplTest {
    
    public VendingMachineBankDaoFileImplTest() {
    }
    
    //Tests that the 4 coin types in inventory are returned in a List when using the
    //getAllCoins method
    @Test
    @DisplayName("Test getAllCoins method")
    public void testGetAllCoins(VendingMachineBankDaoStubFileImpl testBankDao) {
        final int TOTAL_COIN_TYPES = 4;
        final ArrayList<Coin> coinBank = new ArrayList(testBankDao.getAllCoins());
        for (Coin currentCoin : coinBank) {
            System.out.println(currentCoin.toString());
        }
        assertEquals(TOTAL_COIN_TYPES, coinBank.size(), "After retrieveing a list of "
                + "the coins in storage, the total coin records maintained should be equal to " 
        + TOTAL_COIN_TYPES);
        
    }
    //Tests that the proper coin is retrieved upon request to the bankDao
    @ParameterizedTest
    @EnumSource(value = CoinName.class, names = {"QUARTER"})
    @DisplayName("Test getCoinMethod")
    public void testGetCoin(CoinName coinName, VendingMachineBankDaoStubFileImpl testBankDao) {
        final Coin testQuarterRecord = testBankDao.getCoin(coinName);
        String accurateQuarterRecordString = "QUARTER::" + testQuarterRecord.getCoinTotal();
        final Coin accurateQuarterRecord = testBankDao.unMarshallCoin(accurateQuarterRecordString);
        
        assertEquals(accurateQuarterRecord, testQuarterRecord, "When retrieveing a QUARTER"
                + " record its String representation should be equal to " + accurateQuarterRecordString);
    }
    
    //Tests that the coin inventory is properly updated when change is given
    @Test
    @DisplayName("Test giveChange method")
    public void testGiveChange(VendingMachineBankDaoStubFileImpl testBankDao) throws VendingMachineDaoPersistenceException {
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
                            testBankDao.getCoin(CoinName.QUARTER).getCoinTotal(), "After "
                    + TOTAL_QUARTERS + " quarters are given in change this should be refelcted within "
                    + "the coin bank's inventory");
                    break;
                case DIME:
                    assertEquals(currentCoin.getCoinTotal() - TOTAL_DIMES, 
                            testBankDao.getCoin(CoinName.DIME).getCoinTotal(), "After "
                    + TOTAL_DIMES + " dimes are given in change this should be refelcted within "
                    + "the coin bank's inventory");
                    break;
                case NICKEL:
                    assertEquals(currentCoin.getCoinTotal() - TOTAL_NICKELS, 
                            testBankDao.getCoin(CoinName.NICKEL).getCoinTotal(), 
                            "After "
                    + TOTAL_NICKELS + " nickels are given in change this should be refelcted within "
                    + "the coin bank's inventory");
                    break;
                case PENNY:
                    assertEquals(currentCoin.getCoinTotal() - TOTAL_PENNIES, 
                            testBankDao.getCoin(CoinName.PENNY).getCoinTotal(), "After "
                    + TOTAL_PENNIES + " pennies are given in change this should be refelcted within "
                    + "the coin bank's inventory");
                    break;
            }
        }
        
    }
    
}
