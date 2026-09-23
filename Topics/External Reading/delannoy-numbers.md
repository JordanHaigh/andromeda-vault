# Delannoy Numbers

> Locally authored study notes based on the linked reference. They preserve the concepts needed for offline review; see the original page for the full article and its context.

**Reference:** [https://en.wikipedia.org/wiki/Delannoy_number](https://en.wikipedia.org/wiki/Delannoy_number)  
**Captured:** 2026-09-24

## Notes

The Delannoy number D(m,n) counts lattice paths from (0,0) to (m,n) using steps right, up, and diagonally up-right. It obeys D(m,n)=D(m−1,n)+D(m,n−1)+D(m−1,n−1), with boundary values D(0,n)=D(m,0)=1. The central Delannoy numbers are D(n,n). Dynamic programming computes a table in O(mn) time and can use O(min(m,n)) space.

## Connected vault material

- [[Topics/Resources/cosmos/code/mathematical_algorithms/src/delannoy_number/Delannoy Number Guide.md]]
