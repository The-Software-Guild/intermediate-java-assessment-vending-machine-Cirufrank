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
 * @description This class acts as a DTO and represents the coins 
 * in our application and their value
 */

public class Coins {
    final private BigDecimal QUARTER_VAL = new BigDecimal(".25"),
            NICKEL_VAL = new BigDecimal(".05"), DIME_VAL = new BigDecimal(".10"),
            PENNY_VAL = new BigDecimal(".01");
    public BigDecimal getCoinValue(CoinNames coinValToGet) {
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
}
