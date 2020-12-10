/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import global.Global;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hernandez.joseba
 */
public class MariaDb {

    public static Connection connect() {

        String url = "jdbc:mysql://" + Global.SERVER + "/" + Global.DATUBASEA;

        Connection con = null;
        try {
            con = DriverManager.getConnection(url, Global.user, Global.pw);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static ObservableList<Ikaslea> datuakMemorianKargatu() {
        ObservableList<Ikaslea> ikasleak = FXCollections.observableArrayList();

        String sql = "SELECT * FROM ikasleak";
        int zenbakia;
        String izena, abizena;
        try ( Connection conn = connect();  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                zenbakia = rs.getInt("zenbakia");
                izena = rs.getString("izena");
                abizena = rs.getString("abizena");
                ikasleak.add(new Ikaslea(zenbakia, izena, abizena));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ikasleak;
    }

    public static boolean ezabatu(Ikaslea ika) {
        boolean aurkitua = false;
        String sql = "Select * from ikasleak";
        try ( Connection conn = connect();  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if (ika.getZenbakia() == rs.getInt("zenbakia")) {
                    aurkitua = true;
                }
            }
            if (aurkitua) {
                String sqez = "Delete from ikasleak where zenbakia = " + ika.getZenbakia();
                stmt.execute(sqez);
            } else {
                System.out.println("Ikasle hori ez dago datubasean");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    public static boolean gehitu(Ikaslea ika) {
        boolean aurkitua = false;
        String sql = "Select * from ikasleak";

        try ( Connection conn = connect();  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if (ika.getZenbakia() == rs.getInt("zenbakia")) {
                    aurkitua = true;
                }
            }
            if (aurkitua) {
                System.out.println("Zenbakia ezin da errepikatu");
            } else {
                String sqez = "Insert into ikasleak VALUES(" + ika.getZenbakia()+","+"'"+ika.getIzena()+"'"+","+"'"+ika.getAbizena()+"')";
                stmt.execute(sqez);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    
    public static int aldatu(int zein,String zutabea,String balioBerria){
        
        return zein;
    }
    

}
