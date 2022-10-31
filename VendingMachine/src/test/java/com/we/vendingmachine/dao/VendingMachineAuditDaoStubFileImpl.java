/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description TEST STUB: This class implements the audit file of our vending machine that 
 * keeps track of when users purchased items and how much change was returned to them
 */
@Component
public class VendingMachineAuditDaoStubFileImpl implements VendingMachineAuditDao {
    final private String DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";
    public static String auditFile;
    
    public VendingMachineAuditDaoStubFileImpl() {
        auditFile = "audit.txt";
    }
    @Autowired
    public VendingMachineAuditDaoStubFileImpl(@Value("test-audit.txt") String auditFile) {
        this.auditFile = auditFile;
    }
    
    @Override
    public void writeAuditEntry(String message) throws VendingMachineDaoPersistenceException {
        LocalDateTime timeStamp = LocalDateTime.now();
        String formattedTimeStamp = timeStamp.format(
                DateTimeFormatter.ofPattern(DATE_PATTERN));
        try {
            PrintWriter output = new PrintWriter(
                                        new FileWriter( auditFile, true));
            output.println(formattedTimeStamp + ": " + message);
            output.flush();
            output.close();
        } catch (IOException error) {
            throw new VendingMachineDaoPersistenceException("-_- No audit entry successfully wrtiten to the audit file", error);
        }
    }
}
