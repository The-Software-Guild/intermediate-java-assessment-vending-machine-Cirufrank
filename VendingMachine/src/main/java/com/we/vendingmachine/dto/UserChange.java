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
 * @description This class represents our UserChange DTO which defines
 * the properties and methods we'd like to be available to user change 
 * objects
 */

public class UserChange {
    private int totalQuarters = 0, totalNickels = 0,
            totalDimes = 0, totalPennies = 0;
    private BigDecimal totalChange;
    
    public UserChange(BigDecimal userChange) {
        this.totalChange = userChange;
    }
    
    public int getQuarters() {
        return totalQuarters;
    }
    public void setQuarters(int newQuartersAmount) {
        this.totalQuarters = newQuartersAmount;
    }
    public int getNickels() {
        return totalNickels;
    }
    public void setNickels(int newNickelsAmount) {
        this.totalNickels = newNickelsAmount;
    }
    public int getDimes() {
        return totalDimes;
    }
    public void setDimes(int newDimesAmount) {
        this.totalDimes = newDimesAmount;
    }
    public int getPennines() {
        return totalPennies;
    }
    public void setPennies(int newPenniesAmount) {
        this.totalPennies = newPenniesAmount;
    }
}
