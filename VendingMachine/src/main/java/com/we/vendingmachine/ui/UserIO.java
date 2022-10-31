/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.vendingmachine.ui;

import java.math.BigDecimal;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This interfaces defines the view helper methods for
 * the input/output of the application
 */

public interface UserIO {
    /**
     * Displays the specified message to the user
     *
     * @param message message to be displayed to user
     * @return void
     */
    public void print(String message);
    /**
     * Prompts user for item choice until a valid integer has been entered
     *
     * @param message message to be displayed to user
     * @return int representing item or refund choice
     */
    public int readItemChoice(String message);
    
    /**
     * Prompts user to enter string input then
     * returns the string input
     *
     * @param message input prompt to be displayed to user
     * @return String
     */
    public String readString(String message);
    /**
     * Continues to prompt user to enter string input 
     * until it can be parsed to a BigDecimal with a 
     * scale of 2, then returns the BigDecimal input
     *
     * @param message input prompt to be displayed to user
     * @return BigDecimal
     */
    public BigDecimal readBigDecimal(String message);
}
