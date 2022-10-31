/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.vendingmachine.dao;

import com.we.vendingmachine.dto.VendingMachineItem;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This interface declares the methods that should 
 * be available to allow users to purchase items from the Vending 
 * Machine
 */
public interface VendingMachineDao {
    /**
     * Allows user to buy a item and returns the item if a successful purchase is made
     * or throws a VendingMachineDaoPersistenceException otherwise
     * 
     * A successful purchase here means that the number of the said item available 
     * in inventory was decreased by 1, representing the successful purchase of the item
     * from the main DAO's perspective
     *
     * @param item VendingMachineItem that user would like to purchase
     * @return VendingMachineItem if successful purchase is made, and 
     * throw VendingMachineDaoPersistenceException if not
     */
    public VendingMachineItem purchaseItem(int itemId) throws VendingMachineDaoPersistenceException;
    /**
     * Allows a specific item to be retrieved and returned from the 
     * vending machine items in inventory
     *
     * @param itemid int representing item's ID
     * @return VendingMachineItem with a matching id or no such items (null)
     */
    public VendingMachineItem getItem(int itemId) throws VendingMachineDaoPersistenceException;
    /**
     * Retrieves a list of all VendingMachineItem items from inventory
     *
     * @return a List<> of all vending machine items from inventory
     */
    public List <VendingMachineItem> getAllItems() throws VendingMachineDaoPersistenceException;
}
