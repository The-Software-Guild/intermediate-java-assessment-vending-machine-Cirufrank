/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.vendingmachine.service;

import com.we.vendingmachine.dto.VendingMachineItem;
import java.math.BigDecimal;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine
 * 
 * @description This interface declares the methods responsible for the Vending 
 * Machine business logic of our application
 * These methods should be available to perform calculations the determine when to 
 * remove an item from inventory (successful purchase), when there 
 * is not sufficient funds for a purchase to be made, and when 
 * change needs to be returned (exit purchase or over amount is 
 * paid)
 */
public interface VendingMachineServiceLayer {
    /**
     * Allows user to buy a item and returns the item if a successful purchase is made
     * or null otherwise
     *
     * @param inputMony BigDecimal money input by user
     * @param item VendingMachineItem that user would like to purchase
     * @return VendingMachineItem purchase if successful purchase is made, and 
     * null if not
     */
    public VendingMachineItem purchaseItem(BigDecimal inputMoney, VendingMachineItem item);
    /**
     * Determines if input money is sufficient to purchase chosen item
     *
     * @param inputMony BigDecimal money input by user
     * @param itemPrice BigDecimal price for item that user is attempting to purchase
     * @return boolean true is funds are greater than or equal to item user is 
     * attempting to purchase and false otherwise
     */
    public boolean determineSufficientFunds(BigDecimal inputMoney, BigDecimal itemPrice);
    /**
     * Determines funds still needed to be input by user in order for purchase to be made
     *
     * @param inputMony BigDecimal money input by user
     * @param itemPrice BigDecimal price for item that user is attempting to purchase
     * @return BigDecimal representing the funds user need to input to purchase the item
     */
    public BigDecimal determineFundsStillNeeded(BigDecimal inputMoney, BigDecimal itemPrice);
    /**
     * Determines change to be returned to user after successful purchase
     *
     * @param inputMony BigDecimal money input by user
     * @param itemPrice BigDecimal price for item that user is attempting to purchase
     * @return BigDecimal representing the change to be given back to the user
     */
    public BigDecimal giveChange(BigDecimal inputMoney, BigDecimal itemPrice);
}
