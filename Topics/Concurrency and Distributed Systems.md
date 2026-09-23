# Concurrency and Distributed Systems

Concurrency allows overlapping work; distribution places work across processes or machines where communication is delayed and failures are independent. Correctness depends on explicitly modeling shared state, ordering, cancellation, timeouts, and partial failure.

## In-process concurrency

Identify mutable shared state and synchronization ownership. Understand the language memory model, atomic operations, locks, channels, task cancellation, and resource lifetimes. Avoid holding locks across blocking I/O. Bound queues and worker pools; unbounded concurrency can turn a slow dependency into memory exhaustion. Use structured cancellation and propagate deadlines.

## Distributed execution

Assume networks can time out after the receiver has completed work. Retries can duplicate side effects; use idempotency keys or deduplication where required. Apply bounded retries with jitter, deadlines, circuit breaking or load shedding where justified, and backpressure. Define consistency and ordering needs per workflow. A queue offers buffering and decoupling but requires handling poison messages and replay; an event log supports reprocessing but imposes schema and consumer-offset responsibilities.

## Reliability and correctness

Use transactions inside a service boundary where possible. For cross-service workflows, model durable states, compensating actions, and reconciliation. Avoid claiming exactly-once execution without defining the boundary and failure assumptions. Test races, duplicate delivery, delayed messages, dependency outages, and partial deployment. Connect these ideas to [[Topics/Database Design]], [[Topics/Observability]], and [[Topics/Testing and Quality]].

## Cloud primitives

- Azure: [[Topics/Cloud/Azure/Service Bus]], [[Topics/Cloud/Azure/Event Grid]], [[Topics/Cloud/Azure/Event Hubs]]
- AWS: [[Topics/Cloud/AWS/SQS]], [[Topics/Cloud/AWS/SNS]], [[Topics/Cloud/AWS/EventBridge]], [[Topics/Cloud/AWS/Step Functions]]
