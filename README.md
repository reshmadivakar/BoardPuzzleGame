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

## Developer notes.
The above approach is not an optimized approach as it uses all the possible combinations of pieces to find out the final solution. As the board size and 
number of pieces increases the time to find out the final solution will also increases accordingly. Below is the comparsion of data received by executing various sample input files.

| boardsize     | number of pieces | time taken in HHMMSS
| ---      | ---       | ---
| 3 x 3 |    7      | 00:00:00
| 4 x 4     | 7        | 00:00:00
| 6 x 4 | 11       | 00:02:22
| 5 x 6     | 11       |00:07:15
| 4 x 6		| 12		|00:53:18
       
