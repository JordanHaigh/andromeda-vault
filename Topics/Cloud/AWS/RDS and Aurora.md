# RDS and Aurora

> Managed relational database services for common SQL engines, plus Aurora-compatible database engines built for AWS-managed operation.

## Engineering use and design

Choose engine and availability model based on compatibility and operational needs. Design indexes, connection pooling, migrations, backup retention, failover tests, read scaling, parameter management, and recovery objectives. See [[Topics/Database Design]].

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Elastic Load Balancing]]
- [[Topics/Cloud/AWS/API Gateway]]
- [[Topics/Cloud/AWS/EventBridge]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/Welcome.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
