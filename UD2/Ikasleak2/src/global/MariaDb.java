/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package global;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ikaslea;

/**
 *
 * @author lopez.pablo
 */
public class MariaDb {

    /*public static void main(String[] args) {
        konektatu();
        ezabatu(new Ikaslea(1, "ita", "chi"));
        gehitu(new Ikaslea(1, "Yuuji", "Itadori"));
        gehitu(new Ikaslea(1, "YSDDA", "J"));
        aldatu(1, "abizena1", "Itadori");
        aldatu(2, "abizena1", "Fushiguro");
        aldatu(3, "abizena1", "Kugisaki");

    }*/
    public static Connection konektatu() {
        String url = "jdbc:mysql://" + Global.ZERBITZARIA + "/" + Global.DATUBASEA;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, Global.getErabiltzailea(), Global.getPasahitza());
        } catch (SQLException e) {
            System.out.println("Error Code:" + e.getErrorCode() + "-" + e.getMessage());
        }
        return conn;
    }

    public static ObservableList datuakMemorianKargatu() {
        ObservableList<Ikaslea> ikasleak = FXCollections.observableArrayList();

        String sql = "SELECT * FROM ikaslea";

        try (Connection conn = konektatu();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Ikaslea ik1 = new Ikaslea(rs.getInt("zenbakia"), rs.getString("izena"), rs.getString("abizena1"));
                ikasleak.add(ik1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ikasleak;
    }

    public static boolean ezabatu(Ikaslea ik1) {
        String sql = "DELETE FROM ikaslea WHERE zenbakia = ?";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ik1.getZenbakia());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Code:" + e.getErrorCode() + "-" + e.getMessage());
        }
        return true;
    }

    public static boolean gehitu(Ikaslea ik1) {
        String sql = "INSERT INTO ikaslea VALUES(?,?,?)";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ik1.getZenbakia());
            pstmt.setString(2, ik1.getIzena());
            pstmt.setString(3, ik1.getAbizena1());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 19:
                    System.out.println("Txertatu nahi duzun erregistroaren id-a dagoeneko existitzen da.");
                    break;
            }
            return false;
        }
        return true;
    }

    public static int aldatu(int zein, String zutabea, String balioBerria) {
        String sql = "UPDATE ikaslea SET " + zutabea + " = ? WHERE zenbakia = ?";
        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, balioBerria);
            pstmt.setInt(2, zein);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Code:" + e.getErrorCode() + "-" + e.getMessage());
        }
        return zein;
    }
}
