# Azure Cosmos DB

> Globally distributed NoSQL database service with multiple APIs and partitioned data models. It is intended for low-latency access patterns that can be designed around partition keys and distribution requirements.

## Engineering use and design

Start from access patterns and partition boundaries, not from a relational schema copied unchanged. Analyze hot partitions, consistency needs, request-unit consumption, indexing, multi-region writes, TTL, and backup/restore. Compare with [[Topics/Cloud/Azure/Azure SQL Database|Azure SQL]] when joins and relational constraints dominate.

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

- [Official documentation](https://learn.microsoft.com/en-us/azure/cosmos-db/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
