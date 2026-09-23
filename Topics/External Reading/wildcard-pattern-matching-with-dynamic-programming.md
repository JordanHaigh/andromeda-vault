# Wildcard Pattern Matching with Dynamic Programming

> Locally authored study notes based on the linked reference. They preserve the concepts needed for offline review; see the original page for the full article and its context.

**Reference:** [https://iq.opengenus.org/wildcard-pattern-matching-dp/](https://iq.opengenus.org/wildcard-pattern-matching-dp/)  
**Captured:** 2026-09-24

## Notes

The pattern uses `?` for exactly one character and `*` for any sequence, including the empty sequence. Let `dp[i][j]` mean the first i text characters match the first j pattern characters. A literal or `?` uses the diagonal state when characters match. For `*`, combine the state that lets it match zero characters (`dp[i][j-1]`) with the state that lets it consume one more text character (`dp[i-1][j]`). Initialize empty text against prefixes consisting only of `*`. The standard table uses O(mn) time and O(mn) space; row compression can reduce space to O(n).

## Connected vault material

- [[Topics/Resources/cosmos/code/dynamic_programming/src/wildcard_matching/wildcard.md]]
