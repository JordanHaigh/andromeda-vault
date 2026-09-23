# Microsoft Entra ID

> Microsoft identity platform for workforce, workload, and customer identity scenarios. Applications integrate through standard protocols and platform-specific identity features.

## Engineering use and design

Separate authentication (who is calling) from authorization (what they may do). Prefer managed/workload identities over embedded credentials; define tenant boundaries, token audience and lifetime, role/scope mapping, consent, and audit requirements. Connect to [[Topics/Security|Security]].

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

- [Official documentation](https://learn.microsoft.com/en-us/entra/identity/)
- [Service directory](https://azure.microsoft.com/en-us/products/)
