# Fargate

> Serverless compute engine for running containers with ECS or EKS without managing the underlying server fleet.

## Engineering use and design

Useful when container workloads need isolated task or pod compute and the team wants to avoid host operations. Review supported configuration constraints, networking, startup time, ephemeral storage, capacity, and per-resource cost. It complements ECS/EKS rather than replacing their orchestration layer.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Elastic Load Balancing]]
- [[Topics/Cloud/AWS/API Gateway]]
- [[Topics/Cloud/AWS/EventBridge]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/AmazonECS/latest/developerguide/AWS_Fargate.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
