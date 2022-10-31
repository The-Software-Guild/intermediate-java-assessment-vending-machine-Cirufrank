/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.controller;

import com.we.vendingmachine.dao.VendingMachineDaoPersistenceException;
import com.we.vendingmachine.dto.UserChange;
import com.we.vendingmachine.dto.VendingMachineItem;
import com.we.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.we.vendingmachine.service.VendingMachineItemUnavailableException;
import com.we.vendingmachine.service.VendingMachineServiceLayer;
import com.we.vendingmachine.ui.VendingMachineView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This class acts as the controller of our application
 * and is the brains that communicates directly with the view and service 
 * layers of our application to control item purchase flow
 */
@Component
public class VendingMachineController {
    private boolean usingMachine = true;
    final int INPUT_MONEY_ID = 10, REFUND_MONEY_ID = 11, EXIT_ID = 12;
    VendingMachineView view;
    VendingMachineServiceLayer service;
    
    @Autowired
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service; 
    }
    public void run() {
        int menuSelection = 0, userChoice = 0;
        view.displayWelcomeMessage();
        
        while(usingMachine) {
            try {
                displayMenu();
                view.displayInputMoney();
                userChoice = view.getUserItemChoice();
                switch(userChoice) {
                    case INPUT_MONEY_ID:
                        inputUserFunds();
                        break;
                    case REFUND_MONEY_ID:
                        refundUserMoney();
                        break;
                    case EXIT_ID:
                        exitUser();
                        usingMachine = false;
                        break;
                    default:
                        makeUserPurchase(userChoice);
                        break;
                    }
                } catch (VendingMachineInsufficientFundsException | VendingMachineDaoPersistenceException | 
                        VendingMachineItemUnavailableException error)  {
                    view.displayErrorMessage(error.getMessage());
                }
         } 
    }
    private void inputUserFunds() {
            view.readInputMoney();
            view.displayInputMoney();
    }
    private void refundUserMoney() {
        view.displayRefundBanner();
        view.displayRefundedMoney();
        view.displayRefundBanner();
        view.resetInputMoney();
    }
    private void exitUser() {
        view.displayRefundedMoney();
        view.displayGoodbyeMessage();
    }
    private void makeUserPurchase(int choiceOfUser) throws VendingMachineInsufficientFundsException, VendingMachineDaoPersistenceException,
        VendingMachineItemUnavailableException {
        if (!view.inputMoneyIsAboveZero()) {
            view.displayInvalidPurchaseBanner();
            view.displayInputMoneyNeededMessage();
            view.displayInvalidPurchaseBanner();
            return;
        }
        final VendingMachineItem itemChosen = service.getItem(choiceOfUser);
        final UserChange changeLeft = service.purchaseItem(view.getInputMoney(), itemChosen);
        view.resetInputMoney();
        view.displayUserChangeAndItem(itemChosen, changeLeft);
    }
    private void displayMenu() throws VendingMachineDaoPersistenceException {
        final ArrayList<VendingMachineItem> inventory = new ArrayList(service.getAllItems());
        view.displayMenu(inventory);
    }
}
