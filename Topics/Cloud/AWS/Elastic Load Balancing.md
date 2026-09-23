# Elastic Load Balancing

> Managed load balancing for distributing network or HTTP(S) traffic across targets and availability zones.

## Engineering use and design

Choose load balancer behavior based on protocol and routing needs. Configure health checks that represent readiness, TLS policy, target draining, timeouts, access logs, and clear separation between edge protection and application authorization.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/API Gateway]]
- [[Topics/Cloud/AWS/EventBridge]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/elasticloadbalancing/latest/userguide/what-is-load-balancing.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
