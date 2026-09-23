# API Gateway

> Managed API front door for HTTP, REST, and WebSocket API patterns, with routing, authorization integration, throttling, and backend integration.

## Engineering use and design

Treat API Gateway as a public contract boundary: define authentication, authorization, validation, quotas, CORS, request/response mapping, timeouts, payload limits, logging, and compatibility rules. Relates to [[Topics/Web/RESTful APIs]] and [[Topics/Web/GraphQL]].

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Elastic Load Balancing]]
- [[Topics/Cloud/AWS/EventBridge]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/apigateway/latest/developerguide/welcome.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
