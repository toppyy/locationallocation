package locationallocation;

import locationallocation.Qualitytest.Reporter;
import locationallocation.Qualitytest.Testresult;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestReporter {

    @Test public void resultsReportedCorrectly() {
        
        Reporter report = new Reporter();       
        String[] algorithms = { "TB", "GRIA" };
        int[] limits = {2, 10};
        int iterations = 13;

        int resultSize = (((limits[1] + 1)-limits[0]) * algorithms.length * iterations);

        Testresult[] results = new Testresult[ resultSize ];

        report.setPlimits(limits);
        results = report.runTests(algorithms,10,20,iterations);


        assertEquals("Test results reported incorrectly, first P", results[0].getP(), 2 );
        assertEquals("Test results reported incorrectly, first algorithm", results[0].getAlgorithmName(), "TB" );
        assertEquals("Test results reported incorrectly, second algorithm", results[1].getAlgorithmName(), "GRIA" );

        assertEquals("Test results reported incorrectly, last P", results[resultSize-1].getP(), 10 );
        assertEquals("Test results reported incorrectly, last algorithm", results[resultSize-1].getAlgorithmName(), "GRIA" );
        assertEquals("Test results reported incorrectly, last candidate ", results[resultSize-1].getCandidates(), 10 );

    }

}