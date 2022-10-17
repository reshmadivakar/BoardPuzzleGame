# BoardPuzzleGame
A game to place different pieces on a game board to switch off all the board digits.
## Problem
We start the puzzle with an initial board state where each cell has an initial value
and a global “depth”. Each time we place a piece on the board and update the
board state by incrementing each board cell which overlaps with a non-empty
piece cell. If this value equals the “depth” of the board, then it is reset to 0. After
all pieces have been placed, all cells should have the value 0.
## Solution
Approach used will generate all the possible combinations of pieces and apply them in a Depth First Traversal Approach. Below is the steps
1. Find out all the possible coordinates on board for each pieces.
2. Sort the list of pieces by the possible configurations. If there is a piece x with n possible coordinates and piece y with n+1 coordinates 
we will apply piece x first on board and generate n new boards. There after apply piece y on all the generated boards. This will continue until
the last piece is also applied.
3. After applying last piece find out the last piece where the board become fully switched off. 
4. Get the parent pieces and all their coordinates applied to get the final off board.
5. Display the result coordinates in same order as input text file.
