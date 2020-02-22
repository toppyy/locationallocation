

package locationallocation.Utils;

import java.io.FileWriter;
import java.io.IOException;
/**
 * Write into a flat file.
 */

public final class Writer {


    /**
     * Hold path to file.
     */

    private FileWriter fileWriter;


    /**
     * Used to write strings to flatfiles.
     * @param pathInput Path to write to.
     * @throws IOException Output error
     */

    public Writer(final String pathInput) throws IOException {
        this.fileWriter = new FileWriter(pathInput);
    }

    /**
     * Used to write strings to flatfiles.
     */

    public Writer() {
        
    }


    /**
     * Writes an array of strings to given path.
     * @param pathInput Path to write to.
     * @param lines Strings to write
     * @throws IOException Output error
     */

    public static void writeLines(final String pathInput, final String[] lines) throws IOException  {

        FileWriter fileWriter = new FileWriter(pathInput);
        
        for (String st : lines) {
            fileWriter.write(st);
        }
        fileWriter.close();
    }

    /**
     * Writes a string.
     * @param st String to write
     * @throws IOException Output error
     */

    public void write(final String st) throws IOException  {
        
        this.fileWriter.write(st);
        
    }
    /**
     * Close connection to file.
     * @throws IOException Output error
     */

    public void close() throws IOException {
        this.fileWriter.close();
    }
}