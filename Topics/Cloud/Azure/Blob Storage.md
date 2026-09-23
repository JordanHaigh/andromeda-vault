# Blob Storage

> Object storage for unstructured data such as uploads, media, backups, logs, and data-lake files. Objects live in containers and are addressed independently from application compute.

## Engineering use and design

Model object naming and metadata deliberately; choose access tiers and retention based on access patterns; use short-lived delegated access where appropriate; configure lifecycle, encryption, versioning, immutability, and recovery. Relates to [[Topics/Cloud/Azure/Event Grid|Event Grid]] for storage events and [[Topics/Database Design|Database Design]] for metadata ownership.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/Azure/Container Apps]]
- [[Topics/Cloud/Azure/Azure SQL Database]]
- [[Topics/Cloud/Azure/PostgreSQL]]
- [[Topics/Cloud/Azure/Virtual Network]]
- [[Topics/Cloud/Azure/API Management]]
- [[Topics/Cloud/Azure/Event Grid]]
- [[Topics/Cloud/Azure/Event Hubs]]
- [[Topics/Cloud/Azure/Azure Monitor and Application Insights]]

## Official references

- [Official documentation](https://learn.microsoft.com/en-us/azure/storage/blobs/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
