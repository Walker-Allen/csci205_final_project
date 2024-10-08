# CSCI 205 - Software Engineering and Design
Bucknell University
Lewisburg, PA

### Course Info
Instructor: Prof. King
Semester: Fall 2023

## Team Information
Team Name: Wordle Group #2 <br>
Allen Walker: Sophomor Computer Science Engineering Major, the Product Owner <br>
Berty Levi: Junior, the Maintainer <br>
Titus Weng: Sophomore CSEG major, the Scrum Master <br>

## Project Information
This project is a recreation of the hit game world with extra functionality. It started with very high <br>
ambitions that have slowly been trimmed down as a result of time constraints. The original project <br>
was meant to print out the definition of the words after the game was over as well as allow <br>
flexibility in the number of guesses allowed. Most significant among these was the abandonment of a <br>
JavaFX interface, so now the program in run through the console. That said many key features remained <br>
<br>
In the game Wordle the user is prompted with 5 spaces in which to type a word. After they submit it <br>
they are told which letter were in the exact right spot and which are in the word but in the wrong place <br>
they repeat this process until they are able to narrow it down and guess the correct word or lose. <br>
Our program expands on this idea by containing not just a five letter game-mode but includes a four <br>
letter and six letter options as well. On top of this We provided the algorithmic based solver that <br>
can play the game as well. <br>
<br>
For each game-mode two word lists are provided. One containing all accepted guesses and a more curated <br>
list of possible answers. This is a feature implemented in Wordle as well in order to prevent problematic <br>
issues such as user be required to guess the word "aargh". Unfortunately we did have time to trim down the <br>
word list for six letters. <br>
<br>
There are three algorithmic solver provided in this program, RandomSolver, BetterSolver, and OptimalSolver. <br>
All three are capable of solving all game-modes and have on core feature in common. After receiving a <br>
response from the game they can remove every possible wrong answer narrowing the list down. This allows <br>
even RandomSolver to function fairly well. Here is how they all work with some data showing performance: <br>
<br>

Random Solver: Guesses a random word from the guess list and then removes all incorrect possibilities. <br>
Data: <br>
Wordle was played 10000 times <br>
The answer was correctly found 88.97% of the time <br>
When it was solved: <br>
The Mean was: 4.57 <br>
The Median was: 5.0 <br>
The Standard Deviation was: 1.0686 <br>
<br>

Better Solver: The first attempt a Solver better than random guessWork. It performed nearly identically.  <br>
Details for its function or provided in its own description. <br>
Data: <br>
Wordle was played 10000 times <br>
The answer was correctly found 88.9499% of the time <br>
When it was solved: <br>
The Mean was: 4.56 <br>
The Median was: 5.0 <br>
The Standard Deviation was: 1.0243 <br>
<br>

Optimal Solver: The second attempt at a Solver better than random GuessWork. It was a success. <br>
Optimal Solver cheats slightly by being aware of the difference between the Answer List and Guess List <br>
but this is not where its improved performance comes from. It compares every possible guess to every possible <br>
answer and picks the best guess on average (details provided in Optimal Solvers file). This come at a cost <br>
Optimal Solver is significantly slower than the other taking ~15 second per Solve. In order to combat this <br>
the first guess is hard-coded since it would always be the same anyway. Multi-threading would have been a <br>
better solution, but it was not figured out in time. It is still noticeably slower. <br>
Data: <br>
Wordle was played 10000 times <br>
The answer was correctly found 98.7299% of the time <br>
When it was solved: <br>
The Mean was: 3.83 <br>
The Median was: 4.0 <br>
The Standard Deviation was: 0.9202 <br>
<br>

Video Presentation: https://mediaspace.bucknell.edu/media/Final%20Project%20Presentation/1_ldqp4pm7 <br>

## Outer URLS
Sources and Inspiration for Solvers: <br>
OptimalSolver: https://www.youtube.com/watch?v=v68zYyaEmEA <br>
<br>
BetterSolver: https://www.youtube.com/watch?v=yuGUa-krYDA&t=266s <br>


https://github.com/cherdt/fourxfour/blob/master/sorted4.dictionary <br>
<br>
https://github.com/Morgenstern2573/wordle_clone/blob/master/build/words.js <br>
<br>
https://github.com/CameronDeweerd/Wordle-VI/blob/main/answer_list.txt <br>
<br>
https://github.com/jpkhawam/WordleFX/tree/master<br> <br>
<br>
https://www.freecodecamp.org/news/build-a-wordle-clone-in-javascript/ <br>


