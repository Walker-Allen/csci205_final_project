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
 * Class: Board
 *
 * Description: A class to represent the board in the game wordle
 * it opens the text file containing the lists of answer and possible guesses
 * it passes this information onto Solver
 * And runs turns of the game where Solver guess and then it varifies their response
 * first by checking if it is correct then by calling evaluateGuess in order to provide a proper response
 * ****************************************
 */
package org.team04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Board {
    /** Stores the number of letters on the board */
    private int width;
    /** stores the number of tries */
    private Solver Solver;
    /** stores the words being used in the game */
    private ArrayList<char[]> words = new ArrayList<>();
    /** stores the words being used in the game */
    private ArrayList<char[]> answers= new ArrayList<>();
    /** stores a char list representing the correct word */
    private char[] answer;
    /** stores the last response which is used for Solving */
    private int[] lastResponse;
    /** Stores the max number of tuns which is always 6 */
    private int maxTurns = 6;
    /** The number of tuns that have been used*/
    private int turns =0;

    /**
     * Constructor for the board
     * @param inWidth the number of letters on the board
     * @param inSolver the solver that will provide guesses
     * Also opens a text document corresponding to the width to get a word list
     * Uses that word list to pick a random word to be the answer
     */
    public Board(int inWidth,  Solver inSolver){
        width = inWidth;
        Solver = inSolver;
        words = getWords();
        answers = findAnswer();
        //get random answer
        answer = answers.get(((int)(Math.random()*answers.size())));
        lastResponse = new int[width];
        for(int i=0; i<width; i++){
            lastResponse[i]=0;
        }
        Solver.receiveInfo(words,answers);
    }

    /**
     * A helper method for the constructor
     * based on the width it opens one of 3 text documents and fills
     * the word arraylist with their contents in char[] form
     * Also contains a way of opening a fourth text file for testing purposes
     */
    public ArrayList<char[]> getWords() {
        //create a new arrayList to send
        ArrayList<char[]> fileWords = new ArrayList<>();
        //creates the file path based on width
        String filePath;
        if(width ==5) {
            filePath = "src/main/Resources/fiveLetterWordsGuesses.txt";
        }
        else if(width ==4){
            filePath = "src/main/Resources/fourLetterWordsGuesses.txt";
        }
        else if(width ==6){
            filePath = "src/main/Resources/sixLetterWordsGuesses.txt";
        }
        else{
            filePath = "src/test/Resources/exampleWords.txt";
        }

        //fill out the word list
        Path absolutePath = Paths.get(filePath).toAbsolutePath();
        try {
            // Read all lines from the file into a List of Strings
            List<String> lines = Files.readAllLines(absolutePath);
            // Process each line
            for (String line : lines) {
                // Convert each line to a char array
                line = line.toLowerCase();
                char[] newWord = line.toCharArray();
                fileWords.add(newWord);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileWords;
    }

    /**
     * A simple helper method used for testing that can set the answer to a specific word
     * @param input the word to be set as the answer
     *              must be the same length as the width
     */
    public void setAnswer(char[] input) {
        answer = input;
    }

    /**
     * Opens one of three text files of wordlists based on the width
     * Returns an arrayList to be used in order to pick a random answer
     * @return answer which represents a list of possible answers
     */
    private ArrayList<char[]> findAnswer(){
        //answer word list
        ArrayList<char[]> answers = new ArrayList<>();
        //creates the file path based on width
        String filePath;
        if(width ==5) {
            filePath = "src/main/Resources/fiveLetterWordsAnswers.txt";
        }
        else if(width ==4){
            filePath = "src/main/Resources/fourLetterWordsAnswers.txt";
        }
        else {
            filePath = "src/main/Resources/sixLetterWordsAnswers.txt";
        }

        //fill out the word list
        Path absolutePath = Paths.get(filePath).toAbsolutePath();
        try {
            // Read all lines from the file into a List of Strings
            List<String> lines = Files.readAllLines(absolutePath);
            // Process each line
            for (String line : lines) {
                // Convert each line to a char array
                line = line.toLowerCase();
                char[] newWord = line.toCharArray();
                answers.add(newWord);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return answers;
    }

    /**
     * A method that takes a turn
     * @return 0 if the guess was wrong
     *        1 if the guess was correct
     *        2 if the guess was wrong and the player is out of turns
     */
    public int takeTurn(){
        turns++;
        if(turns > maxTurns){
            return 2;
        }
        char[] move;
        if(turns ==1){
            move = Solver.getNextMove(lastResponse, true);
        }
        else {
            move = Solver.getNextMove(lastResponse, false);
        }
        int[] response = WordleHelper.evaluateGuess(move,answer);
        lastResponse = response;
        return correctGuess(move);
    }

    /**
     * A simple helper method to return true if the parameter guess
     * is the same as the answer
     * @param guess the guess to be checked
     * @return true if the guess is correct
     */
    public int correctGuess(char[] guess){
        for(int i=0; i<width; i++){
            if(guess[i]!=answer[i]){
                return 0;
            }
        }
        return 1;
    }


    /**
     * A simple helper method to return the answer as a string
     * @return the answer as a string
     */
    public String getWord() {
        return new String(answer);
    }

    /**
     * A simple helper method to return the width
     * @return the width
     */
    public int getWidth(){
        return width;
    }
}
