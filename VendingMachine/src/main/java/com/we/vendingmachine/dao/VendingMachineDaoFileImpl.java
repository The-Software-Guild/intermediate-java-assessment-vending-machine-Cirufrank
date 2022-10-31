/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.dao;

import com.we.vendingmachine.dto.VendingMachineItem;
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
 * @description This class implements the methods declared by the
 * VendingMachineDao interface that allows our application to read, 
 * retrieve, and update our vending machine item inventory
 */
@Component
@Primary
public class VendingMachineDaoFileImpl implements VendingMachineDao {
   final private String DELIMITER = "::";
   final private Map <Integer, VendingMachineItem> vendingMachineItems = new HashMap<>();
   private String inventoryFile;
   
   public VendingMachineDaoFileImpl() {
       inventoryFile = "inventory.txt";
   }
   public VendingMachineDaoFileImpl(String inventoryFile) {
       this.inventoryFile = inventoryFile;
   }
   
   @Override
   public List <VendingMachineItem> getAllItems() throws VendingMachineDaoPersistenceException {
       loadFile();
       final ArrayList<VendingMachineItem> listOfItems = new ArrayList(vendingMachineItems.values());
       return listOfItems;
   }
   @Override
   public VendingMachineItem getItem(int itemId) throws VendingMachineDaoPersistenceException {
       loadFile();
       final VendingMachineItem item = vendingMachineItems.get(itemId);
       return item;
   }
   @Override
   public VendingMachineItem purchaseItem(int itemId) throws VendingMachineDaoPersistenceException{
       loadFile();
       final int ONE_ITEM_TO_REMOVE = 1;
       final VendingMachineItem itemToPurchase = getItem(itemId);
       final int CURRENT_NUM_OF_ITEMS = itemToPurchase.getNumOfItems();
       final int UPDATED_NUM_OF_ITEMS = CURRENT_NUM_OF_ITEMS - ONE_ITEM_TO_REMOVE;
       itemToPurchase.setNumOfItems(UPDATED_NUM_OF_ITEMS);
       writeFile();
       return itemToPurchase;
   }
   private VendingMachineItem unMarshallItem(String itemAsText) {
       final int ID_INDEX = 0, NAME_INDEX = 1, COST_INDEX = 2, TOTAL_INDEX = 3;
       final String[] itemTokens = itemAsText.split(DELIMITER);
       final int itemId = Integer.valueOf(itemTokens[ID_INDEX]);
       final String itemName = itemTokens[NAME_INDEX];
       final BigDecimal itemCost = new BigDecimal(itemTokens[COST_INDEX]);
       final int totalItems = Integer.valueOf(itemTokens[TOTAL_INDEX]);
       final VendingMachineItem itemFromFile = new VendingMachineItem(itemId, 
               itemName, itemCost, totalItems);
       return itemFromFile;
   }
   
   private String marshallItem(VendingMachineItem item) {
       String itemAsText = item.getItemId() + DELIMITER
               + item.getItemName() + DELIMITER
               + item.getItemCost() + DELIMITER
               + item.getNumOfItems();
       return itemAsText;
   }
   private void loadFile() throws VendingMachineDaoPersistenceException {
       try {
            Scanner scanner = new Scanner(
                                new BufferedReader(
                                    new FileReader(inventoryFile)));
            while (scanner.hasNextLine()) {
               final String itemAsText = scanner.nextLine();
               final VendingMachineItem itemFromFile = unMarshallItem(itemAsText);
               vendingMachineItems.put(itemFromFile.getItemId(), itemFromFile);
            }
            scanner.close();
       } catch (FileNotFoundException error) {
           throw new VendingMachineDaoPersistenceException("-_- COuld not load inventory", error);
       }
       
   }
   private void writeFile() throws VendingMachineDaoPersistenceException {
       try {
           PrintWriter output = new PrintWriter(
                                new FileWriter(
                                    inventoryFile));
           ArrayList<VendingMachineItem> listOfItems = new ArrayList(getAllItems());
           listOfItems.stream().forEach(currentItem -> {
               String itemAsText = marshallItem(currentItem);
               output.println(itemAsText);
               output.flush();
           });
           output.close();
       } catch(IOException error) {
           throw new VendingMachineDaoPersistenceException("-_- Could not write items back to inventory file", error);
       }
   }
}
