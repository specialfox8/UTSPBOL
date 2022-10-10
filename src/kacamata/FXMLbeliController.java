/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package kacamata;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class FXMLbeliController implements Initializable {

    @FXML
    private TextField txtidjual;
    @FXML
    private TextField txtpelanggan;
    @FXML
    private TextField txttotal;
    @FXML
    private Button btnhitung;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnkeluar;
    @FXML
    private ComboBox<String> txttipe;
    @FXML
    private Slider txtjumlah;
    
    @FXML
    private Label lbljumlah;

    private pelangganmodel dt= new pelangganmodel();
    private ComboBox<String> jml;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txttipe.setItems(FXCollections.observableArrayList(
        "Mines","Plus","Hitam"));
        jml.setItems(FXCollections.observableArrayList(
        "1","3","5","10"));
        
        txtjumlah.valueProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue<? extends Number> changed, 
                    Number oldVal, Number newVal){
                lbljumlah.setText(String.valueOf(newVal.intValue()));                           
            }   
        });

    }    

    
    @FXML
    private void hitungklik(ActionEvent event) {
        
        kacamodel k=new kacamodel();
         int biaya;
        switch(k.getHarga()){
            case 0 : biaya=150000;break;
            case 1 : biaya=150000;break;
            case 2 : biaya=100000;break;
            default: biaya=0;
        }        
       int hsl= biaya;
        txttotal.setText(String.valueOf(hsl));  

    }

    @FXML
    private void simpanklik(ActionEvent event) {
        kacamodel k=new kacamodel();
        pelangganmodel p= new pelangganmodel();
        p.setIdjual(txtidjual.getText());
        p.setNamapelanggan(txtpelanggan.getText());
        p.setJumlah((int)txtjumlah.getValue());

       
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

    @FXML
    private void hapusklik(ActionEvent event) {
        txtidjual.setText("");
        txtpelanggan.setText("");
 txttipe.getEditor().clear();
        txttotal.setText("");
        txtidjual.requestFocus();
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }
    boolean editdata=false;
     public void execute(pelangganmodel p){
        if(!p.getIdjual().isEmpty()){
          editdata=true;
          txtidjual.setText(p.getIdjual());
          txtpelanggan.setText(p.getNamapelanggan());
          txtidjual.setEditable(false);
          txtpelanggan.requestFocus();
        }
     }

    

}
