package org.team04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

//class RandomSolverTest {
//
//    @Test
//    void getNextMove() {
//        // Redirect System.out to capture printed output
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        randomSolver randomSolver = new randomSolver();
//
//        // Set up dummy input for testing
//        ArrayList<char[]> dummyInput = new ArrayList<>();
//        dummyInput.add(new char[]{'w', 'h', 'i', 'c', 'h'});
//        randomSolver.receiveInfo(dummyInput);
//
//        // Test the getNextMove method
//        char[] result = randomSolver.getNextMove(new int[]{0, 1, 2, 0, 1}, false);
//
//        // Reset System.out
//        System.setOut(System.out);
//
//        // Add assertions to check if the result is as expected
//        assertNotNull(result);
//        assertEquals(5, result.length);
//        assertTrue(outputStream.toString().contains(new String(result)));
//    }
//
//
//}