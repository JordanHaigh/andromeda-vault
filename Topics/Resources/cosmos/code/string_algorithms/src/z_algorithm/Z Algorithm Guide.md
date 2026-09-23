# Z Algorithm

## What it does

The Z-array records, at each position, the length of the longest substring beginning there that matches the string prefix. A maintained [left,right] match window lets the array be computed in O(n). Pattern matching can be reduced to computing Z on pattern + separator + text.

## Implementations

- [z_algorithm.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/z_algorithm/z_algorithm.cpp>)
- [z_algorithm.py](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/z_algorithm/z_algorithm.py>)
- [z_algorithm_z_array.cpp](<https://github.com/OpenGenus/cosmos/blob/master/code/string_algorithms/src/z_algorithm/z_algorithm_z_array.cpp>)

## Related topics

- [[Topics/Cosmos/String Algorithms]] · [[Topics/Cosmos/Algorithms & Data Structures]]
