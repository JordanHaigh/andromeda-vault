# Counting Substrings Divisible by 8 but Not 3

> Locally authored study notes based on the linked reference. They preserve the concepts needed for offline review; see the original page for the full article and its context.

**Reference:** [https://iq.opengenus.org/number-of-substrings-divisible-by-8-but-not-3/](https://iq.opengenus.org/number-of-substrings-divisible-by-8-but-not-3/)  
**Captured:** 2026-09-24

## Notes

The target is the number of substrings whose represented integer is divisible by 8 and not by 3. A direct method checks each substring and costs O(n²); the linked article presents a linear-time dynamic-programming approach. Useful divisibility facts are that 8 depends on the last three decimal digits, while 3 depends on the digit sum. The page’s example input `1892456` has six qualifying substrings. Beware integer overflow when building long substring values in a brute-force implementation.

## Connected vault material

- [[Topics/Resources/cosmos/code/dynamic_programming/src/number_of_substring_divisible_by_8_but_not_3/Number Of Substring Divisible By 8 But Not 3 Guide.md]]
