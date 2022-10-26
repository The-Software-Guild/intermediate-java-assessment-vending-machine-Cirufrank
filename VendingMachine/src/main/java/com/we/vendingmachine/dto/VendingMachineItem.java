/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine
 * 
 * @description This class acts as the DTO of our application 
 * and allows each tiered layer of our application to 
 * instantiate vending machine item objects
 */

public class VendingMachineItem {
    private String itemName;
    private BigDecimal itemCost;
    private int numOfItems;
    
    public VendingMachineItem() {
    }
    public VendingMachineItem(String itemName) {
        this.itemName = itemName;
    }
    public VendingMachineItem(String itemName, BigDecimal itemCost) {
        this.itemName = itemName;
        this.itemCost = itemCost;
    }
    public VendingMachineItem(String itemName, BigDecimal itemCost, int numOfItems) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.numOfItems = numOfItems;
    }
    
    public String getItemName() {
        return itemName;
    }
    public BigDecimal getItemCost() {
        return itemCost;
    }
    public int getNumOfItems() {
        return numOfItems;
    }
    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }
}
