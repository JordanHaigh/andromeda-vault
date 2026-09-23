# IAM

> AWS identity and access management system for users, roles, policies, and permission evaluation across AWS resources.

## Engineering use and design

Prefer short-lived role credentials and workload identities. Scope permissions to actions and resources, use conditions where appropriate, separate trust policy from permission policy, and audit access paths. Review policy changes as production code. Connect to [[Topics/Security]].

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Elastic Load Balancing]]
- [[Topics/Cloud/AWS/API Gateway]]
- [[Topics/Cloud/AWS/EventBridge]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/iam/latest/UserGuide/introduction.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
