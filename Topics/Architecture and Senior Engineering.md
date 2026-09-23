# Architecture and Senior Engineering

Senior engineering includes technical judgment, system-level ownership, communication, and improving the team's ability to deliver safely. It is not measured by diagram complexity or the number of technologies used.

## Core practices

- Connect technical work to a user or business outcome and make constraints explicit.
- Decompose work around stable ownership boundaries and identify data ownership, interfaces, and failure modes.
- Write lightweight architecture decision records for consequential tradeoffs; include context, alternatives, consequences, and revisit triggers.
- Manage migrations and compatibility across clients, services, schemas, and infrastructure while old and new versions coexist.
- Review designs and code for security, operability, accessibility, maintainability, and cost as well as local correctness.
- Use incident reviews to improve systems and processes without blame; assign follow-up work and verify it changes risk.
- Mentor through clear feedback, pairing, delegation, and creating shared technical understanding.
- Make technical debt visible with risk and carrying cost; schedule repayment where it affects delivery or reliability.

## Systems thinking

Use [[Topics/Cloud/Cloud Architecture]] to reason about deployment boundaries, [[Topics/Database Design]] for data lifecycle, [[Topics/Security]] for threat boundaries, [[Topics/Testing and Quality]] for evidence, [[Topics/Observability]] for production behavior, and [[Topics/Concurrency and Distributed Systems]] for time and failure. Tie these decisions to [[Topics/Deployment and Cloud]] and the provider notes under [[Topics/Cloud/Azure/Index]] and [[Topics/Cloud/AWS/Index]].

## Further study

- [AWS Well-Architected Framework](https://docs.aws.amazon.com/wellarchitected/latest/framework/welcome.html)
- [Azure Architecture Center](https://learn.microsoft.com/en-us/azure/architecture/)
- [Google Cloud Architecture Framework](https://cloud.google.com/architecture/framework)
- [NIST SSDF](https://csrc.nist.gov/Projects/ssdf)
