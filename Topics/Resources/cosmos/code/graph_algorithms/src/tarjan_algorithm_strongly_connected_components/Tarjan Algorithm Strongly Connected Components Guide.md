# Tarjan Algorithm Strongly Connected Components

## What it does

Tarjan’s SCC algorithm uses one DFS, discovery indices, low-link values, and a stack of active vertices. When low[v] equals index[v], v roots a component which is popped from the stack. It runs in O(V+E).

## Implementations

- [tarjan_algorithm_strongly_connected_components.c](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/tarjan_algorithm_strongly_connected_components/tarjan_algorithm_strongly_connected_components.c>)

## Related topics

- [[Topics/Cosmos/Graph Algorithms]] · [[Topics/Cosmos/Algorithms & Data Structures]]
