# Azure Monitor and Application Insights

> Azure’s monitoring platform and application performance monitoring capabilities for metrics, logs, traces, alerts, and service health signals.

## Engineering use and design

Instrument user-visible operations, propagate correlation context, define actionable alert thresholds, and control telemetry cardinality and sensitive data. Monitor service-level objectives as well as infrastructure. Relates to [[Topics/Observability]] and incident response.

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

## Official references

- [Official documentation](https://learn.microsoft.com/en-us/azure/azure-monitor/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
