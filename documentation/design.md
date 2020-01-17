# Design and aim of project

The purpose of the project is provide an solution for the p-median problem. The problem is best described by an example: assume we can decide between ten cities on where to place five hospitals. Also assume that we know the location of the population within the region the cities are located. In which cities should we place the hospitals in order to minimize the (average) distance between the hospital and population?

The p-median problem is considered as *NP*-hard. However, heuristic algorithms giving good, but not necessarily optimal, solutions exists. The object is implement two of these in Java: The Teitz-Bart Heuristic (TB) and The Global Regional Interchange Algorithm (GRIA). Also, for benchmarking, an optimal algorithm (using enumeration) for small datasets is implemented for benchmarking purposes.

### Input:

* *p*: a positive integer. The number of facilities to be located ("hospitals" in the example)
* *candidate points*: Possible locations for a single facility ("city" in the example). A set of coordinate pairs.
* *demand points*: A set of coordinate pairs representing regions (zip codes, for example). Population count for each region is used as a weight.

Points (candidate/demand) are given as CSV-files.

### Output: 

A CSV-file containing the demand points each allocated to a single candidate point.

### Data structures and complexity

Both (TB & GRIA) seem like they could be written using only simple datastructures such as arrays. As for complexity of the solutions, a worst case analysis is yet to be done.

## Sources

https://pdfs.semanticscholar.org/f2ee/2b6b213621d121ad11e809a1b1e10a7a47b6.pdf  
https://onlinelibrary.wiley.com/doi/abs/10.1111/tgis.12322  
http://www.trash.net/~ck/mastersthesis/  


