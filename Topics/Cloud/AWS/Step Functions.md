# Step Functions

> Workflow orchestration service for coordinating distributed application tasks with explicit state transitions, retries, and error paths.

## Engineering use and design

Use when a multi-step process needs visible state, bounded retries, compensation, or long-running coordination. Define idempotent steps, timeout and retry policies, sensitive payload handling, execution history retention, and workflow versioning.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Index]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/step-functions/latest/dg/welcome.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
