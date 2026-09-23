# Container Apps

> Managed hosting for containerized services, jobs, and microservices with environment-level networking and event-driven scaling. It provides a middle ground between a code-first PaaS and operating a Kubernetes cluster directly.

## Engineering use and design

Choose it when containers and service composition matter but a team does not need full Kubernetes control. Define ingress, revisions, scaling rules, secrets, service-to-service security, and observability. Compare [[Topics/Cloud/Azure/Azure Kubernetes Service (AKS)|AKS]] when cluster-level Kubernetes control is a requirement.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/Azure/Blob Storage]]
- [[Topics/Cloud/Azure/Azure SQL Database]]
- [[Topics/Cloud/Azure/PostgreSQL]]
- [[Topics/Cloud/Azure/Virtual Network]]
- [[Topics/Cloud/Azure/API Management]]
- [[Topics/Cloud/Azure/Event Grid]]
- [[Topics/Cloud/Azure/Event Hubs]]
- [[Topics/Cloud/Azure/Azure Monitor and Application Insights]]

## Official references

- [Official documentation](https://learn.microsoft.com/en-us/azure/container-apps/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
