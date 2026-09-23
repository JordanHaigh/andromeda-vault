# Finite Automata

## What it does

A finite-automaton matcher preprocesses a pattern into states representing how much of its prefix currently matches. Each text character makes one transition, so search is O(n) after preprocessing. Transition-table construction and memory depend on the alphabet size; sparse transitions reduce costs for large alphabets.

## Implementations

- [makefile — c/c](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/finite_automata/c/c/makefile>)
- [dfa.c — c](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/finite_automata/c/dfa.c>)
- [dfa.h — c](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/finite_automata/c/dfa.h>)
- [main.c — c](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/finite_automata/c/main.c>)
- [types.c — c](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/finite_automata/c/types.c>)
- [types.h — c](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/finite_automata/c/types.h>)
- [searchstringusingdfa.java](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/finite_automata/searchstringusingdfa.java>)
- [searchstringusingdfa.rs](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/finite_automata/searchstringusingdfa.rs>)

## Related topics

- [[Topics/Cosmos/String Algorithms]] · [[Topics/Cosmos/Algorithms & Data Structures]]
