/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package kacamata;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class FXML_belikacaController implements Initializable {

    @FXML
    private Button hitung;
    @FXML
    private Button hapus;
    @FXML
    private Button keluar;
    @FXML
    private TextField txtidjual;
    @FXML
    private ComboBox<String> tipekaca;
    @FXML
    private Slider jumlah;
    @FXML
    private TextField pelanggan;
    @FXML
    private TextField txttotal;
    
    private pelangganmodel dt= new pelangganmodel();
    @FXML
    private Button simpan;
    @FXML
    private Label lbljumlah;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tipekaca.setItems(FXCollections.observableArrayList(
        "Mines","Plus","Hitam"));
        jumlah.valueProperty().addListener(new ChangeListener<Number>(){
        public void changed(ObservableValue<? extends Number> changed, 
        Number oldVal, Number newVal){
        lbljumlah.setText(String.valueOf(newVal.intValue()));                           
            }   
        });       
       
    }    

    @FXML
    private void hitungklik(ActionEvent event) {

        
        dt.setJumlah(Integer.parseInt(lbljumlah.getText()));        
                
        kacamodel k=new kacamodel();
         int biaya;
        switch(k.getHarga()){
            case 0 : biaya=150000;break;
            case 1 : biaya=150000;break;
            case 2 : biaya=100000;break;
            default: biaya=0;
        }        
       int hsl= biaya*(dt.getJumlah());
        txttotal.setText(String.valueOf(hsl));  
    }

    @FXML
    private void hapusklik(ActionEvent event) {
       txtidjual.setText("");
        pelanggan.setText("");
        tipekaca.getEditor().clear();
//        jumlah.getEditor().clear();
        txttotal.setText("");
        txtidjual.requestFocus();
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        keluar.getScene().getWindow().hide();
    }

    boolean editdata=false;
    
    @FXML
    private void simpanklik(ActionEvent event) {
        kacamodel k=new kacamodel();
        pelangganmodel p= new pelangganmodel();
        p.setIdjual(txtidjual.getText());
        p.setNamapelanggan(pelanggan.getText());
        p.setJumlah((int)jumlah.getValue());
                
       
        FXMLDocumentController.dtp.setpelangganmodel(p);
        if(editdata){
            if(FXMLDocumentController.dtp.update()){
                              Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   txtidjual.setEditable(true);       hapusklik(event);                
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
               a.showAndWait();                    
            }
        }else if(FXMLDocumentController.dtp.validasi(p.getIdjual())<=0){
            if(FXMLDocumentController.dtp.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            hapusklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtidjual.requestFocus();

            }
    }
    
    public void execute(pelangganmodel p){
        if(!p.getIdjual().isEmpty()){
          editdata=true;
          txtidjual.setText(p.getIdjual());
          pelanggan.setText(p.getNamapelanggan());
          txtidjual.setEditable(false);
          pelanggan.requestFocus();
        }
     }
    
}
