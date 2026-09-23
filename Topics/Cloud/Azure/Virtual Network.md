# Virtual Network

> Private network boundary for Azure resources, defining address spaces, subnets, routing, peering, and network security controls.

## Engineering use and design

Plan address allocation, DNS, private endpoints, ingress/egress paths, segmentation, and connectivity to on-premises or other clouds. Network choices affect service reachability and incident diagnosis; document flows and test them from workload identity and network contexts.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/Azure/Container Apps]]
- [[Topics/Cloud/Azure/Blob Storage]]
- [[Topics/Cloud/Azure/Azure SQL Database]]
- [[Topics/Cloud/Azure/PostgreSQL]]
- [[Topics/Cloud/Azure/API Management]]
- [[Topics/Cloud/Azure/Event Grid]]
- [[Topics/Cloud/Azure/Event Hubs]]
- [[Topics/Cloud/Azure/Azure Monitor and Application Insights]]

## Official references

- [Official documentation](https://learn.microsoft.com/en-us/azure/virtual-network/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
