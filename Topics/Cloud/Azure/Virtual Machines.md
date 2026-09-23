# Virtual Machines

> On-demand virtualized compute for workloads that require operating-system access, custom agents, legacy runtimes, or specialized machine configuration.

## Engineering use and design

The team takes responsibility for OS patching, hardening, image lifecycle, capacity, monitoring, backup, and recovery. Prefer managed services when machine-level control is not a real requirement. Connect design to [[Topics/Cloud/Azure/Virtual Network|Virtual Network]], identity, and deployment automation.

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

- [Official documentation](https://learn.microsoft.com/en-us/azure/virtual-machines/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
