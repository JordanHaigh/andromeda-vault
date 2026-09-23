# EC2

> Resizable virtual machine compute for workloads that require operating-system access, specialized runtime configuration, or control over the host environment.

## Engineering use and design

The team owns image patching, autoscaling, capacity, instance recovery, networking, IAM roles, and deployment automation. Compare managed container or function hosting when machine control is unnecessary.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Elastic Load Balancing]]
- [[Topics/Cloud/AWS/API Gateway]]
- [[Topics/Cloud/AWS/EventBridge]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/concepts.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
