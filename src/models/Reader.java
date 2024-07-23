package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Reader {
    //
    public static HashMap<String, String> readFromJson(String path) {
        //
        HashMap<String, String>lines = new HashMap<String, String>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            lines.put("line", in.readLine());

            // closing
            in.close();

        }catch(Exception e){
            System.out.println("Error in reading Json file");
        }

        // return 
        return lines;
    }


    // read file as array list
    public static ArrayList<String> readTextAsList(String path) {
        ArrayList<String>array = new ArrayList<String>();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            
           
            // String line = reader.readLine();
            while (!reader.readLine().isEmpty()) {
                array.add(reader.readLine());
            }

            reader.close();
        } catch(Exception e){
            System.out.println("Error: " + e);
            return null;
        }
        return  array;
    }

    public static String readFromText() throws FileNotFoundException{

        BufferedReader in = new BufferedReader(new FileReader("foo.txt"));
        // read from text file and return as string
        return null;
    }
}
