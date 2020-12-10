/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonWriter;

/**
 *
 * @author hernandez.joseba
 */
public class JsonFitxategia {

    public static ObservableList<Ikaslea> datuakMemorianKargatu(String f) {
        ObservableList<Ikaslea> ikasleakLista = null;
        Ikaslea ik;
        int zenbakia;
        String izena, abizena;
        String jsonFile = f;
        JsonReader reader;

        try {
            reader = Json.createReader(new FileReader("ikasleak.json"));
            JsonStructure ikasleak = reader.read();

            JsonArrayBuilder jab = Json.createArrayBuilder();

            for (int i = 0; i < ikasleak.asJsonArray().size(); i++) {
                jab.add(ikasleak.asJsonArray().get(i));
            }
            JsonArray modelAr = jab.build();
            for (int i = 0; i < modelAr.size(); i++) {
                zenbakia = Integer.parseInt(modelAr.getJsonObject(i).getJsonString("zenbakia").toString());
                izena = modelAr.getJsonObject(i).getJsonString("izena").toString();
                abizena = modelAr.getJsonObject(i).getJsonString("abizena").toString();
                ik = new Ikaslea(zenbakia, izena, abizena);
                ikasleakLista.add(ik);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(JsonFitxategia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ikasleakLista;
    }

    public static boolean datuakFitxatganGorde(ObservableList<Ikaslea> l, String f) throws IOException {

        JsonReader reader = Json.createReader(new FileReader(f));
        JsonStructure ikasleak = reader.read();
        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (int i = 0; i < ikasleak.asJsonArray().size(); i++) {
            jab.add(ikasleak.asJsonArray().get(i));
        }
        jab.add(ikasleak);
        for (int i = 0; i < l.size(); i++) {
            int zenbakia = l.get(i).getZenbakia();
            String izena = l.get(i).getIzena();
            String abizena = l.get(i).getAbizena1();

            JsonObject ikaslea = Json.createObjectBuilder().add("zenbakia", zenbakia).add("izena", izena).add("abizena", abizena).build();
            jab.add(ikaslea);
        }
        JsonArray modelAr = jab.build();
        FileOutputStream out = new FileOutputStream("ikasleak.json");
        JsonWriter jsonWriter = Json.createWriter(out);
        jsonWriter.writeArray(modelAr);
        jsonWriter.close();
        out.close();

        return true;
    }

}
