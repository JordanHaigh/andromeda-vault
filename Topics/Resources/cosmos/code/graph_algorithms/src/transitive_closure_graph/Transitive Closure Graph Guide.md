# Transitive Closure Graph

## What it does

Computes reachability between every vertex pair. Warshall’s dynamic program updates reach[i][j] when there is a route i→k and k→j. It uses O(V³) time and O(V²) memory; adjacency-list DFS from every vertex is an alternative for sparse graphs.

## Implementations

- [transitive_closure_graph.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/transitive_closure_graph/transitive_closure_graph.cpp>)
- [transitive_closure_graph.py](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/transitive_closure_graph/transitive_closure_graph.py>)
- [transitive_closure_graph_floyd_warshall.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/transitive_closure_graph/transitive_closure_graph_floyd_warshall.cpp>)
- [transitive_closure_graph_powering.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/transitive_closure_graph/transitive_closure_graph_powering.cpp>)
- [transitive_closure_graph_powering_improved.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/transitive_closure_graph/transitive_closure_graph_powering_improved.cpp>)

## Related topics

- [[Topics/Cosmos/Graph Algorithms]] · [[Topics/Cosmos/Algorithms & Data Structures]]
