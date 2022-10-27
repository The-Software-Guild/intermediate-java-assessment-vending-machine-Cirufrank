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

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine
 * 
 * @description This class implements the methods declared by the
 * VendingMachineDao interface that allows our application to read, 
 * retrieve, and update our inventory
 */

public class VendingMachineDaoFileImpl implements VendingMachineDao {
   final private String DELIMITER = "::";
   final private Map <Integer, VendingMachineItem> vendingMachineItems = new HashMap<>();
   private String inventoryFile;
   
   VendingMachineDaoFileImpl() {
       inventoryFile = "inventory.txt";
   }
   VendingMachineDaoFileImpl(String inventoryFile) {
       this.inventoryFile = inventoryFile;
   }
   
   @Override
   public List <VendingMachineItem> getAllItems() {
       loadFile();
       final ArrayList<VendingMachineItem> listOfItems = new ArrayList(vendingMachineItems.values());
       return listOfItems;
   }
   @Override
   public VendingMachineItem getItem(int itemId) {
       loadFile();
       final VendingMachineItem item = vendingMachineItems.get(itemId);
       return item;
   }
   @Override
   public VendingMachineItem purchaseItem(int itemId) {
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
   private void loadFile() {
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
           System.out.println("-_- COuld not load inventory");
       }
       
   }
   private void writeFile() {
       try {
           PrintWriter output = new PrintWriter(
                                new FileWriter(
                                    inventoryFile));
           ArrayList<VendingMachineItem> listOfItems = new ArrayList(getAllItems());
           for (VendingMachineItem currentItem : listOfItems) {
               String itemAsText = marshallItem(currentItem);
               output.println(itemAsText);
               output.flush();
           }
           output.close();
       } catch(IOException error) {
           System.out.println("-_- Could not write items back to inventory file");
       }
   }
}
