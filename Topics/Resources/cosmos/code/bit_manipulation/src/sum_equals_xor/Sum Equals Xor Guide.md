# Sum Equals Xor

## What it does

Studies values x for which a+x equals a XOR x. Since addition differs from XOR only where carries occur, the condition holds exactly when a and x share no set bit: (a & x)==0. For bounded nonnegative x, count choices by considering zero bits in the bound’s binary representation.

## Implementations

- [sum_equals_xor.c](<https://github.com/OpenGenus/cosmos/blob/master/code/bit_manipulation/src/sum_equals_xor/sum_equals_xor.c>)
- [sum_equals_xor.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/bit_manipulation/src/sum_equals_xor/sum_equals_xor.cpp>)
- [sum_equals_xor.py](<https://github.com/OpenGenus/cosmos/blob/master/code/bit_manipulation/src/sum_equals_xor/sum_equals_xor.py>)

## Related topics

- [[Topics/Cosmos/Algorithms & Data Structures]]
