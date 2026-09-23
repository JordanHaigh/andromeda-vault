# Cloud Architecture

Cloud architecture is the allocation of workloads, data, identity, networking, and operational responsibility across cloud services. It should make system behavior and failure boundaries clear to both builders and operators.

## Architecture review questions

- What user or business outcome does the system provide, and what are its latency, availability, and recovery targets?
- Where is state stored, who owns it, and how is it backed up, restored, migrated, or deleted?
- Which calls are synchronous? Which use queues or events? What are timeout, retry, idempotency, ordering, and dead-letter policies?
- Which identities can reach each resource? What are ingress and egress paths? How are credentials rotated?
- What happens when a zone, region, dependency, credential, quota, or deployment fails?
- What operational signals and runbooks let a responder diagnose the failure?
- How will cost scale with traffic, storage, logs, and idle capacity?

## Design methods

Use a small number of explicit diagrams: context, container/service, critical data flow, and deployment/network view. Record key tradeoffs in architecture decision records. Prefer reversible decisions early and isolate vendor-specific assumptions behind stable contracts when portability matters. Avoid adding distributed components unless they solve a named constraint.

## Related maps

- [[Topics/Cloud/Azure/Index]]
- [[Topics/Cloud/AWS/Index]]
- [[Topics/Database Design]], [[Topics/Security]], [[Topics/Observability]], [[Topics/Concurrency and Distributed Systems]], [[Topics/Deployment and Cloud]]
