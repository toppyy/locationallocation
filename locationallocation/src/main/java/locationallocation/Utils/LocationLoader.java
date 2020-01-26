package locationallocation.Utils;

import java.io.File; 

import java.util.Scanner; 
import java.io.FileNotFoundException; 

import java.util.ArrayList;
import java.util.List;
import java.io.*;

import locationallocation.Utils.Location;

public class LocationLoader  {

    private File file;
    private boolean header;
    
    public LocationLoader(String path, boolean header)  {
        this.file =  new File(path); 
        this.header = header;
    }

    public ArrayList<String> readFile() throws FileNotFoundException {
        
        Scanner sc = new Scanner(file); 

        ArrayList<String> rows = new ArrayList<String>();

        while (sc.hasNextLine()) {
            rows.add( sc.nextLine() );
        }

        sc.close();






        return rows;
    }


    public Location[] loadAsLocations() {

        try { 

            ArrayList<String> lines = readFile();
            
            // If header is true, skip first row
            int startRow = 0;
            if (this.header)  {
                startRow=1;
            }

            Location[] locations = new Location[lines.size()-startRow];


            for (int i = startRow; i < lines.size(); i++ ) {
                String[] columns = lines.get(i).split(";");

                double x = Double.parseDouble(columns[1]);
                double y = Double.parseDouble(columns[2]);

                locations[i-startRow] = new Location(x,y);
            }

            return locations;

        } catch (IOException e) { 
      
          System.out.println("Problem with reading the file");
          e.printStackTrace(); 
          Location[] rtrn = new Location[0];
          return rtrn;
        } 

    }




}