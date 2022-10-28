/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.ui;

import com.we.vendingmachine.dto.UserChange;
import com.we.vendingmachine.dto.VendingMachineItem;
import java.math.BigDecimal;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine
 * 
 * @description This class acts of the view of the Vending Machine
 * Application and is responsible for all information displayed to 
 * the users of our application
 */

public class VendingMachineView {
    final BigDecimal NO_MONEY = new BigDecimal("0.00");
    BigDecimal inputMoney = new BigDecimal("0.00");
    UserIO io;
    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    public int getUserItemChoice(String prompt) {
        return io.readItemChoice(prompt);
    }
    public void print(String message) {
        System.out.println(message);
    }
    public void displayWelcomeMessage() {
        io.print("==== WELCOME TO THE VENDING MACHINE ===");
    }
    public void readInputMoney(String message) {
        final BigDecimal inputMoney = io.readBigDecimal(message);
        updateInputMoney(inputMoney);
    }
    public BigDecimal getInputMoney() {
        return inputMoney;
    }
    public void setInputMoney(BigDecimal inputMoney) {
        this.inputMoney = inputMoney;
    }
    public void displayRefundedMoney() {
        io.print("Here is your $" + inputMoney + " back");
    }
    public void updateInputMoney(BigDecimal additionalMoney) {
        this.inputMoney = this.inputMoney.add(additionalMoney);
    }
    public void resetInputMoney() {
        this.inputMoney = NO_MONEY;
    }
    public void displayInputMoney() {
        io.print("Total money input: $" + inputMoney);
    }
    public void displayMenu() {
        io.print("=== PLEASE TYPE THE NUMER ITEM YOU'D LIKE TO "
                + "PURCHASE OR SELECT INPUT FUNDS ===");
        io.print("1 -> Kashi: Honey Toasted Crunchy Granola Bar");
        io.print("2 -> Luna: Vanilla Almond Bar");
        io.print("3 -> Nature Valley: Crunchy Oats and Honey Granola Bar");
        io.print("4 -> Bare Fruit: Apple Chips");
        io.print("5 -> Emerald Nuts: 100 Calorie Almond Pack");
        io.print("6 -> Crunchies: Dried Fruit");
        io.print("7 -> Sunrich Natural: Chili Limon Pepitas Pumpkin Seeds");
        io.print("8 -> Fit Joy: Vegan Cheddar Pretzels");
        io.print("9 -> Chocolate rolls");
        io.print("10 -> Input Funds");
        io.print("11 -> Refund input money");
        io.print("12 -> Exit Vending Machine");
    }
    public void displayUserChangeAndItem(VendingMachineItem item, UserChange change) {
        final String itemPurchasedMessage = "Here is your item: " + item.getItemName();
        final String changeMessage = "Also, here is your $" + change.getTotalChange() + "with "
                + change.getTotalQuarters() + " in quarters, "  + change.getTotalDimes() + 
                " in dimes, " + change.getTotalNickels() + " in nickels, and " + change.getTotalPennies()
                                + "in pennies";
        io.print(itemPurchasedMessage);
        io.print(changeMessage);
    }
    public void displayErrorMessage(String message) {
        io.print("=== ERROR ===");
        io.print(message);
    }
    public void displayGoodbyeMessage() {
        io.print("=== GOOD BYE AND THANKS FOR COMING! ===");
    }
}
