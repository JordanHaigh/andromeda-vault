# Number Of Ways In Maze

## What it does

Counts paths through a grid or maze subject to allowed moves and blocked cells. For right/down moves, dp[r][c]=dp[r−1][c]+dp[r][c−1], giving O(rows·columns) time and O(columns) memory. Other move rules require extra state or graph traversal.

## Implementations

- [number_of_ways_in_maze.c](<https://github.com/OpenGenus/cosmos/blob/master/code/backtracking/src/number_of_ways_in_maze/number_of_ways_in_maze.c>)
- [number_of_ways_in_maze.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/backtracking/src/number_of_ways_in_maze/number_of_ways_in_maze.cpp>)
- [number_of_ways_in_maze.go](<https://github.com/OpenGenus/cosmos/blob/master/code/backtracking/src/number_of_ways_in_maze/number_of_ways_in_maze.go>)
- [number_of_ways_in_maze.java](<https://github.com/OpenGenus/cosmos/blob/master/code/backtracking/src/number_of_ways_in_maze/number_of_ways_in_maze.java>)
- [number_of_ways_in_maze.rs](<https://github.com/OpenGenus/cosmos/blob/master/code/backtracking/src/number_of_ways_in_maze/number_of_ways_in_maze.rs>)

## Related topics

- [[Topics/Cosmos/Algorithms & Data Structures]]
