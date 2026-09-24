# Benefits Realisation and Outcome Measurement

A software change is an intervention in a business process. Shipping code proves that an output was delivered; it does not prove that users adopted it, the process improved, or the expected benefit occurred. Benefits management tracks intended changes during delivery. Evaluation asks what changed, for whom, how, why, and what contribution the intervention made. These activities should be planned together, with measures and evidence designed before implementation.

## Build an outcome chain

Write the logic from investment to impact so that every link can be challenged:

1. **Inputs:** people, time, money, data, platforms, and change capacity.
2. **Activities:** what the team will build, configure, automate, or change in the process.
3. **Outputs:** directly delivered things, such as a validated import service, a new workflow, or support guidance.
4. **Adoption and behavior:** whether intended people use the capability and whether their work changes.
5. **Outcomes:** measurable improvements for a user, operator, customer, or business process.
6. **Longer-term impact:** wider effects such as lower operating cost, reduced risk, improved service, or increased capacity.

For each transition, state the assumption and the evidence that would support it. Add negative pathways too: data quality may remain poor, operators may create workarounds, a new queue may shift rather than remove delay, or automation may increase the cost of exception handling. Theory of change and logic models are useful tools for making those causal assumptions inspectable. The [Magenta Book](https://www.gov.uk/government/publications/the-magenta-book/magenta-book-central-government-guidance-on-evaluation-html) treats benefits management and evaluation as complementary and distinguishes monitoring intended benefits from assessing wider, unintended, and causal effects.

## Define measures people can act on

For each outcome, record:

- **Question:** what decision will this measure inform?
- **Population and unit:** which users, records, transactions, sites, or time periods are counted?
- **Metric definition:** numerator, denominator, exclusions, time window, and data source.
- **Baseline:** current value, distribution, measurement period, and known limitations.
- **Target or acceptable range:** what result is valuable enough to justify cost and risk?
- **Owner:** who can interpret the measure and act when it moves?
- **Review point:** when the team will evaluate adoption and impact, not only release health.
- **Guardrail:** what must not worsen, such as error rates, support burden, access equity, privacy, or safety.

Prefer a small balanced set of outcome and guardrail measures. A single speed metric can reward moving work downstream. Pair time saved with rework and quality; pair automation coverage with exception volume and human correction; pair availability with successful completion of the user journey. Define terms before a target becomes an incentive.

Separate **leading indicators** (training completion, workflow adoption, queue age) from **lagging outcomes** (reduced processing effort, fewer missed service commitments, lower incident cost). Leading indicators help manage implementation but are not substitutes for the result the business cares about.

## Compare forecast and observed value

At discovery, write a value hypothesis: “For [user or process], changing [behavior] should improve [outcome], because [mechanism]. We expect [measure] to move from [baseline] toward [target] by [date], without breaching [guardrails].” Record confidence and supporting evidence. Do not turn a forecast into a realized benefit in a status report or CV.

After release, inspect implementation, impact, and cost:

- Was the change delivered and used as intended?
- Did the measure change, and did the change differ across user groups or conditions?
- What else changed during the same period?
- What evidence supports a causal contribution, and what cannot be attributed confidently?
- What were the one-time build and transition costs, ongoing run and support costs, and unintended costs?
- Should the team continue, adapt, scale, or stop?

A before/after comparison can show change over time but cannot, by itself, show that the software caused it. Where the decision warrants it, use comparison groups, staged rollout, interrupted time series, or a theory-based contribution analysis. Choose the method in proportion to the value, risk, available data, and decision being made. Report uncertainty and missing data plainly. For value-for-money decisions, consider non-monetary outcomes and disbenefits as well as financial value.

## CV-grounded evidence example

The supplied CV describes production data-processing and geospatial tools for rail customers, including investigation of processing failures and data-quality edge cases, improvements to logging and automated testing, and reduction of manual intervention. That is evidence of production engineering and operational improvement. The CV does not provide a quantified baseline, a measured before/after value, or the attribution method for the change.

A truthful case record can therefore state the work and the kinds of evidence that would strengthen it, without inventing values:

| Field | Current evidence or next detail to collect |
| --- | --- |
| User/process | Rail customers and the data-processing workflows they depend on; identify the specific user roles and workflow from project records. |
| Intervention | Changes to production data-processing/geospatial tools, logging, automated testing, and handling of edge cases; confirm the exact change boundary for each example. |
| Observed problem | Processing failures, data-quality edge cases, and manual intervention are described in the CV; locate incident records or user examples that show frequency and severity. |
| Baseline and result | Not stated in the CV. Search operational dashboards, release notes, support records, or stakeholder feedback before claiming a reduction. |
| Guardrails | Data correctness, customer impact, processing completion, privacy, and support effort; recover actual measures or describe them as design concerns only. |
| Attribution | Not stated. Separate the work personally led from changes delivered by the wider team and note other changes occurring at the same time. |

This example is intentionally incomplete where the source evidence is incomplete. For the cloud transformation program, the CV says work ended before production release; describe architecture and delivery leadership, not realized production benefits. Link the resulting evidence to [[Topics/Business Analysis/Leadership Evidence and Development]].

## Working template

```text
Outcome and affected users:
Current workflow and evidence:
Change hypothesis and causal assumptions:
Options considered (including process change / do less):
Baseline measure, source, period, and limitations:
Target / decision threshold / review date:
Guardrail measures:
Build, transition, adoption, run, and support costs:
Delivery and adoption evidence:
Observed outcome and unintended effects:
Attribution limits and confidence:
Decision: continue / adapt / scale / stop:
Owner and next review:
```

## Connected topics

- [[Topics/Business Analysis/Business Analysis and Solution Discovery]]
- [[Topics/Business Analysis/Leadership Evidence and Development]]
- [[Topics/Observability]]
- [[Topics/Deployment and Cloud]]
- [[Topics/Technical Leadership/Planning and Delivery]]
- [[Topics/Case Studies/Reliable Data Import]]

## References

- [HM Treasury: The Magenta Book (updated May 2026)](https://www.gov.uk/government/publications/the-magenta-book/magenta-book-central-government-guidance-on-evaluation-html)
- [DORA Core Model and research](https://dora.dev/research/)
