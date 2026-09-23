# Binomial Coefficient

## What it does

Computes C(n,k), the number of k-element subsets of an n-element set. Pascal’s recurrence C(n,k)=C(n−1,k−1)+C(n−1,k), with edge values 1, is stable and avoids factorial overflow in intermediate values. A one-dimensional DP computes it in O(nk) time and O(k) space.

## Implementations

- [binomial_coefficient.c](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/binomial_coefficient/binomial_coefficient.c>)
- [binomial_coefficient.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/binomial_coefficient/binomial_coefficient.cpp>)
- [binomial_coefficient.ex](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/binomial_coefficient/binomial_coefficient.ex>)
- [binomial_coefficient.java](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/binomial_coefficient/binomial_coefficient.java>)
- [binomial_coefficient.js](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/binomial_coefficient/binomial_coefficient.js>)
- [binomial_coefficient.py](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/binomial_coefficient/binomial_coefficient.py>)

## Related topics

- [[Topics/Cosmos/Dynamic Programming]] · [[Topics/Cosmos/Algorithms & Data Structures]]
