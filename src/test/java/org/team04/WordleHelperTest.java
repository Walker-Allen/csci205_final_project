package org.team04;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WordleHelperTest {
    /**
     * Test the getSucc method
     */
    @Test
    void getSucc() {
        int[] testData = new int[]{2,3,4,5,4,0,0,0,0,4};
        double percentage = 60.0;
        double testPercentage = WordleHelper.getSucc(testData);
        assertEquals(percentage,testPercentage);
    }

    /**
     *  tes the getNoFails method
     */
    @Test
    void getNoFails() {
        int[] testData = new int[]{2,3,4,5,4,0,0,0,0,4};
        int[] cleanData = new int[]{2,3,4,4,4,5};
        int[] testCleanData = WordleHelper.getNoFails(testData);
        assertArrayEquals(cleanData,testCleanData);
    }

    /**
     * Test the getMean Method with an error of .01
     */
    @Test
    void getMean() {
        int[] testData = new int[]{2,3,4,4,4,5};
        double Mean = 3.66;
        double testMean = WordleHelper.getMean(testData);
        assertTrue(Mean <= testMean && testMean<=Mean+.01);
    }

    /**
     * Test the getMedian method
     */
    @Test
    void getMedian() {
        int[] testData = new int[]{2,3,4,5,5,5};
        double Median = 4.5;
        double testMedian = WordleHelper.getMedian(testData);
        assertEquals(Median, testMedian);

        testData = new int[]{2,3,3,4,5,5,6};
        Median = 4;
        testMedian = WordleHelper.getMedian(testData);
        assertEquals(Median, testMedian);
    }

    /**
     * Test the getStdDev Method with an accepted error of .001
     */
    @Test
    void getStdDev() {
        int[] testData = new int[]{2,4,4,5,5};
        double testMean = 4;
        double testStdDev = 1.095;
        double StdDev = WordleHelper.getStdDev(testData, testMean);
        assertTrue(testStdDev <= StdDev && StdDev <= testStdDev +.001);
    }

    /**
     * Test the removeWrongWords method
     */
    @Test
    void removeWrongWords(){
        ArrayList<char[]> testWords = new ArrayList<>();
        testWords.add(("aback").toCharArray());
        testWords.add(("abase").toCharArray());
        testWords.add(("abate").toCharArray());
        testWords.add(("abbey").toCharArray());
        testWords.add(("abbot").toCharArray());
        testWords.add(("wryly").toCharArray());

        //first test
        int[] testResponse = new int[]{0,0,0,0,0};
        char[] testGuess = ("abate").toCharArray();

        ArrayList<char[]> testRemainingWords = new ArrayList<>();
        testRemainingWords.add(("wryly").toCharArray());

        ArrayList<char[]> remainingWords = WordleHelper.removeWrongWords(testResponse,testGuess,testWords);
        for(int i=0; i< testRemainingWords.size();i++) {
            assertArrayEquals(remainingWords.get(i), testRemainingWords.get(i));
        }

        //second test
        testResponse = new int[]{0,0,0,0,0};
        testGuess = ("wryly").toCharArray();

        testRemainingWords.remove(0);
        testRemainingWords.add(("aback").toCharArray());
        testRemainingWords.add(("abase").toCharArray());
        testRemainingWords.add(("abate").toCharArray());
        testRemainingWords.add(("abbot").toCharArray());

        remainingWords = WordleHelper.removeWrongWords(testResponse,testGuess,testWords);
        for(int i=0; i< testRemainingWords.size();i++) {
            System.out.println(remainingWords.get(i));
            assertArrayEquals(remainingWords.get(i), testRemainingWords.get(i));
        }
    }

    /**
     * Test the constructor
     */
    @Test
    void evaluateGuess() {
        char[] testAnswer = {'t', 'e', 's', 't', 's'};

        char[] correctGuess = {'t', 'e', 's', 't', 's'};
        int[] result = WordleHelper.evaluateGuess(correctGuess,testAnswer);
        assertArrayEquals(new int[]{2, 2, 2, 2, 2}, result);

        char[] partialCorrectGuess = {'t', 'e', 's', 't', 'a'};
        result = WordleHelper.evaluateGuess(partialCorrectGuess,testAnswer);
        assertArrayEquals(new int[]{2, 2, 2, 2, 0}, result);

        char[] incorrectGuess = {'a', 'b', 'c', 'd', 'e'};
        result = WordleHelper.evaluateGuess(incorrectGuess,testAnswer);
        assertArrayEquals(new int[]{0, 0, 0, 0, 1}, result);
    }

}