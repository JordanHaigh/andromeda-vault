# Scheduling to Minimize Maximum Lateness

> Locally authored study notes based on the linked reference. They preserve the concepts needed for offline review; see the original page for the full article and its context.

**Reference:** [https://iq.opengenus.org/scheduling-to-minimize-lateness/](https://iq.opengenus.org/scheduling-to-minimize-lateness/)  
**Captured:** 2026-09-24

## Notes

For a single machine where all jobs are available at time zero, each job has a processing time and deadline, and the objective is to minimize maximum lateness, order jobs by earliest deadline first. The linked article contrasts this with shortest-processing-time and minimum-slack-time heuristics, which can fail. An exchange argument proves the earliest-deadline order is optimal by swapping inverted adjacent jobs without increasing maximum lateness. This objective is maximum lateness, not total completion time or weighted tardiness.

## Connected vault material

- [[Topics/Resources/cosmos/code/greedy_algorithms/src/min_lateness/Min Lateness Guide.md]]
