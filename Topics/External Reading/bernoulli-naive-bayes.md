# Bernoulli Naive Bayes

> Locally authored study notes based on the linked reference. They preserve the concepts needed for offline review; see the original page for the full article and its context.

**Reference:** [https://iq.opengenus.org/bernoulli-naive-bayes/](https://iq.opengenus.org/bernoulli-naive-bayes/)  
**Captured:** 2026-09-24

## Notes

Bernoulli Naive Bayes models each feature as present or absent. For class c and binary feature vector x, its score is proportional to P(c) times the product over features of P(xᵢ|c); absent features contribute as well as present features. This differs from Multinomial Naive Bayes, which models counts. Smoothing prevents an unseen feature/class combination from forcing the whole likelihood to zero. The conditional-independence assumption is often false, but the classifier can still be effective, especially for binary document features.

## Connected vault material

- [[Topics/Resources/cosmos/code/artificial_intelligence/src/bernoulli_naive_bayes/Bernoulli Naive Bayes Guide.md]]
