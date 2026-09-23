# Finding the Largest Element in an Array

> Locally authored study notes based on the linked reference. They preserve the concepts needed for offline review; see the original page for the full article and its context.

**Reference:** [https://iq.opengenus.org/largest-element-in-an-array/](https://iq.opengenus.org/largest-element-in-an-array/)  
**Captured:** 2026-09-24

## Notes

A single pass is sufficient: initialize the candidate from the first element, compare each remaining value, and replace the candidate when a larger value appears. This takes O(n) time and O(1) extra space. The non-empty-array assumption matters; empty input needs an explicit policy, and initializing to zero would be incorrect when all values are negative.

## Connected vault material

- [[Topics/Resources/cosmos/code/languages/cpp/largest-element-in-an-array/Largest Element In An Array Guide.md]]
