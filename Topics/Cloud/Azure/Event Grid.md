# Event Grid

> Event routing service for reacting to discrete changes from Azure and custom sources through event subscriptions and handlers.

## Engineering use and design

Use when producers should announce that something happened and independent handlers can react. Specify event schema, filtering, delivery failure policy, dead-letter destination, replay/reconciliation, and idempotency. It is not a substitute for a durable work queue or ordered event log.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/Azure/Container Apps]]
- [[Topics/Cloud/Azure/Blob Storage]]
- [[Topics/Cloud/Azure/Azure SQL Database]]
- [[Topics/Cloud/Azure/PostgreSQL]]
- [[Topics/Cloud/Azure/Virtual Network]]
- [[Topics/Cloud/Azure/API Management]]
- [[Topics/Cloud/Azure/Event Hubs]]
- [[Topics/Cloud/Azure/Azure Monitor and Application Insights]]

## Official references

- [Official documentation](https://learn.microsoft.com/en-us/azure/event-grid/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
