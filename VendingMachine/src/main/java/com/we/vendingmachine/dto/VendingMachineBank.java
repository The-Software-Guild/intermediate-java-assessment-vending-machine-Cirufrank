/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.dto;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine
 * 
 * @description This class acts as a DTO for our application 
 * and allows each tiered layer of our application to 
 * instantiate vending machine bank objects
 * 
 * This bank is a simulation and therefore will reset its
 * available change each time the application is started
 */
public class VendingMachineBank {
    private int totalQuarters = 10000, totalNickels = 10000,
            totalDimes = 10000, totalPennies = 10000;
    VendingMachineBank() {
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
