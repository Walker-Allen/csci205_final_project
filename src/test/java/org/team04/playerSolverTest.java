package org.team04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

class PlayerSolverTest {


    @Test
    void getNextMove() {
        // Set up a mock input for testing
        String mockInput = "apple\n";
        InputStream inputStream = new ByteArrayInputStream(mockInput.getBytes());
        System.setIn(inputStream);

        playerSolver playerSolver = new playerSolver();

        // Set up dummy input for testing
        ArrayList<char[]> dummyInput = new ArrayList<>();
        dummyInput.add(new char[]{'a', 'p', 'p', 'l', 'e'});
        //playerSolver.receiveInfo(dummyInput);

        // Test the getNextMove method
        char[] result = playerSolver.getNextMove(new int[]{0, 1, 2, 0, 1}, false);

        // Reset System.in
        System.setIn(System.in);

        // Add assertions to check if the result is as expected
        assertNotNull(result);
        assertEquals(5, result.length);
        assertArrayEquals(new char[]{'a', 'p', 'p', 'l', 'e'}, result);
    }
}

