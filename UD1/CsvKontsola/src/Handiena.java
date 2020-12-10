
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hernandez.joseba
 */
public class Handiena {

    public static void main(String[] args) {

        String csvFile = "Mendiak.csv";
        BufferedReader reader = null;
        String line = "";
        String csvSplitBy = ";";

        int handiena = 0;
        String emaitza="";

        try {
            reader = new BufferedReader(new FileReader(csvFile));
            while ((line = reader.readLine()) != null) {
                String[] mendiak = line.split(csvSplitBy);
                

                if (handiena < Integer.parseInt(mendiak[1])) {
                    handiena = Integer.parseInt(mendiak[1]);

                    emaitza = "Mendia [izena= " + mendiak[0] + " ,altuera= " + handiena + ", probintzia= " + mendiak[2] + "]";
                    
                }

                //System.out.println("Mendia [izena= " + mendiak[0] + " ,altuera= " + mendiak[1] + ", probintzia= " + mendiak[2] + "]");
            }
            System.out.println(emaitza);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
