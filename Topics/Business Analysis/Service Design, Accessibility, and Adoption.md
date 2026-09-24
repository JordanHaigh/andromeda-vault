# Service design, accessibility, and adoption

Software is one part of a service. People experience the whole journey: finding information, deciding what to do, providing evidence, waiting, receiving an outcome, correcting errors, getting support, and sometimes completing offline steps. Senior practitioners design and improve that end-to-end service with users, operations, policy, content, data, and technology together.

This guide links business analysis to service design, inclusive research, accessibility engineering, change adoption, and continuous improvement. It complements [[Topics/Business Analysis/Business Analysis and Solution Discovery]], [[Topics/Web/Content Management Systems]], [[Topics/Testing and Quality]], and [[Topics/Observability]].

## Frame the service around a real need

A user need describes what someone is trying to accomplish and the context or barrier, without prescribing a feature. Distinguish user needs from stakeholder requests, legal requirements, internal policy, and technical constraints; all may matter, but they are different types of evidence.

Map the ecosystem: user groups, frontline staff, support, suppliers, partner services, data sources, policies, handoffs, channels, and exceptions. Identify the service boundary, but trace dependencies outside it where the user journey depends on them. Include people who do not qualify, abandon, need help, or encounter an error; their outcomes are part of service quality.

Use qualitative research to learn why and how work happens, and quantitative evidence to understand frequency, scale, and variation. Review support contacts, complaints, analytics, operational queues, policy, and existing research. Recruit a range of users, including disabled people and people with low digital confidence where relevant. Protect privacy, obtain appropriate consent, minimize collected data, and avoid treating a small convenience sample as representative.

Useful artifacts include a research plan, participant profile and recruitment rationale, interview or observation notes, evidence-backed journey map, service blueprint, assumptions log, opportunity map, and explicit unanswered questions. A journey map captures user actions, channels, questions, pain points, emotions, delays, and backstage dependencies. A service blueprint adds the visible service interactions, staff actions, systems, policies, and operational processes behind them.

The [GOV.UK Service Manual](https://www.gov.uk/service-manual) provides practical guidance across user research, design, accessibility, measurement, technology, and team operation. Its [discovery research guidance](https://www.gov.uk/service-manual/user-research/user-research-in-discovery) recommends understanding who uses a service, what they need, current channels, and their problems before planning or building.

## Design for completion across channels

Define the user's successful end state and the service's start and end points. Reduce unnecessary steps, repeated data entry, waiting, unclear language, and handoffs. Support realistic interruption, recovery, correction, and appeal. Ensure online and offline actions fit together: a service can fail even if its web form works when letters, phone support, staff tools, or partner processes contradict it.

Prototype uncertain parts early. Test task completion with representative participants using realistic content and data. Observe rather than teach users how to pass. Record completion, error, time, confidence, assistance needed, and the point where people abandon. A successful prototype validates only the assumptions it tested; it does not establish production reliability, security, accessibility, or adoption.

For content, use users' language, clear labels, meaningful error messages, explicit next steps, and content that remains understandable when encountered out of sequence. Structure information so navigation, search, assistive technology, and machine processing can interpret it. See [[Topics/Web/Content Management Systems]] and [[Topics/Web/RESTful APIs]] where content is delivered through systems and APIs.

## Accessibility is a service quality and delivery requirement

Accessibility concerns whether people with different sensory, motor, cognitive, speech, and other access needs can perceive, understand, navigate, and operate the service. It also benefits users facing temporary, situational, device, language, bandwidth, or environmental constraints. Treat accessibility as a product quality attribute and a continuous responsibility, not as a one-time audit at the end.

Use [WCAG 2.2](https://www.w3.org/TR/wcag/) as the normative web content standard where applicable. Its testable success criteria are organized under perceivable, operable, understandable, and robust principles at A, AA, and AAA conformance levels. Agree the required conformance target for the product and jurisdiction; do not assume one level or legal rule applies everywhere. WCAG conformance is not proof that every disabled user can complete every task.

Build accessibility into the delivery lifecycle:

- Discovery: include disabled people in research; identify assistive technology, language, device, support, and cognitive needs.
- Design: use semantic structure, keyboard flows, visible focus, clear information hierarchy, contrast, zoom/reflow, captions/transcripts, error prevention, and accessible alternatives.
- Implementation: use native platform semantics and tested design-system components; provide accessible names, roles, states, labels, and status announcements; ensure focus order and state changes make sense.
- Verification: combine automated checks with manual keyboard testing, screen reader testing, zoom/reflow checks, content review, and user testing with disabled participants. Automated tools catch only a subset of failures.
- Release: document known limitations, provide an accessible contact or support path, and prioritize remediation by user impact and risk.
- Operations: monitor feedback, regression-test shared components, and retest after changes in content, workflows, frameworks, and third-party components.

The [GOV.UK accessibility introduction](https://www.gov.uk/service-manual/helping-people-to-use-your-service/making-your-service-accessible-an-introduction) emphasizes considering accessibility before design and involving disabled users. Accessibility obligations vary by jurisdiction and service type; obtain qualified legal and specialist advice for compliance decisions.

## Adoption and organizational change

A shipped feature is not necessarily a changed service. Adoption depends on whether affected people know about the change, can use it, trust it, have time and authority to change, and receive support when things go wrong. Identify groups who must change behavior: end users, administrators, service staff, support, partner teams, compliance, and operations.

Plan adoption as part of the solution: communication, training, in-product guidance, migration, data cleanup, parallel running, cutover, support readiness, feedback channels, ownership, and retirement of old routes. Choose these based on the size of the behavior and operating-model change. Do not assume training can compensate for a confusing or unsafe service.

Measure a sequence of signals:

- **Reach:** intended users or teams know the change exists.
- **Activation:** they complete the first useful task.
- **Effective use:** they complete the target task with acceptable quality and effort.
- **Retention or sustained use:** the new behavior continues where repetition matters.
- **Outcome:** user or service performance improves.
- **Guardrails:** errors, inequity, support demand, safety incidents, cost, or unwanted workarounds remain acceptable.

Segment results by meaningful cohorts and channels. An aggregate adoption rate can hide exclusion or a failed segment. Use feedback and service data together. The [Service Standard point on simplicity](https://www.gov.uk/service-manual/service-standard/point-4-make-the-service-simple-to-use) recommends frequent usability testing and measurement across the user-facing parts of a service; its [metrics guidance](https://www.gov.uk/service-manual/measuring-success/how-to-set-performance-metrics-for-your-service) helps teams set and use service performance measures.

## Measure service outcomes responsibly

Select a small metric set that supports a decision. Define each metric's meaning, numerator, denominator, cohort, event source, time window, exclusions, owner, and expected failure modes. Distinguish leading signals (task success, completion time, error rate) from lagging outcomes (cost to serve, resolution time, satisfaction, reduced harm). Pair efficiency with quality and equity measures so optimization does not shift burden onto users or staff.

Baseline before rollout when possible. Establish expected changes, a review cadence, and a response if measures cross guardrails. Segment by user needs and access methods without collecting sensitive attributes unnecessarily. Document data gaps and instrumentation changes. Use [[Topics/Business Analysis/Benefits Realisation and Outcome Measurement]] to link outputs to outcomes and value.

## Service operations and learning

A service needs named product/service ownership, technical and operational ownership, support routes, service levels, incident response, content stewardship, accessibility ownership, and a funded path for maintenance. Define how users report a problem and how they will learn about progress and resolution.

Review real support contacts, incidents, failure demand, user research, analytics, operational measures, and accessibility findings together. A complaint can reveal a broken journey or a policy issue that telemetry cannot see. Feed this learning into prioritization and make incremental changes. Link to [[Topics/Observability]], [[Topics/Deployment and Cloud]], [[Topics/Security]], and [[Topics/Technical Leadership/Planning and Delivery]].

## Field checklist

- Can each user group state what they came to do?
- Have we observed how the work is done today across channels?
- Can users complete the whole task, including exceptions and support?
- Have disabled users and low-confidence users participated in research and testing?
- Are accessibility checks present in design, code, content, release, and operations?
- Can users recover from errors and understand what happens next?
- Is adoption supported with migration, communication, training, and support proportionate to the change?
- Do metrics cover reach, task success, outcomes, and guardrails?
- Is there a named owner and a continuing improvement loop?

## Further reading

- [GOV.UK Service Manual](https://www.gov.uk/service-manual)
- [GOV.UK: User research in discovery](https://www.gov.uk/service-manual/user-research/user-research-in-discovery)
- [GOV.UK: Making your service accessible](https://www.gov.uk/service-manual/helping-people-to-use-your-service/making-your-service-accessible-an-introduction)
- [GOV.UK Service Standard: Make the service simple to use](https://www.gov.uk/service-manual/service-standard/point-4-make-the-service-simple-to-use)
- [W3C: Web Content Accessibility Guidelines 2.2](https://www.w3.org/TR/wcag/)
