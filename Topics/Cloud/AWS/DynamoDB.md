# DynamoDB

> Managed key-value and document database designed around predictable access patterns and partitioned scaling.

## Engineering use and design

Work backward from queries to partition and sort keys. Plan hot-key mitigation, secondary indexes, conditional writes, transaction needs, TTL, streams, consistency, backups, and capacity/cost behavior. Avoid treating it as a drop-in relational database.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Elastic Load Balancing]]
- [[Topics/Cloud/AWS/API Gateway]]
- [[Topics/Cloud/AWS/EventBridge]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Introduction.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
