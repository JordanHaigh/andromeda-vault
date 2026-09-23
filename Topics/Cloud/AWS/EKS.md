# EKS

> Managed Kubernetes service for teams whose workloads or platform strategy require Kubernetes APIs and ecosystem compatibility.

## Engineering use and design

The service does not remove the need to manage cluster upgrades, nodes, workload security, observability, resource limits, networking, autoscaling, and Kubernetes operations. Compare with [[Topics/Cloud/AWS/ECS|ECS]] and use Kubernetes only when its portability and control justify its cost.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Elastic Load Balancing]]
- [[Topics/Cloud/AWS/API Gateway]]
- [[Topics/Cloud/AWS/EventBridge]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/eks/latest/userguide/what-is-eks.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
