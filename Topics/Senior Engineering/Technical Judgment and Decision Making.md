# Technical Judgment and Decision Making

Senior technical judgment means choosing a solution that fits the problem, constraints, and expected lifetime of the system. It combines implementation knowledge with uncertainty management: not every decision deserves the same analysis, and not every unknown can be removed before work begins.

## Frame the decision

Before comparing technologies or designs, write down:

- The outcome and the people or workflow it should improve.
- Current behavior, evidence, and the pain or risk that motivates change.
- Functional needs, quality attributes, constraints, and explicit non-goals.
- Data ownership, trust boundaries, external dependencies, and operational responsibilities.
- What is known, assumed, uncertain, and still needs discovery.

If the problem statement is unclear, return to [[Topics/Business Analysis/Business Analysis and Solution Discovery]] rather than treating an implementation request as proof of need.

## Compare options proportionately

Generate at least one credible alternative, including doing less or changing the process. Compare options against a small set of criteria that matter to the outcome: user impact, security, reliability, reversibility, delivery time, operating cost, team familiarity, and long-term change cost. Record weights only when they clarify a decision; numeric scores do not make weak evidence objective.

Prefer the simplest option that meets the real requirements and leaves a safe path to change. Challenge complexity that has no named need, but do not optimize for local simplicity when it moves hidden cost or risk to users, operators, or another team.

## Make reversibility explicit

For consequential choices, identify whether the choice is easy to reverse, costly to reverse, or effectively irreversible. For reversible choices, make a timely decision and learn through implementation. For hard-to-reverse choices, gather stronger evidence, review failure modes, and define a migration or exit path. Record the signals that would trigger reconsideration.

Use an architecture decision record for durable tradeoffs: context, decision, alternatives considered, consequences, owners, and revisit conditions. Keep it short enough to stay current. Link it to requirements, implementation work, and operational guidance where useful.

## Review beyond the happy path

Ask how the design handles:

- Partial failure, duplicate delivery, timeouts, retries, ordering, concurrency, and recovery.
- Authorization, sensitive data, secrets, auditability, abuse, and dependency compromise.
- Backward compatibility, migration, rollback, and clients that upgrade at different times.
- Deployment, alerting, support ownership, accessibility, cost, and expected scale.
- Testability and observability: how will the team know it is correct and healthy?

Connect these questions to [[Topics/Concurrency and Distributed Systems]], [[Topics/Security]], [[Topics/Database Design]], [[Topics/Testing and Quality]], and [[Topics/Observability]].

## Learn from production

After release, compare observed behavior with the expected outcome. Review incidents and near misses for system and process causes, then track corrective work to completion. Revisit decisions when assumptions change, support cost rises, new evidence appears, or the design prevents valuable work. This closes the loop between architecture and the people who rely on it.

## Related guides

- [[Topics/Senior Engineering/Index]]
- [[Topics/Technical Leadership/Technical Strategy and Architecture Stewardship]]
- [[Topics/Technical Leadership/Planning and Delivery]]
- [[Topics/Architecture and Senior Engineering]]
- [[Topics/Business Analysis/Leadership Evidence and Development]]
