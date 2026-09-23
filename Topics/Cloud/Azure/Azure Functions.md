# Azure Functions

> Event-driven compute built around functions invoked by HTTP requests, timers, queues, streams, and other triggers. Functions are useful for short-lived handlers and integration glue; orchestration patterns can coordinate longer workflows.

## Engineering use and design

Design handlers to be idempotent where retries are possible. Make timeout, concurrency, cold-start, trigger delivery, poison-message, and dependency behavior explicit. For containerized function apps that need to run alongside microservices, compare the Container Apps hosting model.

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

- [Official documentation](https://learn.microsoft.com/en-us/azure/azure-functions/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
