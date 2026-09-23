# SQS

> Managed message queues for decoupling producers and consumers and buffering work during load variation or downstream outages.

## Engineering use and design

Choose standard or FIFO semantics according to ordering and deduplication requirements. Set visibility timeout, retention, redrive/dead-letter policy, long polling, batch size, and concurrency. Consumers should acknowledge only after durable work and safely handle redelivery.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Elastic Load Balancing]]
- [[Topics/Cloud/AWS/API Gateway]]
- [[Topics/Cloud/AWS/EventBridge]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/welcome.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
