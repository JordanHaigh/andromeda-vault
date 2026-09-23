# Bridges In Graph

## What it does

A bridge is an edge whose removal increases the number of connected components. In DFS, a tree edge (u,v) is a bridge when low[v] > discovery[u]. The algorithm runs in O(V+E) and must distinguish a parent edge from parallel edges correctly.

## Implementations

- [Count_bridges.py](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/bridges_in_graph/Count_bridges.py>)
- [bridges.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/bridges_in_graph/bridges.cpp>)

## Related topics

- [[Topics/Cosmos/Graph Algorithms]] · [[Topics/Cosmos/Algorithms & Data Structures]]
