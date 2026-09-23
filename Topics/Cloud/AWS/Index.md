# AWS services

Service notes explain where each service fits, the main design questions, relationships, and official documentation entry points. Treat the provider docs as authoritative for current limits, regional availability, product status, and pricing.

## Compute and application hosting

- [[Topics/Cloud/AWS/EC2]] — Resizable virtual machine compute for workloads that require operating-system access, specialized runtime configuration, or control over the host environment.
- [[Topics/Cloud/AWS/Lambda]] — Event-driven serverless compute invoked by AWS services, HTTP integration, schedules, and other event sources.
- [[Topics/Cloud/AWS/ECS]] — AWS container orchestration service for deploying and operating containerized workloads using AWS-managed control planes and task definitions.
- [[Topics/Cloud/AWS/EKS]] — Managed Kubernetes service for teams whose workloads or platform strategy require Kubernetes APIs and ecosystem compatibility.
- [[Topics/Cloud/AWS/Fargate]] — Serverless compute engine for running containers with ECS or EKS without managing the underlying server fleet.
- [[Topics/Cloud/AWS/Step Functions]] — Workflow orchestration service for coordinating distributed application tasks with explicit state transitions, retries, and error paths.

## Data, storage, and networking

- [[Topics/Cloud/AWS/RDS and Aurora]] — Managed relational database services for common SQL engines, plus Aurora-compatible database engines built for AWS-managed operation.
- [[Topics/Cloud/AWS/DynamoDB]] — Managed key-value and document database designed around predictable access patterns and partitioned scaling.
- [[Topics/Cloud/AWS/VPC]] — Isolated virtual network environment for AWS resources, including subnets, route tables, gateways, endpoints, and network access controls.
- [[Topics/Cloud/AWS/Route 53]] — Scalable DNS and domain routing service for hosted zones, health checks, and routing policies.

## APIs, messaging, and operations

- [[Topics/Cloud/AWS/S3]] — Object storage for files, backups, static assets, data lakes, and application blobs.
- [[Topics/Cloud/AWS/Elastic Load Balancing]] — Managed load balancing for distributing network or HTTP(S) traffic across targets and availability zones.
- [[Topics/Cloud/AWS/CloudFront]] — Content delivery network and edge distribution service for caching and delivering web content and APIs close to users.
- [[Topics/Cloud/AWS/API Gateway]] — Managed API front door for HTTP, REST, and WebSocket API patterns, with routing, authorization integration, throttling, and backend integration.
- [[Topics/Cloud/AWS/IAM]] — AWS identity and access management system for users, roles, policies, and permission evaluation across AWS resources.
- [[Topics/Cloud/AWS/KMS and Secrets Manager]] — AWS managed key services and secret lifecycle service used to protect credentials, tokens, and encryption keys.
- [[Topics/Cloud/AWS/SQS]] — Managed message queues for decoupling producers and consumers and buffering work during load variation or downstream outages.
- [[Topics/Cloud/AWS/SNS]] — Managed publish/subscribe notification service that fans messages out to subscribed endpoints and services.
- [[Topics/Cloud/AWS/EventBridge]] — Event bus and routing service for matching events to targets across AWS services, applications, and supported SaaS integrations.
- [[Topics/Cloud/AWS/CloudWatch]] — AWS platform for metrics, logs, alarms, dashboards, and operational signals from services and applications.

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
