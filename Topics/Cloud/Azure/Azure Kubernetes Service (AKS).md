# Azure Kubernetes Service (AKS)

> Managed Kubernetes for teams that need Kubernetes APIs, ecosystem compatibility, workload scheduling, and control over cluster configuration. Azure manages the control plane; the application team still owns workload design and operational readiness.

## Engineering use and design

Adopt only when the platform flexibility pays for its operational cost. Plan upgrades, node pools, autoscaling, network policy, workload identity, ingress, resource requests, secrets, backup, and incident ownership. Compare with [[Topics/Cloud/Azure/Container Apps|Container Apps]] and [[Topics/Cloud/Azure/App Service|App Service]].

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

- [Official documentation](https://learn.microsoft.com/en-us/azure/aks/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
