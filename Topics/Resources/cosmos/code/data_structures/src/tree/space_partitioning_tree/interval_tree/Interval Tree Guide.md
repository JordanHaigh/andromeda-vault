# Interval Tree

## What it does

Stores intervals in a balanced search tree ordered by one endpoint, augmenting each node with the maximum high endpoint in its subtree. This lets overlap queries prune subtrees whose maximum endpoint is below the query. Query complexity depends on tree balance and result count.

## Implementations

- [interval_tree.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/data_structures/src/tree/space_partitioning_tree/interval_tree/interval_tree.cpp>)
- [interval_tree.java](<https://github.com/OpenGenus/cosmos/blob/master/code/data_structures/src/tree/space_partitioning_tree/interval_tree/interval_tree.java>)

## Related topics

- [[Topics/Cosmos/Algorithms & Data Structures]]
