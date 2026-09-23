# Lagrange Polynomial Interpolation

> Locally authored study notes based on the linked reference. They preserve the concepts needed for offline review; see the original page for the full article and its context.

**Reference:** [https://en.wikipedia.org/wiki/Lagrange_polynomial](https://en.wikipedia.org/wiki/Lagrange_polynomial)  
**Captured:** 2026-09-24

## Notes

Given n+1 data points with distinct x-coordinates, the Lagrange form constructs the unique polynomial of degree at most n passing through them: P(x)=Σᵢ yᵢ Lᵢ(x), where Lᵢ(x)=Πⱼ≠ᵢ (x−xⱼ)/(xᵢ−xⱼ). Each basis polynomial is 1 at its own node and 0 at the other nodes, so the weighted sum reproduces every given y-value. Direct evaluation costs O(n²) in the straightforward form; barycentric forms improve repeated evaluation.

## Connected vault material

- [[Topics/Resources/cosmos/code/mathematical_algorithms/src/largrange_polynomial/Largrange Polynomial Guide.md]]
