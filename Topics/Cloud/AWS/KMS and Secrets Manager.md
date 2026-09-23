# KMS and Secrets Manager

> AWS managed key services and secret lifecycle service used to protect credentials, tokens, and encryption keys.

## Engineering use and design

Choose key ownership and rotation policy, grant narrowly scoped access, and plan application rotation without downtime. Avoid embedding secret values in images, logs, or source control. Define behavior when secret retrieval or key use is temporarily unavailable.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Index]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/kms/latest/developerguide/overview.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
