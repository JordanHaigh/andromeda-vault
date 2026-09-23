# Horner Polynomial Evaluation

## What it does

Evaluates a polynomial using nested multiplication: a₀+a₁x+…+aₙxⁿ becomes (((aₙx+aₙ₋₁)x+aₙ₋₂)… )x+a₀. It reduces evaluation to O(n) multiplications and additions and avoids separately computing powers.

## Implementations

- [horner_polynomial_evaluation.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/mathematical_algorithms/src/horner_polynomial_evaluation/horner_polynomial_evaluation.cpp>)
- [horner_polynomial_evaluation.java](<https://github.com/OpenGenus/cosmos/blob/master/code/mathematical_algorithms/src/horner_polynomial_evaluation/horner_polynomial_evaluation.java>)

## Related topics

- [[Topics/Cosmos/Mathematics and Numerical Analysis]]
