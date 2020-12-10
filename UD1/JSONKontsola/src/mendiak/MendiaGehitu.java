/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mendiak;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class MendiaGehitu {

    public static void main(String[] args) {

        try {
            JsonReader reader = Json.createReader(new FileReader("mendiak.json"));
            JsonStructure mendiak = reader.read();

            JsonObject mendiBerria = Json.createObjectBuilder()
                    .add("MENDIA", "MendiBerria")
                    .add("ALTUERA", 1000)
                    .add("PROBINTZIA", "Bizkaia")
                    .build();

            JsonArrayBuilder jab = Json.createArrayBuilder();

            for (int i = 0; i < mendiak.asJsonArray().size(); i++) {
                jab.add(mendiak.asJsonArray().get(i));
            }
            jab.add(mendiBerria);
            JsonArray modelAr = jab.build();
            System.out.println("Mendi berria sortu da!" + modelAr.toString());
            
            FileOutputStream out = new FileOutputStream("mendiak.json");            
            JsonWriter jsonWriter = Json.createWriter(out);
            jsonWriter.writeArray(modelAr);
            jsonWriter.close();
            out.close();
            System.out.println("mendiak.json artxiboa eguneratu da!");

        } catch (IOException ex) {
            Logger.getLogger(MendiaGehitu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
