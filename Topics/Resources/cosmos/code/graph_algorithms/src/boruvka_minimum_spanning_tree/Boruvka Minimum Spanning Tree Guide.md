# Boruvka Minimum Spanning Tree

## What it does

Borůvka’s algorithm repeatedly chooses the cheapest outgoing edge for every current component and merges components. Each phase at least halves the number of components, yielding O(E log V) phases/work with suitable component tracking. Ties may produce duplicate candidate edges that must be handled.

## Implementations

- [boruvka_minimum_spanning_tree.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/boruvka_minimum_spanning_tree/boruvka_minimum_spanning_tree.cpp>)

## Related topics

- [[Topics/Cosmos/Graph Algorithms]] · [[Topics/Cosmos/Algorithms & Data Structures]]
