# Kmp Algorithm

## What it does

Knuth–Morris–Pratt searches for a pattern in linear time by precomputing the longest proper prefix that is also a suffix at each pattern position. On a mismatch it reuses this prefix table instead of rescanning text characters. Preprocessing and search are O(m+n) time with O(m) auxiliary space.

## Implementations

- [kmp.java](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/kmp_algorithm/kmp.java>)
- [kmp.py](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/kmp_algorithm/kmp.py>)
- [kmp_algorithm.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/kmp_algorithm/kmp_algorithm.cpp>)

## Related topics

- [[Topics/Cosmos/String Algorithms]] · [[Topics/Cosmos/Algorithms & Data Structures]]
