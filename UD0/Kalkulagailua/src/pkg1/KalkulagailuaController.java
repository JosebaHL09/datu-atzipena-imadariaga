/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.lang.*;

/**
 *
 * @author root
 */
public class KalkulagailuaController implements Initializable {

    @FXML
    private Label emaitza;

    @FXML
    private TextField zb1, zb2;

    @FXML
    private void handleBatuAction(ActionEvent event) {
        System.out.println("Batuketa egin dugu.");
        emaitza.setText("" + (Double.parseDouble(zb1.getText()) + Double.parseDouble(zb2.getText())));
    }

    @FXML
    private void handleBiderkatuAction(ActionEvent event) {
        System.out.println("Biderketa egin dugu.");
        emaitza.setText("" + (Double.parseDouble(zb1.getText()) * Double.parseDouble(zb2.getText())));
    }

    @FXML
    private void handleKenduAction(ActionEvent event) {
        System.out.println("Kenketa egin dugu.");
        emaitza.setText("" + (Double.parseDouble(zb1.getText()) - Double.parseDouble(zb2.getText())));
    }

    @FXML
    private void handleZatiAction(ActionEvent event) {
        System.out.println("Zatiketa egin dugu.");
        emaitza.setText("" + (Double.parseDouble(zb1.getText()) / Double.parseDouble(zb2.getText())));
    }

    @FXML
    private void handleBerAction(ActionEvent event) {
        System.out.println("Berreketa egin dugu.");
        double result = 1;
        double i = Double.parseDouble(zb2.getText());
        while (i > 0) {
            result = Double.parseDouble(zb1.getText()) * result;
            i--;
        }
        emaitza.setText("" + (result));
    }

    @FXML
    private void handleErroAction(ActionEvent event) {
        System.out.println("Erroaren kalkulua egin dugu.");

        emaitza.setText("" + (Math.sqrt(Double.parseDouble(zb1.getText()))));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
