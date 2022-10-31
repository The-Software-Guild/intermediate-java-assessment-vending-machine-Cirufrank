/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.we.vendingmachine;

import com.we.vendingmachine.controller.VendingMachineController;
import com.we.vendingmachine.dao.VendingMachineAuditDao;
import com.we.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.we.vendingmachine.dao.VendingMachineBankDao;
import com.we.vendingmachine.dao.VendingMachineBankDaoFileImpl;
import com.we.vendingmachine.dao.VendingMachineDao;
import com.we.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.we.vendingmachine.service.VendingMachineServiceLayer;
import com.we.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.we.vendingmachine.ui.UserIO;
import com.we.vendingmachine.ui.UserIOConsoleImpl;
import com.we.vendingmachine.ui.VendingMachineView;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This application allows users to read and update
 * vending machine items by simulating valid purchase of them
 * 
 * It uses the MVC architectural paradigm to organize files
 * in order to keep code DRY while separating concerns
 * 
 * Through the use of file storage the inventory of items
 * remaining after purchase persists
 * 
 * Additionally, dependency injection is utilized in order to take advantage
 * of loose coupling
 * 
 * Lastly, this application is updated to use Spring DI for dependency injection
 *
 * 
 * 
 */

public class App {
    public static void main(String[] args) {
//        UserIO myIO = new UserIOConsoleImpl();
//        VendingMachineView myView = new VendingMachineView(myIO);
//        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
//        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
//        VendingMachineBankDao myBankDao = new VendingMachineBankDaoFileImpl();
//        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myBankDao, myAuditDao);
//        VendingMachineController controller = new VendingMachineController(myView, myService);
//        controller.run();
          AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
          appContext.scan("com.we.vendingmachine");
          appContext.refresh();
          
          VendingMachineController controller = appContext.getBean("vendingMachineController", VendingMachineController.class);
          controller.run();
    }
}
