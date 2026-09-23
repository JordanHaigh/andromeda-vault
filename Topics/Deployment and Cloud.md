# Deployment and Cloud Engineering

Deployment engineering turns source changes into controlled, observable production changes. Cloud engineering adds service selection, identity, network boundaries, infrastructure lifecycle, resilience, and cost ownership.

## Delivery pipeline

Build once and promote the same immutable artifact through environments. Automate formatting, static analysis, tests, dependency and image scanning, provenance, and deployment checks. Keep configuration and secrets out of artifacts. Use reviewed infrastructure as code, short-lived credentials, and least-privilege deployment identities.

## Release safety

Use progressive delivery, canaries, blue/green or rolling strategies according to state and traffic behavior. Separate deployment from feature exposure where possible. Define health signals, abort thresholds, rollback or roll-forward criteria, database migration compatibility, and operator visibility. Practice recovery from a partially completed release.

## Cloud architecture

Choose managed services based on operational fit and constraints, not familiarity alone. Model zones and regions, identity, network flows, quotas, service limits, backup/recovery, data residency, cost allocation, and exit/migration options. A service being managed does not remove application-level failure responsibility. Provider maps: [[Topics/Cloud/Azure/Index]] and [[Topics/Cloud/AWS/Index]].

## Related topics

- [[Topics/Security]], [[Topics/Observability]], [[Topics/Database Design]]
- [[Topics/Concurrency and Distributed Systems]], [[Topics/Cloud/Cloud Architecture]]
