# Key Vault

> Managed storage and access control for secrets, keys, and certificates used by applications and platform resources.

## Engineering use and design

Use identity-based access and least privilege; avoid logging secret values; plan rotation, soft delete and recovery, certificate lifecycle, network access, and service outage behavior. Secret retrieval should have bounded caching and clear failure modes.

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

- [Official documentation](https://learn.microsoft.com/en-us/azure/key-vault/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
