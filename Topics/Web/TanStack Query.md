# TanStack Query

TanStack Query manages asynchronous server state in a frontend: query identity, cached results, background refetching, mutation state, invalidation, and synchronization between views. It does not replace local UI state or make server data permanently authoritative.

## Design practices

Use stable query keys that uniquely encode parameters and tenant/user scope. Define retry behavior for transient failures while avoiding repeated non-idempotent effects. Set stale and garbage-collection behavior to match product freshness and memory needs. After writes, choose targeted invalidation, direct cache updates, or optimistic updates deliberately; optimistic updates need rollback and conflict handling. Keep credentials and sensitive records out of persisted caches unless storage protection and logout clearing are designed.

## SSR and integration

For server rendering, create request-scoped clients to prevent one user's cached data crossing request boundaries. Define hydration, prefetch, and error handling with the current framework integration. If using [[Topics/Web/TanStack Router]], ensure route loading and query cache responsibilities do not fetch the same data redundantly.

## Links

- Parent: [[Topics/Web/TanStack]]
- Related: [[Topics/Web/TanStack Router]], [[Topics/Web/RESTful APIs]], [[Topics/Web/GraphQL]], [[Topics/Testing and Quality]]
- [Official TanStack Query docs](https://tanstack.com/query/latest/docs/framework/react/overview)
- [Server rendering guide](https://tanstack.com/query/latest/docs/framework/react/guides/ssr)
