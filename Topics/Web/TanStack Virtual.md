# TanStack Virtual

TanStack Virtual is a headless virtualization utility that renders only a visible window of a large list or grid, reducing DOM work while preserving a larger logical collection. The application owns markup, styling, semantics, and data loading.

## Engineering considerations

Virtualization can complicate keyboard navigation, screen-reader position announcements, browser find, focus retention, variable-height measurement, scroll restoration, and testing. Keep stable item keys, handle dynamic row sizes deliberately, and test on devices with different viewport sizes. Virtualization reduces rendered UI work; it does not reduce the cost of fetching, sorting, or holding the entire dataset unless those concerns are addressed separately.

## Links

- Parent: [[Topics/Web/TanStack]]
- Related: [[Topics/Web/TanStack Table]], [[Topics/Testing and Quality]], [[Topics/Observability]]
- [Official TanStack Virtual docs](https://tanstack.com/virtual/latest)
