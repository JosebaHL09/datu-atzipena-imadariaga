/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutoriala;

import java.io.FileNotFoundException;
import java.io.StringReader;
import javax.json.Json;
import javax.json.stream.JsonParser;

/**
 *
 * @author hernandez.joseba
 */
public class StreamIrakurri {

    public static void main(String[] args) {
        try{
            JsonParser parser = Json.createParser(new StringReader("datuberriak.json"));
        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            switch (event) {
                case START_ARRAY:
                case END_ARRAY:
                case START_OBJECT:
                case END_OBJECT:
                case VALUE_FALSE:
                case VALUE_NULL:
                case VALUE_TRUE:
                    System.out.println(event.toString());
                    break;
                case KEY_NAME:
                    System.out.print(event.toString() + " "
                            + parser.getString() + " - ");
                    break;
                case VALUE_STRING:
                case VALUE_NUMBER:
                    System.out.println(event.toString() + " "
                            + parser.getString());
                    break;
            }
        }
        }catch(Exception ex){
            System.out.println("Fitxategia ez da aurkitu");
        }
        
    }
}

