# Insertion Sort: Complexity and Behavior

> Locally authored study notes based on the linked reference. They preserve the concepts needed for offline review; see the original page for the full article and its context.

**Reference:** [https://iq.opengenus.org/insertion-sort-analysis/](https://iq.opengenus.org/insertion-sort-analysis/)  
**Captured:** 2026-09-24

## Notes

Insertion sort maintains a sorted prefix and inserts each next value into its correct position by shifting larger values. It is in-place and stable when equal keys are not moved past each other. For already sorted input it runs in O(n); average and worst cases are O(n²), with O(1) auxiliary space. It works well for small or nearly sorted inputs and is often useful as a component inside hybrid sorting algorithms.

## Connected vault material

- [[Topics/Resources/cosmos/code/sorting/src/insertion_sort/Insertion Sort Guide.md]]
