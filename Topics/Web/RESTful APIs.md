# RESTful APIs

REST is an architectural style for networked systems. In day-to-day web engineering, a REST-oriented HTTP API exposes resources and uses HTTP methods, status codes, headers, and representations consistently. A JSON endpoint is not automatically REST; the useful design goal is a stable, understandable contract that follows web semantics.

## Design the contract

- Model nouns and relationships as resources; choose stable identifiers and avoid encoding every operation as an RPC action.
- Use HTTP methods according to their safety and idempotency semantics. A retry of a safe or idempotent request should not create surprising duplicate effects.
- Return status codes and problem details that distinguish validation, authorization, conflict, missing resources, throttling, and server failure.
- Define pagination, filtering, sorting, sparse field selection, and maximum page sizes before collections grow.
- Specify authentication and authorization separately; validate ownership and scope on every object access.
- Decide how clients observe concurrent updates (ETags/conditional requests), how long-running work is represented, and how errors are correlated.

## Evolution and operations

Publish an OpenAPI contract where it helps client generation, review, and contract testing. Prefer additive evolution, document deprecation windows, and test old clients against new servers. Version only when incompatible semantics cannot be evolved safely. Avoid relying on caches for correctness; set cache directives intentionally. Define request limits, timeouts, rate limits, idempotency keys for unsafe retryable operations, and audit fields.

## Related topics

- [[Topics/Web/GraphQL]] for client-selected nested data and typed schemas.
- [[Topics/Cloud/Azure/API Management]] and [[Topics/Cloud/AWS/API Gateway]] for gateway policy boundaries.
- [[Topics/Security]] for identity, object authorization, and threat modeling.
- [[Topics/Testing and Quality]] for schema, consumer contract, and compatibility testing.
- [[Topics/Observability]] for request traces, useful error logs, and service objectives.

## References

- [HTTP Semantics (RFC 9110)](https://www.rfc-editor.org/rfc/rfc9110)
- [OpenAPI Specification](https://spec.openapis.org/oas/latest.html)
- [Roy Fielding's dissertation](https://www.ics.uci.edu/~fielding/pubs/dissertation/top.htm)
