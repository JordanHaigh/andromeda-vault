# EventBridge

> Event bus and routing service for matching events to targets across AWS services, applications, and supported SaaS integrations.

## Engineering use and design

Model event envelopes and ownership, rule filters, retries, dead-letter queues, schema compatibility, replay, and cross-account permissions. Consumers must tolerate at-least-once delivery and should be idempotent. Compare with SNS fan-out and SQS work queues.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Elastic Load Balancing]]
- [[Topics/Cloud/AWS/API Gateway]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/eventbridge/latest/userguide/eb-what-is.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
