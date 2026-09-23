# TanStack

TanStack is a family of open-source TypeScript tools for application data, routing, tables, forms, virtualization, and full-stack web development. The libraries are modular: adopt the part that solves a real problem and understand each package's framework adapters, version, and ownership boundary.

## Choose by problem

- [[Topics/Web/TanStack Query]] manages asynchronous server state: fetching, caching, synchronization, mutations, and invalidation.
- [[Topics/Web/TanStack Router]] handles route matching, typed navigation, search parameters, route loading, and route lifecycle.
- [[Topics/Web/TanStack Table]] supplies headless table state and row/column processing; the application owns markup and visual behavior.
- [[Topics/Web/TanStack Form]] models form state, validation, derived state, and field composition.
- [[Topics/Web/TanStack Virtual]] renders a window of a large collection to reduce DOM work.
- [[Topics/Web/TanStack Start]] is the full-stack framework option in the ecosystem; evaluate its current runtime and deployment model from current docs.

## Architecture notes

Keep client state, server state, URL state, and form state distinct. Use query keys that encode the complete data identity and invalidate only when domain writes make cached reads stale. Treat route loaders and query caches as an integrated lifecycle when both are used. Headless libraries leave accessibility, keyboard support, styling, and interaction details to the application team; test those responsibilities explicitly.

## Related topics

- [[Topics/Web/RESTful APIs]], [[Topics/Web/GraphQL]], [[Topics/Testing and Quality]], and [[Topics/Observability]].
- [[Topics/Web/Content Management Systems]] for applications consuming editorial content.

## Official references

- [TanStack documentation home](https://tanstack.com/)
- [TanStack Query](https://tanstack.com/query/latest)
- [TanStack Router](https://tanstack.com/router/latest)
- [TanStack Table](https://tanstack.com/table/latest)
- [TanStack Form](https://tanstack.com/form/latest)
- [TanStack Virtual](https://tanstack.com/virtual/latest)
- [TanStack Start](https://tanstack.com/start/latest)
