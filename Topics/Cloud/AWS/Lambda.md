# Lambda

> Event-driven serverless compute invoked by AWS services, HTTP integration, schedules, and other event sources.

## Engineering use and design

Design for bounded execution, retries, duplicate delivery, concurrency quotas, cold starts, and least-privilege execution roles. Keep durable state outside the function and make side effects idempotent where event sources may redeliver.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Elastic Load Balancing]]
- [[Topics/Cloud/AWS/API Gateway]]
- [[Topics/Cloud/AWS/EventBridge]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/lambda/latest/dg/welcome.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
