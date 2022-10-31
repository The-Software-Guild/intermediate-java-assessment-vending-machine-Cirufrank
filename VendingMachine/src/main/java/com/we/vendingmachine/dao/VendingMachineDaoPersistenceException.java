/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.dao;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This class allows us to create a custom exception that
 * will be thrown if any errors happen throughout data access within
 * our application
 */

public class VendingMachineDaoPersistenceException extends Exception {
    public VendingMachineDaoPersistenceException(String message) {
        super(message);
    }
    
    public VendingMachineDaoPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}