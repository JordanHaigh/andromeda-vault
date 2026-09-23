# VPC

> Isolated virtual network environment for AWS resources, including subnets, route tables, gateways, endpoints, and network access controls.

## Engineering use and design

Design IP ranges and subnet layout before deployment. Map traffic paths, public/private boundaries, DNS, egress, endpoint use, security groups, network ACLs, and cross-account connectivity. Validate flows and failure cases, not only console configuration.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Elastic Load Balancing]]
- [[Topics/Cloud/AWS/API Gateway]]
- [[Topics/Cloud/AWS/EventBridge]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/vpc/latest/userguide/what-is-amazon-vpc.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
