/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.dao;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This class is our parameter resolver for the 
 * VendingMachineDaoStubFileImpl class, and it allows us to use dependency injection without our unit tests to that we
 * do not have to continuously instantiate a VendingMachineDaoStubFileImpl
 * object before each test is ran
 */

public class VendingMachineParameterResolver implements ParameterResolver {
    @Override 
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType() == VendingMachineDaoStubFileImpl.class;
    }
    
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.we.vendingmachine.dao");
        appContext.refresh();
        
        VendingMachineDao testDao = appContext.getBean("vendingMachineDaoStubFileImpl", VendingMachineDaoStubFileImpl.class);
        
        return testDao;
    }
}
