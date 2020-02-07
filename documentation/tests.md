# Tests

Both GRIA and TeitzBart are tested on the following topics:    
1. Performance
2. Solution quality

The motivation for the first part is self-evident. The "quality" of the solution demands some explanation. It is tested due to the fact that neither algorithm is guarenteed to provide an optimal solution. However, both are said to provide "good results". The quality is tested by calculating two separate metrics:
* Probability of providing the optimal solution
* The difference to an optimal solutions cost

This can be only tested for cases in which an optimal solution can be found by the brute force algorithm.

