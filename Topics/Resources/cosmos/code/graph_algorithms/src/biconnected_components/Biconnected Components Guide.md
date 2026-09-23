# Biconnected Components

## What it does

Partitions an undirected graph into maximal vertex-biconnected components, using DFS discovery times and low-link values with an edge stack. Articulation points separate blocks. The standard Tarjan-style traversal is O(V+E) and requires careful handling of root and parent edges.

## Implementations

- [biconnected_components.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/biconnected_components/biconnected_components.cpp>)
- [biconnected_components.java](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/biconnected_components/biconnected_components.java>)
- [biconnected_components.py](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/biconnected_components/biconnected_components.py>)

## Related topics

- [[Topics/Cosmos/Graph Algorithms]] · [[Topics/Cosmos/Algorithms & Data Structures]]
