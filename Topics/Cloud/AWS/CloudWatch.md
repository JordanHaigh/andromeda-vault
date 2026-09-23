# CloudWatch

> AWS platform for metrics, logs, alarms, dashboards, and operational signals from services and applications.

## Engineering use and design

Choose metrics and dimensions that answer operational questions without uncontrolled cardinality. Define alert ownership, thresholds, runbooks, retention, log privacy, trace correlation, and cost limits. Relates to [[Topics/Observability]] and reliability engineering.

## Connect this service to the system

Think through the service boundary, identity, network path, data ownership, failure behavior, operational signals, and cost model together. Capture the chosen configuration in infrastructure as code and review it alongside application changes. Verify quotas, regional availability, support lifecycle, and pricing in the provider documentation for the target deployment.

## Related services

- [[Topics/Cloud/AWS/Index]]

## Official references

- [Official documentation](https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/WhatIsCloudWatch.html)
- [AWS documentation home](https://docs.aws.amazon.com/)
