# Lossless Compression

## What it does

This folder contains the Lossless Compression resource from the cosmos master collection. The source implementations define the exact input contract, edge cases, and variant used here. The notes below identify those files and give a starting point for comparing implementations; verify behavioral details against the code before relying on them.

## Implementations

- [huffman.cpp — huffman](<https://github.com/OpenGenus/cosmos/blob/master/code/compression/src/lossless_compression/huffman/huffman.cpp>)
- [lzw.cpp — lempel_ziv_welch](<https://github.com/OpenGenus/cosmos/blob/master/code/compression/src/lossless_compression/lempel_ziv_welch/lzw.cpp>)
- [lzw.py — lempel_ziv_welch](<https://github.com/OpenGenus/cosmos/blob/master/code/compression/src/lossless_compression/lempel_ziv_welch/lzw.py>)

## Existing explanations and examples

- [Huffman Guide.md](<huffman/Huffman Guide.md>)
- [Lempel Ziv Welch Guide.md](<lempel_ziv_welch/Lempel Ziv Welch Guide.md>)

## Related topics

- [[Topics/Cosmos/Algorithms & Data Structures]]
