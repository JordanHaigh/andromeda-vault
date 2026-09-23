# Tridiagonal Matrices

> Locally authored study notes based on the linked reference. They preserve the concepts needed for offline review; see the original page for the full article and its context.

**Reference:** [https://en.wikipedia.org/wiki/Tridiagonal_matrix](https://en.wikipedia.org/wiki/Tridiagonal_matrix)  
**Captured:** 2026-09-24

## Notes

A tridiagonal matrix can have nonzero entries only on the main diagonal and the diagonals immediately above and below it. Such matrices use O(n) storage rather than O(n²). Many linear systems with this structure can be solved in O(n) time using the Thomas algorithm, a specialized elimination method. Numerical stability and zero or tiny pivots still need attention; structure improves efficiency but does not remove conditioning concerns.

## Connected vault material

- [[Topics/Resources/cosmos/code/mathematical_algorithms/src/tridiagonal_matrix/Tridiagonal Matrix Guide.md]]
