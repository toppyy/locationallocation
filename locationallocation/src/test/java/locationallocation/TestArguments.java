package locationallocation;


import locationallocation.Utils.Arguments;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestArguments {

    

    private static String[] splitString(final String input) {
        return input.split(" ");
    }

    
    @Test
    public void correctArgumentSet() {


        Arguments args = new Arguments(splitString("out file.csv a GRIA p 6 dl dl.csv pl pl.csv"));

        assertEquals("6", args.getP());
        assertEquals("file.csv", args.getOutputPath());
        assertEquals("GRIA", args.getAlgorithm());
        assertEquals("dl.csv", args.getDemandLocationPath());
        assertEquals("pl.csv", args.getPossibleLocationPath());
    
        assertTrue(args.allRequiredArgumentsGiven());

    }

    @Test
    public void inCorrectArgumentSet() {


        Arguments args = new Arguments(splitString("output file.csv a GRIA p 6 dl dl.csv pl pl.csv"));

        assertEquals("6", args.getP());
        assertEquals(null, args.getOutputPath());
        assertEquals("GRIA", args.getAlgorithm());
        assertEquals("dl.csv", args.getDemandLocationPath());
        assertEquals("pl.csv", args.getPossibleLocationPath());
    
        assertFalse(args.allRequiredArgumentsGiven());


        Arguments args2 = new Arguments(splitString("output file.csv z a GRIA p 6 dl dl.csv pl pl.csv"));

        assertEquals(null, args2.getP());
        assertEquals(null, args2.getOutputPath());
        assertEquals(null, args2.getAlgorithm());
        assertEquals(null, args2.getDemandLocationPath());
        assertEquals(null, args2.getPossibleLocationPath());

        assertFalse(args2.allRequiredArgumentsGiven());

    }

}