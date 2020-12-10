/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbcariketak;
import java.sql.*;
/**
 *
 * @author hernandez.joseba
 */
public class SQLite {

    public static void main(String[] args) {
        String dbIzena;
        String taulaIzena;

        dbIzena = "datubaseak/proba1.db";
        taulaIzena = "taula1";
        connect(dbIzena);
        createDatabase(dbIzena);
        createTable(dbIzena, "taula1");
        insert(dbIzena, "taula1", 1, "Benito", "Camela");
        insert(dbIzena, "taula1", 2, "Naruto", "Uzumaki");
        insert(dbIzena, "taula1", 3, "Yuuji", "Itadori");
        selectAll(dbIzena, taulaIzena);
        selectById(dbIzena, taulaIzena, 1);
    }

    public static Connection connect(String db) {
        String url = "jdbc:sqlite:" + db;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createDatabase(String db) {
        Connection conn = connect(db);
        try {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Driver hau erabili dugu: " + meta.getDriverName());
                System.out.println(db + " datu-basea sortu dugu.");
            }
        } catch (SQLException ex) {
            System.out.println("Ezin da konektatu. Ezin da datu-basea sortu");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Errorea konexioa ixterakoan.");
                }
            }
        }
    }

    public static void createTable(String db, String table) {
        String sql = "CREATE TABLE IF NOT EXISTS " + table + "(\n "
                + "id integer PRIMARY KEY,\n "
                + "eremu1 text,\n "
                + "eremu2 text\n"
                + ");";
        try (Connection conn = connect(db);
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert(String db, String taula, int id, String eremu1, String eremu2) {
        String sql = "INSERT INTO " + taula + "(id, eremu1, eremu2) VALUES(?,?,?)";

        try (Connection conn = connect(db);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, eremu1);
            pstmt.setString(3, eremu2);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            switch(e.getErrorCode()){
                case 19:
                    System.out.println("Txertatu nahi duzun erregistroaren id-a dagoeneko existitzen da.");
                    break;
            }
        }
    }

    public static void selectAll(String db, String taula) {
        String sql = "SELECT * FROM " + taula;

        try (Connection conn = connect(db);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t"
                        + rs.getString("eremu1") + "\t"
                        + rs.getString("eremu2"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void selectById(String db, String taula, int id) {
        String sql = "SELECT * FROM " + taula + " WHERE id = " + id;

        try (Connection conn = connect(db);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t"
                        + rs.getString("eremu1") + "\t"
                        + rs.getString("eremu2"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}   
