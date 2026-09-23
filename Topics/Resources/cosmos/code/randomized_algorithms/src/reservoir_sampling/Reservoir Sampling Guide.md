# Reservoir Sampling

## What it does

Selects a uniform sample of k items from a stream of unknown length using O(k) memory. Keep the first k items; for item i>k, choose a random index in [1,i] and replace a sample entry only when the index is at most k. Each stream item has equal inclusion probability.

## Implementations

- [reservoir_sampling.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/randomized_algorithms/src/reservoir_sampling/reservoir_sampling.cpp>)
- [reservoir_sampling.rs](<https://github.com/OpenGenus/cosmos/blob/master/code/randomized_algorithms/src/reservoir_sampling/reservoir_sampling.rs>)

## Related topics

- [[Topics/Cosmos/Algorithms & Data Structures]]
