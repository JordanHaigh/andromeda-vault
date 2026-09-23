# Aho Corasick Algorithm

## What it does

Aho–Corasick searches for many patterns in one text pass. It builds a trie of patterns, failure links that move to the longest viable suffix, and output links for patterns completed at each state. Construction is proportional to total pattern length; scanning is O(n + matches) after construction.

## Implementations

- [aho_corasick_algorithm.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/aho_corasick_algorithm/aho_corasick_algorithm.cpp>)
- [aho_corasick_algorithm.java](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/aho_corasick_algorithm/aho_corasick_algorithm.java>)
- [aho_corasick_algorithm2.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/aho_corasick_algorithm/aho_corasick_algorithm2.cpp>)

## Related topics

- [[Topics/Cosmos/String Algorithms]] · [[Topics/Cosmos/Algorithms & Data Structures]]
