/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kacamata;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author steve
 */
public class dbpelanggan {
 private pelangganmodel dt=new pelangganmodel ();

    public pelangganmodel  getpelangganmodel () {
        return dt;
    }

    public void setpelangganmodel (pelangganmodel  s) {
        dt = s;
    }
    
        public ObservableList<pelangganmodel >  Load() {
        try {   ObservableList<pelangganmodel > tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
           con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select idjual, namapelanggan,jumlah,Harga from pelanggan");
            int i = 1;
            while (rs.next()) {
                pelangganmodel  d=new pelangganmodel ();
                d.setIdjual(rs.getString("idjual")); 
                d.setNamapelanggan(rs.getString("namapelanggan"));
                d.setJumlah(rs.getInt("jumlah"));
                d.setNamapelanggan(rs.getString("Harga"));
                
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
public int validasi(String nomor) {
        int val = 0;
        try {  Koneksi con = new Koneksi();     con.bukaKoneksi();   con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from pelanggan where idjual = '" + nomor + "'");
            while (rs.next()) {   val = rs.getInt("jumlah");            }
            con.tutupKoneksi();
        } catch (SQLException e) {            e.printStackTrace();        }
        return val;
    }        

     public boolean insert() {
        boolean berhasil = false;    Koneksi con = new Koneksi();
        try {         con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into pelanggan (idjual, namapelanggan) values (?,?)");
            con.preparedStatement.setString(1, getpelangganmodel ().getIdjual());
            con.preparedStatement.setString(2, getpelangganmodel ().getNamapelanggan());
           

            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            e.printStackTrace();            berhasil = false;
        } finally {            con.tutupKoneksi();            return berhasil;        }
    }
     public boolean delete(String nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from pelanggan where idjual  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
     public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
            "update pelanggan set namapelanggan = ?  where  idjual = ? ; ");
            con.preparedStatement.setString(1, getpelangganmodel ().getIdjual());
            con.preparedStatement.setString(2, getpelangganmodel ().getNamapelanggan());
            

            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
}
