/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.service;

import com.we.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.we.vendingmachine.dao.VendingMachineBankDaoStubFileImpl;
import com.we.vendingmachine.dao.VendingMachineDaoStubFileImpl;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This class is our parameter resolver for the 
 * VendingMachineServiceLayerImpl class, and it allows us to use dependency injection within our unit tests to that we
 * do not have to continuously instantiate a VendingMachineServiceLayerImpl
 * object before each test is ran
 */

public class VendingMachineServiceImplParameterResolver implements ParameterResolver {
    @Override 
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType() == VendingMachineServiceLayerStubImpl.class;
    }
    
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        //        return new VendingMachineServiceLayerImpl(new VendingMachineDaoStubFileImpl("test-inventory.txt"),
        //        new VendingMachineBankDaoStubFileImpl("test-coin-inventory.txt"), 
        //                new VendingMachineAuditDaoFileImpl("test-audit.txt"));
        
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.we.vendingmachine");
        appContext.refresh();
        
        VendingMachineServiceLayer testServiceLayer = appContext.getBean("vendingMachineServiceLayerStubImpl", 
                VendingMachineServiceLayerStubImpl.class);
        return testServiceLayer;
    }
}

