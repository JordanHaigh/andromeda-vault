# Product strategy and commercial judgment

Senior engineers help an organization choose valuable problems, make explicit economic trade-offs, and learn whether software changed the outcome. This capability joins product strategy, business analysis, technical judgment, and financial reasoning. It is not a demand that engineers pretend to be accountants or product managers; it is the ability to make technology decisions legible in the language of users, operating models, risk, and value.

## Start with the business model and the user outcome

A product or service exists in a context: a person or organization has a job to do, a current process, constraints, alternatives, and an expected outcome. Before proposing software, establish:

- **Who receives value?** Distinguish customers, end users, operators, buyers, approvers, and people affected indirectly.
- **What changes for them?** Describe an observable outcome, not an output such as a screen, integration, model, or migration.
- **How is value captured or protected?** Revenue, retention, conversion, lower cost to serve, reduced risk, faster decisions, compliance, mission outcomes, or capacity released may matter in different settings.
- **What is the counterfactual?** What happens if the team does nothing, delays, buys an existing product, changes a process, or solves only part of the need?
- **What constraints shape the choice?** Strategy, time, people, regulation, data, contracts, architecture, operational capacity, accessibility, and risk tolerance.

A concise problem statement is: *For [population/context], [important situation] prevents or makes it costly to [desired task/outcome]. We believe [change] will improve [observable measure] while respecting [constraints]. We will test this by [evidence] by [decision date].*

Link this framing to [[Topics/Business Analysis/Business Analysis and Solution Discovery]] and [[Topics/Business Analysis/Benefits Realisation and Outcome Measurement]].

## Choose work as a portfolio of bets

Prioritization is a decision under uncertainty, not a universal scoring formula. Compare opportunities with explicit criteria such as strategic fit, user impact, urgency, confidence in the evidence, cost and opportunity cost, risk reduction, enabling value, reversibility, and the time until useful learning. Document who decides and what new evidence would change the order.

### Useful economic concepts

- **Cost of delay:** the economic or mission impact of waiting. Estimate the shape and timing of the impact, state assumptions, and avoid false precision. A time-sensitive compliance exposure differs from a speculative revenue gain.
- **Opportunity cost:** the best meaningful alternative displaced by this work, including maintenance, reliability, security, or the team's capacity to handle future demand.
- **Total cost of ownership (TCO):** include build or purchase, migration, integration, training, operations, support, security, licensing, cloud or AI consumption, vendor management, exit costs, and the cost of change over the chosen horizon.
- **Marginal economics:** compare the incremental cost and benefit of one more user, transaction, workflow, or capability. Fixed and variable costs behave differently as demand grows.
- **Time to value:** time from investment or commitment until a specified outcome is actually realized. Separate time to launch from time to adoption and time to measurable benefit.
- **Risk-adjusted value:** make uncertainty, downside exposure, mitigations, and option value explicit. A small reversible experiment can be valuable because it buys information.

No single framework (RICE, WSJF, NPV, ROI, payback period, or weighted scoring) makes a poor decision good. Use a framework to reveal assumptions, not to launder them into an objective-looking score. Check sensitivity: which assumption or weight flips the decision?

## Build-versus-buy and sourcing decisions

Compare feasible options against the user's outcome and operating context, not feature checklists alone. Consider: capability fit; differentiation; integration and data portability; security and privacy; accessibility; service levels; observability; total lifecycle cost; vendor viability and lock-in; customization; implementation time; skills; support model; contractual terms; exit and migration path; and opportunity cost.

A useful decision record captures options including “do nothing/change process,” evaluation criteria, evidence, assumptions, costs over a stated horizon, risks and owners, selected option, rejected alternatives, triggers for revisiting, and a date to review. Prototype the highest-risk assumptions before committing to a large irreversible build or purchase.

## Connect technical choices to unit economics

A unit metric relates costs or resources to a meaningful unit: cost per completed case, successful transaction, active customer, tenant, workflow, report, or AI-assisted task. A resource metric such as cost per request, GB, seat, or model token helps engineers control a driver; a business metric such as cost to serve or contribution margin helps product and finance understand outcomes. The relationship between the two must be evidenced rather than assumed.

Define numerator, denominator, inclusion rules, time window, population, data owner, and refresh cadence. Watch for cost allocation errors, shared infrastructure, seasonality, changing mix, retries, failed work, quality differences, and delayed benefits. A cheaper unit is not necessarily better if quality, reliability, safety, or customer outcomes fall. Compare trends within a coherent product or service scope before comparing unrelated teams. [[Topics/Cloud/Cloud Architecture]] and [[Topics/Deployment and Cloud]] provide implementation context.

The FinOps Foundation's current framework explicitly connects technology usage and costs to business value through KPIs, forecasting, benchmarking, and unit economics; it treats this as collaboration among engineering, product, finance, procurement, and leadership. See [FinOps Framework](https://www.finops.org/framework/), [FinOps Domains](https://www.finops.org/framework/domains/), and [Unit Economics](https://www.finops.org/framework/capabilities/unit-economics/).

## Benefits, disbenefits, and measurement

For each proposed benefit, state an owner, baseline, target or expected range, measurement source, time horizon, assumptions, and review point. Include disbenefits: maintenance burden, support demand, displaced work, user effort shifted elsewhere, accessibility regressions, security exposure, or cloud/AI cost. Measure outputs, adoption, outcomes, and impacts separately.

When possible, compare cohorts or use a before/after series with known caveats. Record confounders such as seasonality, policy changes, concurrent launches, population changes, and instrumentation changes. Use a counterfactual method proportionate to the decision: randomized experiment when ethical and feasible, matched comparison or interrupted time series when suitable, and qualitative triangulation when quantities cannot explain behavior. Do not attribute every change after release to the release.

A benefits map should connect:

1. investment and activities;
2. delivered capability and operational changes;
3. adoption and behavior change;
4. user, service, or business outcomes;
5. strategic or financial impact.

If the chain breaks, identify the missing assumption. A feature can ship without adoption; adoption can happen without improved outcomes; operational savings can be absorbed rather than realized as cash. This is why post-release ownership and benefits reviews matter.

## Governance, ethics, and decision quality

Make decision rights clear: who owns the problem, who controls budget, who accepts operational risk, who is accountable for user research and accessibility, who approves data use, and who can stop or roll back a release. Use [[Topics/Security]], [[Topics/Testing and Quality]], and [[Topics/Observability]] to make guardrails testable.

For automation and agentic systems, include the cost of human review, exceptions, failure recovery, model and tool use, data exposure, and reversibility. Compare quality-adjusted end-to-end effort with the current process; “minutes saved” can conceal verification and rework. Link to [[Topics/Business Analysis/Agentic Delivery Pilot and Evaluation]] and [[Topics/Business Analysis/Technical Leadership in Agentic Engineering]].

## Practical decision brief

- User and affected groups:
- Problem and current process:
- Desired outcome and why now:
- Baseline and evidence quality:
- Options, including no-build/process change/buy:
- TCO and opportunity cost over [horizon]:
- Main assumptions and sensitivity:
- Risks, accessibility, security, and operational obligations:
- Smallest useful test and stop/continue threshold:
- Decision owner and date:
- Outcome owner, measure, and post-release review:

## Related notes

- [[Topics/Business Analysis/Index]]
- [[Topics/Business Analysis/Business Analysis and Solution Discovery]]
- [[Topics/Business Analysis/Benefits Realisation and Outcome Measurement]]
- [[Topics/Business Analysis/Service Design, Accessibility, and Adoption]]
- [[Topics/Business Analysis/Agentic Delivery Pilot and Evaluation]]
- [[Topics/Cloud/Cloud Architecture]]
- [[Topics/Technical Leadership/Planning and Delivery]]

## Further reading

- [FinOps Foundation Framework](https://www.finops.org/framework/)
- [FinOps Foundation: Unit Economics](https://www.finops.org/framework/capabilities/unit-economics/)
- [FinOps Foundation: Quantify Business Value](https://www.finops.org/framework/domains/quantify-business-value/)
- [GOV.UK Service Manual: Measuring success](https://www.gov.uk/service-manual/measuring-success)
