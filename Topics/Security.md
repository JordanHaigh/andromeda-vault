# Application and Platform Security

Security engineering reduces risk across identities, data, software supply chains, runtime environments, and operational processes. It works best as a continuous design and delivery concern rather than a final review.

## Start with threats and trust boundaries

Map assets, actors, entry points, data flows, trust boundaries, and failure impact. Identify abuse cases such as account takeover, privilege escalation, injection, data exposure, denial of service, and supply-chain compromise. Prioritize mitigations based on likelihood, impact, and the system's exposure.

## Build controls into design

Use least privilege, strong authentication, object-level authorization, secure defaults, input validation, output encoding, safe query construction, explicit CORS and CSRF decisions, secret management, encryption in transit and at rest, and auditable administrative actions. Keep dependency provenance and patching visible. Rate-limit expensive endpoints and avoid leaking sensitive data in logs, errors, traces, and analytics.

## Operate securely

Define vulnerability intake, incident response, key rotation, backup access, recovery, retention, and security logging. Make security tests part of CI, review permissions as code, and rehearse credential compromise and service isolation. Cloud identity pages: [[Topics/Cloud/Azure/Microsoft Entra ID]], [[Topics/Cloud/Azure/Key Vault]], [[Topics/Cloud/AWS/IAM]], [[Topics/Cloud/AWS/KMS and Secrets Manager]].

## References

- [OWASP Application Security Verification Standard](https://owasp.org/www-project-application-security-verification-standard/)
- [OWASP Top 10](https://owasp.org/www-project-top-ten/)
- [NIST Secure Software Development Framework](https://csrc.nist.gov/Projects/ssdf)
