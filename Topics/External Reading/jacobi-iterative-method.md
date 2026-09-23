# Jacobi Iterative Method

> Locally authored study notes based on the linked reference. They preserve the concepts needed for offline review; see the original page for the full article and its context.

**Reference:** [https://en.wikipedia.org/wiki/Jacobi_method](https://en.wikipedia.org/wiki/Jacobi_method)  
**Captured:** 2026-09-24

## Notes

For a linear system Ax=b, Jacobi isolates each diagonal term and computes each new component from the previous iteration’s vector: xᵢ⁽ᵏ⁺¹⁾=(bᵢ−Σⱼ≠ᵢ aᵢⱼxⱼ⁽ᵏ⁾)/aᵢᵢ. All components in an iteration use the old vector, so updates can run in parallel. Convergence is not guaranteed for every matrix; strict diagonal dominance is a common sufficient condition, and more generally convergence depends on the iteration matrix’s spectral radius being below 1.

## Connected vault material

- [[Topics/Resources/cosmos/code/mathematical_algorithms/src/jacobi_method/Jacobi Method Guide.md]]
