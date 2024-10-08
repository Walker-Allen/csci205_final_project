package org.team04;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    /**
     * Test the getWords method using exampleWords.txt
     */
    @Test
    void getWords() {
        Board board = new Board(7, new playerSolver());
        ArrayList<char[]> tobePulled;
        ArrayList<char[]> fileWords = new ArrayList<>();
        fileWords.add(("aback").toCharArray());
        fileWords.add(("abase").toCharArray());
        fileWords.add(("abate").toCharArray());
        fileWords.add(("abbey").toCharArray());
        fileWords.add(("abbot").toCharArray());
        fileWords.add(("wryly").toCharArray());
        tobePulled = board.getWords();
        for(int i=0; i<fileWords.size();i++){
            assertArrayEquals(fileWords.get(i),tobePulled.get(i));
        }
    }

    /**
     * Test the correctGuess method
     */
    @Test
    void correctGuess() {
        Board board = new Board(5, new playerSolver());
        char[] testAnswer = {'t', 'e', 's', 't', 's'};
        board.setAnswer(testAnswer);

        char[] correctGuess = {'t', 'e', 's', 't', 's'};
        int result = board.correctGuess(correctGuess);
        assertEquals(1, result);

        char[] incorrectGuess = {'a', 'b', 'c', 'd', 'e'};
        result = board.correctGuess(incorrectGuess);
        assertEquals(0, result);
    }

    /**
     * Test the getWidth method
     */
    @Test
    void getWidth() {
        Board board = new Board(5, new playerSolver());
        int result = board.getWidth();
        assertEquals(5, result);
    }
}