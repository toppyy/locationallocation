package locationallocation.Utils;

import java.io.File; 

import java.util.Scanner; 
import java.io.FileNotFoundException;

/**
 * Reads Locations from flatfiles.
 */


public class LocationLoader  {
    
    private File file;
    private boolean header;
    
    /**
     * Reads flat files into sets of locations.
     * @param pathInput path of flatfile
     * @param headerInput true/false. Does the file have a header? If true, first row is not read.
     */
    
    public LocationLoader(final String pathInput, final boolean headerInput)  {
        this.file =  new File(pathInput); 
        this.header = headerInput;
    }
    

    
    /** 
     * Reads a flatfile and returns rows.
     * @return String[] Rows as an array.
     * @throws FileNotFoundException
     */
    private ArrayList readFile() throws FileNotFoundException {
        
        Scanner sc = new Scanner(file); 

        ArrayList rows = new ArrayList();

        while (sc.hasNextLine()) {
            rows.add(sc.nextLine());
        }

        sc.close();

        return rows;
    }

   


    
    /** 
     * Read a file as locations.
     * Reads a flat file into a list of rows. Converts each row into a Location.
     * Expected format is:
     *  id;x;y;w
     * Content: id for location, x- and y-coordinates and optional weight.
     * 
     * @return Location[]
     * @throws FileNotFoundException
     */
    public Location[] loadAsLocations() throws FileNotFoundException {


        ArrayList lines = readFile();
        
        // If header is true, skip first row
        int startRow = 0;
        if (this.header)  {
            startRow = 1;
        }

        Location[] locations = new Location[lines.size() - startRow];


        for (int i = startRow; i < lines.size(); i++) {

            String[] columns = lines.get(i).split(";");

            String id = columns[0];

            double x = Double.parseDouble(columns[1]);
            double y = Double.parseDouble(columns[2]);

            double w = 1;

            
            if (columns.length > 3) {
                w = Double.parseDouble(columns[3]);
            }

            locations[i - startRow] = new Location(id, x, y, w);
        }

        return locations;

    }

}