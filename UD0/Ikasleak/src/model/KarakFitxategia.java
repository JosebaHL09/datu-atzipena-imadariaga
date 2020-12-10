/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hernandez.joseba
 */
public class KarakFitxategia {

    public static ObservableList<Ikaslea> datuakMemorianKargatu(String f) {
        ObservableList<Ikaslea> ikasleak = null;

        String csvFile = f;
        BufferedReader reader = null;
        String line = "";
        String csvSplitBy = ";";
        try {
            reader = new BufferedReader(new FileReader(csvFile));
            while ((line = reader.readLine()) != null) {
                String[] ikasleakArray = line.split(csvSplitBy);
                Ikaslea ik = new Ikaslea(Integer.parseInt(ikasleakArray[0]), ikasleakArray[1], ikasleakArray[2]);
                ikasleak.add(ik);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KarakFitxategia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KarakFitxategia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ikasleak;
    }

    public static boolean datuakFitxatganGorde(ObservableList l, String f) throws IOException {

        ObservableList<Ikaslea> ikasleak = l;
        String csvFile = f;

        for (int i = 0; i < ikasleak.size(); i++) {
            int zenbakia = ikasleak.get(i).getZenbakia();
            String izena = ikasleak.get(i).getIzena();
            String abizena = ikasleak.get(i).getAbizena1();
            String line = zenbakia + ";" + izena + ";" + abizena;
            
            FileWriter datuakIdatzi = new FileWriter(new File(f));
            datuakIdatzi.write(line + "\n");
            datuakIdatzi.close();
        }

        return true;
    }

}
