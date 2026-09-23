# SNS

> Managed publish/subscribe notification service that fans messages out to subscribed endpoints and services.

## Engineering use and design

Use for one-to-many notification delivery. Define subscription filtering, delivery retries, dead-letter handling, access policy, message format, and consumer idempotency. Compare with [[Topics/Cloud/AWS/SQS|SQS]] for buffering and [[Topics/Cloud/AWS/EventBridge|EventBridge]] for event routing.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Elastic Load Balancing]]
- [[Topics/Cloud/AWS/API Gateway]]
- [[Topics/Cloud/AWS/EventBridge]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/sns/latest/dg/welcome.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
