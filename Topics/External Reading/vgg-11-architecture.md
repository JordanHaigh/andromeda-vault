# VGG-11 Architecture

> Locally authored study notes based on the linked reference. They preserve the concepts needed for offline review; see the original page for the full article and its context.

**Reference:** [https://iq.opengenus.org/vgg-11/](https://iq.opengenus.org/vgg-11/)  
**Captured:** 2026-09-24

## Notes

VGG-11 is a convolutional image classifier built from small 3×3 convolutions, interleaved with max-pooling, followed by fully connected layers and a 1000-class output in the original ImageNet configuration. “11 layers” counts learned convolutional and fully connected layers, not pooling operations. The design shows how depth built from small filters can increase representational power, while the large fully connected layers make the original model memory intensive.

## Connected vault material

- [[Topics/Resources/cosmos/code/artificial_intelligence/src/VGG-11/Vgg 11 Guide.md]]
