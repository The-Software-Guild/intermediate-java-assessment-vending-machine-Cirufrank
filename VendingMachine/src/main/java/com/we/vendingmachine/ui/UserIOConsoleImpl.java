/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author ciruf
 */
public class UserIOConsoleImpl implements UserIO {
    private Scanner scanner = new Scanner(System.in);
    public void print(String message) {
        System.out.println(message);
    }   
    public String readString(String message) {
        print(message);
        final String userInput = scanner.nextLine().trim().intern();
        return userInput;
    }
    @Override
    public int readItemChoice(String message) {
        final int MIN_CHOICE = 1, MAX_CHOICE = 12;
        boolean invalidChoice = true;
        String stringItemChoice;
        int itemChoice = 1;
        do {
            try {
                if (itemChoice < MIN_CHOICE || itemChoice > MAX_CHOICE) {
                    print("Invalid choice, please enter an integer displayed on "
                        + "the menu");
                }
                stringItemChoice = readString(message);
                itemChoice = Integer.valueOf(stringItemChoice);
                invalidChoice = false;
            } catch(NumberFormatException error) {
                print("Invalid choice, please enter an integer displayed on "
                        + "the menu");
            }
        } while (invalidChoice || itemChoice < MIN_CHOICE || itemChoice > MAX_CHOICE);
        
        return itemChoice;
    }
    @Override
    public BigDecimal readBigDecimal(String message) {
        boolean invalidInput = true;
        String stringBigDecimal;
        BigDecimal inputMoney = new BigDecimal("0.00");
        while(invalidInput) {
            try {
                stringBigDecimal = readString(message);
                inputMoney = new BigDecimal(stringBigDecimal);
                invalidInput = false;
                return inputMoney;
            } catch(NumberFormatException error) {
                print("Invalid input, please type input in format 0.00");
            }
            
        }
       return inputMoney;
    }
    
}
