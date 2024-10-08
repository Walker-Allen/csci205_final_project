/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Titus Weng and Walker Allen
 * Section: CSCI-205
 * Date: 12/3/2023
 * Time: 10:36 PM
 *
 * Project: csci205_final_project
 * Package: org.team04
 * Class: WordleMain
 *
 * Description: The wordle main class used to run the game, interact with the user.
 * More specifically it contains a series of methods for asking the user how they would like to run the code
 * It then runs the code in the way instructed
 * Depending on the instructions in prints a report
 * Asks the user if they would like to run it all again
 * ****************************************
 */


package org.team04;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Scanner;

/**
 * The main class for the game
 */
public class WordleMain extends Application {

    /** Scanner for use across all methods*/
    static Scanner scanner = new Scanner(System.in);

    /**
     * The main method for the game
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Introduce the user
        System.out.println("Welcome to Wordle!");
        boolean keepPlaying = true;
        while(keepPlaying) {
            //Get user preferences
            //How many letters should the words have
            int width = getWidth();
            //How should the game be solved
            Solver chosenSolver = getSolver();
            //if the solver mode is not human, ask how many times to play
            if (!(chosenSolver instanceof playerSolver)) {
                int timesToPlay = getRuns();
                //store information about the runs
                int[] runs = new int[timesToPlay];
                //run games
                while (timesToPlay > 0) {
                    //Create an instance of the board to play
                    Board game = new Board(width, chosenSolver);
                    //play the game using the selected board
                    runs[timesToPlay - 1] = playGame(game);
                    timesToPlay--;
                    System.out.println("Games left: "+timesToPlay);
                }
                outputResult(runs);
            }
            //if the solver mode is human, play one game
            else {
                //Create an instance of the board to play
                Board game = new Board(width, chosenSolver);
                //play the game using the selected board
                playGame(game);
            }
            // ask if the user wants to play again, if they want to, restart the loop
            keepPlaying = tokeepPlaying();
        }
        System.out.println("Thanks for playing Wordle!");
        //Break the program
        System.exit(0);
    }

    /**
     * A method that actually runs the game
     * @param playSpace the board to be used
     * @return the number of turns used
     */
    private static int playGame(Board playSpace){
        int turnsUsed =0;

        boolean completed = false;
        while(!completed){
            int output = playSpace.takeTurn();
            if(output == 2){
                System.out.println("Out of Guesses. The correct word was: " + playSpace.getWord());
                return 0;
            }
            else if(output==1) {
                System.out.println("Correct");
                completed = true;
            }
            turnsUsed++;
        }
        return turnsUsed;
    }

    /**
     * A simple helper method for main to ask if the user wants to play again after one game has ended
     * @return a boolean that is true if the user wants to play again
     */
    private static boolean tokeepPlaying(){
        System.out.println("Do you want to play again [Y|N]");
        String response = scanner.next();
        // Consume the newline character left in the buffer
        scanner.nextLine();

        if(response.equals("Y") || response.equals("y")){
            return true;
        }
        else if(response.equals("N") || response.equals("n")){
            return false;
        }
        else{
            return tokeepPlaying();
        }
    }

    /**
     * A helper method for main that take in an array of ints
     * the ints are the number of turns used each solve. They equal zer0 if the solve failed
     * @param data the array of ints
     */
    private static void outputResult(int[] data){
        //information it collects
        int timesPlayed =data.length;
        double succPercent = WordleHelper.getSucc(data);
        //remove all fails from the list and sort it in increasing order
        int[] noFails = WordleHelper.getNoFails(data);

        double meanGuesses=WordleHelper.getMean(noFails);
        double medianGuesses= WordleHelper.getMedian(noFails);
        double stdDev = WordleHelper.getStdDev(noFails,medianGuesses);

        System.out.println("Wordle was played "+ timesPlayed + " times");

        System.out.printf("The answer was correctly found %.4f", succPercent);
        System.out.println("% of the time");

        System.out.println("When it was solved:");
        System.out.printf("The Mean was: %.2f %n", meanGuesses); //Trunkate the response to 2 decimal places
        System.out.println("The Median was: " + medianGuesses);
        System.out.printf("The Standard Deviation was: %.4f %n", stdDev); //Trunkate the response to 4 decimal places
    }


    /**
     * A helper method for main that asks how many times the user want to place
     * Should only run if a human is not playing
     * @return an int that is the number of times to play
     */
    private static int getRuns(){
        System.out.println("How many times should the Solver run?");
        return scanner.nextInt();
    }

    /**
     * A simple helper method for main
     * Asks the user how they want the Wordle to be solved
     * @return choice which is a solver object
     */
    private static Solver getSolver(){
        System.out.println("How would you like to Solve the Wordle");
        System.out.println("Play Yourself [1]");
        System.out.println("RandomSolver [2]");
        System.out.println("Better Solver [3]");
        System.out.println("Optimal Solver [4]");

        try {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                return getSolver(); // Recursive call
            }

            int option = Integer.parseInt(input);

            Solver choice;

            switch (option) {
                case 1:
                    choice = new playerSolver();
                    break;
                case 2:
                    choice = new randomSolver();
                    break;
                case 3:
                    choice = new betterSolver();
                    break;
                case 4:
                    choice = new optimalSolver();
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid solver.");
                    return getSolver(); // Recursive call
            }

            return choice;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return getSolver(); // Recursive call
        }
    }

    /**
     * A simple helper method for main
     * asks how long the words should be
     * @return choice which is an int
     */
    private static int getWidth() {
        System.out.println("Please choose a word length [4, 5, 6]: ");
        try {
            String input = scanner.nextLine().trim();
            int choice = Integer.parseInt(input);

            if (choice == 4 || choice == 5 || choice == 6) {
                return choice;
            } else {
                System.out.println("Invalid choice. Please choose a valid word length.");
                return getWidth(); // Recursive call
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return getWidth(); // Recursive call
        }
    }

    //Java Fx stuff that is not used
    /**
     * The start method for the game
     * @param primaryStage the stage to be used
     * @throws IOException if the FXML file cannot be loaded
     */
    public void start(Stage primaryStage) throws IOException {
        // Load the FXML file. Obtain the root of the scene graph
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/WordleUI.fxml"));
        Parent root = loader.load();

        // Set up the stage and show it
        primaryStage.setTitle("Wordle");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        // Introduce the user
        System.out.println("Welcome to Wordle!");
    }
}

