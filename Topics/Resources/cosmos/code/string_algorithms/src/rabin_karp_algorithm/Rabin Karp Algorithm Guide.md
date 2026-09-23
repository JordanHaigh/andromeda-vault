# Rabin Karp Algorithm

## What it does

Rabin–Karp compares rolling hashes of text windows with a pattern hash, verifying actual characters when hashes match. Rolling updates make each next window cheap. Expected search is O(n+m), while collisions can produce O(nm) worst-case work; use a wide hash and retain equality verification.

## Implementations

- [rabinKarp.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/rabin_karp_algorithm/rabinKarp.cpp>)
- [rabin_karp.c](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/rabin_karp_algorithm/rabin_karp.c>)
- [rabin_karp.java](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/rabin_karp_algorithm/rabin_karp.java>)
- [rabin_karp.py](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/rabin_karp_algorithm/rabin_karp.py>)

## Related topics

- [[Topics/Cosmos/String Algorithms]] · [[Topics/Cosmos/Algorithms & Data Structures]]
