# Event Hubs

> Managed event streaming and ingestion service for high-volume streams consumed by one or more independent consumer groups.

## Engineering use and design

Partition count, partition key, retention, consumer offsets, checkpointing, and replay requirements shape the design. Treat events as append-oriented stream records and ensure consumers tolerate retries and reprocessing. Compare with [[Topics/Cloud/Azure/Service Bus|Service Bus]] for business commands and queue workflows.

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
- [[Topics/Cloud/Azure/Azure Monitor and Application Insights]]

## Official references

- [Official documentation](https://learn.microsoft.com/en-us/azure/event-hubs/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
