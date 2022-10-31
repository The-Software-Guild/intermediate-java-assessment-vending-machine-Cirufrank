/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.vendingmachine.dao;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This interface declares the method responsible for
 * auditing the items purchased from our vending machine
 */

public interface VendingMachineAuditDao {
    /**
     * writes an entry to our audit file to keep track of items
     * purchased
     *
     * @param message text to be written to Vending Machine Audit file
     * @return void
     */
    public void writeAuditEntry(String message) throws VendingMachineDaoPersistenceException;
}
