/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.ui;

import com.we.vendingmachine.dto.UserChange;
import com.we.vendingmachine.dto.VendingMachineItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This class acts of the view of the Vending Machine
 * Application and is responsible for all information displayed to 
 * the users of our application
 * 
 */
@Component
@Primary
public class VendingMachineView {
    final int INT_NO_MONEY = 0;
    final BigDecimal NO_MONEY = new BigDecimal("0.00");
    BigDecimal inputMoney = new BigDecimal("0.00");
    final String INPUT_MONEY_PROMPT = "Please input amount in this format: 0.00",
            ITEM_CHOICE_PROMPT = "Please enter choice below",
            NO_MONEY_TO_REFUND_MESSAGE = "No money to refund",
            INPUT_MONEY_TO_MAKE_PURCHASE_MESSAGE = "Must input money to make a purchase",
            INVALID_PURCHASE_BANNER = "=== INVALID PURCHASE ===",
            WELCOME_BANNER = "==== WELCOME TO THE VENDING MACHINE ===",
            REFUND_BANNER = "=== REFUND ===",
            ERROR_BANNER = "=== ERROR ===",
            GOODBYE_BANNER = "=== GOOD BYE AND THANKS FOR COMING! ===",
            INVENTORY_LIST_BANNER = "=== PLEASE TYPE THE NUMER ITEM YOU'D LIKE TO "
                + "PURCHASE OR SELECT INPUT FUNDS ===",
            ITEM_1_TEXT = "1 -> Kashi: Honey Toasted Crunchy Granola Bar",
            ITEM_2_TEXT = "2 -> Luna: Vanilla Almond Bar",
            ITEM_3_TEXT = "3 -> Nature Valley: Crunchy Oats and Honey Granola Bar",
            ITEM_4_TEXT = "4 -> Bare Fruit: Apple Chips",
            ITEM_5_TEXT = "5 -> Emerald Nuts: 100 Calorie Almond Pack",
            ITEM_6_TEXT = "6 -> Crunchies: Dried Fruit",
            ITEM_7_TEXT = "7 -> Sunrich Natural: Chili Limon Pepitas Pumpkin Seeds",
            ITEM_8_TEXT = "8 -> Fit Joy: Vegan Cheddar Pretzels",
            ITEM_9_TEXT = "9 -> Chocolate rolls",
            CHOICE_10_INPUT_FUNDS_TEXT = "10 -> Input Funds",
            CHOICE_11_REFUND_MONEY_TEXT = "11 -> Refund input money",
            CHOICE_12_EXIT_TEXT = "12 -> Exit Vending Machine";
    
    private UserIO io;
    @Autowired
    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    public int getUserItemChoice() {
        return io.readItemChoice(ITEM_CHOICE_PROMPT);
    }
    public void print(String message) {
        System.out.println(message);
    }
    public void displayWelcomeMessage() {
        io.print(WELCOME_BANNER);
    }
    public void readInputMoney() {
        final BigDecimal inputMoney = io.readBigDecimal(INPUT_MONEY_PROMPT);
        updateInputMoney(inputMoney);
    }
    public BigDecimal getInputMoney() {
        return inputMoney;
    }
    public void setInputMoney(BigDecimal inputMoney) {
        this.inputMoney = inputMoney;
    }
    public void displayRefundBanner() {
        io.print(REFUND_BANNER);
    }
    public void displayInvalidPurchaseBanner() {
        io.print(INVALID_PURCHASE_BANNER);
    }
    public void displayInputMoneyNeededMessage() {
        io.print(INPUT_MONEY_TO_MAKE_PURCHASE_MESSAGE);
    }
    public void displayRefundedMoney() {
        if (inputMoneyIsAboveZero())
        io.print("Here is your $" + inputMoney + " back");
        else io.print(NO_MONEY_TO_REFUND_MESSAGE);
    }
    public boolean inputMoneyIsAboveZero() {
        return (inputMoney.compareTo(NO_MONEY) > INT_NO_MONEY);
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
    public void displayMenu(ArrayList<VendingMachineItem> inventory) {
        io.print(INVENTORY_LIST_BANNER);
        displayInventoryList(inventory);
        io.print(CHOICE_10_INPUT_FUNDS_TEXT);
        io.print(CHOICE_11_REFUND_MONEY_TEXT);
        io.print(CHOICE_12_EXIT_TEXT);
    }
    
    private void displayInventoryList(ArrayList<VendingMachineItem> inventory) {
        //Iterate through the sequence of objects within out inventory list and 
        //display them and their OUT OF STOCK status is applicable to the user
        final String OUT_OF_STOCK_MESSAGE = " OUT OF STOCK";
        final int NONE = 0;
        inventory.stream().forEach((item) -> {
            final int totalInInventory = item.getNumOfItems();
            String itemChoiceText = createItemChoiceText(item);
            if (totalInInventory <= NONE) itemChoiceText += OUT_OF_STOCK_MESSAGE;
            io.print(itemChoiceText); 
        });
    }
    private String createItemChoiceText(VendingMachineItem item) {
        return item.getItemId() + "-> $" + item.getItemCost() + ": " + item.getItemName();
    }
    public void displayUserChangeAndItem(VendingMachineItem item, UserChange change) {
        final String itemPurchasedMessage = "Here is your item: " + item.getItemName();
        final String changeMessage = "Also, here is your $" + change.getTotalChange() + " in change: "
                + change.getTotalQuarters() + " in quarters, "  + change.getTotalDimes() + 
                " in dimes, " + change.getTotalNickels() + " in nickels, and " + change.getTotalPennies()
                                + " in pennies";
        io.print(itemPurchasedMessage);
        io.print(changeMessage);
    }
    public void displayErrorMessage(String message) {
        io.print(ERROR_BANNER);
        io.print(message);
        io.print(ERROR_BANNER);
    }
    public void displayGoodbyeMessage() {
        io.print(GOODBYE_BANNER);
    }
}
