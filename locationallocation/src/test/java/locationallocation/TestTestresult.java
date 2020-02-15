package locationallocation;

import locationallocation.Qualitytest.Testresult;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestTestresult {


    @Test public void TestGetters() {

        int p = 5, candidates = 10, demandlocations = 20;
        double cost = 123.4;
        long timeElapsed = 5567;
        String algorithmName = "TB";
        
        Testresult result = new Testresult(p,candidates,demandlocations,cost,timeElapsed,algorithmName);
        

        assertEquals("Getter for P does not work", p, result.getP() );
        assertEquals("Getter for candidates does not work", candidates, result.getCandidates() );
        assertEquals("Getter for demandlocations does not work", demandlocations, result.getDemandlocations() );
        assertEquals("Getter for cost does not work", cost, result.getCost(), 0.0001 );
        assertEquals("Getter for timeElapsed does not work", timeElapsed, result.getTimeElapsed() );
        assertEquals("Getter for algorithmName does not work", algorithmName, result.getAlgorithmName() );

        




    }

}