/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.we.vendingmachine.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This class automates the Unit tests for the 
 * VendingMachineAuditDao using JUnit
 */

@ExtendWith(VendingMachineAuditDaoParameterResolver.class)
@DisplayName("Vending Machince Audit Data Access Object Test")
public class VendingMachineAuditDaoFileImplTest {
    
    public VendingMachineAuditDaoFileImplTest() {
    }
    
    //Tests that after an audit entry is written, the most recent audit entry contains it
    //Uses a Random object to help ensure entries are unique to confirm the audit entry line was
    //written
    @Test
    @DisplayName("Test writeAuditEntryMethod")
    public void testWriteAuditEntry(VendingMachineAuditDaoStubFileImpl testAuditDao) throws VendingMachineDaoPersistenceException {
       
        final Random random = new Random();
        final String MESSAGE = "This is a test" + random.nextInt();
        try {
            String mostRecentTestEntry = "";
            testAuditDao.writeAuditEntry(MESSAGE);
            Scanner scanner = new Scanner(new BufferedReader(
                new FileReader("test-audit.txt")));
            while (scanner.hasNextLine()) {
                mostRecentTestEntry = scanner.nextLine();
            }
            scanner.close();
            assertTrue(mostRecentTestEntry.contains(MESSAGE), "THe aduit log should contain "
                    + "entry " + MESSAGE);
        } catch(IOException error) {
            fail("Message could not be read from file");
        }
        
    }
    
}
