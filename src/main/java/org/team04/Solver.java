/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Titus Weng And Walker Allen
 * Section: CSCI-205
 * Date: 12/3/2023
 * Time: 10:36 PM
 *
 * Project: csci205_final_project
 * Package: org.team04
 * Class: Solver
 * Description:
 * An interface that is used by the Board class in order to gather responses in the games of Wordle it runs/.
 * Takes in an arrayList of the possible words to guess and the generates guess everytime the getNextMove method is
 * called.
 * ****************************************
 */
package org.team04;

import java.util.ArrayList;

public interface Solver {

    /**
     * Generates the nest guess in the form of a char list with 5 letters
     * @return char[] a list of 5 letters
     *
     * @param firstGuess a boolean that tells the Solver whether it has been used for this WordleGame before
     * @param response array of integers containing the numerical response to the previous guess
     *                 Solver's store the previous guess themselves
     */
    public char[] getNextMove(int[] response, boolean firstGuess);


    /**
     * Receives two arrayLists which Solver can store to use as list of Guesses and answers
     * @param guessInput an arrayList of possible inputs
     * @param answerInput an arrayList of possible answers
     */
    public void receiveInfo(ArrayList<char[]> guessInput,ArrayList<char[]> answerInput);
}
