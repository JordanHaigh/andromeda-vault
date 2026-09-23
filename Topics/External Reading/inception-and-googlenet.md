# Inception and GoogLeNet

> Locally authored study notes based on the linked reference. They preserve the concepts needed for offline review; see the original page for the full article and its context.

**Reference:** [https://iq.opengenus.org/inception-pre-trained-cnn-model/](https://iq.opengenus.org/inception-pre-trained-cnn-model/)  
**Captured:** 2026-09-24

## Notes

An Inception module evaluates several receptive-field sizes in parallel, commonly 1×1, 3×3, and 5×5 convolutions plus pooling, then concatenates their feature maps. 1×1 convolutions before expensive filters reduce channel depth and computation. GoogLeNet (Inception v1) builds a deep network from these modules; later versions factorize convolutions, such as replacing a 5×5 operation with two 3×3 operations, to reduce cost while preserving useful capacity.

## Connected vault material

- [[Topics/Resources/cosmos/code/artificial_intelligence/src/Inception_Pre-trained_Model/Inception_model.md]]
