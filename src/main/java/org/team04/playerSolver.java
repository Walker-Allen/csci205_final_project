/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Walker Allen
 * Section: 9am
 * Date: 11/10/2023
 * Time: 9:18 AM
 *
 * Project: csci205_final_project
 * Package: org.team04
 * Class: humanSolver
 *
 * Description:
 * Player Solver implements the Solver Class in order to provide guess to the Board Class.
 * Player Solver prompts the user for word containing the chosen number of letter
 * It then verifies that the typed a series of letter of the correct length
 */
package org.team04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class playerSolver implements Solver{
    /** An arraylist of the words in the chosen list
     *  This is used to check if the user input is a valid word
     */
    private ArrayList<char[]> words = new ArrayList<>();

    /**
     * Generates the nest guess in the form of a char list with 5 letters
     *
     * @param firstGuess a boolean that tells the Solver whether it has been used for this WordleGame before
     * @param response array of integers containing the numerical response to the previous guess
     *                 Solver's store the previous guess themselves
     * @return guess which contains a char[] which contains a guess
     */
    @Override
    public char[] getNextMove(int[] response, boolean firstGuess) {
        Scanner scanner = new Scanner(System.in);
        if(!firstGuess) {
            //print the response
            for (int i = 0; i < response.length; i++) {
                System.out.print(response[i]);
            }
            System.out.println();
        }

        boolean validGuess = false;
        char[] charArray = {'a'};
        while(!validGuess) {
            System.out.println("Type a " + response.length + " letter word:");
            String userInput = scanner.nextLine();
            charArray = userInput.toCharArray();

            for(int i=0;i< words.size();i++){
                if (Arrays.equals(words.get(i), charArray)){
                    return charArray;
                }
            }
            //#todo this needs to be fixed to account for bad characters
            if (charArray.length != response.length) {
                System.out.println("Invalid guess, try again. This will not count as a turn.");
            }
            else {
                validGuess = true;
            }
        }
        return charArray;
    }

    /**
     * Receives two arrayLists which Solver can store to use as list of Guesses and answers
     * @param guessInput an arrayList of possible inputs and stores it in guessWords
     * @param answerInput an arrayList of possible answers and does not use it
     */
    @Override
    public void receiveInfo(ArrayList<char[]> guessInput,ArrayList<char[]> answerInput) {
        words = guessInput;
    }
}
