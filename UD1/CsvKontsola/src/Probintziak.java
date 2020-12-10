
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
public class Probintziak {

    public static void main(String[] args) {

        String csvFile = "Mendiak.csv";
        BufferedReader reader = null;
        String line = "";
        String csvSplitBy = ";";

        try {
            FileWriter bizkaia = new FileWriter("bizkaia.csv");
            FileWriter araba = new FileWriter("araba.csv");
            FileWriter gipuzkoa = new FileWriter("gipuzkoa.csv");
            reader = new BufferedReader(new FileReader(csvFile));
            while ((line = reader.readLine()) != null) {
                String[] mendiak = line.split(csvSplitBy);

                if (mendiak[2].equals("Araba")) {
                    araba.write(mendiak[0] + ";" + mendiak[1] + ";" + "Araba \n");
                } else if (mendiak[2].equals("Bizkaia")) {
                    bizkaia.write(mendiak[0] + ";" + mendiak[1] + ";" + "Bizkaia \n");
                } else if (mendiak[2].equals("Gipuzkoa")) {
                    gipuzkoa.write(mendiak[0] + ";" + mendiak[1] + ";" + "Gipuzkoa \n");
                }

                //System.out.println("Mendia [izena= " + mendiak[0] + " ,altuera= " + mendiak[1] + ", probintzia= " + mendiak[2] + "]");
            }
            araba.close();
            bizkaia.close();
            gipuzkoa.close();

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
