# Reliable Data Import

> **Illustrative case study.** This scenario uses a rail and geospatial data context because the supplied CV describes work in that domain. The specific workflow, architecture, controls, and metrics below are examples for learning; they are not claims about the user's historical systems or a real customer deployment.

A customer submits asset and location updates in batches. Today, malformed records and unclear errors create manual investigation, while retrying a failed batch risks duplicates. The engineering problem is not simply “build an import API.” The team must preserve data correctness, make failure understandable, keep customer information protected, and establish whether the new workflow improves the process.

## Outcome and discovery

Identify the people who prepare, submit, validate, and act on these records. Map the current workflow from source export through review and downstream use. Observe valid submissions, correction loops, duplicate handling, and exceptional records. Confirm data ownership, retention requirements, coordinate reference systems, permitted fields, and the operational decision made from accepted data.

A suitable outcome hypothesis might be: “For authorized data operators, a validated and traceable import path should reduce the effort needed to correct rejected batches while maintaining accepted-record accuracy and timely processing.” Do not set an invented numeric target. Obtain a baseline from representative historical batches and operator research, then define a target with the product and operations owners.

Consider process clarification, improved validation in the existing tool, a new service, or assisted review. Choose a new service only if the workflow, scale, ownership, and constraints justify it. Use [[Topics/Business Analysis/Business Analysis and Solution Discovery]] and [[Topics/Business Analysis/Benefits Realisation and Outcome Measurement]].

## Example system boundary

A possible design separates:

1. An authenticated submission API that validates request shape and records a submission identifier.
2. An immutable raw-input store with explicit retention and access controls.
3. A validation and normalization worker that applies versioned domain rules.
4. A result store containing accepted records, row-level rejections, provenance, and rule versions.
5. A status API or event feed for operators to inspect progress and retrieve errors.
6. A review workflow for records requiring human judgment.
7. Metrics, logs, traces, alerts, and audit events across the full submission journey.

Keep boundaries aligned with data ownership and operational responsibility rather than splitting every step into a separate service. Decide which system is authoritative for each field and how corrections propagate to downstream consumers. See [[Topics/Database Design]] and [[Topics/Cloud/Cloud Architecture]].

## Data evolution and recovery

Version the import contract and the validation rules. Store enough provenance to explain which rule set evaluated each batch and what source data was received, subject to retention and privacy requirements. Make accepted data and user-visible status changes consistent even if the workflow crosses a queue and database boundary.

For schema changes, use an expand-and-contract sequence: add compatible fields or structures, deploy readers that tolerate old and new forms, backfill in resumable batches, observe correctness and load, move writers and consumers, and remove old fields only after usage is verified. Design each stage with a rollback or forward-repair path. Do not assume a database transaction can cover an external message broker or object store; use explicit state transitions, an outbox or equivalent publication strategy, and reconciliation where needed.

Preserve raw input only as long as there is a justified need. Restrict, encrypt, and audit access. Ensure logs and traces carry opaque correlation IDs rather than customer payloads or secrets. Link to [[Topics/Security]] and [[Topics/Concurrency and Distributed Systems]].

## Duplicate delivery and partial failure

Assume a client retries after a timeout even though the server may have accepted the request. Assign a stable idempotency key to each logical submission, persist its state, and return the existing status when the same request is retried. Define behavior for key reuse with different content.

Give each batch and record stable identifiers. A worker should claim work with an expiring lease or equivalent concurrency control, write results idempotently, and acknowledge a message only after durable state is recorded. Bound retries, classify permanent versus transient failures, quarantine poison inputs, and expose enough state for safe replay. Reconciliation should compare submitted, processed, accepted, rejected, and pending counts and identify gaps without creating duplicates.

## Security and trust boundaries

Threat-model the upload, operator UI, API, worker identity, storage, message queue, and downstream consumers. Verify the submitter can access the target asset or customer. Enforce size, type, schema, and resource limits. Treat file contents, URLs, metadata, and external source data as untrusted. Prevent one tenant from observing another tenant's status or records. Protect service identities with least privilege and separate read, write, replay, and administrative operations. Record who performed high-impact manual corrections.

Test unauthorized access, cross-tenant identifiers, malformed content, resource exhaustion, replay, and poisoned records. Keep sensitive values out of diagnostics. For identity and authorization design, connect to [[Topics/Security]].

## Test strategy

Build tests around business examples before broad implementation:

- Unit tests for domain validation, normalization, and rule versions.
- Contract tests for clients and consumers, including old and new payload versions.
- Integration tests for database, queue, and object-store boundaries.
- Property or generative tests for coordinate and schema edge cases where feasible.
- Fault-injection tests for timeout after persistence, duplicate messages, worker crash, queue redelivery, and partial batch failure.
- Security tests for tenant isolation, authorization, and untrusted payloads.
- End-to-end tests for a user following a submission through accepted, rejected, and needs-review states.
- Data-quality reconciliation against known sample batches.

A test that the endpoint returns success does not prove the imported data is accurate or operationally useful. Keep representative fixtures, expected totals, and rule-version provenance. Link to [[Topics/Testing and Quality]].

## Delivery and production operation

Release schema and service changes in compatible stages. Start with synthetic or replayed data, then a limited internal cohort or low-risk subset. Define go/no-go conditions, a feature switch, queue drain behavior, rollback or forward recovery, and a named incident owner. Keep API and worker versions compatible during rolling deployment. Do not delete or replay customer input without an auditable procedure.

Trace one submission across API, storage, queue, worker, and result retrieval. Useful technical indicators include accepted request rate, processing duration, queue age, retry and quarantine rates, worker failures, database saturation, and reconciliation mismatches. User and business measures may include completion time, correction effort, successful first-pass acceptance, exception resolution time, support contacts, and data-quality errors. Define each metric and its denominator; do not infer user benefit from throughput alone.

Alert on symptoms that require action, such as a growing age of actionable work, missing reconciliations, or a sustained failure to complete batches. Pair alerts with runbooks and ownership. Retain incident and audit evidence without logging sensitive payloads. See [[Topics/Deployment and Cloud]] and [[Topics/Observability]].

## Evaluate after release

Compare post-release observations with the baseline and the hypothesis. Ask which users adopted the flow, whether failures became easier to correct, what new work was introduced, and whether accepted data remained trustworthy. Separate contribution from causation where other changes occurred. Include build, migration, support, and cloud costs. Decide whether to continue, adapt, expand, or revert. Record intended benefits that have not yet been verified as forecasts, not results.

## Decisions worth recording

- Source of truth and data ownership.
- Import contract and versioning policy.
- Idempotency scope and duplicate behavior.
- Validation and correction ownership.
- Retention, access, and audit policy.
- Reliability objectives and recovery expectations.
- Rollout cohorts, rollback conditions, and support owner.
- Outcome baseline, review date, and attribution limits.

## Connected notes

- [[Topics/Business Analysis/Benefits Realisation and Outcome Measurement]]
- [[Topics/Technical Leadership/Organizational Influence and Cross-Team Leadership]]
- [[Topics/Technical Leadership/Technical Strategy and Architecture Stewardship]]
- [[Topics/Technical Leadership/Planning and Delivery]]
- [[Topics/Database Design]]
- [[Topics/Concurrency and Distributed Systems]]
- [[Topics/Security]]
- [[Topics/Testing and Quality]]
- [[Topics/Deployment and Cloud]]
- [[Topics/Observability]]
- [[Topics/Web/RESTful APIs]]
- [[Topics/Case Studies/Versioned API and Online Migration]]
