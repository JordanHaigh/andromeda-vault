# Business Analysis and Solution Discovery

Business analysis connects an organizational need to a change that creates value for stakeholders. It is not limited to gathering feature requests or writing tickets. It means understanding the situation, the people affected, the constraints, the desired future state, and whether a proposed solution actually improves things.

## Start with the need

A stakeholder may ask for a feature, report, integration, chatbot, or automation. Treat that request as evidence about a possible need, not yet as the solution. Clarify:

- What outcome is blocked or what opportunity is being missed?
- Who experiences the problem, in which workflow, and how often?
- What happens today, including workarounds, handoffs, exceptions, and controls?
- What evidence shows that the problem matters: delay, rework, errors, cost, risk, poor service, or lost opportunity?
- What would be observably better, and for whom?
- What constraints apply: policy, regulation, data sensitivity, accessibility, service availability, budget, time, or existing systems?

Separate stated positions from underlying interests. Ask for a recent concrete example and walk through it end to end. Compare how the process works for different roles and edge cases. Keep assumptions and unknowns visible rather than silently turning them into requirements.

## Model the current and future state

Use a lightweight model suited to the question: stakeholder map, user journey, process flow, context diagram, data-flow sketch, decision table, domain model, or service blueprint. Map inputs, decisions, outputs, system boundaries, responsible roles, queues, failure paths, and data ownership. Validate the model with people who do the work, including support and operations where appropriate.

Describe the future state in outcome terms before discussing implementation. Identify which steps disappear, change, become automated, or still require human judgment. Include exception handling, recovery, audit, accessibility, and adoption. A process that is unclear or inconsistent may need simplification before automation.

## Compare solution options

Compare more than one credible option:

1. Change a policy, workflow, role, or communication practice.
2. Configure or extend an existing product or platform.
3. Build a focused software capability or integration.
4. Automate a bounded decision or repetitive task with rules or conventional software.
5. Use an AI or software agent where context interpretation and tool use add value and the risks can be contained.
6. Combine these options, or decide not to proceed yet.

Estimate value, delivery effort, operating cost, risk, reversibility, dependencies, time to learn, and the change burden on users. State who receives the value and who carries the cost. Include build, run, training, support, data, model, and governance costs in an agentic solution. Prefer a small test that can disprove an assumption over a large implementation built on hope.

## Define evidence of success

Choose a baseline and a small set of measures before delivery. Depending on the problem, track cycle time, completion rate, rework, error rate, cost per case, queue age, user effort, adoption, customer outcome, or risk exposure. Pair speed or cost measures with quality and safety measures so optimization does not move harm elsewhere.

Define:

- The target population and workflow.
- The baseline and measurement period.
- A target or threshold and the date to assess it.
- Data source and measurement owner.
- Guardrail metrics and unacceptable outcomes.
- A rollout and rollback decision rule.

A delivered feature is an output. Adoption, task success, reduced rework, improved service, and reduced risk are outcomes. Do not claim benefits that have not been measured.

## Turn understanding into delivery

Use a concise opportunity brief containing:

- **Need:** the problem or opportunity, with evidence.
- **People and context:** affected stakeholders, workflow, and constraints.
- **Current state:** process and systems today, including exceptions.
- **Desired outcome:** who should be better off and how it will be measured.
- **Options:** viable alternatives and key tradeoffs.
- **Risks and unknowns:** assumptions to test, including data and operational risks.
- **First increment:** smallest valuable test or release, its acceptance criteria, and review point.

Break work into outcome-aligned increments. Use examples and acceptance criteria to resolve ambiguity. Keep business rules explicit and owned; do not bury them in prompts or generated code. Link each important requirement to its rationale, affected stakeholders, validation method, and operational owner.

## Related topics

- [[Topics/Business Analysis/Technical Leadership in Agentic Engineering]]
- [[Topics/Business Analysis/Leadership Evidence and Development]]
- [[Topics/Architecture and Senior Engineering]]
- [[Topics/Testing and Quality]]
- [[Topics/Observability]]
- [[Topics/Security]]
- [[Topics/Web/RESTful APIs]]

## References

- [IIBA: Defining Business Analysis](https://www.iiba.org/knowledgehub/the-business-analysis-standard/2-understanding-business-analysis/2-1-defining-business-analysis/)
- [IIBA: What is Business Analysis?](https://www.iiba.org/professional-development/career-centre/what-is-business-analysis/)
