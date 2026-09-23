# Autokey Cipher

> Locally authored study notes based on the linked reference. They preserve the concepts needed for offline review; see the original page for the full article and its context.

**Reference:** [https://iq.opengenus.org/auto-key-cipher/](https://iq.opengenus.org/auto-key-cipher/)  
**Captured:** 2026-09-24

## Notes

The linked implementation describes a plaintext-autokey variant: convert letters to values 0–25, combine plaintext with a key stream modulo 26, then extend the key stream with plaintext rather than repeating a short keyword. Decryption reconstructs plaintext progressively so recovered plaintext can extend the stream. The article’s worked example uses modular addition and subtraction.

This is a classical teaching cipher, not secure encryption: it lacks modern key management and is vulnerable to statistical and known-plaintext analysis. Do not use it to protect data.

## Connected vault material

- [[Topics/Resources/cosmos/code/cryptography/src/autokey_cipher/Autokey Cipher Guide.md]]
