/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Titus Weng and Walker Allen
 * Section: 9am
 * Date: 11/10/2023
 * Time: 9:17 AM
 *
 * Project: csci205_final_project
 * Package: org.team04
 * Class: optimalSolver
 *
 * Description:
 * This class interfaces the Solver class and was the first attempt at making a Solver better than randomSolver
 * It works by calculating the number of time every letter appears in every spot. ]
 * Then it goes through every word in the guess list and:
 *  if it has the letter "a" in a spot it adds the totasl number of time "a" appeared in that spot to its score
 *  repeats for all letter and guesses the Word with the highest score
 * Algorithm technique taken from this youtube video: https://www.youtube.com/watch?v=yuGUa-krYDA&t=266s
 * Performance details in readMe.
 * ****************************************
 */
package org.team04;

import java.util.ArrayList;

public class betterSolver implements Solver{
    /** An arraylist of the words in the chosen list */
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
        if(!firstGuess) {
            // print the response
            for (int i = 0; i < response.length; i++) {
                System.out.print(response[i]);
            }
            System.out.println();

            //remove bad guesses
            words = WordleHelper.removeWrongWords(response, lastGuess, words);
        }
        char[] guess = new char[response.length];

        //find a new guess
        //find the totals for all the letters
        int[][] letterCounts= new int[response.length][27];
        for(int i=0; i< words.size();i++){
            for(int j=0; j<words.get(i).length;j++){
                letterCounts[j][words.get(i)[j] - 96]++;
            }
        }
        //find the word with the highest score
        int highScore = 0;
        for(int i =0; i< words.size();i++){
            int wordTotal=0;
            for(int j=0; j<words.get(i).length;j++){
                wordTotal+=letterCounts[j][words.get(i)[j] - 96];
            }
            if(wordTotal>highScore){
                guess = words.get(i);
                   highScore=wordTotal;
            }
        }

        // print out the guess and use it
        System.out.println(guess);
        lastGuess = guess;
        return guess;
    }


    /**
     * Receives the response from the board in the form of
     * a list of integers
     */
    @Override
    public void receiveInfo(ArrayList<char[]> guessInput,ArrayList<char[]> answerInput){
        words = guessInput;
    }


}
