/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unieibar;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hernandez.joseba
 */
public class Konbertsoreak {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Mendia> mendienAl = new ArrayList<>();

    public static void main(String[] args) {
        String emaitza = "", izena, probintzia,file;
        int altuera;
        Scanner in = new Scanner(System.in);
        System.out.println("===========================================");
        System.out.println("Konbertsorea");
        System.out.println("===========================================");
        while (!emaitza.equals("ez")) {

            System.out.print("\tSartu mendiaren izena:");
            izena = in.next();
            System.out.print("\tSartu mendiaren altuera:");
            altuera = in.nextInt();
            System.out.print("\tSartu mendiaren probintzia:");
            probintzia = in.next();
            System.out.println("===========================================");
            mendienAl.add(new Mendia(izena, altuera, probintzia));
            System.out.println("Mendi gehiago satu nahi dituzu?(bai/ez)");
            emaitza = in.next();
            emaitza.toLowerCase();
        }
        for (int i = 0; i < mendienAl.size(); i++) {
            System.out.println("Izena: " + mendienAl.get(i).getIzena() + ", Altuera: " + mendienAl.get(i).getAltuera() + ", Probintzia: " + mendienAl.get(i).getProbintzia());
        }

        System.out.println("");
        System.out.print("Erabaki gorde nahi duzun formatua(CSV/XML/JSON): ");
        emaitza = in.next();
        emaitza.toLowerCase();
        System.out.print("Fitxategiaren izena jarri(bakarrik izena): ");
        file = in.next();
        switch (emaitza) {
            case "csv":
                file += ".csv";
                csvFitxategia(file);
                break;
            case "xml":
                file += ".xml";
                csvFitxategia(file);
                break;
            case "json":
                file += ".json";
                csvFitxategia(file);
                break;
            default:
                System.out.println("Fitxategi mota hori ez dago.");
                break;
        }

    }
    
    public static void csvFitxategia(String s){
        String izena,probintzia,linea;
        int altuera;
        try {
            FileWriter writer = new FileWriter(s);
            for (int i = 0; i < mendienAl.size(); i++) {
                izena = mendienAl.get(i).getIzena();
                probintzia = mendienAl.get(i).getProbintzia();
                altuera = mendienAl.get(i).getAltuera();
                linea = izena + ";" + altuera + ";" + probintzia + "\n";
                writer.write(linea);
                System.out.println("Mendi berria sartu da listan!");
            }
            writer.close();
            System.out.println("Mendi guztiak sartu dira");
        } catch (IOException ex) {
            Logger.getLogger(Konbertsoreak.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void xmlFitxategia(String s){
        
    }
    public static void jsonFitxategia(String s){
        
    }

}
