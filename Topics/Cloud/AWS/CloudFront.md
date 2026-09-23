# CloudFront

> Content delivery network and edge distribution service for caching and delivering web content and APIs close to users.

## Engineering use and design

Specify cache keys, TTL, invalidation strategy, origin protection, TLS, signed access where needed, request forwarding, and failure behavior. Ensure private or dynamic origin access is deliberate; a CDN does not replace application authorization.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Elastic Load Balancing]]
- [[Topics/Cloud/AWS/API Gateway]]
- [[Topics/Cloud/AWS/EventBridge]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/AmazonCloudFront/latest/DeveloperGuide/Introduction.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
