/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.we.vendingmachine.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine
 * 
 * @description This class automates the Unit tests for the 
 * VendingMachineAuditDao using JUnit
 */

@ExtendWith(VendingMachineAuditDaoParameterResolver.class)
public class VendingMachineAuditDaoFileImplTest {
    
    public VendingMachineAuditDaoFileImplTest() {
    }

    @Test
    public void testWriteAuditEntry(VendingMachineAuditDaoFileImpl testAuditDao) {
        final String MESSAGE = "This is a test";
        try {
            String mostRecentTestEntry = "";
            testAuditDao.writeAuditEntry(MESSAGE);
            Scanner scanner = new Scanner(new BufferedReader(
                new FileReader("test-audit.txt")));
            while (scanner.hasNextLine()) {
                mostRecentTestEntry = scanner.nextLine();
            }
            scanner.close();
            assertTrue(mostRecentTestEntry.contains(MESSAGE));
        } catch(IOException error) {
            fail("Message could not be read from file");
        }
        
    }
    
}
