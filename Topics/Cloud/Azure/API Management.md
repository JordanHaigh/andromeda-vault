# API Management

> API gateway and lifecycle platform for publishing APIs with policies for authentication, transformation, rate limits, routing, and developer onboarding.

## Engineering use and design

Place gateway policies at a clear boundary and keep domain behavior in the service. Define versioning and deprecation, subscription and identity models, quotas, backend timeouts, caching, and policy tests. Supports REST/OpenAPI and GraphQL API scenarios; see [[Topics/Web/RESTful APIs]] and [[Topics/Web/GraphQL]].

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/Azure/Container Apps]]
- [[Topics/Cloud/Azure/Blob Storage]]
- [[Topics/Cloud/Azure/Azure SQL Database]]
- [[Topics/Cloud/Azure/PostgreSQL]]
- [[Topics/Cloud/Azure/Virtual Network]]
- [[Topics/Cloud/Azure/Event Grid]]
- [[Topics/Cloud/Azure/Event Hubs]]
- [[Topics/Cloud/Azure/Azure Monitor and Application Insights]]

## Official references

- [Official documentation](https://learn.microsoft.com/en-us/azure/api-management/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
