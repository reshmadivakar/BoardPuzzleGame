# BoardPuzzleGame
A game to place different pieces on a game board to switch off all the board bits.
## Problem
We start the puzzle with an initial board state where each cell has an initial value and a global “depth”. Each time we place a piece on the board and update the board state by incrementing each board cell which overlaps with a non-emptypiece cell. If this value equals the “depth” of the board, then it is reset to 0. After all pieces have been placed, all cells should have the value 0.
## Solution
Approach used here will generate all the possible combinations of pieces and apply them in a Depth First Traversal Approach. Below is the detailed explanation with the steps. Consider the example with a board size 3 x 3 and given 3 pieces with sizes 2x2, 1x2, 3x2

 * Step 1: Find out all the possible coordinates on board for each pieces. In the above example for piece 1 with 2x2 size there will be 4 possible coordinates on board  [(0,0) (0,1) (1,0) (1,1)]. Similarly for piece 2 there will be 6 possible coordinates where it can be placed and for piece 3 there will be 2 coordinates.
* Step 2: Sort the list of pieces by the possible configurations. In the above example piece 3 can be placed in only two ways. Apply those two pieces on to the board and generate two new boards. There after apply piece 1 to each of the two and generate 8 new boards followed by applying piece 3 to each of these new boards. Each new board will store the parent piece and the coordinates where it is applied.
* Step 3: After applying last piece find out the last piece configuration where the board become fully switched off. 
* Step 4: Get the parent pieces and all their coordinates applied to get the final off board.
* Step 5: Display the result coordinates in same order as input text file.

## How to run
* Make sure you have Java 8 or above installed in the machine
* Checkout the code from the repo
* Run ``` mvn clean install ``` to build the project
* Then run ``` mvn compile exec:java -Dexec.mainClass="com.brs.assignment.Main" ``` to execute the program.
* The solution gets printed in the terminal between the below given lines as shown below:
 ```
 =========  Printing GAME RESULT  ==========
 ANSWERS
 =========  ==================== ===========
```

## Solution
| boardsize | depth    | number of pieces | number of possibilities | time taken in hh:mm:ss | solution
| ---   |---     | ---       | --- |-- |---
| 3 * 3 | 2 | 7  |  576 | 00:00:00 |0,0  1,0  1,0  0,0  1,0  1,0  0,1
| 4 * 4 | 2 | 7  |  186624.0   | 00:00:00 |1,0  0,1  0,1  2,2  1,0  1,0  0,2
| 6 * 4 | 3 | 11 |  5.59872 x 10<sup>8</sup>   | 00:02:22 |0,0  1,3  0,2  2,0  1,0  0,4  1,2  0,0  0,0  0,2  1,0
| 5 * 6 | 3 | 11 |  2.985984 x 10<sup>8</sup>   |00:07:15 |1,0  3,2  0,0  2,0  0,0  0,0  0,0  1,2  0,0  2,1  3,0
| 4 * 6 | 3 | 12 |  4.2467328 x 10<sup>10</sup>	|00:53:18 |2,0  3,1  0,0  4,0  2,3  1,0  0,1  3,2  1,0  2,1  0,0  1,0
| 7 * 5 | 4 | 12 |  1.492992 x 10<sup>11</sup>	|01:15:07 | 1,0  0,2  0,3  0,3  0,0  0,2  2,3  2,3  0,1  1,2  0,0  0,1

## Developer notes
The above approach is not an optimized approach as it uses all the possible combinations of pieces to find out the final solution. As the board size and 
number of pieces increases the time to find out the final solution will also increases accordingly. Above is the comparsion of data received by executing various sample input files. The last few files will take a long time to generate solutions using the current strategy as the possibilities are very high.

## Further Work
We need to create a strategy where we can do some early elimination of the nodes or some matrix mathematics based apporach.
 
