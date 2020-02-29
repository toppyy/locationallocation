

package locationallocation.Utils;

import java.io.FileWriter;
import java.io.IOException;
/**
 * Write into a flat file.
 */

public final class Writer {
    
    private Writer() {

    }

  
    /**
     * Writes an array of strings to given path.
     * @param pathOutput Path to write to.
     * @param lines Strings to write
     * @throws IOException Output error
     */

    public static void writeLines(final String pathOutput, final String[] lines) throws IOException  {

        FileWriter fileWriter = new FileWriter(pathOutput);
        
        for (String st : lines) {
            fileWriter.write(st);
        }
        fileWriter.close();
        System.out.println("Wrote results to: " + pathOutput);
    }

}