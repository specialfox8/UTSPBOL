/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package kacamata;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class FXMLpelangganController implements Initializable {

    @FXML
    private TableView<pelangganmodel> tbvp;
    @FXML
    private Button tambah;
    @FXML
    private Button hapus;
    @FXML
    private Button ubah;
    @FXML
    private Button keluar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showdata();
    }    
    
    private void showdata(){
            ObservableList<pelangganmodel> data=FXMLDocumentController.dtp.Load();
        if(data!=null){            
            tbvp.getColumns().clear();
            tbvp.getItems().clear();
            TableColumn col=new TableColumn("IdKamar");
            col.setCellValueFactory(new PropertyValueFactory<pelangganmodel, String>("idjual"));
            tbvp.getColumns().addAll(col);
            col=new TableColumn("NamaPelanggan");
            col.setCellValueFactory(new PropertyValueFactory<pelangganmodel, String>("namapelanggan"));
            tbvp.getColumns().addAll(col);
            col=new TableColumn("jumlah");
            col.setCellValueFactory(new PropertyValueFactory<pelangganmodel, String>("jumlah"));
            tbvp.getColumns().addAll(col);
            col=new TableColumn("Harga");
            col.setCellValueFactory(new PropertyValueFactory<kacamodel, String>("Harga"));
            tbvp.getColumns().addAll(col);

            tbvp.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvp.getScene().getWindow().hide();

        }
    }

    @FXML
    private void tambahklik(ActionEvent event) {
         try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLbeli.fxml"));    
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.show();        
        } catch (IOException e){   e.printStackTrace();   }
    }

    @FXML
    private void hapusklik(ActionEvent event) {
         pelangganmodel s= new pelangganmodel();  
         s=tbvp.getSelectionModel().getSelectedItem();
       Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",               ButtonType.YES,ButtonType.NO);
       a.showAndWait();
       if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtp.delete(s.getIdjual())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,                  "Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,                   "Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();     
       }    
    }

    @FXML
    private void ubahklik(ActionEvent event) {
         pelangganmodel s= new pelangganmodel();
        s=tbvp.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLbeli.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLbeliController isidt=(FXMLbeliController)loader.getController();
        isidt.execute(s);                
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();  
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        keluar.getScene().getWindow().hide();
    }
    
}
