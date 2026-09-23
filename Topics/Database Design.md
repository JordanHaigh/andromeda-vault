# Database Design

Database design aligns persistent data structures with business invariants, access patterns, consistency needs, and operational constraints. The right model makes invalid states difficult to represent and keeps important reads and writes understandable.

## Relational fundamentals

Identify entities, keys, relationships, optionality, uniqueness, and lifecycle. Normalize to avoid update anomalies, then denormalize only for a measured read or scale need with a plan to preserve consistency. Use foreign keys, check constraints, and transactions to enforce invariants close to the data. Treat nullability and time semantics as deliberate domain decisions.

## Performance and operations

Derive indexes from query predicates, sort order, selectivity, and write volume; inspect real query plans. Bound queries and avoid unbounded scans. Account for connection pooling, lock contention, isolation anomalies, and transaction duration. Schema migrations need an expand/migrate/contract path when old and new application versions overlap. Practice backup restore, point-in-time recovery, and failover rather than assuming configured backups are usable.

## Distributed and non-relational choices

Select storage from consistency and access requirements. Partitioning/sharding introduces routing and rebalancing complexity; replicas affect freshness and failover; event streams imply replay and schema evolution. NoSQL models should begin with access patterns, partition keys, and operational limits. Define ownership of cross-service data and avoid distributed transactions where asynchronous workflows with explicit compensation fit better.

## Related topics

- [[Topics/Cloud/Azure/Azure SQL Database]], [[Topics/Cloud/Azure/Azure Cosmos DB]], [[Topics/Cloud/AWS/RDS and Aurora]], [[Topics/Cloud/AWS/DynamoDB]]
- [[Topics/Concurrency and Distributed Systems]], [[Topics/Security]], [[Topics/Testing and Quality]], [[Topics/Observability]]
