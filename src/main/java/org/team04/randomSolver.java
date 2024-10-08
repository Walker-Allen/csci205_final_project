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
 * Class: randomSolver
 * Description:
 * Random Solver implements the Solver Class in order to provide guess to the Board Class.
 * It contains an implementation of recieveInfo that only stores the possible guesses.
 * For generating guesses it picks a random Word from the guess list and returns it.
 * When recieving a response it removes every guess that is no longer possible to make future guesses better
 * Data on performance provided in the ReadMe
 * ****************************************
 */
package org.team04;

import java.util.ArrayList;

public class randomSolver implements Solver{
    /** An arraylist of the possible guesses */
    private ArrayList<char[]> words = new ArrayList<>();

    /** stores the last guess from getNextMove */
    private char[] lastGuess;

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
        if (!firstGuess) {
            // print the response
            for (int i = 0; i < response.length; i++) {
                System.out.print(response[i]);
            }
            System.out.println();

            words = WordleHelper.removeWrongWords(response,lastGuess,words);
        }

        //System.out.println("Size of List: " + words.size());

        // get a random word from the list
        int random = (int) (Math.random() * words.size());
        char[] guess = words.get(random);

        // print out the guess and use it
        System.out.println(guess);
        lastGuess = guess;
        return guess;
    }


    /**
     * Receives two arrayLists which Solver can store to use as list of Guesses and answers
     * @param guessInput an arrayList of possible inputs and stores it in guessWords
     * @param answerInput an arrayList of possible answers and does not use it
     */
    @Override
    public void receiveInfo(ArrayList<char[]> guessInput,ArrayList<char[]> answerInput){
        words = guessInput;
    }


}

