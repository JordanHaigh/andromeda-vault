# Kasai Algorithm

## What it does

Kasai’s algorithm computes the longest-common-prefix (LCP) array for suffixes in O(n), given a suffix array and inverse ranks. It reuses the previous suffix’s common-prefix length, which can fall by at most one when the start position advances.

## Implementations

- [kasai_algorithm.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/kasai_algorithm/kasai_algorithm.cpp>)

## Related topics

- [[Topics/Cosmos/String Algorithms]] · [[Topics/Cosmos/Algorithms & Data Structures]]
