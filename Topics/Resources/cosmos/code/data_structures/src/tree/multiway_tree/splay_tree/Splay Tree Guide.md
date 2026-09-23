# Splay Tree
A splay tree is a self-adjusting Binary Search Tree with the additional property that recently accessed elements are quick to access again ( O(1) time). It performs basic operations such as insertion, look-up and removal in O(log n) amortized time. For many sequences of non-random operations, splay trees perform better than other search trees, even when the specific pattern of the sequence is unknown.

All normal operations on a binary search tree are combined with one basic operation, called splaying. Splaying the tree for a certain element rearranges the tree so that the element is placed at the root of the tree.
---
