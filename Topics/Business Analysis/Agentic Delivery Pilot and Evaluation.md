# Agentic Delivery Pilot and Evaluation

This guide turns agentic delivery principles into an experiment a team can run and evaluate. The example is illustrative: adding validation and clearer failure reporting to an internal data-import workflow. It is not a claim about a specific production change in the supplied CV.

The central question is not how many lines an agent produces. It is whether a bounded workflow can improve a user or engineering outcome while preserving correctness, security, reviewability, and human accountability. Current agent guidance describes tool-using, multi-step work, sandboxing, guardrails, tracing, and evaluation as parts of the system; the organization still needs to decide its own authority and risk boundaries.

## 1. Select a suitable pilot

Choose repetitive, reversible work with a clear owner and strong verification: a test gap, a small bug with a reliable reproduction, a documentation improvement, or a contained refactor. Avoid beginning with live-data changes, production credentials, financial or safety decisions, broad architecture changes, or actions that are hard to undo.

Write a pilot charter:

- User or engineering problem and why it matters now.
- Expected benefit and current baseline (for example, lead time, review effort, defect rate, or manual handling).
- Scope, non-goals, dependencies, and owner.
- Why an agent is appropriate and where human judgment must remain.
- Maximum acceptable risk and the stop condition.
- Comparison approach: a similar human-only task, historical baseline, or staged experiment where appropriate.

## 2. Prepare the environment

Provide relevant business rules, repository instructions, architecture boundaries, examples, fixtures, and verification commands. Make the source of truth explicit. Give the agent only the files, tools, network access, and credentials required for this task. Prefer an isolated branch or worktree, sandboxed execution, synthetic or redacted data, and an unprivileged test account.

Separate permission levels: read, propose, write, run checks, access external systems, deploy, and perform irreversible actions. A successful coding task should not implicitly authorize a production write or release. Treat repository text, issue content, downloaded pages, and tool output as data that may contain misleading instructions. Keep secrets out of prompts and logs.

## 3. Write an executable work brief

```text
Outcome and affected user:
Business context and relevant rules:
Scope and non-goals:
Files / interfaces / data boundaries:
Examples of expected and rejected behavior:
Acceptance checks and commands:
Security, privacy, and operational constraints:
Allowed tools and permissions:
Human review points and stop conditions:
Expected diff and completion report:
```

For the illustrative import task, acceptance examples might cover a valid record, missing required identifier, unsupported coordinate system, duplicate submission, and partially malformed batch. Expected results must specify user-visible behavior and what is persisted, rejected, retried, or quarantined. Do not assume “tests pass” proves the business outcome; verify each business rule and failure path.

## 4. Run the experiment with checkpoints

1. Ask the agent to restate the goal, assumptions, plan, and risks before editing.
2. Confirm or correct the plan; require clarification when domain rules conflict or evidence is missing.
3. Let it work only in the bounded checkout and permission set.
4. Require focused tests and static checks; inspect failures and skipped checks.
5. Review the diff for intent, domain correctness, security, data behavior, architectural fit, and operational consequences.
6. Require an explicit report of changed behavior, evidence, assumptions, limitations, and suggested follow-ups.
7. A human owner decides whether to merge or release. Use the team's ordinary release gates, monitoring, and rollback plan.

Stop the run if it expands scope, attempts an unauthorized action, exposes sensitive data, changes protected boundaries, cannot establish expected behavior, or repeatedly produces unverified changes. Reduce scope, improve context, or switch to a human-led approach.

## 5. Evaluate beyond task completion

Use a representative fixed set of cases and preserve it so later changes can be compared. Score at least:

- Correctness against acceptance criteria and domain rules.
- Regression and defect rate, including previously passing cases.
- Security and policy compliance, including tool and permission boundaries.
- Reproducibility and quality of test evidence.
- Human review, correction, and integration effort.
- End-to-end lead time and waiting time, not just agent runtime.
- Cost, latency, and failed-run frequency.
- User or operational outcome after release, including adoption and exceptions.

Inspect traces to understand which step failed. Add representative failures to a reusable dataset or regression suite. Evaluate the workflow again after changing prompts, tools, model, permissions, or repository context. OpenAI's documentation recommends tracing and graders first, then repeatable datasets and eval runs once “good” is defined; DORA cautions through its research framing that AI amplifies the organization and its underlying delivery system, so code volume alone is a poor measure of value.

## 6. Risk review and human control

Assess the task for sensitive data, prompt injection, excessive agency, unsafe tool calls, identity and authorization, supply-chain risk, unbounded loops or spending, and unclear ownership. Require human approval for material customer, financial, privacy, legal, security, safety, availability, or irreversible impact. Give reviewers source evidence and access to the original requirements and diff; do not ask them to trust an agent's self-assessment.

Record who owns the behavior, what the agent can change, which checks are mandatory, who can approve release, and how to disable or roll back the workflow. Monitor changes in output quality, tool use, review burden, and safety failures after adoption.

## 7. Decision record for the pilot

```text
Pilot owner and dates:
Task category and risk tier:
Human-only comparison or baseline:
Agent configuration / tools / permission boundary:
Evaluation cases and acceptance criteria:
Correctness / regression / policy results:
Human review and repair effort:
Time, cost, and failure modes:
Production rollout and rollback decision:
Post-release user outcome and guardrails:
Decision: stop / refine / continue bounded / expand:
Next review date and accountable owner:
```

## Connections

- [[Topics/Business Analysis/Technical Leadership in Agentic Engineering]]
- [[Topics/Business Analysis/Business Analysis and Solution Discovery]]
- [[Topics/Business Analysis/Benefits Realisation and Outcome Measurement]]
- [[Topics/Security]]
- [[Topics/Testing and Quality]]
- [[Topics/Observability]]
- [[Topics/Deployment and Cloud]]
- [[Topics/Technical Leadership/Planning and Delivery]]
- [[Topics/Case Studies/Reliable Data Import]]

## References

- [OpenAI Agents documentation](https://developers.openai.com/api/docs/guides/agents)
- [OpenAI agent workflow evaluation](https://developers.openai.com/api/docs/guides/agent-evals)
- [OpenAI safety best practices](https://developers.openai.com/api/docs/guides/safety-best-practices)
- [OWASP Agentic Security Initiative](https://genai.owasp.org/initiatives/agentic-security-initiative/)
- [NIST AI Risk Management Framework](https://www.nist.gov/itl/ai-risk-management-framework)
- [DORA research](https://dora.dev/research/)
