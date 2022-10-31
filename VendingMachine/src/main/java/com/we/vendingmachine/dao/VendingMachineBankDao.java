/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.vendingmachine.dao;

import com.we.vendingmachine.dto.Coin;
import com.we.vendingmachine.dto.UserChange;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This interface declares the methods that should 
 * be available to give user change after a purchase from the 
 * Vending Machine
 */

public interface VendingMachineBankDao {
    /**
     * Determines change to be returned to user after successful purchase
     *
     * @param change UserChange object representing the change to be given to the user
     * @return UserChange object representing the change to be given back to the user
     */
    public UserChange giveChange(UserChange change) throws VendingMachineDaoPersistenceException;
    /**
     * Returns List of coin inventory from the Vending Machine Bank
     *
     * @return List of coin inventory available within the Vending Machine's Coin Bank
     */
    public List<Coin> getAllCoins() throws VendingMachineDaoPersistenceException;
}
