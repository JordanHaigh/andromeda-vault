# Azure services

Service notes explain where each service fits, the main design questions, relationships, and official documentation entry points. Treat the provider docs as authoritative for current limits, regional availability, product status, and pricing.

## Compute and application hosting

- [[Topics/Cloud/Azure/App Service]] — Managed hosting for web applications and HTTP APIs. It removes much of the routine server, patching, load-balancing, and runtime management while keeping familiar application deployment workflows.
- [[Topics/Cloud/Azure/Azure Functions]] — Event-driven compute built around functions invoked by HTTP requests, timers, queues, streams, and other triggers. Functions are useful for short-lived handlers and integration glue; orchestration patterns can coordinate longer workflows.
- [[Topics/Cloud/Azure/Container Apps]] — Managed hosting for containerized services, jobs, and microservices with environment-level networking and event-driven scaling. It provides a middle ground between a code-first PaaS and operating a Kubernetes cluster directly.
- [[Topics/Cloud/Azure/Azure Kubernetes Service (AKS)]] — Managed Kubernetes for teams that need Kubernetes APIs, ecosystem compatibility, workload scheduling, and control over cluster configuration. Azure manages the control plane; the application team still owns workload design and operational readiness.
- [[Topics/Cloud/Azure/Virtual Machines]] — On-demand virtualized compute for workloads that require operating-system access, custom agents, legacy runtimes, or specialized machine configuration.
- [[Topics/Cloud/Azure/Static Web Apps]] — Hosting and delivery for static frontends and supported full-stack web applications, with integrated build and deployment flows and optional API integration.

## Data, storage, and networking

- [[Topics/Cloud/Azure/Blob Storage]] — Object storage for unstructured data such as uploads, media, backups, logs, and data-lake files. Objects live in containers and are addressed independently from application compute.
- [[Topics/Cloud/Azure/Azure SQL Database]] — Managed relational database service based on SQL Server, with managed patching, backups, scaling options, and platform integrations.
- [[Topics/Cloud/Azure/Azure Cosmos DB]] — Globally distributed NoSQL database service with multiple APIs and partitioned data models. It is intended for low-latency access patterns that can be designed around partition keys and distribution requirements.
- [[Topics/Cloud/Azure/PostgreSQL]] — Managed PostgreSQL database service for applications that want PostgreSQL semantics without managing the database host directly.
- [[Topics/Cloud/Azure/Virtual Network]] — Private network boundary for Azure resources, defining address spaces, subnets, routing, peering, and network security controls.

## APIs, messaging, and operations

- [[Topics/Cloud/Azure/Front Door]] — Global entry point for web applications that combines edge routing with capabilities such as acceleration, TLS termination, and web application firewall integration depending on configuration.
- [[Topics/Cloud/Azure/API Management]] — API gateway and lifecycle platform for publishing APIs with policies for authentication, transformation, rate limits, routing, and developer onboarding.
- [[Topics/Cloud/Azure/Service Bus]] — Enterprise messaging service for queues and topics, supporting asynchronous communication between application components.
- [[Topics/Cloud/Azure/Event Grid]] — Event routing service for reacting to discrete changes from Azure and custom sources through event subscriptions and handlers.
- [[Topics/Cloud/Azure/Event Hubs]] — Managed event streaming and ingestion service for high-volume streams consumed by one or more independent consumer groups.
- [[Topics/Cloud/Azure/Microsoft Entra ID]] — Microsoft identity platform for workforce, workload, and customer identity scenarios. Applications integrate through standard protocols and platform-specific identity features.
- [[Topics/Cloud/Azure/Key Vault]] — Managed storage and access control for secrets, keys, and certificates used by applications and platform resources.
- [[Topics/Cloud/Azure/Azure Monitor and Application Insights]] — Azure’s monitoring platform and application performance monitoring capabilities for metrics, logs, traces, alerts, and service health signals.

## Architecture connections

- [[Topics/Cloud/Cloud Architecture]]
- [[Topics/Database Design]]
- [[Topics/Security]]
- [[Topics/Observability]]
- [[Topics/Concurrency and Distributed Systems]]
- [[Topics/Web/RESTful APIs]]
- [[Topics/Web/GraphQL]]

## Official overview

- [Key Azure services for developers](https://learn.microsoft.com/en-us/azure/developer/intro/azure-developer-key-services)
- [AWS documentation](https://docs.aws.amazon.com/)
