# Static Web Apps

> Hosting and delivery for static frontends and supported full-stack web applications, with integrated build and deployment flows and optional API integration.

## Engineering use and design

Good fit for frontends whose content can be served from a CDN-like edge and whose dynamic needs are handled by APIs or functions. Consider routing, authentication, preview environments, cache invalidation, and API boundaries.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/Azure/Container Apps]]
- [[Topics/Cloud/Azure/Blob Storage]]
- [[Topics/Cloud/Azure/Azure SQL Database]]
- [[Topics/Cloud/Azure/PostgreSQL]]
- [[Topics/Cloud/Azure/Virtual Network]]
- [[Topics/Cloud/Azure/API Management]]
- [[Topics/Cloud/Azure/Event Grid]]
- [[Topics/Cloud/Azure/Event Hubs]]
- [[Topics/Cloud/Azure/Azure Monitor and Application Insights]]

## Official references

- [Official documentation](https://learn.microsoft.com/en-us/azure/static-web-apps/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
