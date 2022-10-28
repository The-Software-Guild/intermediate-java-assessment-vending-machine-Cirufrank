/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.service;

import com.we.vendingmachine.dao.VendingMachineAuditDao;
import com.we.vendingmachine.dao.VendingMachineBankDao;
import com.we.vendingmachine.dao.VendingMachineDao;
import com.we.vendingmachine.dto.Coin;
import com.we.vendingmachine.dto.UserChange;
import com.we.vendingmachine.dto.VendingMachineItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine
 * 
 * @description This class implements the VendignMachineService Layer interface 
 * and the methods responsible for the Vending Machine's business logic of our application
 * 
 * These methods should be available to perform calculations the determine when to 
 * remove an item from inventory (successful purchase), when there 
 * is not sufficient funds for a purchase to be made, and when 
 * change needs to be returned (exit purchase or over amount is 
 * paid)
 */

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    private VendingMachineDao dao;
    private VendingMachineBankDao bankDao;
    private VendingMachineAuditDao auditDao;
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineBankDao bankDao,
            VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.bankDao = bankDao;
        this.auditDao = auditDao;
    }
    public UserChange purchaseItem(BigDecimal inputMoney, VendingMachineItem item) throws VendingMachineInsufficientFundsException {
        final int ONE_ITEM = 1;
        determineSufficientFunds(inputMoney, item.getItemCost());
        final UserChange userChange = determineChange(inputMoney, item.getItemCost());
        final String itemPurchaseReport = "Item Purchased: ID: " + item.getItemId() 
                + " | Item Name: " + item.getItemName() + " | Quantity: " + ONE_ITEM;
        bankDao.giveChange(userChange);
        final ArrayList<Coin> userCoins = new ArrayList(userChange.getCoins());
        String coinsGivenMessage = "";
        for (Coin currentCoin : userCoins) {
            coinsGivenMessage += currentCoin.getCoinType() + ": " + currentCoin.getCoinTotal() + 
                    " withdrawn | ";
        }
        String coinBankInventoryReport = "Inventory Updated: " + coinsGivenMessage;
        auditDao.writeAuditEntry(itemPurchaseReport);
        auditDao.writeAuditEntry(coinBankInventoryReport);
        return userChange;
    }
  
    public void determineSufficientFunds(BigDecimal inputMoney, BigDecimal itemPrice) throws VendingMachineInsufficientFundsException {
        final int EXACT_AMOUNT = 0;
        final BigDecimal changeLeftOver = inputMoney.subtract(itemPrice);
        if ((changeLeftOver.compareTo(BigDecimal.ZERO) >= EXACT_AMOUNT) != true) {
            throw new VendingMachineInsufficientFundsException("ERROR: Funds insufficient "
                    + "to make specified purchase");
        }
    }
  
    public BigDecimal determineFundsStillNeeded(BigDecimal inputMoney, BigDecimal itemPrice) {
        final BigDecimal fundsNeeded = itemPrice.subtract(inputMoney);
        return fundsNeeded;
    }
 
    public UserChange determineChange(BigDecimal inputMoney, BigDecimal itemPrice) {
        final int NO_MORE_CHANGE = 0;
        BigDecimal totalChange = inputMoney.subtract(itemPrice);
        final UserChange userChange = new UserChange(totalChange);
        for (Coin currentCoin : UserChange.VAL_DESC_COIN_LIST) {
            if ((totalChange.compareTo(BigDecimal.ZERO) > NO_MORE_CHANGE) == true) {
                final BigDecimal coinValue = currentCoin.getCoinValue();
                final BigDecimal coinsToGive = totalChange.divide(coinValue, RoundingMode.DOWN);
                final int intCoinsToGive = coinsToGive.intValueExact();
                final BigDecimal coinsValue = coinsToGive.multiply(coinValue);
                final Coin coinsForUser = new Coin(currentCoin.getCoinType(), intCoinsToGive);
                totalChange = totalChange.subtract(coinsValue);
                userChange.updateCoinAmount(coinsForUser);
            }
        }
        return userChange;
    }
    public VendingMachineItem getItem(int itemId) {
        return dao.getItem(itemId);
    }

}
