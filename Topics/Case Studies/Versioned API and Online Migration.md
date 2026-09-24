# Versioned API and Online Migration

> **Illustrative case study.** The scenario is a generic service that adds a required classification to a resource returned to multiple client versions. It demonstrates compatibility and rollout decisions; it does not describe a particular system in the supplied CV.

A team needs to add a field that changes how a resource is classified. A naive release updates the database, API, and clients together. That fails when clients and servers deploy at different times, background jobs still write the old shape, or a rollback returns to code that cannot read the new data.

## Contract and compatibility

Start by identifying every producer, consumer, stored representation, export, and operational tool that depends on the current contract. Clarify whether the new field can be absent, how old values are interpreted, and who owns classification rules. Define examples for old and new clients, including omitted, null, invalid, and unknown values.

Prefer additive changes when they preserve existing behavior. If semantics change, do not treat the same field name as compatible merely because its JSON type is unchanged. Version the contract or create a transition path. Document deprecation, consumer notification, and a removal condition based on observed use rather than a guessed date.

For REST, specify request and response behavior, status codes, error cases, and compatibility expectations. For GraphQL, consider nullability, enum expansion, field deprecation, and clients that may not select a new field. Use schema checks and consumer contract tests to make assumptions visible. See [[Topics/Web/RESTful APIs]] and [[Topics/Web/GraphQL]].

## Expand, migrate, contract

1. **Expand:** add a nullable or defaulted field and compatible readers. Avoid a breaking constraint before data is populated.
2. **Deploy readers:** support both old and new stored forms, with one explicit interpretation for each.
3. **Backfill:** update records in bounded, resumable batches. Track progress, errors, write contention, and safe restart behavior.
4. **Dual-write or switch writers:** if needed, write both forms temporarily. Define which representation is authoritative and detect divergence.
5. **Verify:** compare counts and values, inspect exceptions, and confirm all consumers and jobs understand the new shape.
6. **Enforce:** add stronger constraints or make the field required only after evidence shows all relevant data and writers comply.
7. **Contract:** stop old writes, observe remaining old clients, then remove compatibility code and storage in a later release.

The sequence can vary by database and workload. Each step needs a clear success condition, owner, and recovery plan. A backward-compatible application binary does not guarantee a safe data migration.

## Tests and rollout

Test old client against new server, new client against old server where that deployment order is possible, background jobs, cached data, exports, and rollback. Verify authorization remains unchanged and the new classification does not disclose information. Use synthetic data or a representative anonymized subset for migration rehearsals.

Roll out first to a small cohort or internal consumer. Monitor error rates, unknown values, old-version traffic, backfill lag, and data divergence. Keep a switch to disable new behavior where possible. If migration is irreversible, prepare a tested forward-repair procedure and make that constraint explicit in the decision record.

## Observability and cleanup

Record API version or client cohort, migration batch, and opaque resource identifiers so a failure can be traced without logging sensitive payloads. Alert on actionable conditions: a stalled backfill, rising classification failures, contract errors, or consumers still depending on a deprecated shape near the removal decision.

Remove compatibility paths only when telemetry, consumer communication, and ownership confirm they are no longer needed. Archive the decision and migration evidence. Compare the change with the original business outcome and operating-cost hypothesis.

## Review checklist

- Are all consumers and writers known, including scheduled jobs and exports?
- Is the data owner clear, and is there one authoritative representation at each stage?
- Can old and new versions coexist safely during deployment and rollback?
- Are backfill work and errors resumable and observable?
- Are contract tests based on real consumer expectations?
- Are deprecation and removal tied to usage evidence?
- Are security, privacy, and audit controls unchanged or improved?
- Is post-release success measured as a user or business outcome?

## Related notes

- [[Topics/Case Studies/Reliable Data Import]]
- [[Topics/Database Design]]
- [[Topics/Concurrency and Distributed Systems]]
- [[Topics/Security]]
- [[Topics/Testing and Quality]]
- [[Topics/Deployment and Cloud]]
- [[Topics/Observability]]
- [[Topics/Web/RESTful APIs]]
- [[Topics/Web/GraphQL]]
- [[Topics/Business Analysis/Benefits Realisation and Outcome Measurement]]

## Further reading

- [Google AIP-180: Backwards compatibility](https://google.aip.dev/180)
- [Google AIP-185: API versioning](https://google.aip.dev/185)
