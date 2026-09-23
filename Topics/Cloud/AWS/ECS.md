# ECS

> AWS container orchestration service for deploying and operating containerized workloads using AWS-managed control planes and task definitions.

## Engineering use and design

Define task sizing, deployment health, service discovery, secrets, IAM task roles, autoscaling, load balancing, image provenance, and graceful shutdown. Compare with [[Topics/Cloud/AWS/EKS|EKS]] when Kubernetes APIs or ecosystem compatibility are required.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Elastic Load Balancing]]
- [[Topics/Cloud/AWS/API Gateway]]
- [[Topics/Cloud/AWS/EventBridge]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/AmazonECS/latest/developerguide/Welcome.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
