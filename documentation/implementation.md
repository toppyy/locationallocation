# Implementation

Three algorithms solving the p-median problem are implemented: 
* Brute force 
* TeitzBart
* Global/Regional Interchange.

## Brute force

The brute force solutions is based on enumeration. The program enlists all possible combinations and chooses the one with the minimum overall cost.

## TeitzBart 

The algorithm chooses P first candidates as the initial solution. Then each candidate facility (not in initial solution) is used to replace each facility in the solution. If the swap provides a lower overall cost, it's chosen.

The implementation uses primitives, arrays and a set as data structures. The set is implemented as a part of the program.

## Global/Regional Interchange (GRIA)

The algorithm has two consecutive steps after creating an initial solution of the first P facilities:

* Global interchange for (initial) solution. 
    1. Remove the facility which's removal increases total cost the least.
    2. Add the facility which provides the greatest cost decrease.
    3. If the swap lowers total cost, let it be. Reiterate phases 1-3 until it does not.

* Regional interchange for (initial) solution: 
    1. Allocate each candidate facility to closest facility in solution.
    2. Swap each candidate with the facility it's allocated to. Count cost. 
    3. Find the swap that has the lowest total cost.

The implementation uses primitives, arrays, and a set as data structures. The set is implemented as a part of the program.