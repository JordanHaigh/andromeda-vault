# Newton Polynomial Interpolation

> Locally authored study notes based on the linked reference. They preserve the concepts needed for offline review; see the original page for the full article and its context.

**Reference:** [https://en.wikipedia.org/wiki/Newton_polynomial](https://en.wikipedia.org/wiki/Newton_polynomial)  
**Captured:** 2026-09-24

## Notes

Newton interpolation expresses the interpolating polynomial in nested form using divided differences: P(x)=f[x₀]+f[x₀,x₁](x−x₀)+f[x₀,x₁,x₂](x−x₀)(x−x₁)+…. The coefficients are computed from a divided-difference table. Adding a new data point can extend the polynomial with one additional term, which is a practical advantage over recomputing a full Lagrange form. Distinct interpolation nodes are required.

## Connected vault material

- [[Topics/Resources/cosmos/code/mathematical_algorithms/src/newton_polynomial/Newton Polynomial Guide.md]]
