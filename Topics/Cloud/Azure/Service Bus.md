# Service Bus

> Enterprise messaging service for queues and topics, supporting asynchronous communication between application components.

## Engineering use and design

Use a queue for competing consumers and a topic/subscription model for fan-out. Design for at-least-once delivery: idempotent consumers, dead-letter handling, duplicate detection where applicable, lock renewal, ordering constraints, retries, and schema evolution. Compare with [[Topics/Cloud/Azure/Event Grid|Event Grid]] for event routing and [[Topics/Cloud/Azure/Event Hubs|Event Hubs]] for streaming ingestion.

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

- [Official documentation](https://learn.microsoft.com/en-us/azure/service-bus-messaging/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
