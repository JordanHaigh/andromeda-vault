# TanStack Table

TanStack Table is a headless table and data-grid engine: it manages table state and data-processing models while the application supplies markup, styling, and interaction presentation. It can support sorting, filtering, grouping, pagination, column sizing, row selection, and custom features depending on the chosen version and adapter.

## Design responsibilities

The application owns semantic table markup, keyboard navigation, focus management, accessible names, loading and empty states, responsive behavior, and data fetching. Decide whether sorting and filtering occur locally or on the server; do not paginate a partial local dataset as though it were complete. Keep column definitions and server query parameters aligned, and consider virtualization for very large rendered collections.

## Links

- Parent: [[Topics/Web/TanStack]]
- Related: [[Topics/Web/TanStack Virtual]], [[Topics/Testing and Quality]], [[Topics/Observability]]
- [Official overview](https://tanstack.com/table/latest/docs/overview)
- [TanStack Table docs](https://tanstack.com/table/latest)
