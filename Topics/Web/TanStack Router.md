# TanStack Router

TanStack Router is a typed router for supported frontend frameworks. It provides nested routes, typed navigation, route search parameter handling, route loaders, and route lifecycle mechanisms. The official guide supports both file-based and code-based routing patterns.

## Architecture decisions

Treat the URL as durable, shareable state for filters, pagination, selected tabs, and navigable views. Validate and normalize search parameters at route boundaries. Define loading, not-found, error, and authorization behavior for each route. Decide whether route loaders or [[Topics/Web/TanStack Query]] own data acquisition and caching; integration should avoid duplicate requests and ambiguous invalidation.

Review file-based route generation and generated types in the build pipeline. Test deep links, browser back/forward behavior, nested layouts, and access boundaries. A route guard improves navigation flow but is not a substitute for server-side authorization.

## Links

- Parent: [[Topics/Web/TanStack]]
- Related: [[Topics/Web/TanStack Query]], [[Topics/Web/RESTful APIs]], [[Topics/Security]], [[Topics/Testing and Quality]]
- [Official overview](https://tanstack.com/router/latest/docs/framework/react/overview)
- [Router documentation](https://tanstack.com/router/latest)
