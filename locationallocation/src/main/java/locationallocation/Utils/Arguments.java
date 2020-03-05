
package locationallocation.Utils;

/**
 *  A parser for arguments given trough cmd.
 */


public class Arguments {

    private String locDemand, locPossible, p, a, out;



    /**
     * Parses arguments given through cmd.
     * @param args
     */
    public Arguments(final String[] args) {

        this.parseArguments(args);

    }

    private void setArgument(final String which, final String value) {

        if (which.equals("dl")) {
            this.locDemand = value;
        }
        if (which.equals("pl")) {
            this.locPossible = value;
        }
        if (which.equals("p")) {
            this.p = value;
        }
        if (which.equals("a")) {
            this.a = value;
        }
        if (which.equals("out")) {
            this.out = value;
        }
    }

    /**
     * Parse arguments. 
     * @param args Arguments given as an array of Strings in which odd indexes hold keys and even indexes values.
     */
    
    private void parseArguments(final String[] args) {

        if (args.length % 2 != 0) {
            System.out.println("Number of arguments (key-value -pairs) is not even.");
            return;
        }

        for (int i = 0; i < args.length; i += 2) {
            
            this.setArgument(args[i], args[i + 1]);

        }
    }


    public final boolean allRequiredArgumentsGiven() {


        if (
            this.locDemand == null
            |
            this.locPossible == null
            |
            this.p == null
            |
            this.a == null
            |
            this.out == null
            ) {
            return false;
        }

        return true;
       
    }

    public final String getAlgorithm() {
        return this.a;
    }
    public final String getOutputPath() {
        return this.out;
    }
    public final String getP() {
        return this.p;
    }
    public final String getDemandLocationPath() {
        return this.locDemand;
    }
    public final String getPossibleLocationPath() {
        return this.locPossible;
    }

}