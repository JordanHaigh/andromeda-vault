# Boolean Parenthesization

## What it does

Counts ways to parenthesize a boolean expression so it evaluates to a requested value. Interval DP stores true and false counts for every operand range, combines all split points using the operator’s truth table, and applies a modulus if required. Complexity is O(n³) for n operands.

## Implementations

- [boolean_parenthesization.c](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/boolean_parenthesization/boolean_parenthesization.c>)
- [boolean_parenthesization.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/boolean_parenthesization/boolean_parenthesization.cpp>)
- [boolean_parenthesization.java](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/boolean_parenthesization/boolean_parenthesization.java>)
- [boolean_parenthesization.py](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/boolean_parenthesization/boolean_parenthesization.py>)
- [boolean_parenthesization.swift](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/boolean_parenthesization/boolean_parenthesization.swift>)

## Related topics

- [[Topics/Cosmos/Dynamic Programming]] · [[Topics/Cosmos/Algorithms & Data Structures]]
