/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kacamata;

import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author steve
 */
public class dbkaca {
     private kacamodel dt=new kacamodel();    
    public kacamodel getkacamodel(){ return(dt);}
    public void setkacamodel(kacamodel s){ dt=s;}
    
    public ObservableList<kacamodel>  Load() {
        try {
            ObservableList<kacamodel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select idkaca,tipekaca ,harga  from kaca");
            int i = 1;
            while (rs.next()) {
                kacamodel d=new kacamodel();
                d.setIdkaca(rs.getString("idkaca"));
                d.setTipekaca(rs.getString("tipekaca"));
                d.setHarga(rs.getInt("harga"));
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
}
}
}
