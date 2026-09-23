# Longest Path Directed Acyclic Graph

## What it does

In a DAG, a topological order permits longest-path dynamic programming: relax outgoing edges in order using max rather than min. It runs in O(V+E), can handle negative weights, and requires acyclicity. Initialize unreachable vertices distinctly from zero.

## Implementations

- [longest_path_directed_acyclic_graph.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/longest_path_directed_acyclic_graph/longest_path_directed_acyclic_graph.cpp>)

## Related topics

- [[Topics/Cosmos/Graph Algorithms]] · [[Topics/Cosmos/Algorithms & Data Structures]]
