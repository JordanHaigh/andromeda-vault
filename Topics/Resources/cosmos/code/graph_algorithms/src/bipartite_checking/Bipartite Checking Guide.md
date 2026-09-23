# Bipartite Checking

## What it does

Tests whether vertices can be split into two sets with every edge crossing between sets. Breadth-first or depth-first search assigns alternating colors and rejects an edge joining equal colors. It runs in O(V+E); an odd cycle is exactly an obstruction.

## Implementations

- [bipartite_checking.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/bipartite_checking/bipartite_checking.cpp>)
- [bipartite_checking.java](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/bipartite_checking/bipartite_checking.java>)
- [bipartite_checking2.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/bipartite_checking/bipartite_checking2.cpp>)
- [bipartite_checking_adjacency_list.java](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/bipartite_checking/bipartite_checking_adjacency_list.java>)

## Related topics

- [[Topics/Cosmos/Graph Algorithms]] · [[Topics/Cosmos/Algorithms & Data Structures]]
