/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This class implements the UserIO interface and acts as a 
 * console implementation of the input/output functionality of the application
 */
@Component
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
        //Continue to prompt user for choice until the string input can 
        //both be parsed to an integer and is a valid choice available within 
        //the menu
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
        //Continue to prompt user for input until their input String can be 
        //parsed to a BigDecimal
        final int TWO_DECIMAL_PLACES = 2;
        boolean invalidInput = true;
        String stringBigDecimal;
        BigDecimal inputMoney = new BigDecimal("0.00");
        while(invalidInput) {
            try {
                stringBigDecimal = readString(message);
                inputMoney = new BigDecimal(stringBigDecimal).
                        setScale(TWO_DECIMAL_PLACES, 
                                RoundingMode.HALF_UP);
                invalidInput = false;
                return inputMoney;
            } catch(NumberFormatException error) {
                print("Invalid input, please type input in format 0.00");
            }
            
        }
       return inputMoney;
    }
    
}
