# GraphQL

GraphQL defines a typed schema and a query language through which a client asks for a shape of data. The server validates a query against the schema and resolves requested fields. GraphQL does not prescribe a database, deployment model, authorization system, or caching strategy.

## Core parts

- **Schema:** object, scalar, enum, interface, union, input, and root operation types form the public contract.
- **Query:** reads selected fields; clients can nest related fields but should be bounded against expensive or deeply recursive selection.
- **Mutation:** describes writes and their result shape. Mutations still need authorization, validation, transaction boundaries, and idempotency design.
- **Subscription:** models server-to-client updates through a transport that the implementation supports; connection lifetime and delivery semantics require separate decisions.
- **Resolvers:** field functions connect schema fields to domain services or data sources. Keep domain rules in reusable service boundaries rather than duplicating them across resolvers.

## Production design

Prevent N+1 access with batching/caching tools such as per-request data loaders. Set query depth, complexity, time, and payload limits. Apply authorization at the object and field boundary where sensitive data can appear. Decide how partial data and field errors are represented, how persisted/allowlisted queries work, and what may be cached. Schema changes need compatibility checks and a deprecation process. For large files, use a separate upload design rather than forcing binary payloads through ordinary query shapes.

## When it fits

GraphQL is useful when clients need different nested projections, multiple frontends share a domain graph, or teams need a typed API composition boundary. REST can be simpler for cacheable resource operations, public HTTP conventions, and straightforward command/query surfaces. Both can coexist when each has a clear ownership and security model.

## Related topics

- [[Topics/Web/RESTful APIs]]
- [[Topics/Security]]
- [[Topics/Testing and Quality]]
- [[Topics/Observability]]
- [[Topics/Cloud/Azure/API Management]]
- [[Topics/Cloud/AWS/API Gateway]]

## References

- [GraphQL Learn](https://graphql.org/learn/)
- [GraphQL Specification](https://spec.graphql.org/)
- [GraphQL Foundation](https://graphql.org/foundation/)
