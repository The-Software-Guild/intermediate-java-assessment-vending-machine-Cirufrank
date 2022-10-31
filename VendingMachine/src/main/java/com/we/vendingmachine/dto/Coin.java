/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This class acts as a DTO and represents the coins 
 * in our application and their total in inventory
 */

public class Coin {
    final private static BigDecimal QUARTER_VAL = new BigDecimal(".25"),
            NICKEL_VAL = new BigDecimal(".05"), DIME_VAL = new BigDecimal(".10"),
            PENNY_VAL = new BigDecimal(".01");
    private CoinName type;
    private int total;
    private BigDecimal value;
    public Coin(CoinName type, int total) {
        this.type = type;
        this.total = total;
        this.value = getCoinValue(type);
    }
    public CoinName getCoinType() {
        return type;
    }
    public int getCoinTotal() {
        return total;
    }
    public BigDecimal getCoinValue() {
        return value;
    }
    public void setCoinTotal(int newTotal) {
        this.total = newTotal;
    }
    public static BigDecimal getCoinValue(CoinName coinValToGet) {
        switch (coinValToGet) {
            case PENNY:
                return PENNY_VAL;
            case NICKEL:
                return NICKEL_VAL;
            case DIME:
                return DIME_VAL;
            case QUARTER:
                return QUARTER_VAL;
            default:
                //Maybe update this to a custom exception later
                throw new UnsupportedOperationException();
        }
    }
    
    //The hashCode and equals methods can be used to assert on whole Coin
    //objects to check their equality with another Coin object
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.type);
        hash = 59 * hash + Objects.hashCode(this.total);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coin other = (Coin) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return Objects.equals(this.total, other.total);
    }
    //This makes the test error message more readable when calling the toString()
    //method on a Coin object
    @Override
    public String toString() {
        return "coin{" + "type=" + type + ", value=" + value + ", total=" + 
                total + '}';
    }
}
