# Observability and Production Debugging

Observability is the ability to infer a system's internal behavior from its outputs. Application logs, metrics, traces, profiles, and events should help answer concrete operational questions while controlling privacy and cost.

## Signals

- **Metrics** reveal rates, errors, saturation, and trends; use stable dimensions and avoid unbounded cardinality.
- **Logs** preserve contextual events; use structured fields, severity, correlation identifiers, and retention appropriate to sensitivity.
- **Traces** show a request across service boundaries; propagate trace context and record meaningful spans without exposing secrets.
- **Profiles and runtime diagnostics** explain resource consumption and concurrency behavior when aggregate signals are insufficient.

## Make signals actionable

Define service-level indicators around user-visible outcomes and service-level objectives with an error budget. Alert on symptoms requiring action, not every internal fluctuation. Link alerts to dashboards, runbooks, and ownership. Correlate application and infrastructure context, deployment versions, and dependency health. Review noisy alerts and missing signals after incidents.

## Related topics

- [[Topics/Deployment and Cloud]], [[Topics/Concurrency and Distributed Systems]], [[Topics/Testing and Quality]]
- [[Topics/Cloud/Azure/Azure Monitor and Application Insights]], [[Topics/Cloud/AWS/CloudWatch]]
- [[Topics/Cloud/Azure/Service Bus]], [[Topics/Cloud/AWS/SQS]], [[Topics/Cloud/AWS/EventBridge]]
