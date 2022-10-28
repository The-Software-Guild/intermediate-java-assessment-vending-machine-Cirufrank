/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.controller;

import com.we.vendingmachine.dto.UserChange;
import com.we.vendingmachine.dto.VendingMachineItem;
import com.we.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.we.vendingmachine.service.VendingMachineServiceLayer;
import com.we.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine
 * 
 * @description This class acts as the controller of our application
 * and is the brains that communicates directly with the view and service 
 * layers of our application to control item purchase flow
 */

public class VendingMachineController {
    private boolean usingMachine = true;
    final int INPUT_MONEY_ID = 10, REFUND_MONEY_ID = 11, EXIT_ID = 12;
    VendingMachineView view;
    VendingMachineServiceLayer service;
    
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service; 
    }
    public void run() {
        int menuSelection = 0, userChoice = 0;
        try {
            while(usingMachine) {
            view.displayMenu();
            view.displayInputMoney();
            userChoice = view.getUserItemChoice("Please enter choice below");
            switch(userChoice) {
                case INPUT_MONEY_ID:
                    view.readInputMoney("Please input amount in this format: 0.00");
                    view.displayInputMoney();
                    break;
                case REFUND_MONEY_ID:
                    view.displayRefundedMoney();
                    view.resetInputMoney();
                    break;
                case EXIT_ID:
                    view.displayRefundedMoney();
                    view.displayGoodbyeMessage();
                    usingMachine = false;
                    break;
                default:
                    final VendingMachineItem itemChosen = service.getItem(userChoice);
                    final UserChange changeLeft = service.purchaseItem(view.getInputMoney(), itemChosen);
                    view.displayUserChangeAndItem(itemChosen, changeLeft);
                    break;
             }
            }
        } catch (VendingMachineInsufficientFundsException error) {
            view.displayErrorMessage(error.getMessage());
        }
    }
}
