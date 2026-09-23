# Johnson Algorithm Shortest Path

## What it does

Computes all-pairs shortest paths on a sparse directed graph, allowing negative edges but no negative cycles. Bellman–Ford derives vertex potentials; reweighting makes edges non-negative, then Dijkstra runs from each vertex and distances are converted back. Typical complexity is O(VE + V² log V) with a heap.

## Implementations

- [johnson_algorithm_shortest_path.py](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/johnson_algorithm_shortest_path/johnson_algorithm_shortest_path.py>)

## Related topics

- [[Topics/Cosmos/Graph Algorithms]] · [[Topics/Cosmos/Algorithms & Data Structures]]
