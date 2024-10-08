/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Titus Weng and Walker Allen
 * Section: 9am
 * Date: 11/10/2023
 * Time: 9:11 AM
 *
 * Project: csci205_final_project
 * Package: org.team04
 * Class: optimalSolver
 *
 * Description: A solver that uses the optimal strategy to solve the Wordle game.
 * It compares every word to every other word and finds the one that has the highest chance of being the answer.
 *  Specifically when evaluating words it counts the total number of time is generated exact matches and correct letters
 *  It then picks the word with the highest number of exact matches using the number of correct letters as a tie breaker
 * Inspiration taken from this video: https://www.youtube.com/watch?v=v68zYyaEmEA
 * Performance details in readMe.
 * ****************************************
 */


package org.team04;

import java.util.ArrayList;

public class optimalSolver implements Solver {
    /** An arraylist of the words in the chosen list */
    private ArrayList<char[]> guessWords = new ArrayList<>();
    /** An arraylist of the words in the chosen list that could be answers*/
    private ArrayList<char[]> answerWords = new ArrayList<>();
    /** stores the last guess from getNextMove */
    private char[] lastGuess;

    /**
     * Generates the nest guess in the form of a char list with 5 letters
     * @param response array of integers containing the numerical response to the previous guess
     * @param firstGuess a boolean that tells the Solver whether it has been used for this WordleGame before
     * @return char[] a list of 5 letters
     */
    @Override
    public char[] getNextMove(int[] response, boolean firstGuess) {
        char[] guess;
        //the first guess it hard coded for performance reasons
        // more detail explanation in readMe
        if(response.length==6){
            guess = new char[]{'c', 'a', 'r', 'i', 'e', 's'};
        }
        else if(response.length==5){
            guess = new char[]{'s','o','a','r','e'};
        }
        else {
            guess = new char[]{'s','a','l','e'};
        }

        if (!firstGuess) {
            // print the response
            for (int i = 0; i < response.length; i++) {
                System.out.print(response[i]);
            }
            System.out.println();

            //remove bad options first
            answerWords = WordleHelper.removeWrongWords(response,lastGuess,answerWords);

            //compare every guess to each other and see which one is best
            double[] guessQuality = new  double[guessWords.size()];
            //do the looping
            for(int i=0; i< guessWords.size();i++){
                char[] guessWord = guessWords.get(i);
                for(int k=0; k<answerWords.size();k++) {
                    char[] answerWord = answerWords.get(k);
                    //compare the two
                    int[] values = WordleHelper.evaluateGuess(guessWord, answerWord);
                    int numTwo = 0; int numOne =0;
                    for(int j=0; j< values.length;j++){
                        if (values[j]==2){
                            numTwo++;
                        }
                        else if(values[j]==1){
                            numOne++;
                        }
                    }
                    guessQuality[i]+=(numTwo*5)+(numOne);
                }
            }
            //find the highest value in guessQuality and return the corresponding word
            int largest=0; double highestValue =0;
            for(int i=0; i< guessQuality.length;i++){
                if(guessQuality[i]>highestValue){
                    highestValue = guessQuality[i];
                    largest =i;
                }
                guess = guessWords.get(largest);
            }

        }
        // print out the guess and use i
        System.out.println(guess);
        lastGuess = guess;
        return guess;
    }

    /**
     * Receives the response from the board in the form of
     * a list of integers
     *
     * @param guessInput  an arrayList of possible inputs and stores it in guessWords
     * @param answerInput an arrayList of possible answers and stores it in answerWords
     */
    @Override
    public void receiveInfo(ArrayList<char[]> guessInput,ArrayList<char[]> answerInput){
        guessWords = guessInput;
        answerWords= answerInput;
    }
}
