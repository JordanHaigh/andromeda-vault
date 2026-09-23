# App Service

> Managed hosting for web applications and HTTP APIs. It removes much of the routine server, patching, load-balancing, and runtime management while keeping familiar application deployment workflows.

## Engineering use and design

Use for conventional web backends when the team wants a managed application host and does not need to own a Kubernetes platform. Plan runtime configuration, deployment slots or equivalent release strategy, identity, networking, scale limits, health checks, and persistent state separately.

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

- [Official documentation](https://learn.microsoft.com/en-us/azure/app-service/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
