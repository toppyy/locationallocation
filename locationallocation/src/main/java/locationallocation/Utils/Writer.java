/**
 * Write into a flat file.
 */

package locationallocation.Utils;

import java.io.FileWriter;
import java.io.IOException;


public final class Writer {


    /**
     * Hold path to file.
     */

    private FileWriter fileWriter;


    /**
     * Writes an array of strings to given path.
     * @param pathInput Path to write to.
     * @throws IOException Output error
     */

    public Writer(final String pathInput) throws IOException {
        this.fileWriter = new FileWriter(pathInput);
    }

    /**
     * Writes an array of strings to given path.
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