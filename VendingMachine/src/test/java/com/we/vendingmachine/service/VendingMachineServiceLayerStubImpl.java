/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.service;

import com.we.vendingmachine.dao.VendingMachineAuditDao;
import com.we.vendingmachine.dao.VendingMachineAuditDaoStubFileImpl;
import com.we.vendingmachine.dao.VendingMachineBankDao;
import com.we.vendingmachine.dao.VendingMachineBankDaoStubFileImpl;
import com.we.vendingmachine.dao.VendingMachineDao;
import com.we.vendingmachine.dao.VendingMachineDaoPersistenceException;
import com.we.vendingmachine.dao.VendingMachineDaoStubFileImpl;
import com.we.vendingmachine.dto.Coin;
import com.we.vendingmachine.dto.UserChange;
import com.we.vendingmachine.dto.VendingMachineItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description TEST STUB: This class implements the VendingMachineService Layer interface 
 * and the methods responsible for the Vending Machine's business logic of our application
 * 
 * These methods should be available to perform calculations the determine when to 
 * remove an item from inventory (successful purchase), when there 
 * is not sufficient funds for a purchase to be made, and when 
 * change needs to be returned (exit purchase or over amount is 
 * paid)
 */
@Component
public class VendingMachineServiceLayerStubImpl implements VendingMachineServiceLayer {
    final private int ONE_ITEM = 1;
    @Autowired
    @Qualifier("vendingMachineDaoStubFileImpl")
    private VendingMachineDao dao;
    @Autowired
    @Qualifier("vendingMachineBankDaoStubFileImpl")
    private VendingMachineBankDao bankDao;
    @Autowired
    @Qualifier("vendingMachineAuditDaoStubFileImpl")
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerStubImpl(VendingMachineDao dao, VendingMachineBankDao bankDao,
            VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.bankDao = bankDao;
        this.auditDao = auditDao;
    }
    public UserChange purchaseItem(BigDecimal inputMoney, VendingMachineItem item) throws 
            VendingMachineInsufficientFundsException, VendingMachineDaoPersistenceException, 
            VendingMachineItemUnavailableException {
        //Remove item from inventory, withdraw needed change from inventory, write to the audit 
        //file when the item was pruchased and the inventory coins withdrawn, and 
        //calculate user change coins and return the UserChange to the user
        determineSufficientFunds(inputMoney, item.getItemCost(), item.getItemName());
        determineItemAvailability(item);
        dao.purchaseItem(item.getItemId());
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
        String coinBankInventoryReport = "Coin Bank Inventory Updated: " + coinsGivenMessage;
        auditDao.writeAuditEntry(itemPurchaseReport);
        auditDao.writeAuditEntry(coinBankInventoryReport);
        return userChange;
    }
  
    public void determineSufficientFunds(BigDecimal inputMoney, BigDecimal itemPrice, String itemName) throws VendingMachineInsufficientFundsException {
        //Ensure the funds input by the user are sufficient to purchase the item chosen
        final int EXACT_AMOUNT = 0;
        final BigDecimal changeLeftOver = inputMoney.subtract(itemPrice);
        if ((changeLeftOver.compareTo(BigDecimal.ZERO) >= EXACT_AMOUNT) != true) {
            final BigDecimal fundsNeeded = determineFundsStillNeeded(inputMoney, itemPrice);
            throw new VendingMachineInsufficientFundsException("Funds insufficient: Additional $" + fundsNeeded
                    + " required to make specified purchase of ITEM: " + itemName) ;
        }
    }
    private void determineItemAvailability(VendingMachineItem item) throws VendingMachineItemUnavailableException{
        //Determine if the item selected is available for purchase
        if (item.getNumOfItems() < ONE_ITEM) {
            throw new VendingMachineItemUnavailableException("We're sorry, " + item.getItemName() + " is out of stock");
        }
    }
    public BigDecimal determineFundsStillNeeded(BigDecimal inputMoney, BigDecimal itemPrice) {
        final BigDecimal fundsNeeded = itemPrice.subtract(inputMoney);
        return fundsNeeded;
    }
 
    public UserChange determineChange(BigDecimal inputMoney, BigDecimal itemPrice) {
        //Calcuate user change based on remainding funds after item is paid for, and provide
        //change in the least amount of coins possible
        final int NO_MORE_CHANGE = 0;
        BigDecimal totalChange = inputMoney.subtract(itemPrice);
        final UserChange userChange = new UserChange(totalChange);
        for (Coin currentCoin : UserChange.VAL_DESC_COIN_LIST) {
            if ((totalChange.compareTo(BigDecimal.ZERO) > NO_MORE_CHANGE) == true) {
                final int NO_DECIMALS = 0;
                final BigDecimal coinValue = currentCoin.getCoinValue();
                final BigDecimal coinsToGive = totalChange.divide(coinValue, NO_DECIMALS, RoundingMode.DOWN);
                final int intCoinsToGive = coinsToGive.intValueExact();
                final BigDecimal coinsValue = coinsToGive.multiply(coinValue);
                final Coin coinsForUser = new Coin(currentCoin.getCoinType(), intCoinsToGive);
                totalChange = totalChange.subtract(coinsValue);
                userChange.updateCoinAmount(coinsForUser);
            }
        }
        return userChange;
    }
    public VendingMachineItem getItem(int itemId) throws VendingMachineDaoPersistenceException {
        return dao.getItem(itemId);
    }
    public List<VendingMachineItem> getAllItems() throws VendingMachineDaoPersistenceException {
        return dao.getAllItems();
    }

}
