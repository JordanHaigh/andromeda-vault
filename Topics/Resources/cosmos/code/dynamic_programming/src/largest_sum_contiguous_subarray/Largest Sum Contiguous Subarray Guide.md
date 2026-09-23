# Largest Sum Contiguous Subarray

## What it does

Kadane’s algorithm tracks the maximum sum of a non-empty subarray ending at the current position: ending_here=max(x, ending_here+x), while best stores the largest value seen. This is O(n) time and O(1) space. Initialize from the first element so all-negative inputs work; define empty-input behavior explicitly.

## Implementations

- [largest_sum_contiguous_subarray.c](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/largest_sum_contiguous_subarray/largest_sum_contiguous_subarray.c>)
- [largest_sum_contiguous_subarray.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/largest_sum_contiguous_subarray/largest_sum_contiguous_subarray.cpp>)
- [largest_sum_contiguous_subarray.go](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/largest_sum_contiguous_subarray/largest_sum_contiguous_subarray.go>)
- [largest_sum_contiguous_subarray.hs](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/largest_sum_contiguous_subarray/largest_sum_contiguous_subarray.hs>)
- [largest_sum_contiguous_subarray.java](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/largest_sum_contiguous_subarray/largest_sum_contiguous_subarray.java>)
- [largest_sum_contiguous_subarray.py](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/largest_sum_contiguous_subarray/largest_sum_contiguous_subarray.py>)

## Related topics

- [[Topics/Cosmos/Dynamic Programming]] · [[Topics/Cosmos/Algorithms & Data Structures]]
