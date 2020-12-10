/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutoriala;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;

/**
 *
 * @author hernandez.joseba
 */
public class ZerotikSortu {

    public static void main(String[] args) {
        try {
            JsonObject m1 = Json.createObjectBuilder()
                    .add("MENDIA", "Aketegi")
                    .add("ALTUERA", 1548)
                    .add("PROBINTZIA", "Gipuzkoa")
                    .build();
            JsonObject m2 = Json.createObjectBuilder()
                    .add("MENDIA", "Albertia")
                    .add("ALTUERA", 868)
                    .add("PROBINTZIA", "Araba")
                    .build();

            JsonArrayBuilder jab = Json.createArrayBuilder();
            jab.add(m1);
            jab.add(m2);

            JsonArray modelAr = jab.build();
            System.out.println(modelAr.toString());
            FileOutputStream out = new FileOutputStream("mendiak.json");
            JsonWriter jsonWriter = Json.createWriter(out);
            jsonWriter.writeArray(modelAr);
            jsonWriter.close();
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ZerotikSortu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ZerotikSortu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
