
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hernandez.joseba
 */
public class Ordenazioa {

    static ArrayList<Mendia> izena = new ArrayList<>();
    static ArrayList<Mendia> altuera = new ArrayList<>();

    public static void main(String[] args) {
        String erantzuna;
        hasieratu();

        Scanner in = new Scanner(System.in);

        System.out.println("Ordenatu.....");
        System.out.println("Erabaki zure aukera (Izena/Altuera)");
        erantzuna = in.next();

        switch (erantzuna) {
            case "Izena":
                for (int i = 0; i < izena.size(); i++) {
                    System.out.println(izena.get(i));
                }
                break;
            case "Altuera":
                for (int i = 0; i < altuera.size(); i++) {
                    System.out.println(altuera.get(i));
                }
                break;
            default:
                System.out.println("Sartu agertzen diren parametroak");
                break;
        }

    }

    public static void hasieratu() {

        String csvFile = "Mendiak.csv";
        BufferedReader reader = null;
        String line = "";
        String csvSplitBy = ";";

        try {
            reader = new BufferedReader(new FileReader(csvFile));
            while ((line = reader.readLine()) != null) {
                String[] mendiak = line.split(csvSplitBy);
                Mendia mendia = new Mendia(mendiak[0], Integer.parseInt(mendiak[1]), mendiak[2]);

                izena.add(mendia);
                altuera.add(mendia);

            }
            for(int i = 0;i<izena.size();i++){
              //Collections.sort(izena);
            }
            
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
