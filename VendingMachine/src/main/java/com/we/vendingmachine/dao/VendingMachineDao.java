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
 * @project Assessment: Vending Machine
 * 
 * @description This interface declares the methods that should 
 * be available to allow users to purchase items from the Vending 
 * Machine
 */
public interface VendingMachineDao {
    /**
     * Allows user to buy a item and returns the item if a successful purchase is made
     * or null otherwise
     *
     * @param item VendingMachineItem that user would like to purchase
     * @return VendingMachineItem purchase if successful purchase is made, and 
     * null if not
     */
    public VendingMachineItem purchaseItem(int itemId) throws VendingMachineDaoPersistenceException;
    /**
     * Allows a specific item to be retrieved and returned from the 
     * available vending items, decreasing the number of that item available in
     * inventory when doing so
     *
     * @param itemid int representing item ID
     * @return VendingMachineItem with a matching id or no such items
     */
    public VendingMachineItem getItem(int itemId) throws VendingMachineDaoPersistenceException;
    /**
     * Retrieves a list of all items from inventory
     *
     * @return a List<> of all vending machine items from inventory
     */
    public List <VendingMachineItem> getAllItems() throws VendingMachineDaoPersistenceException;
}
