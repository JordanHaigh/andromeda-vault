# Front Door

> Global entry point for web applications that combines edge routing with capabilities such as acceleration, TLS termination, and web application firewall integration depending on configuration.

## Engineering use and design

Use when global ingress, edge routing, and centralized web protection are useful. Decide cache behavior, origin health checks, failover, TLS ownership, WAF rules, and whether private origins are required. Relates to [[Topics/Cloud/Azure/API Management|API Management]] at the API policy layer.

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

- [Official documentation](https://learn.microsoft.com/en-us/azure/frontdoor/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
