# Ford Fulkerson Maximum Flow

## What it does

Repeatedly finds an augmenting path in the residual graph and pushes its bottleneck capacity, updating forward and reverse residual edges. With integer capacities this terminates; Edmonds–Karp chooses BFS paths for O(VE²). Reverse edges are essential to undo earlier choices.

## Implementations

- [ford_fulkerson_maximum_flow.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/ford_fulkerson_maximum_flow/ford_fulkerson_maximum_flow.cpp>)
- [ford_fulkerson_maximum_flow_using_bfs.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/ford_fulkerson_maximum_flow/ford_fulkerson_maximum_flow_using_bfs.cpp>)
- [ford_fulkerson_maximum_flow_using_bfs.java](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/ford_fulkerson_maximum_flow/ford_fulkerson_maximum_flow_using_bfs.java>)
- [ford_fulkerson_maximum_flow_using_bfs.py](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/ford_fulkerson_maximum_flow/ford_fulkerson_maximum_flow_using_bfs.py>)

## Related topics

- [[Topics/Cosmos/Graph Algorithms]] · [[Topics/Cosmos/Algorithms & Data Structures]]
