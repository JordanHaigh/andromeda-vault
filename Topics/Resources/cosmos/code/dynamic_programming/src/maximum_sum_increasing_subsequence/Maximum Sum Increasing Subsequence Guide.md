# Maximum Sum Increasing Subsequence

## What it does

Finds the maximum sum among strictly increasing subsequences. A quadratic dynamic program sets each position’s best sum to its value plus the best sum ending at an earlier smaller value. The basic method is O(n²); coordinate compression and a Fenwick or segment tree can reduce it to O(n log n).

## Implementations

- [maximum_sum_increasing_subsequence.c](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/maximum_sum_increasing_subsequence/maximum_sum_increasing_subsequence.c>)
- [maximum_sum_increasing_subsequence.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/maximum_sum_increasing_subsequence/maximum_sum_increasing_subsequence.cpp>)

## Related topics

- [[Topics/Cosmos/Dynamic Programming]] · [[Topics/Cosmos/Algorithms & Data Structures]]
