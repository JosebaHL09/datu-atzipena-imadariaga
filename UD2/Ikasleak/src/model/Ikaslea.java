/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hernandez.joseba
 */
public class Ikaslea {
    private int zenbakia;
    private String izena;
    private String abizena;

    public Ikaslea(int zenbakia, String izena, String abizena) {
        this.zenbakia = zenbakia;
        this.izena = izena;
        this.abizena = abizena;
    }

    public int getZenbakia() {
        return zenbakia;
    }

    public void setZenbakia(int zenbakia) {
        this.zenbakia = zenbakia;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getAbizena() {
        return abizena;
    }

    public void setAbizena(String abizena) {
        this.abizena = abizena;
    }
    
    
}
