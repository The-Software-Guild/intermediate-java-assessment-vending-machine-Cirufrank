/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.service;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This class allows us to create a custom exception that
 * will be thrown if the money a user inputs into our application is 
 * insufficient to make the purchase that they're attempting
 */

public class VendingMachineInsufficientFundsException extends Exception {
    public VendingMachineInsufficientFundsException(String message) {
        super(message);
    }
    
    public VendingMachineInsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
