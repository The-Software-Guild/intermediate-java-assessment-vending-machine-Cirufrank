/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    final private int NO_COINS = 0;
    final private Coin QUARTERS = new Coin(CoinName.QUARTER, NO_COINS);
    final private Coin NICKELS = new Coin(CoinName.NICKEL, NO_COINS);
    final private Coin DIMES = new Coin(CoinName.DIME, NO_COINS);
    final private Coin PENNIES = new Coin(CoinName.PENNY, NO_COINS);

    private BigDecimal totalChange;
    
    public UserChange(BigDecimal userChange) {
        this.totalChange = userChange;
    }
    
    public Coin getQuarters() {
        return QUARTERS;
    }
    public void setTotalQuarters(int newQuartersAmount) {
        QUARTERS.setCoinTotal(newQuartersAmount);
    }
    public Coin getNickels() {
        return NICKELS;
    }
    public void setTotalNickels(int newNickelsAmount) {
        NICKELS.setCoinTotal(newNickelsAmount);
    }
    public Coin getDimes() {
        return DIMES;
    }
    public void setTotalDimes(int newDimesAmount) {
        DIMES.setCoinTotal(newDimesAmount);
    }
    public Coin getPennines() {
        return PENNIES;
    }
    public void setTotalPennies(int newPenniesAmount) {
        PENNIES.setCoinTotal(newPenniesAmount);
    }
    public ArrayList<Coin> getCoins() {
        final ArrayList<Coin> coinList = new ArrayList<Coin>(Arrays.asList(QUARTERS, NICKELS, DIMES, PENNIES));
        return coinList;
    }
    public void updateCoinAmount(Coin coin) {
        final CoinName coinName = coin.getCoinType();
        final int coinAmount = coin.getCoinTotal();
        switch(coinName) {
            case QUARTER:
                QUARTERS.setCoinTotal(coinAmount);
                break;
            case NICKEL:
                NICKELS.setCoinTotal(coinAmount);
                break;
            case DIME:
                DIMES.setCoinTotal(coinAmount);
                break;
            case PENNY:
                PENNIES.setCoinTotal(coinAmount);
                break;
            default:
                //Maybe update this to a custom exception later
                throw new UnsupportedOperationException();
            
        }
    }
    public void updateCoins(List<Coin> coins) {
        for (Coin currentCoin : coins) {
            updateCoinAmount(currentCoin);
        }
    }
}
