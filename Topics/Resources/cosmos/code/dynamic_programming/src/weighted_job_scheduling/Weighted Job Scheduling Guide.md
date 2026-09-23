# Weighted Job Scheduling

## What it does

Selects non-overlapping jobs to maximize total profit when each job has a start, finish, and weight. Sort by finish time, find the latest compatible earlier job by binary search, then compare taking the job plus its predecessor optimum with skipping it. Complexity is O(n log n) after sorting.

## Implementations

- [weighted_job_scheduling.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/weighted_job_scheduling/weighted_job_scheduling.cpp>)
- [weighted_job_scheduling.py](<https://github.com/OpenGenus/cosmos/blob/master/code/dynamic_programming/src/weighted_job_scheduling/weighted_job_scheduling.py>)

## Related topics

- [[Topics/Cosmos/Dynamic Programming]] · [[Topics/Cosmos/Algorithms & Data Structures]]
