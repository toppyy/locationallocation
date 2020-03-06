# Location allocation and the p-median problem

The program allocates demand locations to supply facilities by:
* Calculating euclidean distances between locations
* Choosing a set of facilities that try to minimize the total cost (~distance) between demand locations and supply facilities.



## Instructions

Arguments:
* <i>out</i>: path of output file
* <i>p</i>: number of facilities to allocate to
* <i>dl</i>: file containing location of demand locations
* <i>pl</i>: file containing location of possible facility locations
* <i>a</i>: name of algorithm ("Naive"/"TeitzBart"/"GRIA")

The input files are expected to
* have a header row
* have at least three columns: ID, X and Y coordinates
* a possible fourth column to weight distances between demand and possible facility locations

## Running and example

Solve example problem with GRIA and write results to EXAMPLE.csv. Chooses a set of 7 facilities that minimize the cost from demand locations.

    git clone https://github.com/toppyy/locationallocation   
    cd locationallocation/locationallocation/
    gradle run --args "out EXAMPLE.csv p 7 a GRIA dl src/test/resources/testdata_1_demand_locations.csv pl src/test/resources/testdata_1_facility_locations.csv"

Alternatively run `gradle run` to start GUI and choose "Load example".

## Documentation

* [Design and aim of the project](https://github.com/toppyy/locationallocation/blob/master/documentation/design.md)

* [Implementation](https://github.com/toppyy/locationallocation/blob/master/documentation/implementation.md)

* [Javadoc](https://toppyy.github.io/projects/locationallocation_code_quality/javadoc/index.html)

* [Performance and quality tests](https://toppyy.github.io/projects/locationallocation_code_quality/qualityandperformance/QualityAndPerformance.html)

* [Test summary](https://toppyy.github.io/projects/locationallocation_code_quality/testsummary/index.html)

* [Test coverage](https://toppyy.github.io/projects/locationallocation_code_quality/jacoco/index.html)
  
* [Checkstyle](https://toppyy.github.io/projects/locationallocation_code_quality/checkstyle/main.html)


## Weekly reports

* [Weekly report #1](https://github.com/toppyy/locationallocation/blob/master/documentation/week1.md)
* [Weekly report #2](https://github.com/toppyy/locationallocation/blob/master/documentation/week2.md)
* [Weekly report #3](https://github.com/toppyy/locationallocation/blob/master/documentation/week3.md)
* [Weekly report #4](https://github.com/toppyy/locationallocation/blob/master/documentation/week4.md)
* [Weekly report #5](https://github.com/toppyy/locationallocation/blob/master/documentation/week5.md)
* [Weekly report #6](https://github.com/toppyy/locationallocation/blob/master/documentation/week6.md)