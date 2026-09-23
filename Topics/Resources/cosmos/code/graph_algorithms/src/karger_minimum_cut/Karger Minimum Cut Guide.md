# Karger Minimum Cut

## What it does

Karger’s randomized contraction algorithm repeatedly contracts a uniformly chosen edge until two supernodes remain; their crossing edges form a cut. One run succeeds with probability at least 2/(n(n−1)) for a fixed minimum cut, so repetition boosts confidence. Preserve parallel edges and avoid self-loop selection.

## Implementations

- [karger_minimum_cut.java](<https://github.com/OpenGenus/cosmos/blob/master/code/graph_algorithms/src/karger_minimum_cut/karger_minimum_cut.java>)

## Related topics

- [[Topics/Cosmos/Graph Algorithms]] · [[Topics/Cosmos/Algorithms & Data Structures]]
