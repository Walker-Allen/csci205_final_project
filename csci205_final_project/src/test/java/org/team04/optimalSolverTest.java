package org.team04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptimalSolverTest {

    @Test
    void getNextMove() {
        // Create an instance of the optimalSolver class
        betterSolver solver = new betterSolver();

        // Create some dummy data for the test
        int[] dummyResponse = {0, 1, 2, 0, 1};
        boolean firstGuess = false;

        // Call the method
        char[] result = solver.getNextMove(dummyResponse, firstGuess);

        // Add assertions to check if the result is as expected
        assertNotNull(result);
        assertEquals(5, result.length);
    }

//    @Test
//    void receiveInfo() {
//        // Create an instance of the optimalSolver class
//        optimalSolver solver = new optimalSolver();
//
//        // Create some dummy data for the test
//        ArrayList<char[]> dummyInput = new ArrayList<>();
//        dummyInput.add(new char[]{'w', 'h', 'i', 'c', 'h'});
//        dummyInput.add(new char[]{'t', 'h', 'e', 'r', 'e'});
//
//        // Call the method
//        solver.receiveInfo(dummyInput);
//
//        // Add assertions to check if the internal state is updated as expected
//        assertNotNull(solver.getWords());
//        assertEquals(dummyInput.size(), solver.getWords().size());
//    }
}
