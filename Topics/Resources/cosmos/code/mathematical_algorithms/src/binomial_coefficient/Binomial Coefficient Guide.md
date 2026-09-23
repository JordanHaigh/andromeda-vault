# Binomial Coefficient

## What it does

Computes C(n,k), the number of k-element subsets of an n-element set. Pascal’s recurrence C(n,k)=C(n−1,k−1)+C(n−1,k), with edge values 1, is stable and avoids factorial overflow in intermediate values. A one-dimensional DP computes it in O(nk) time and O(k) space.

## Implementations

- [binomial_coefficient.c](<https://github.com/OpenGenus/cosmos/blob/master/code/mathematical_algorithms/src/binomial_coefficient/binomial_coefficient.c>)
- [binomial_coefficient.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/mathematical_algorithms/src/binomial_coefficient/binomial_coefficient.cpp>)
- [binomial_coefficient.go](<https://github.com/OpenGenus/cosmos/blob/master/code/mathematical_algorithms/src/binomial_coefficient/binomial_coefficient.go>)
- [binomial_coefficient.java](<https://github.com/OpenGenus/cosmos/blob/master/code/mathematical_algorithms/src/binomial_coefficient/binomial_coefficient.java>)
- [binomial_coefficient.py](<https://github.com/OpenGenus/cosmos/blob/master/code/mathematical_algorithms/src/binomial_coefficient/binomial_coefficient.py>)

## Related topics

- [[Topics/Cosmos/Mathematics and Numerical Analysis]]
