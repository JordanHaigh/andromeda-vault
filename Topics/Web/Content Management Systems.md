# Content Management Systems

A content management system (CMS) lets people create, review, publish, organize, and retire content without treating every content change as a software release. CMS design spans editorial workflow, content modeling, delivery, security, localization, and governance.

## Main deployment shapes

- **Traditional / coupled CMS:** authoring and page rendering share a platform. This can make editorial workflows and templating straightforward, while binding delivery to the CMS stack.
- **Headless CMS:** the CMS manages content and exposes APIs; separate applications render it. This separates editorial tooling from presentation and makes preview, caching, API versioning, and consumer contracts central concerns.
- **Hybrid / decoupled CMS:** editorial, preview, and delivery combine elements of both approaches. Validate which features are native and which rely on plugins or custom infrastructure.

## Model content, not just pages

Define content types, fields, references, validation, ownership, localization, asset metadata, and lifecycle states. Treat structured content as an API consumed by web, mobile, email, and search systems. Plan drafts, approvals, scheduled publishing, preview links, revision history, rollback, and audit trails. Separate content identity from URLs so a presentation redesign does not destroy references.

## Security and operations

Use role-based editorial permissions, MFA/SSO where available, least-privilege API credentials, secret rotation, dependency patching, backup and restore, and review of plugin or extension supply chains. Public delivery needs caching and invalidation rules. Preview and draft content must not leak through CDN caches or search indexing. Include content migrations and editorial communication in release plans.

## Selection questions

Who authors and approves? Which channels consume the content? How important are localization, preview, workflow, and compliance? Who owns hosting and upgrades? Are API limits, export portability, and content migration practical? Evaluate total cost including editors, developers, infrastructure, plugins, and operational support.

## Related topics

- [[Topics/Web/RESTful APIs]] and [[Topics/Web/GraphQL]] for delivery contracts.
- [[Topics/Cloud/Azure/Static Web Apps]] and [[Topics/Cloud/Azure/Blob Storage]] for hosted sites and assets.
- [[Topics/Security]], [[Topics/Testing and Quality]], and [[Topics/Observability]].

## Product documentation

- [WordPress documentation](https://wordpress.org/documentation/)
- [Drupal documentation](https://www.drupal.org/docs)
- [Strapi documentation](https://docs.strapi.io/)
- [Directus documentation](https://docs.directus.io/)
- [Contentful documentation](https://www.contentful.com/developers/docs/)
- [Sanity documentation](https://www.sanity.io/docs)
