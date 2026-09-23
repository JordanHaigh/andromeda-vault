# Testing and Software Quality

Testing builds evidence about behavior at different boundaries. A good suite makes failures diagnosable and gives fast feedback without pretending every risk can be reduced to a test count.

## Choose tests by failure mode

- **Unit tests** isolate logic and edge cases with fast feedback.
- **Integration tests** exercise real boundaries such as databases, queues, filesystems, and framework configuration.
- **Contract tests** check compatibility between API providers and consumers.
- **End-to-end tests** validate a small number of critical user journeys through deployed-like systems.
- **Property-based, fuzz, and mutation testing** explore input spaces and test whether assertions detect defects.
- **Load, resilience, and security tests** probe operational limits and failure behavior.

## Keep suites trustworthy

Use deterministic clocks, generated test data, isolated resources, and cleanup that survives failures. Assert externally meaningful behavior rather than implementation trivia. Make flaky tests visible and owned; quarantining should be temporary and tracked. Test authorization denial paths, retries, timeouts, migrations, duplicate events, and rollback. A deployment should report which checks ran against which build artifact.

## Related topics

- [[Topics/Web/RESTful APIs]], [[Topics/Web/GraphQL]], [[Topics/Web/TanStack Query]]
- [[Topics/Deployment and Cloud]], [[Topics/Observability]], [[Topics/Security]]
- [[Topics/Concurrency and Distributed Systems]]
