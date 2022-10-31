/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.dao;

import com.we.vendingmachine.dto.Coin;
import com.we.vendingmachine.dto.CoinName;
import com.we.vendingmachine.dto.UserChange;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This class is a file implementation of the VendingMachineBankDao
 * and acts as the DAO responsible for reading from and updating/writing to the 
 * coin bank inventory file
 */
@Component
@Primary
public class VendingMachineBankDaoFileImpl implements VendingMachineBankDao {
    final private Map<CoinName, Coin> coinBank = new HashMap<>();
    private String inventoryFile;
    final private String DELIMITER = "::";
    @Override
    public UserChange giveChange(UserChange change) throws VendingMachineDaoPersistenceException{
        //Withdraw coins needed to give user change from inventory then return change to user
        loadCoins();
        final ArrayList<Coin> changeToGive = change.getCoins();
          changeToGive.stream().forEach(changeCoin -> {
              final CoinName typeOfChange = changeCoin.getCoinType();
              final Coin inventoryCoin = coinBank.get(typeOfChange);
              final int totalInInventory = inventoryCoin.getCoinTotal();
              final int coinsNeededForChange = changeCoin.getCoinTotal();
              final int remainingCoinsInInventory = totalInInventory - coinsNeededForChange;
              inventoryCoin.setCoinTotal(remainingCoinsInInventory);
          });
        writeCoins();
        return change;
    }
    public VendingMachineBankDaoFileImpl() {
        inventoryFile  = "coin-inventory.txt";
    }
    public VendingMachineBankDaoFileImpl(String coinInventoryFile) {
        this.inventoryFile = coinInventoryFile;
    }
    private Coin unMarshallCoin(String coinAsText) {
        final int COIN_NAME_INDEX = 0, TOTAL_COINS_INDEX = 1;
        final String[] coinTokens = coinAsText.split(DELIMITER);
        final CoinName coinType = CoinName.valueOf(coinTokens[0]);
        final int totalCoins = Integer.valueOf(coinTokens[TOTAL_COINS_INDEX]);
        final Coin coin = new Coin(coinType, totalCoins);
        return coin;
    }
    private String marshallCoin(Coin coin) {
        String coinAsText = "" + coin.getCoinType()
                + DELIMITER + coin.getCoinTotal();
        return coinAsText;
    }
    private void loadCoins() throws VendingMachineDaoPersistenceException{
        try {
            Scanner scanner = new Scanner(
                                        new BufferedReader(
                                            new FileReader(inventoryFile)));
            while (scanner.hasNextLine()) {
                final String coinAsText = scanner.nextLine();
                final Coin currentCoin = unMarshallCoin(coinAsText);
                coinBank.put(currentCoin.getCoinType(), currentCoin);
            }
            scanner.close();
        } catch(FileNotFoundException error) {
            throw new VendingMachineDaoPersistenceException("-_- Could not load coin inventory from file", error);
        }
    }
    private void writeCoins() throws VendingMachineDaoPersistenceException {
        try {
            PrintWriter output = new PrintWriter(
                                        new FileWriter(inventoryFile));
            ArrayList<Coin> coins = new ArrayList(this.getAllCoins());
            coins.stream().forEach(currentCoin -> {
                final String coinAsText = marshallCoin(currentCoin);
                output.println(coinAsText);
                output.flush();
            });
            output.close();
        } catch (IOException error) {
            throw new VendingMachineDaoPersistenceException("-_- Could not write coin inventory to file", error);
        }
    }
    public List<Coin> getAllCoins() throws VendingMachineDaoPersistenceException{
        loadCoins();
        ArrayList<Coin> allCoins = new ArrayList(coinBank.values());
        return allCoins;
    }
    public Coin getCoin(CoinName coinType) throws VendingMachineDaoPersistenceException {
        loadCoins();
        final Coin coin = coinBank.get(coinType);
        return coin;
    }
    
}
