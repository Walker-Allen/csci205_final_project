/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Titus Weng and Walker Allen
 * Section: 9am
 * Date: 11/30/2023
 * Time: 11:05 AM
 *
 * Project: csci205_final_project
 * Package: org.team04
 * Class: WordleHelper
 *
 * Description:
 * This class contains a collecting of methods that are used throughout the rest of this project
 * They were moved here because they were either used many times or took up to much space and were simple
 */
package org.team04;

import java.util.ArrayList;
import java.util.Arrays;

public final class WordleHelper {

    /**
     * Calculates the number of successfull Solves from a simulation
     * This method takes in an arrayList of integers and return the percentage that were not zero
     */
    public static double getSucc(int[] data){
        //count the number of fails
        double fails =0;
        for(int i=0; i<data.length;i++){
            if(data[i]==0) {fails++;}
        }
        return 100*(1-fails/data.length);
    }

    /**
     * A method to get the number of fails
     * @param data the array of data
     * @return the number of fails
     */
     public static int[] getNoFails(int[] data) {
        int fails =0;
        for(int i=0; i<data.length;i++){
            if(data[i]==0) {fails++;}
        }

        //copy the array
        int[] noFails= new int[data.length - fails];
        for(int i = 0; i< data.length; i++){
            if(data[i]!=0){
                for(int k=0; k<noFails.length;k++){
                    if(noFails[k]==0){
                        noFails[k]= data[i];
                        break;
                    }
                }
            }
        }
        //sort the array
        Arrays.sort(noFails);
        return noFails;
    }


    /**
     * A method to get the mean of the number of guesses
     * @param noFails the array of data
     * @return the mean of the number of guesses
     */
    public static double getMean(int[] noFails){
        //calculate the mean
        double total=0;
        for(int i=0; i<noFails.length;i++){
            total+=noFails[i];
        }
        return total/noFails.length;
    }

    /**
     * A method to get the median of the number of guesses
     * @param noFails the array of data
     * @return the median of the number of guesses
     */
    public static double getMedian(int[] noFails){
        //calculate the median
        if(noFails.length%2!=0){
            return  noFails[noFails.length/2];
        }
        else {
            return ((noFails[(noFails.length-1)/2] + noFails[noFails.length/2])/2.0);
        }
    }

    /**
     * A method to get the standard deviation of the number of guesses
     * @param noFails the array of data
     * @param meanGuesses the mean of the number of guesses
     * @return the standard deviation of the number of guesses
     */
    public static double getStdDev(int[] noFails, double meanGuesses){
        //calculate the standard deviation
        double tempTotal=0;
        for(int i=0; i< noFails.length; i++){
            tempTotal+= Math.pow((noFails[i]-meanGuesses),2);
        }
        tempTotal/=noFails.length;
        return Math.sqrt(tempTotal);
    }


    //A method that is used for the Solvers

    /**
     * A helpe method used in all three AI solvers
     * @param response the response from the board
     * @param guess the guess that was made
     * @param list the list of possible words
     * @return the list of possible words with the invalid ones removed
     */
    public static ArrayList<char[]> removeWrongWords(int[] response, char[] guess, ArrayList<char[]> list){
        // remove invalid words from the list
        ArrayList<char[]> validWords = new ArrayList<>();
        char[] usedLetters = new char[27];
        for (char[] word : list) {
            // look at every spot in the response
            boolean isValidWord = true;
            // check for twos lining up
            for (int j = 0; j < response.length; j++) {
                if (response[j] == 2) {
                    if (guess[j] != word[j]) {
                        isValidWord = false;
                        break;
                    }
                    //store the letter the 2 used
                    usedLetters[guess[j] - 96]++;
                }
            }
            //check for ones
            for (int j = 0; j < response.length; j++) {
                if (response[j] == 1) {
                    boolean containsLetter = false;
                    for (int i = 0; i < response.length; i++) {
                        if (guess[j] == word[i]) {
                            //does not currently account for multiple ones on the same letter
                            containsLetter = true;
                        }
                        if (guess[j] == word[j]) {
                            isValidWord = false;
                            break;
                        }
                    }
                    usedLetters[guess[j] - 96]++;
                    if (!containsLetter) {
                        isValidWord = false;
                    }
                }
            }
            for (int j = 0; j < response.length; j++) {
                // check for zeros existing
                if (response[j] == 0) {
                    // for every letter in the words
                    char[] tempUsedLetters = new char[27];
                    System.arraycopy(usedLetters, 0, tempUsedLetters, 0, usedLetters.length);
                    for (char letter : word) {
                        if (guess[j] == letter) {
                            //account for the letter being used in 2 once already
                            if (tempUsedLetters[guess[j] - 96] > 0) {
                                tempUsedLetters[guess[j] - 96]--;
                            } else {
                                isValidWord = false;
                                break;
                            }
                        }
                    }
                }
            }
            if (isValidWord) {
                validWords.add(word);
            }
        }
        return validWords;
    }


    /**
     * Return an array of integers that represents
     * how accurate of a guess the guess was
     * @param guess the guess
     * @return an array of integers that represents
     */
    public static int[] evaluateGuess(char[] guess, char[] answer) {
        int width = guess.length;
        // Create a loop; initialize all values to 0
        int[] response = new int[width];

        // Arrays to keep track of used letters in both guess and answer
        boolean[] usedLettersInGuess = new boolean[width];
        boolean[] usedLettersInAnswer = new boolean[width];

        // First pass: Check for correct letters in correct positions
        for (int i = 0; i < width; i++) {
            char letter = guess[i];
            if (letter == answer[i]) {
                response[i] = 2;
                usedLettersInGuess[i] = true;
                usedLettersInAnswer[i] = true;
            }
        }

        // Second pass: Check for correct letters in wrong positions
        for (int i = 0; i < width; i++) {
            char letter = guess[i];
            if (response[i] == 2) {
                continue; // Skip letters already marked as correct in position
            }
            for (int k = 0; k < width; k++) {
                if (letter == answer[k] && !usedLettersInGuess[i] && !usedLettersInAnswer[k]) {
                    response[i] = 1;
                    usedLettersInGuess[i] = true;
                    usedLettersInAnswer[k] = true;
                    break; // Break out of the inner loop after finding a match
                }
            }
        }

        // Third pass: Mark incorrect letters
        for (int i = 0; i < width; i++) {
            if (response[i] == 0) {
                response[i] = 0;
            }
        }

        return response;
    }
}




