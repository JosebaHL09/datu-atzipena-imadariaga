/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcariketak;

import java.sql.*;
import static jdbcariketak.SQLite.connect;

/**
 *
 * @author hernandez.joseba
 */
public class MariaDB {

    public static void main(String[] args) {
        /*
        connect("test");
        createDatabase("db1");
        */
        String db = "db1",taula = "irakasleak";
        //createTable(db,taula);
        /*insert(db,taula,1,"Kakashi");
        insert(db,taula,2,"Gai");
        insert(db,taula,3,"Manuel");
        insert(db,taula,4,"Doraemon");*/
        updateAdina(db,taula);
        selectAll(db,taula);
    }

    public static Connection connect(String db) {
        String server = "localhost", user = "root", pw = "root";
        String url = "jdbc:mysql://" + server + "/" + db;

        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, pw);
            System.out.println("datubasera konektatu zara!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static void createDatabase(String db) {

        try ( Connection conn = connect("")) {
            Statement stmt = conn.createStatement();

            DatabaseMetaData meta = conn.getMetaData();
            String sql = "CREATE DATABASE " + db;
            System.out.println("Thre driver name is " + meta.getDriverName());
            stmt.execute(sql);
            System.out.println(db + " datubasea sortu da!");
        } catch (SQLException ex) {
            System.out.println("Errorea dba sortzean: " + ex.getMessage());
        }
    }

    public static void createTable(String db, String taula) {
        String sql = "CREATE TABLE IF NOT EXISTS " + taula + "(\n "
                + "id integer PRIMARY KEY,\n "
                + "izena text,\n "
                + "adina integer\n"
                + ");";
        try ( Connection conn = connect(db);  Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Taula sortu da!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void insert(String db, String taula, int id, String izena) {
        String sql = "INSERT INTO " + taula + "(id, izena) VALUES(?,?)";

        try (Connection conn = connect(db);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, izena);
            pstmt.executeUpdate();
            System.out.println("Ikasle berria sartu ");
        } catch (SQLException e) {
            switch(e.getErrorCode()){
                case 19:
                    System.out.println("Txertatu nahi duzun erregistroaren id-a dagoeneko existitzen da.");
                    break;
            }
        }
    }
    
    public static void updateAdina(String db, String taula){
        String sql = "Select izena from "+taula;
        try (Connection conn = connect(db);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            String izena;
            int adina;
            // loop through the result set
            while (rs.next()) {
                izena = rs.getString("izena");
                adina = izena.length()*5;
                String sql1 = "UPDATE " + taula + " SET adina = " + adina + " WHERE izena = '" +izena+"'";
                stmt.execute(sql1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        
    }
    
    public static void selectAll(String db, String taula) {
        String sql = "SELECT * FROM " + taula;

        try (Connection conn = connect(db);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            System.out.println("Id \tIzena\tAdina");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t"
                        + rs.getString("izena") + "\t"
                        + rs.getInt("adina"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
