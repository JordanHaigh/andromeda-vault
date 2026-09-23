# Largest Sum Contiguous Subarray

This problem asks for a contiguous range of an array whose elements have the maximum possible sum. It is commonly solved with Kadane’s algorithm, which tracks the best sum ending at the current position and the best sum seen so far.

## Core recurrence

For values `a[i]`, maintain `ending_here = max(a[i], ending_here + a[i])` and `best = max(best, ending_here)`. Starting both values from the first element handles arrays containing only negative numbers. Resetting the running sum to zero can instead return the wrong answer when the problem requires a non-empty subarray.

**Complexity:** O(n) time and O(1) extra space. The algorithm also needs a policy for empty input; this repository’s language-specific functions may differ in their return type and edge-case behavior, so inspect each implementation.

## Implementations

- [C implementation](https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/largest_sum_contiguous_subarray/largest_sum_contiguous_subarray.c)
- [CPP implementation](https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/largest_sum_contiguous_subarray/largest_sum_contiguous_subarray.cpp)
- [GO implementation](https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/largest_sum_contiguous_subarray/largest_sum_contiguous_subarray.go)
- [HS implementation](https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/largest_sum_contiguous_subarray/largest_sum_contiguous_subarray.hs)
- [JAVA implementation](https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/largest_sum_contiguous_subarray/largest_sum_contiguous_subarray.java)
- [PY implementation](https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/largest_sum_contiguous_subarray/largest_sum_contiguous_subarray.py)

The original archive included repeated generic placeholder pages across unrelated folders. Their boilerplate has been removed from this vault; the six language implementations above are distinct and should be compared as separate implementations.

## Related topics

- [[Topics/Cosmos/Dynamic Programming]]
- [[Topics/Cosmos/Algorithms & Data Structures]]
